package com.z1kses.solver.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

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

public class UnitTest {
	// Easy level
	// DONE
	private static Integer[][] level1 = { { null, 5, 3, null, null, 7, 2, null, null },
			{ 4, null, null, null, 1, 6, null, null, 8 }, { 6, 8, null, null, null, null, null, null, null },
			{ 7, 9, null, 6, 2, null, null, null, 3 }, { null, 6, null, null, 5, null, null, 4, null },
			{ 2, null, null, null, 3, 8, null, 5, 9 }, { null, null, null, null, null, null, null, 6, 1 },
			{ 1, null, null, 3, 7, null, null, null, 4 }, { null, null, 9, 4, null, null, 5, 2, null } };

	// Medium level
	// DONE
	private static Integer[][] level2 = { { 5, 6, null, 2, null, null, 4, null, null },
			{ null, 8, 7, 1, null, 5, 9, null, null }, { null, null, 1, null, 4, null, null, 7, null },
			{ null, 3, null, 5, 1, null, null, null, 7 }, { null, null, 5, null, null, null, 3, null, null },
			{ 2, null, null, null, 3, 6, null, 5, null }, { null, 4, null, null, 5, null, 7, null, null },
			{ null, null, 6, 4, null, 1, 5, 8, null }, { null, null, 9, null, null, 3, null, 2, 4 } };

	// Hard level
	// DONE
	private static Integer[][] level3 = { { 4, null, null, null, null, null, 1, 5, 3 },
			{ null, null, 7, null, null, null, null, null, 2 }, { null, 8, 3, 1, 2, null, null, null, null },
			{ null, null, 6, null, null, 9, null, null, null }, { null, 7, null, null, 8, null, null, 2, null },
			{ null, null, null, 2, null, null, 7, null, null }, { null, null, null, null, 3, 6, 9, 1, null },
			{ 5, null, null, null, null, null, 2, null, null }, { 6, 3, 1, null, null, null, null, null, 5 } };

	// Challenger level - 153
	// DONE
	private static Integer[][] level4 = { { 2, null, 9, 7, null, null, 6, null, null },
			{ null, 1, null, 3, null, null, 4, null, 7 }, { 7, null, null, 5, null, null, null, null, null },
			{ null, null, null, 9, null, null, null, null, 2 }, { null, 2, null, null, 1, null, null, 9, null },
			{ 6, null, null, null, null, 5, null, null, null }, { null, null, null, null, null, 7, null, null, 5 },
			{ 9, null, 6, null, null, 3, null, 8, null }, { null, null, 5, null, null, 2, 7, null, 6 } };

	// Challenger level - 151
	// DONE
	private static Integer[][] level5 = { { null, 9, null, null, null, 6, null, null, 3 },
			{ 6, null, 7, null, null, null, 8, null, null }, { null, 4, null, null, 5, 7, null, null, 6 },
			{ null, 8, null, 4, null, null, 5, null, null }, { null, 1, null, null, 2, null, null, 4, null },
			{ null, null, 3, null, null, 1, null, 6, null }, { 2, null, null, 6, 7, null, null, 8, null },
			{ null, null, 8, null, null, null, 6, null, 5 }, { 1, null, null, 3, null, null, null, 7, null } };

	// Challenger level - 152
	// DONE
	private static Integer[][] level6 = { { null, 7, null, null, 1, null, 8, null, 9 },
			{ 8, null, null, 5, null, null, null, 2, null }, { 1, null, 6, null, null, null, null, 5, null },
			{ null, 1, 5, null, null, 7, null, null, null }, { null, null, 8, 1, null, 4, 3, null, null },
			{ null, null, null, 8, null, null, 6, 1, null }, { null, 8, null, null, null, null, 5, null, 6 },
			{ null, 9, null, null, null, 8, null, null, 7 }, { 6, null, 2, null, 4, null, null, 8, null } };

	// Challenger level - 154
	// DONE
	private static Integer[][] level7 = { { null, null, null, null, 1, null, null, null, 3 },
			{ null, 5, 3, null, null, null, 6, null, null }, { 8, 9, null, 3, null, null, null, null, 1 },
			{ 7, null, null, 9, null, 1, null, 8, null }, { null, null, null, null, null, null, null, null, null },
			{ null, 6, null, 7, null, 4, null, null, 2 }, { 2, null, null, null, null, 3, null, 9, 5 },
			{ null, null, 8, null, null, null, 4, 2, null }, { 4, null, null, null, 9, null, null, null, null } };

	// Challenger level - 155
	// DONE
	private static Integer[][] level8 = { { null, null, null, null, null, 2, 8, 1, null },
			{ null, 1, null, null, null, null, null, 7, null }, { 8, null, 3, null, 1, null, null, null, null },
			{ 2, null, null, 5, null, null, 9, null, 4 }, { null, null, 8, 6, null, 1, 2, null, null },
			{ 4, null, 5, null, null, 3, null, null, 1 }, { null, null, null, null, 5, null, 1, null, 3 },
			{ null, 8, null, null, null, null, null, 2, null }, { null, 5, 7, 2, null, null, null, null, null } };

	// Challenger level - 156
	// DONE
	private static Integer[][] level9 = { { 3, null, null, null, 7, null, 1, null, null },
			{ null, 1, 2, null, null, null, null, 3, null }, { null, null, null, 3, 2, null, 5, null, 4 },
			{ 8, null, null, 5, null, 2, null, null, 1 }, { null, null, 7, null, null, null, 6, null, null },
			{ 1, null, null, 7, null, 4, null, null, 9 }, { 9, null, 6, null, 4, 8, null, null, null },
			{ null, 2, null, null, null, null, 9, 1, null }, { null, null, 1, null, 9, null, null, null, 6 } };

