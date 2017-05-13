package com.z1kses.solver.entity.impl;

import java.util.ArrayList;
import java.util.List;

import com.z1kses.solver.entity.Element;

public class Cell implements Element<Integer> {

	private static final int MAX_VALUE = 9;
	private static final int MIN_VALUE = 1;

	private Integer value;
	private List<Integer> possibleValues;

	public Cell() {
		possibleValues = new ArrayList<>();

		for (int i = MIN_VALUE; i <= MAX_VALUE; i++)
			possibleValues.add(i);
	}

	public Cell(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public List<Integer> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}

	@Override
	public String toString() {
		return "Cell [value=" + value + ", possibleValues=" + possibleValues + "]";
	}

}
