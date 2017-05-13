package com.z1kses.solver.strategy.impl.set;

import java.util.ArrayList;
import java.util.List;
import com.z1kses.solver.entity.impl.Cell;

public class RowSetSudokuStrategy extends GenericSetSudokuStrategy {

	@Override
	protected List<Cell> createUnresolvedCells(Cell[][] field, int x, int y) {
		List<Cell> currentUnresolvedCells = new ArrayList<>();

		for (int i = 0; i < MAX_SIZE; i++) {
			if (field[x][i].getValue() == null && i != y)
				currentUnresolvedCells.add(field[x][i]);
		}
		return currentUnresolvedCells;
	}
}
