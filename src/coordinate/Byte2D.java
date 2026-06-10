package coordinate;

public class Byte2D implements IntegerVector2D {
	private byte x, y;
	
	public Byte2D(int x, int y) {
		this.x = (byte) x;	 this.y = (byte) y;
	}
	public Byte2D(Vector2D base) {
		this.x = base.xToByte();	 this.y = base.yToByte();
	}

	@Override public int xToInt() { return (int) x; }

	@Override public int yToInt() { return (int) y; }
	
	@Override
	public Byte2D copy() {
		return new Byte2D(x,y);
	}

	@Override
	public void setOrderedPair(int x, int y) { 
		this.x = (byte) x;	 this.y = (byte) y;
	}
}