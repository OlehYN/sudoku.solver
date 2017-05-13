package com.z1kses.solver.strategy.impl.simple;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.strategy.SudokuStrategy;

public class RowSudokuStrategy implements SudokuStrategy {

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {
		int initSize = cell.getPossibleValues().size();

		for (int i = 0; i < MAX_SIZE; i++) {
			Integer value = field[x][i].getValue();

			if (value != null)
				cell.getPossibleValues().remove(value);
		}

		int endSize = cell.getPossibleValues().size();

		if (endSize == 1) {
			cell.setValue(cell.getPossibleValues().get(0));

			for (int i = 0; i < MAX_SIZE; i++) {
				if (field[x][i].getValue() == null) {
					field[x][i].getPossibleValues().remove((Integer) cell.getPossibleValues().get(0));
				}
			}

			cell.setPossibleValues(null);
		}

		return endSize != initSize;
	}

	@Override
	public boolean isBase() {
		return true;
	}

}
