package com.z1kses.solver.util.impl;

import com.z1kses.solver.entity.Element;
import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.exception.AlreadySolvedException;
import com.z1kses.solver.exception.CannotSolveException;
import com.z1kses.solver.iterator.SudokuSolverIterator;
import com.z1kses.solver.util.Solver;

public class SudokuSolver implements Solver<Cell[][]> {

	private Cell[][] field;
	private static final int FIELD_SIZE = 9;

	private SudokuSolverIterator sudokuSolverIterator;

	public SudokuSolver(Integer[][] input, SudokuSolverIterator sudokuSolverIterator) {
		this.sudokuSolverIterator = sudokuSolverIterator;

		this.field = new Cell[FIELD_SIZE][FIELD_SIZE];

		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++)
				field[i][j] = createCell(input[i][j]);
	}

	private Cell createCell(Integer value) {
		if (value == null)
			return new Cell();
		else
			return new Cell(value);
	}

	@Override
	public boolean isSolved() {
		for (Element<Integer>[] subArray : field)
			for (Element<Integer> element : subArray)
				if (element.getValue() == null)
					return false;
		return true;
	}

	@Override
	public void solve() {
		if (isSolved())
			throw new AlreadySolvedException();

		while (!isSolved()) {
			boolean hasProgress = sudokuSolverIterator.calculateNextStep(field);

			if (!hasProgress) {
				for (int i = 0; i < FIELD_SIZE; i++) {
					for (int j = 0; j < FIELD_SIZE; j++)
						System.out.print(field[i][j].getValue() + " ");
					System.out.println();
				}
				
				System.out.println(field[6][3]);
				throw new CannotSolveException();
			}
		}
	}

	@Override
	public void nextStep() {
		if (isSolved())
			throw new AlreadySolvedException();

		boolean hasProgress = sudokuSolverIterator.calculateNextStep(field);

		if (!hasProgress)
			throw new CannotSolveException();
	}

	@Override
	public Cell[][] getField() {
		return field;
	}
}
