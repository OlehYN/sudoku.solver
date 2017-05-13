package com.z1kses.solver.entity;

import com.z1kses.solver.entity.impl.Cell;

public class CellContainer {
	private int x;
	private int y;
	private Cell cell;

	public CellContainer(int x, int y, Cell cell) {
		this.x = x;
		this.y = y;
		this.cell = cell;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	@Override
	public String toString() {
		return "CellContainer [x=" + x + ", y=" + y + ", cell=" + cell + "]";
	}

}
