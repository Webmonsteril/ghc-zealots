public final class Cell {
	private final int row;
	private final int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public int getDistance(final Cell cell) {
		int x = Math.abs(this.row - cell.row);
		int y = Math.abs(this.column - cell.column);
		
		Double d = Math.sqrt(x*x + y*y);
		return (int) Math.ceil(d);
	}


    @Override
    public String toString() {
        return "(" + row + ", " + column + ')';
    }
}
