package coordinate;

public class Double2D extends AbstractVector2D implements FloatingVector2D {

	public static Vector2D displacement(Vector2D initial,Vector2D terminal) {
		return new Vector2D.BoxedVector2D(
				new AbstractDouble() {
					@Override public double doubleValue() {
						return terminal.xToDouble() - initial.xToDouble();
					}
				},
				new AbstractDouble() {
					@Override public double doubleValue() {
						return terminal.yToDouble() - initial.yToDouble();
					}
				}
			);
	}
 
	private double x,y;

	public Double2D(double x, double y) {
		this.x=x;this.y=y;
	}
	public Double2D(Vector2D base) {
		this.x= base.xToDouble();this.y=base.yToDouble();
	}
	 
	private double x(){return x; }
	private double y(){return y; }

	@Override public int xToInt() { return (int) x(); }
	@Override public float xToFloat() { return (float) x(); }
	@Override public double xToDouble() { return (double) x(); }
	@Override public String xToString() { return ""+x(); }

	@Override public int yToInt() { return (int) y(); }
	@Override public float yToFloat() { return (float) y(); }
	@Override public double yToDouble() { return (double) y(); }
	@Override public String yToString() { return ""+y(); }

	@Override
	public Double2D copy() { return new Double2D(x, y); }
	@Override
	public void setOrderedPair(double x, double y) { this.x=x; this.y=y; }

}
