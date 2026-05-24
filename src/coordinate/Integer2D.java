package coordinate;

public class Integer2D extends IntegerVector2D  {
    private int x, y;
    
    public Integer2D(int x, int y) {
        this.x = x;     this.y = y;
    }
    public Integer2D(Vector2D  base) {
        this.x = base.xToInt();     this.y = base.yToInt();
    }

    @Override
    public void setOrderedPair(int x, int y) { 
        this.x = x;     this.y = y;
    }

    @Override public int xToInt() { return (int) x; }

    @Override public int yToInt() { return (int) y; }
}