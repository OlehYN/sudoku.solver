package com.z1kses.solver.strategy.impl.hidden;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.strategy.SudokuStrategy;

public class HiddenOneRowSudokuStrategy implements SudokuStrategy {

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {
		List<Integer> values = new ArrayList<>();

		for (int i = 0; i < MAX_SIZE; i++)
			if (field[x][i].getPossibleValues() != null && !(y == i))
				values.addAll(field[x][i].getPossibleValues());

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
