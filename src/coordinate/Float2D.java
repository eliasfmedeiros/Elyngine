package coordinate;

public class Float2D extends FloatingVector2D  {
    private float x,y;

    public Float2D(float x, float y) {
        this.x=x;this.y=y;
    }
    public Float2D(Vector2D  base) {
        this.x= base.xToFloat();this.y=base.yToFloat();
    }
    
    private float x(){return x; }
    private float y(){return y; }

    @Override public int xToInt() { return (int) x(); }
    @Override public float xToFloat() { return (float) x(); }
    @Override public double xToDouble() { return (double) x(); }
    @Override protected String xToString() { return ""+x(); }

    @Override public int yToInt() { return (int) y(); }
    @Override public float yToFloat() { return (float) y(); }
    @Override public double yToDouble() { return (double) y(); }
    @Override protected String yToString() { return ""+y(); }

    public Vector2D displacementTo(Vector2D target) {
		Vector2D that = this;
		return new Vector2D.Boxed(
				new Vector2D.DoubleSupplier() {
					@Override
					public double doubleValue() {
						return target.xToFloat() - that.xToFloat();
					}
				},
				new Vector2D.DoubleSupplier() {
					@Override
					public double doubleValue() {
						return target.yToFloat() - that.yToFloat();
					}
				}
			);
	}
    
    @Override
    public void setOrderedPair(double x, double y) {
        this.setOrderedPair((float) x, (float) y);
    }
    public void setOrderedPair(float x, float y) {
        this.x=x;this.y=y;
    }
    @Override
    public void setOrderedPair(Vector2D  base){
        this.setOrderedPair(base.xToFloat(), base.yToFloat());
    }

    @Override
    public Float2D add(double x, double y) {
        this.setOrderedPair( this.xToDouble() + x, this.yToDouble() + y);
        return this;
    }
    public Float2D add(float x, float y) {
        this.setOrderedPair(this.xToFloat() + x, this.yToFloat() + y);
        return this;
    }
    @Override
    public Float2D add(Vector2D  other) {
        return this.add(other.xToFloat(), other.yToFloat());
    }
    public Float2D add(Double2D other) {
        return this.add(other.xToDouble(), other.yToDouble());
    }

    @Override
    public Float2D subtract(double x, double y) {
        return this.add(-x, -y);
    }
    public Float2D subtract(float x, float y) {
        return this.add(-x, -y);
    }
    @Override
    public Float2D subtract(Vector2D  other) {
        return this.add(-other.xToFloat(), -other.yToFloat());
    }
    public Float2D subtract(Double2D other) {
        return this.add(-other.xToDouble(), -other.yToDouble());
    }

    @Override
    public Float2D hadamardMultiply(double x, double y) {
        this.setOrderedPair(this.xToDouble() * x, yToDouble() * y);
        return this;
    }
    public Float2D hadamardMultiply(float x, float y) {
        this.setOrderedPair(this.xToFloat() * x, yToFloat() * y);
        return this;
    }    
    @Override
    public Float2D hadamardMultiply(Vector2D  other) {
        return this.hadamardMultiply(other.xToFloat(), other.yToFloat());
    }
    public Float2D hadamardMultiply(Double2D other) {
        return this.hadamardMultiply(other.xToDouble(), other.yToDouble());
    }

    @Override
    public Float2D multiply(double scalar) {
        return this.hadamardMultiply(scalar, scalar);
    }    
    public Float2D multiply(float scalar) {
        return this.hadamardMultiply(scalar, scalar);
    }
}
