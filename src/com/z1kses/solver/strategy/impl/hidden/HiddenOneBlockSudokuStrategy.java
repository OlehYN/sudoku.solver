package com.z1kses.solver.strategy.impl.hidden;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.strategy.SudokuStrategy;

public class HiddenOneBlockSudokuStrategy implements SudokuStrategy {

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {
		List<Integer> values = new ArrayList<>();
		int row = x / 3;
		int column = y / 3;

		for (int i = row * 3; i < row * 3 + 3; i++)
			for (int j = column * 3; j < column * 3 + 3; j++)
				if (field[i][j].getPossibleValues() != null && !(x == i && y == j))
					values.addAll(field[i][j].getPossibleValues());

		List<Integer> currentValues = new ArrayList<>();
		currentValues.addAll(cell.getPossibleValues());

		currentValues.removeAll(values);

		if (currentValues.size() == 1) {
			cell.setValue(currentValues.get(0));
			cell.setPossibleValues(null);
			
			return true;
		}
		return false;
	}

	@Override
	public boolean isBase() {
		return false;
	}

}
