package com.z1kses.solver.strategy.impl.locked;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.strategy.SudokuStrategy;

public class LockedRowSudokuStrategy implements SudokuStrategy {

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {

		List<Integer> values = new ArrayList<>();
		List<Integer> currentValues = new ArrayList<>();
		int row = x / 3;
		int column = y / 3;

		for (int i = row * 3; i < row * 3 + 3; i++)
			for (int j = column * 3; j < column * 3 + 3; j++)
				if (field[i][j].getValue() == null)
					if (x != i)
						values.addAll(field[i][j].getPossibleValues());
					else
						currentValues.addAll(field[i][j].getPossibleValues());

		currentValues.removeAll(values);

		boolean hasProgress = false;

		if (currentValues.size() > 0) {
			for (int i = 0; i < MAX_SIZE; i++) {
				int index = i / 3;

				if (column != index)
					if (field[x][i].getPossibleValues() != null) {
						int oldSize = field[x][i].getPossibleValues().size();
						field[x][i].getPossibleValues().removeAll(currentValues);
						int newSize = field[x][i].getPossibleValues().size();

						if (field[x][i].getPossibleValues().size() == 1) {
							field[x][i].setValue(field[x][i].getPossibleValues().get(0));
							field[x][i].setPossibleValues(null);
						}

						hasProgress = newSize != oldSize || hasProgress;
					}
			}
		}

		return hasProgress;
	}

	@Override
	public boolean isBase() {
		return false;
	}

}
