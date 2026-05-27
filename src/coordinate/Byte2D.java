package coordinate;

public class Byte2D extends IntegerVector2D  {
    private byte x, y;
    
    public Byte2D(int x, int y) {
        this.x = (byte)x;     this.y = (byte)y;
    }
    public Byte2D(byte x, byte y) {
        this.x = x;     this.y = y;
    }
    public Byte2D(Vector2D  base) {
        this.x = base.xToByte();     this.y = base.yToByte();
    }

    @Override
    public void setOrderedPair(int x, int y) { 
        this.x = (byte)x;     this.y = (byte)y;
    }

    @Override public int xToInt() { return (int) x; }
    
    @Override public int yToInt() { return (int) y; }
}