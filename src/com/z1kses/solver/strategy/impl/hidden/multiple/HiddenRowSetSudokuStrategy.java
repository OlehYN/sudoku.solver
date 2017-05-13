package com.z1kses.solver.strategy.impl.hidden.multiple;

import java.util.ArrayList;
import java.util.List;
import com.z1kses.solver.entity.impl.Cell;

public class HiddenRowSetSudokuStrategy extends GenericHiddenSetSudokuStrategy {

	@Override
	protected List<Cell> getUnresolvedCells(Cell[][] field, int x, int y) {
		List<Cell> currentUnresolvedCells = new ArrayList<>();

		for (int i = 0; i < MAX_SIZE; i++)
			if (field[x][i].getValue() == null && i != y)
				currentUnresolvedCells.add(field[x][i]);

		return currentUnresolvedCells;
	}

}
