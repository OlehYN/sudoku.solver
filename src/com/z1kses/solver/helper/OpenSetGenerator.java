package com.z1kses.solver.helper;

import java.util.List;

import com.z1kses.solver.entity.OpenSetContainer;
import com.z1kses.solver.entity.impl.Cell;

public interface OpenSetGenerator {
	List<OpenSetContainer> generateOpenSetContainers(List<Cell> cells, Cell startCell);
}
