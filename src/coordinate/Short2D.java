package coordinate;

public class Short2D extends IntegerVector2D  {
    private short x, y;
    
    public Short2D(int x, int y) {
        this.x = (short)x;     this.y = (short)y;
    }
    public Short2D(short x, short y) {
        this.x = x;     this.y = y;
    }
    public Short2D(Vector2D  base) {
        this.x = base.xToShort();     this.y = base.yToShort();
    }

    @Override
    public void setOrderedPair(int x, int y) { 
        this.x = (short)x;     this.y = (short)y;
    }

    @Override public int xToInt() { return (int) x; }

    @Override public int yToInt() { return (int) y; }
}