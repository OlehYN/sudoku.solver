package com.z1kses.solver;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.iterator.SudokuSolverIterator;
import com.z1kses.solver.iterator.impl.SudokuSolverIteratorImpl;
import com.z1kses.solver.strategy.SudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.multiple.HiddenBlockSetSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.multiple.HiddenColumnSetSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.multiple.HiddenRowSetSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.one.HiddenOneBlockSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.one.HiddenOneColumnSudokuStrategy;
import com.z1kses.solver.strategy.impl.hidden.one.HiddenOneRowSudokuStrategy;
import com.z1kses.solver.strategy.impl.locked.LockedColumnSudokuStrategy;
import com.z1kses.solver.strategy.impl.locked.LockedRowSudokuStrategy;
import com.z1kses.solver.strategy.impl.set.BlockSetSudokuStrategy;
import com.z1kses.solver.strategy.impl.set.ColumnSetSudokuStrategy;
import com.z1kses.solver.strategy.impl.set.RowSetSudokuStrategy;
import com.z1kses.solver.strategy.impl.simple.BlockSudokuStrategy;
import com.z1kses.solver.strategy.impl.simple.ColumnSudokuStrategy;
import com.z1kses.solver.strategy.impl.simple.RowSudokuStrategy;
import com.z1kses.solver.util.Solver;
import com.z1kses.solver.util.impl.SudokuSolver;

public class App {

	private static final int MAX_SIZE = 9;

	// Final boss
	// FAIL
	private static Integer[][] boss = { { null, null, 5, 3, null, null, null, null, null },
			{ 8, null, null, null, null, null, null, 2, null }, { null, 7, null, null, 1, null, 5, null, null },
			{ 4, null, null, null, null, 5, 3, null, null }, { null, 1, null, null, 7, null, null, null, 6 },
			{ null, null, 3, 2, null, null, null, 8, null }, { null, 6, null, 5, null, null, null, null, 9 },
			{ null, null, 4, null, null, null, null, 3, null }, { null, null, null, null, null, 9, 7, null, null } };

	public static void main(String[] args) {
		SudokuStrategy rowStrategy = new RowSudokuStrategy();
		SudokuStrategy columnStrategy = new ColumnSudokuStrategy();
		SudokuStrategy blockStrategy = new BlockSudokuStrategy();

		SudokuStrategy hiddenRowStrategy = new HiddenOneRowSudokuStrategy();
		SudokuStrategy hiddenColumnStrategy = new HiddenOneColumnSudokuStrategy();
		SudokuStrategy hiddenBlockStrategy = new HiddenOneBlockSudokuStrategy();

		SudokuStrategy lockedRowStrategy = new LockedRowSudokuStrategy();
		SudokuStrategy lockedColumnStrategy = new LockedColumnSudokuStrategy();

		SudokuStrategy rowSetStrategy = new RowSetSudokuStrategy();
		SudokuStrategy columnSetStrategy = new ColumnSetSudokuStrategy();
		SudokuStrategy blockSetStrategy = new BlockSetSudokuStrategy();

		SudokuStrategy hiddenRowSetStrategy = new HiddenRowSetSudokuStrategy();
		SudokuStrategy hiddenColumnSetStrategy = new HiddenColumnSetSudokuStrategy();
		SudokuStrategy hiddenBlockSetStrategy = new HiddenBlockSetSudokuStrategy();

		List<SudokuStrategy> strategies = new ArrayList<>();

		strategies.add(rowSetStrategy);
		strategies.add(columnSetStrategy);
		strategies.add(blockSetStrategy);

		strategies.add(hiddenBlockSetStrategy);
		strategies.add(hiddenRowSetStrategy);
		strategies.add(hiddenColumnSetStrategy);

		strategies.add(lockedRowStrategy);
		strategies.add(lockedColumnStrategy);

		strategies.add(hiddenRowStrategy);
		strategies.add(hiddenColumnStrategy);
		strategies.add(hiddenBlockStrategy);

		strategies.add(rowStrategy);
		strategies.add(blockStrategy);
		strategies.add(columnStrategy);

		SudokuSolverIterator sudokuSolverIterator = new SudokuSolverIteratorImpl(strategies);

		Solver<Cell[][]> solver = new SudokuSolver(boss, sudokuSolverIterator);

		try {
			solver.solve();
		} catch (Exception e) {
			Cell[][] result = solver.getField();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					System.out.print(result[i][j].getValue() + " ");
				System.out.println();
			}

			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					System.out.println(result[i][j]);

			return;
		}
		Cell[][] result = solver.getField();

		for (int i = 0; i < MAX_SIZE; i++) {
			for (int j = 0; j < MAX_SIZE; j++)
				System.out.print(result[i][j].getValue() + " ");
			System.out.println();
		}
	}

}
