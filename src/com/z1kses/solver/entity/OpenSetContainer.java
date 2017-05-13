package com.z1kses.solver.entity;

import java.util.List;

import com.z1kses.solver.entity.impl.Cell;

public class OpenSetContainer {
	private List<Cell> main;
	private List<Cell> other;

	public OpenSetContainer(List<Cell> main, List<Cell> other) {
		super();
		this.main = main;
		this.other = other;
	}

	public List<Cell> getMain() {
		return main;
	}

	public void setMain(List<Cell> main) {
		this.main = main;
	}

	public List<Cell> getOther() {
		return other;
	}

	public void setOther(List<Cell> other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "OpenSetContainer [main=" + main + ", other=" + other + "]";
	}

}
