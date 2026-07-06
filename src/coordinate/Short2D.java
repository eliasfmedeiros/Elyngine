package coordinate;

public class Short2D extends AbstractVector2D implements IntegerVector2D {
	private short x, y;
	
	public Short2D(int x, int y) {
		this.x = (short) x;	 this.y = (short) y;
	}
	public Short2D(Vector2D base) {
		this.x = base.xToShort();	 this.y = base.yToShort();
	}

	@Override public int xToInt() { return (int) x; }

	@Override public int yToInt() { return (int) y; }
	
	@Override
	public Short2D copy() {
		return new Short2D(x,y);
	}

	@Override
	public void setOrderedPair(int x, int y) { 
		this.x = (short) x;	 this.y = (short) y;
	}

}