package coordinate;

public abstract class Vector2D {

	public static final Vector2D ZERO = new Vector2D.Boxed(0f, 0f).asReadOnly();
	public static final Vector2D UP =	new Vector2D.Boxed(0f, 1f).asReadOnly();
	public static final Vector2D DOWN = new Vector2D.Boxed(0f, -1f).asReadOnly();
	public static final Vector2D RIGH = new Vector2D.Boxed(1f, 0f).asReadOnly();
	public static final Vector2D LEFT = new Vector2D.Boxed(-1f, 0f).asReadOnly();

	public static double degreesToRadians(double degrees) {
		return Math.toRadians(degrees);
	}

	public static double radiansToDegrees(double radians) {
		return Math.toDegrees(radians);
	}

	public abstract int xToInt();

	public abstract float xToFloat();

	public abstract double xToDouble();

	protected abstract String xToString();

	public abstract int yToInt();

	public abstract float yToFloat();

	public abstract double yToDouble();

	protected abstract String yToString();

	public byte xToByte() {	return (byte) xToInt();	}

	public short xToShort() { return (short) xToInt(); }

	public byte yToByte() {	return (byte) yToInt();	}

	public short yToShort() { return (short) yToInt(); }

	public Vector2D asReadOnly() {
		Vector2D that = this;
		return new Vector2D() {

			@Override
			public int xToInt() {   return that.xToInt(); } 

			@Override
			public float xToFloat() {	return that.xToFloat();	}

			@Override
			public double xToDouble() {	return that.xToDouble();	}

			@Override
			protected String xToString() {	return that.xToString();	}

			@Override
			public int yToInt() {	return that.yToInt();	}

			@Override
			public float yToFloat() {	return that.yToFloat();	}

			@Override
			public double yToDouble() {	return that.yToDouble();	}

			@Override
			protected String yToString() {	return that.yToString();	}

			@Override
			public Vector2D asReadOnly() {	return this;	}
		};
	}

	public Byte2D toByte2D() {
		return new Byte2D(xToByte(), yToByte());
	}

	public Short2D toShort2D() {
		return new Short2D(xToShort(), yToShort());
	}

	public Integer2D toInt2D() {
		return new Integer2D(xToInt(), yToInt());
	}

	public Float2D toFloat2D() {
		return new Float2D(xToFloat(), yToFloat());
	}

	public Double2D toDouble2D() {
		return new Double2D(xToDouble(), yToDouble());
	}

	public boolean isZero() {
		return this.xToDouble() == 0d && this.yToDouble() == 0d;
	}

	public double magnitudeSquared() {
		double x = this.xToDouble(), y = this.yToDouble();
		return x * x + y * y;
	}

	/** Returns the vector magnitude (length) using Euclidean distance. */
	public double magnitude() {
		return Math.sqrt(magnitudeSquared());
	}

	public double cosAngleTo(Vector2D other) {
		double mag = this.magnitude() * other.magnitude();
		if (mag == 0)
			throw new ArithmeticException("Cannot operate with zero-magnitude vector.");
		double dot = this.xToDouble() * other.xToDouble() + this.yToDouble() * other.yToDouble();
		return Math.clamp(dot / mag, -1, 1);
	}

	public double angleTo(Vector2D other) {
		return Math.acos(cosAngleTo(other));
	}

	public Vector2D displacementTo(Vector2D target) {
		Vector2D that = this;
		return new Vector2D.Boxed(
				new DoubleSupplier() {
					@Override
					public double doubleValue() {
						return target.xToDouble() - that.xToDouble();
					}
				},
				new DoubleSupplier() {
					@Override
					public double doubleValue() {
						return target.yToDouble() - that.yToDouble();
					}
				}
			);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Vector2D))
			return false;
		return equals((Vector2D) o);
	}

	private boolean equals(Vector2D that) {
		return that.xToDouble() == this.xToDouble() && that.yToDouble() == this.yToDouble();
	}

	@Override
	public String toString() {
		return Vector2D.class.getSimpleName() + '{' + "x=" + xToString() + ", y=" + yToString() + '}';
	}

	public static final class Boxed extends Vector2D {
		public Number x, y;

		public Boxed(Number x, Number y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int xToInt() {	return x.intValue();	}

		@Override
		public float xToFloat() {	return x.floatValue();	}

		@Override
		public double xToDouble() {	return x.doubleValue();	}

		@Override
		protected String xToString() {	return "" + x;	}

		@Override
		public int yToInt() {	return y.intValue();	}

		@Override
		public float yToFloat() {	return y.floatValue();	}

		@Override
		public double yToDouble() {	return y.doubleValue();	}

		@Override
		protected String yToString() {	return "" + y;	}

		public Vector2D.Boxed copy() {	return new Boxed(x, y);	}
	}

	static abstract class DoubleSupplier extends Number {
		@Override
		public int intValue() {	return (int) doubleValue();	}

		@Override
		public long longValue() {	return (long) doubleValue();	}

		@Override
		public float floatValue() {	return (float) doubleValue();	}
	};

}
