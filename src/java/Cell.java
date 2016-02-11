package Delivery;

public class Cell {
	public int r;
	public int c;

	public Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	
	public int getDistance(Cell c) {
		int x = Math.abs(this.r - c.r);
		int y = Math.abs(this.c - c.c);
		
		Double d = Math.sqrt(x*x + y*y);
		return (int) Math.ceil(d);
	}
	
	
}
