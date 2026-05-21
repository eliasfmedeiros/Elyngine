package coordinate;

public class Double2D extends FloatingVector2D  {
    
    private double x,y;

    public Double2D(double x, double y) {
        this.x=x;this.y=y;
    }
    public Double2D(Vector2D  base) {
        this.x= base.xToDouble();this.y=base.yToDouble();
    }
    
    private double x(){return x; }
    private double y(){return y; }

    @Override public int xToInt() { return (int) x(); }
    @Override public float xToFloat() { return (float) x(); }
    @Override public double xToDouble() { return (double) x(); }
    @Override protected String xToString() { return ""+x(); }

    @Override public int yToInt() { return (int) y(); }
    @Override public float yToFloat() { return (float) y(); }
    @Override public double yToDouble() { return (double) y(); }
    @Override protected String yToString() { return ""+y(); }

    @Override
    public void setOrderedPair(double x, double y) {
        this.x=x;this.y=y;
    }
    @Override
    public void setOrderedPair(Vector2D  base){
        this.setOrderedPair(base.xToDouble(), base.yToDouble());
    }

    @Override
    public Double2D add(double x, double y) {
        this.setOrderedPair(this.xToDouble() + x, this.yToDouble() + y);
        return this;
    }
    @Override
    public Double2D add(Vector2D  other) {
        return this.add(other.xToDouble(), other.yToDouble());
    }

    @Override
    public Double2D subtract(double x, double y) {
        return this.add(-x, -y);
    }
    @Override
    public Double2D subtract(Vector2D  other) {
        return this.add(-other.xToDouble(), -other.yToDouble());
    }

    @Override
    public Double2D hadamardMultiply(double x, double y) {
        this.setOrderedPair(this.xToDouble() * x, yToDouble() * y);
        return this;
    }
    @Override    
    public Double2D hadamardMultiply(Vector2D  other) {
        return this.hadamardMultiply(other.xToDouble(), other.yToDouble());
    }

    @Override
    public Double2D multiply(double scalar) {
        return this.hadamardMultiply(scalar, scalar);
    }
}
