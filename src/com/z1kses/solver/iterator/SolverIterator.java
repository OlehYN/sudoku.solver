package com.z1kses.solver.iterator;

public interface SolverIterator<T> {
	boolean calculateNextStep(T field);
}
