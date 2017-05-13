package com.z1kses.solver.strategy;

import com.z1kses.solver.entity.impl.Cell;

public interface SudokuStrategy {
	int MAX_SIZE = 9;

	boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y);
	
	boolean isBase();
}
