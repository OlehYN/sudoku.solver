package com.z1kses.solver;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.iterator.SudokuSolverIterator;
import com.z1kses.solver.iterator.impl.SudokuSolverIteratorImpl;
import com.z1kses.solver.strategy.SudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.HiddenOneBlockSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.HiddenOneColumnSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.HiddenOneRowSudokuStrategy;
import com.z1kses.solver.strategy.impl.locked.LockedColumnSudokuStrategy;
import com.z1kses.solver.strategy.impl.locked.LockedRowSudokuStrategy;
import com.z1kses.solver.strategy.impl.simple.BlockSudokuStrategy;
import com.z1kses.solver.strategy.impl.simple.ColumnSudokuStrategy;
import com.z1kses.solver.strategy.impl.simple.RowSudokuStrategy;
import com.z1kses.solver.util.Solver;
import com.z1kses.solver.util.impl.SudokuSolver;

public class App {

	private static final int MAX_SIZE = 9;

	// Easy level
	private static Integer[][] level1 = { { null, 5, 3, null, null, 7, 2, null, null },
			{ 4, null, null, null, 1, 6, null, null, 8 }, { 6, 8, null, null, null, null, null, null, null },
			{ 7, 9, null, 6, 2, null, null, null, 3 }, { null, 6, null, null, 5, null, null, 4, null },
			{ 2, null, null, null, 3, 8, null, 5, 9 }, { null, null, null, null, null, null, null, 6, 1 },
			{ 1, null, null, 3, 7, null, null, null, 4 }, { null, null, 9, 4, null, null, 5, 2, null } };

	// Medium level
	private static Integer[][] level2 = { { 5, 6, null, 2, null, null, 4, null, null },
			{ null, 8, 7, 1, null, 5, 9, null, null }, { null, null, 1, null, 4, null, null, 7, null },
			{ null, 3, null, 5, 1, null, null, null, 7 }, { null, null, 5, null, null, null, 3, null, null },
			{ 2, null, null, null, 3, 6, null, 5, null }, { null, 4, null, null, 5, null, 7, null, null },
			{ null, null, 6, 4, null, 1, 5, 8, null }, { null, null, 9, null, null, 3, null, 2, 4 } };

	// Hard level
	private static Integer[][] level3 = { { 4, null, null, null, null, null, 1, 5, 3 },
			{ null, null, 7, null, null, null, null, null, 2 }, { null, 8, 3, 1, 2, null, null, null, null },
			{ null, null, 6, null, null, 9, null, null, null }, { null, 7, null, null, 8, null, null, 2, null },
			{ null, null, null, 2, null, null, 7, null, null }, { null, null, null, null, 3, 6, 9, 1, null },
			{ 5, null, null, null, null, null, 2, null, null }, { 6, 3, 1, null, null, null, null, null, 5 } };

	// Challenger level
	private static Integer[][] level4 = { { 2, null, 9, 7, null, null, 6, null, null },
			{ null, 1, null, 3, null, null, 4, null, 7 }, { 7, null, null, 5, null, null, null, null, null },
			{ null, null, null, 9, null, null, null, null, 2 }, { null, 2, null, null, 1, null, null, 9, null },
			{ 6, null, null, null, null, 5, null, null, null }, { null, null, null, null, null, 7, null, null, 5 },
			{ 9, null, 6, null, null, 3, null, 8, null }, { null, null, 5, null, null, 2, 7, null, 6 } };

	public static void main(String[] args) {
		SudokuStrategy rowStrategy = new RowSudokuStrategy();
		SudokuStrategy columnStrategy = new ColumnSudokuStrategy();
		SudokuStrategy blockStrategy = new BlockSudokuStrategy();

		SudokuStrategy hiddenRowStrategy = new HiddenOneRowSudokuStrategy();
		SudokuStrategy hiddenColumnStrategy = new HiddenOneColumnSudokuStrategy();
		SudokuStrategy hiddenBlockStrategy = new HiddenOneBlockSudokuStrategy();

		SudokuStrategy lockedRowStrategy = new LockedRowSudokuStrategy();
		SudokuStrategy lockedColumnStrategy = new LockedColumnSudokuStrategy();

		List<SudokuStrategy> strategies = new ArrayList<>();

		strategies.add(lockedRowStrategy);
		strategies.add(lockedColumnStrategy);

		strategies.add(rowStrategy);
		strategies.add(blockStrategy);
		strategies.add(columnStrategy);

		strategies.add(hiddenRowStrategy);
		strategies.add(hiddenColumnStrategy);
		strategies.add(hiddenBlockStrategy);

		SudokuSolverIterator sudokuSolverIterator = new SudokuSolverIteratorImpl(strategies);

		Solver<Cell[][]> solver = new SudokuSolver(level4, sudokuSolverIterator);
		solver.solve();

		Cell[][] result = solver.getField();

		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++)
				System.out.print(result[i][j].getValue() + " ");
			System.out.println();
		}
	}

}
