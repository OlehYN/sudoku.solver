package com.z1kses.solver.strategy.impl.hidden.multiple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.z1kses.solver.entity.OpenSetContainer;
import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.helper.OpenSetGenerator;
import com.z1kses.solver.helper.impl.OpenSetGeneratorImpl;
import com.z1kses.solver.strategy.SudokuStrategy;

public abstract class GenericHiddenSetSudokuStrategy implements SudokuStrategy {

	private OpenSetGenerator openSetGenerator = new OpenSetGeneratorImpl();

	@Override
	public boolean calculateNextValue(Cell cell, Cell[][] field, int x, int y) {
		List<Cell> currentUnresolvedCells = getUnresolvedCells(field, x, y);

		List<OpenSetContainer> openSetContainers = openSetGenerator.generateOpenSetContainers(currentUnresolvedCells,
				cell);

		for (OpenSetContainer openSetContainer : openSetContainers) {
			int setSize = openSetContainer.getMain().size();

			Set<Integer> possibleValues = new HashSet<>();
			Set<Integer> valuesToRemove = new HashSet<>();

			for (Cell mainCell : openSetContainer.getMain())
				possibleValues.addAll(mainCell.getPossibleValues());

			for (Cell mainCell : openSetContainer.getMain())
				for (Integer value : possibleValues)
					if (!mainCell.getPossibleValues().contains(value))
						valuesToRemove.add(value);

			possibleValues.removeAll(valuesToRemove);

			if (setSize > possibleValues.size() || openSetContainer.getOther().size() == 0
					|| openSetContainer.getMain().size() == 1)
				continue;

			List<Set<Integer>> possibleCombinations = generateCombinations(possibleValues, setSize);

			outer: for (Set<Integer> combination : possibleCombinations) {

				for (Cell otherCell : openSetContainer.getOther()) {

					for (Integer value : combination)
						if (otherCell.getPossibleValues().contains(value))
							continue outer;
				}

				// It's a good combination

				List<Integer> valuesToClean = new ArrayList<>();
				for (Cell mainCell : openSetContainer.getMain())
					for (Integer value : mainCell.getPossibleValues())
						if (!combination.contains(value))
							valuesToClean.add(value);

				if (valuesToClean.size() != 0) {

					for (Cell mainCell : openSetContainer.getMain())
						mainCell.getPossibleValues().removeAll(valuesToClean);

					return true;
				}
			}
		}
		return false;
	}

	protected abstract List<Cell> getUnresolvedCells(Cell[][] field, int x, int y);

	private List<Set<Integer>> generateCombinations(Set<Integer> possibleValues, int setSize) {
		List<Set<Integer>> possibleCombinations = new ArrayList<>();
		Set<Integer> initSet = new HashSet<>();

		if (possibleValues.size() == setSize) {
			initSet.addAll(possibleValues);

			possibleCombinations.add(initSet);

			return possibleCombinations;
		} else {
			possibleCombinations.add(initSet);

			for (Integer value : possibleValues) {
				List<Set<Integer>> newCombinations = new ArrayList<>();

				for (Set<Integer> set : possibleCombinations) {
					if (set.size() < setSize) {
						Set<Integer> newSet = new HashSet<>();
						newSet.addAll(set);
						newSet.add(value);

						newCombinations.add(newSet);
					}
				}

				possibleCombinations.addAll(newCombinations);
			}

			for (int i = 0; i < possibleCombinations.size(); i++)
				if (possibleCombinations.get(i).size() != setSize) {
					possibleCombinations.remove(i);
					--i;
				}

			return possibleCombinations;
		}
	}

	@Override
	public boolean isBase() {
		return false;
	}
}