	// Challenger level - 157
	// DONE
	private static Integer[][] level10 = { { null, 4, null, null, null, 6, null, null, 3 },
			{ 5, null, null, null, 8, 4, null, null, 2 }, { null, null, 2, null, null, null, null, 4, null },
			{ null, null, null, null, 2, 5, null, null, 7 }, { null, 1, null, null, null, null, null, 5, null },
			{ 9, null, null, 6, 3, null, null, null, null }, { null, 3, null, null, null, null, 4, null, null },
			{ 8, null, null, 7, 6, null, null, null, 5 }, { 1, null, null, 8, null, null, null, 3, null } };

	// Challenger level - 158
	// DONE
	private static Integer[][] level11 = { { 2, null, null, 3, null, null, 1, 5, null },
			{ null, 5, null, 4, null, null, null, null, null }, { 6, null, null, null, null, null, null, 8, null },
			{ 7, null, null, 2, null, 8, null, null, null }, { null, null, 4, null, null, null, 6, null, null },
			{ null, null, null, 7, null, 1, null, null, 2 }, { null, 9, null, null, null, null, null, null, 6 },
			{ null, null, null, null, null, 9, null, 1, null }, { null, 8, 3, null, null, 7, null, null, 5 } };

	// Challenger level - 159
	// DONE
	private static Integer[][] level12 = { { null, 2, null, null, null, null, null, null, 9 },
			{ null, null, null, null, null, 3, 4, 2, null }, { null, 5, null, null, 6, 9, 7, null, null },
			{ null, 9, 8, null, null, 5, null, null, 4 }, { null, null, 5, null, 2, null, 3, null, null },
			{ 2, null, null, 9, null, null, 1, 5, null }, { null, null, 9, 8, 3, null, null, 1, null },
			{ null, 8, 2, 7, null, null, null, null, null }, { 3, null, null, null, null, null, null, 4, null } };

	// Challenger level - 160
	// DONE
	// America's Best Sudoku (volume 81) has fallen
	private static Integer[][] level13 = { { 5, null, null, null, 4, null, 6, null, null },
			{ null, 9, null, 7, null, null, 5, null, null }, { null, null, 6, 2, null, null, null, 3, 1 },
			{ null, 3, null, 8, null, 4, 1, null, null }, { null, null, null, null, null, null, null, null, null },
			{ null, null, 1, 9, null, 3, null, 4, null }, { 4, 7, null, null, null, 2, 8, null, null },
			{ null, null, 2, null, null, 8, null, 5, null }, { null, null, 5, null, 6, null, null, null, 3 } };

	// Final boss
	// FAIL
	private static Integer[][] boss = { { null, null, 5, 3, null, null, null, null, null },
			{ 8, null, null, null, null, null, null, 2, null }, { null, 7, null, null, 1, null, 5, null, null },
			{ 4, null, null, null, null, 5, 3, null, null }, { null, 1, null, null, 7, null, null, null, 6 },
			{ null, null, 3, 2, null, null, null, 8, null }, { null, 6, null, 5, null, null, null, null, 9 },
			{ null, null, 4, null, null, null, null, 3, null }, { null, null, null, null, null, 9, 7, null, null } };

	private static SudokuSolverIterator sudokuSolverIterator;

	@BeforeClass
	public static void setUp() {
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

		strategies.add(hiddenBlockSetStrategy);
		strategies.add(hiddenRowSetStrategy);
		strategies.add(hiddenColumnSetStrategy);

		strategies.add(rowSetStrategy);
		strategies.add(columnSetStrategy);
		strategies.add(blockSetStrategy);

		strategies.add(lockedRowStrategy);
		strategies.add(lockedColumnStrategy);

		strategies.add(hiddenRowStrategy);
		strategies.add(hiddenColumnStrategy);
		strategies.add(hiddenBlockStrategy);

		strategies.add(rowStrategy);
		strategies.add(blockStrategy);
		strategies.add(columnStrategy);

		sudokuSolverIterator = new SudokuSolverIteratorImpl(strategies);
	}

	@Test
	public void testLevel1() {
		Solver<Cell[][]> solver = new SudokuSolver(level1, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel2() {
		Solver<Cell[][]> solver = new SudokuSolver(level2, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel3() {
		Solver<Cell[][]> solver = new SudokuSolver(level3, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel4() {
		Solver<Cell[][]> solver = new SudokuSolver(level4, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel5() {
		Solver<Cell[][]> solver = new SudokuSolver(level5, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel6() {
		Solver<Cell[][]> solver = new SudokuSolver(level6, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel7() {
		Solver<Cell[][]> solver = new SudokuSolver(level7, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel8() {
		Solver<Cell[][]> solver = new SudokuSolver(level8, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel9() {
		Solver<Cell[][]> solver = new SudokuSolver(level9, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel10() {
		Solver<Cell[][]> solver = new SudokuSolver(level10, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel11() {
		Solver<Cell[][]> solver = new SudokuSolver(level11, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel12() {
		Solver<Cell[][]> solver = new SudokuSolver(level12, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testLevel13() {
		Solver<Cell[][]> solver = new SudokuSolver(level13, sudokuSolverIterator);
		solver.solve();
	}

	@Test
	public void testBoss() {
		Solver<Cell[][]> solver = new SudokuSolver(boss, sudokuSolverIterator);
		solver.solve();
	}
}
