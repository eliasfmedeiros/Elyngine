package coordinate;

public class Float2D extends AbstractVector2D implements FloatingVector2D {

	public static Vector2D displacement(Vector2D initial,Vector2D terminal) {
		return new Vector2D.BoxedVector2D(
				new AbstractDouble() {
					@Override public double doubleValue() {
						return terminal.xToFloat() - initial.xToFloat();
					}
				},
				new AbstractDouble() {
					@Override public double doubleValue() {
						return terminal.yToFloat() - initial.yToFloat();
					}
				}
			);
	}

	private float x,y;

	public Float2D(float x, float y) {
		this.x=x;this.y=y;
	}
	public Float2D(Vector2D base) {
		this.x= base.xToFloat();this.y=base.yToFloat();
	}
	
	private float x(){return x; }
	private float y(){return y; }

	@Override public int xToInt() { return (int) x(); }
	@Override public float xToFloat() { return (float) x(); }
	@Override public double xToDouble() { return (double) x(); }
	@Override public String xToString() { return ""+x(); }

	@Override public int yToInt() { return (int) y(); }
	@Override public float yToFloat() { return (float) y(); }
	@Override public double yToDouble() { return (double) y(); }
	@Override public String yToString() { return ""+y(); }
	
	@Override
	public Float2D copy() {
		return new Float2D(x, y);
	}
	@Override
	public void setOrderedPair(double x, double y) {
		this.x=(float) x;this.y=(float) y;
	} 
	public void setOrderedPair(float x, float y) {
		this.x=x;this.y=y;
	}
	public void setOrderedPair(Float2D base){
		this.setOrderedPair(base.xToFloat(), base.yToFloat());
	}

	public Float2D add(float x, float y) {
		this.setOrderedPair(this.xToFloat() + x, this.yToFloat() + y);
		return this;
	}
	public Float2D add(Float2D other) {
		return this.add(other.xToFloat(), other.yToFloat());
	}

	public Float2D subtract(float x, float y) {
		return this.add(-x, -y);
	}
	public Float2D subtract(Float2D other) {
		return this.subtract(other.xToFloat(), other.yToFloat());
	}

	public Float2D hadamardMultiply(float x, float y) {
		this.setOrderedPair(this.xToFloat() * x, yToFloat() * y);
		return this;
	}
	public Float2D hadamardMultiply(Float2D other) {
		return this.hadamardMultiply(other.xToFloat(), other.yToFloat());
	}

	public Float2D multiply(float scalar) {
		return this.hadamardMultiply(scalar, scalar);
	}
	
	public void resetOrderedPair() {
		this.setOrderedPair(0f, 0f);
	}
	
}
