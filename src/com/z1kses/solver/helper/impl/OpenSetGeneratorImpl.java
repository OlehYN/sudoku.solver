package com.z1kses.solver.helper.impl;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.OpenSetContainer;
import com.z1kses.solver.entity.impl.Cell;
import com.z1kses.solver.helper.OpenSetGenerator;

public class OpenSetGeneratorImpl implements OpenSetGenerator {

	@Override
	public List<OpenSetContainer> generateOpenSetContainers(List<Cell> cells, Cell startCell) {
		List<OpenSetContainer> result = new ArrayList<>();

		List<Cell> initList = new ArrayList<>();
		initList.add(startCell);

		OpenSetContainer initOpenSetContainer = new OpenSetContainer(initList, new ArrayList<>());
		result.add(initOpenSetContainer);

		for (Cell cell : cells) {
			List<OpenSetContainer> iterationSets = new ArrayList<>();

			for (OpenSetContainer openSetContainer : result) {
				OpenSetContainer newOpenSetContainer = new OpenSetContainer(new ArrayList<>(openSetContainer.getMain()),
						new ArrayList<>(openSetContainer.getOther()));

				newOpenSetContainer.getOther().add(cell);
				iterationSets.add(newOpenSetContainer);

				openSetContainer.getMain().add(cell);
			}

			result.addAll(iterationSets);
		}

		return result;
	}

}
