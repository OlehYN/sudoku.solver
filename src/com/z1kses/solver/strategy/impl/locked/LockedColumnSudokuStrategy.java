package com.z1kses.solver.strategy.impl.locked;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.strategy.SudokuStrategy;

public class LockedColumnSudokuStrategy implements SudokuStrategy {

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {

		List<Integer> values = new ArrayList<>();
		List<Integer> currentValues = new ArrayList<>();
		int row = x / 3;
		int column = y / 3;

		for (int i = row * 3; i < row * 3 + 3; i++)
			for (int j = column * 3; j < column * 3 + 3; j++)
				if (field[i][j].getValue() == null)
					if (y != j)
						values.addAll(field[i][j].getPossibleValues());
					else
						currentValues.addAll(field[i][j].getPossibleValues());

		currentValues.removeAll(values);

		boolean hasProgress = false;

		if (currentValues.size() > 0) {
			for (int i = 0; i < MAX_SIZE; i++) {
				int index = i / 3;

				if (row != index)
					if (field[i][y].getValue() == null) {

						int oldSize = field[i][y].getPossibleValues().size();
						field[i][y].getPossibleValues().removeAll(currentValues);
						int newSize = field[i][y].getPossibleValues().size();

						if (field[i][y].getPossibleValues().size() == 1) {
							field[i][y].setValue(field[i][y].getPossibleValues().get(0));
							field[i][y].setPossibleValues(null);
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
