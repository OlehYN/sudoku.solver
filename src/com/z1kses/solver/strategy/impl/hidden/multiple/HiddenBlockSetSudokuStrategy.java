package com.z1kses.solver.strategy.impl.hidden.multiple;

import java.util.ArrayList;
import java.util.List;
import com.z1kses.solver.entity.impl.Cell;

public class HiddenBlockSetSudokuStrategy extends GenericHiddenSetSudokuStrategy {

	@Override
	protected List<Cell> getUnresolvedCells(Cell[][] field, int x, int y) {
		List<Cell> currentUnresolvedCells = new ArrayList<>();

		int row = x / 3;
		int column = y / 3;

		for (int i = row * 3; i < row * 3 + 3; i++)
			for (int j = column * 3; j < column * 3 + 3; j++)
				if (field[i][j].getValue() == null && !(x == i && y == j))
					currentUnresolvedCells.add(field[i][j]);

		return currentUnresolvedCells;
	}

}
