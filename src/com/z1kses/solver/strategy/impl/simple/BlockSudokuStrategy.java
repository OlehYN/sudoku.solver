package com.z1kses.solver.strategy.impl.simple;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.strategy.SudokuStrategy;

public class BlockSudokuStrategy implements SudokuStrategy {

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {
		int initSize = cell.getPossibleValues().size();

		int row = x / 3;
		int column = y / 3;

		for (int i = row * 3; i < row * 3 + 3; i++) {
			for (int j = column * 3; j < column * 3 + 3; j++) {
				Integer value = field[i][j].getValue();

				if (value != null)
					cell.getPossibleValues().remove(value);
			}
		}

		int endSize = cell.getPossibleValues().size();

		if (endSize == 1) {
			cell.setValue(cell.getPossibleValues().get(0));

			for (int i = row * 3; i < row * 3 + 3; i++)
				for (int j = column * 3; j < column * 3 + 3; j++)
					if (field[i][j].getValue() == null)
						field[i][j].getPossibleValues().remove((Integer) cell.getPossibleValues().get(0));

			cell.setPossibleValues(null);
		}

		return endSize != initSize;
	}

	@Override
	public boolean isBase() {
		return true;
	}


}
