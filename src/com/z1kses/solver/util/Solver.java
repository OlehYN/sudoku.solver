package com.z1kses.solver.util;

public interface Solver<T> {

	void solve();

	void nextStep();

	boolean isSolved();

	T getField();
}
