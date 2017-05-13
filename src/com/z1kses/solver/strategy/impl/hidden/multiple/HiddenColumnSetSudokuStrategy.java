package com.z1kses.solver.strategy.impl.hidden.multiple;

import java.util.ArrayList;
import java.util.List;
import com.z1kses.solver.entity.impl.Cell;

public class HiddenColumnSetSudokuStrategy extends GenericHiddenSetSudokuStrategy {

	@Override
	protected List<Cell> getUnresolvedCells(Cell[][] field, int x, int y) {
		List<Cell> currentUnresolvedCells = new ArrayList<>();

		for (int i = 0; i < MAX_SIZE; i++)
			if (field[i][y].getValue() == null && i != x)
				currentUnresolvedCells.add(field[i][y]);

		return currentUnresolvedCells;
	}

}
