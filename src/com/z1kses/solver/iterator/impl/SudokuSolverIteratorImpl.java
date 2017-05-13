package com.z1kses.solver.iterator.impl;

import java.util.List;

import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.iterator.SudokuSolverIterator;
import com.z1kses.solver.strategy.SudokuStrategy;

public class SudokuSolverIteratorImpl implements SudokuSolverIterator {

	private List<SudokuStrategy> strategies;
	private static final int MAX_SIZE = 9;

	public SudokuSolverIteratorImpl(List<SudokuStrategy> strategies) {
		this.strategies = strategies;
	}

	@Override
	public boolean calculateNextStep(Cell[][] field) {
		boolean progress = false;

		for (SudokuStrategy strategy : strategies)
			if (strategy.isBase())
				for (int i = 0; i < MAX_SIZE; i++)
					for (int j = 0; j < MAX_SIZE; j++)
						if (field[i][j].getValue() == null)
							progress = strategy.calculateNextValue(field[i][j], field, i, j) || progress;

		boolean advancedCalculation = false;
		if (!progress)
			for (SudokuStrategy strategy : strategies)
				if (!strategy.isBase())
					for (int i = 0; i < MAX_SIZE; i++)
						for (int j = 0; j < MAX_SIZE; j++)
							if (field[i][j].getValue() == null) {
								advancedCalculation = strategy.calculateNextValue(field[i][j], field, i, j);

								if (advancedCalculation)
									return true;
							}

		return progress;
	}

}
