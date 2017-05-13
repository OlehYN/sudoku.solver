package com.z1kses.solver.strategy.impl.set;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.z1kses.solver.entity.OpenSetContainer;
import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.helper.OpenSetGenerator;
import com.z1kses.solver.helper.impl.OpenSetGeneratorImpl;
import com.z1kses.solver.strategy.SudokuStrategy;

public abstract class GenericSetSudokuStrategy implements SudokuStrategy {

	private OpenSetGenerator openSetGenerator = new OpenSetGeneratorImpl();

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {

		List<Cell> currentUnresolvedCells = createUnresolvedCells(field, x, y);

		List<OpenSetContainer> openSetContainers = openSetGenerator.generateOpenSetContainers(currentUnresolvedCells,
				cell);

		for (OpenSetContainer openSetContainer : openSetContainers) {
			int setSize = openSetContainer.getMain().size();

			Set<Integer> possibleValues = new HashSet<>();

			for (Cell mainCell : openSetContainer.getMain())
				possibleValues.addAll(mainCell.getPossibleValues());

			if (setSize != possibleValues.size() || openSetContainer.getOther().size() == 0
					|| openSetContainer.getMain().size() == 1)
				continue;

			if (setSize != possibleValues.size())
				continue;

			int oldSize = 0;
			for (Cell otherCell : openSetContainer.getOther())
				oldSize += otherCell.getPossibleValues().size();

			for (Cell otherCell : openSetContainer.getOther())
				otherCell.getPossibleValues().removeAll(possibleValues);

			for (Cell otherCell : openSetContainer.getOther())
				oldSize -= otherCell.getPossibleValues().size();

			if (oldSize != 0)
				return true;
		}
		return false;
	}

	protected abstract List<Cell> createUnresolvedCells(Cell[][] field, int x, int y);

	@Override
	public boolean isBase() {
		return false;
	}
}
