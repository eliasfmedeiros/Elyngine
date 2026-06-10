package coordinate;

public interface Vector2D {

	public static final Vector2D ZERO = new Vector2D.BoxedVector2D(0f, 0f).asReadOnly();
	public static final Vector2D UP =	new Vector2D.BoxedVector2D(0f, 1f).asReadOnly();
	public static final Vector2D DOWN = new Vector2D.BoxedVector2D(0f, -1f).asReadOnly();
	public static final Vector2D RIGH = new Vector2D.BoxedVector2D(1f, 0f).asReadOnly();
	public static final Vector2D LEFT = new Vector2D.BoxedVector2D(-1f, 0f).asReadOnly();

	public static double degreesToRadians(double degrees) {
		return Math.toRadians(degrees);
	}

	public static double radiansToDegrees(double radians) {
		return Math.toDegrees(radians);
	}

	public static String toString(Vector2D vector) {
		return vector.getClass().getSimpleName() + '{' + "x=" + vector.xToString() + ", y=" + vector.yToString() + '}';
	}

	public abstract int xToInt();

	public abstract float xToFloat();

	public abstract double xToDouble();

	public abstract String xToString();

	public abstract int yToInt();

	public abstract float yToFloat();

	public abstract double yToDouble();

	public abstract String yToString();

	default byte xToByte() {	return (byte) xToInt();	}

	default short xToShort() { return (short) xToInt(); }

	default byte yToByte() {	return (byte) yToInt();	}

	default short yToShort() { return (short) yToInt(); }

	default Vector2D asReadOnly() {
		Vector2D that = this;
		return new Vector2D() {

			@Override
			public int xToInt() {   return that.xToInt(); } 

			@Override
			public float xToFloat() {	return that.xToFloat();	}

			@Override
			public double xToDouble() {	return that.xToDouble();	}

			@Override
			public String xToString() {	return that.xToString();	}

			@Override
			public int yToInt() {	return that.yToInt();	}

			@Override
			public float yToFloat() {	return that.yToFloat();	}

			@Override
			public double yToDouble() {	return that.yToDouble();	}

			@Override
			public String yToString() {	return that.yToString();	}

			@Override
			public Vector2D asReadOnly() {	return this;	}
		};
	}

	default Byte2D toByte2D() {
		return new Byte2D(xToByte(), yToByte());
	}

	default Short2D toShort2D() {
		return new Short2D(xToShort(), yToShort());
	}

	default Integer2D toInt2D() {
		return new Integer2D(xToInt(), yToInt());
	}

	default Float2D toFloat2D() {
		return new Float2D(xToFloat(), yToFloat());
	}

	default Double2D toDouble2D() {
		return new Double2D(xToDouble(), yToDouble());
	}

	default boolean isZero() {
		return this.xToDouble() == 0d && this.yToDouble() == 0d;
	}

	default double magnitudeSquared() {
		double x = this.xToDouble(), y = this.yToDouble();
		return x * x + y * y;
	}

	/** Returns the vector magnitude (length) using Euclidean distance. */
	default double magnitude() {
		return Math.sqrt(this.magnitudeSquared());
	}

	default double cosAngleTo(Vector2D other) {
		double mag = this.magnitude() * other.magnitude();
		if (mag == 0)
			throw new ArithmeticException("Cannot operate with zero-magnitude vector.");
		double dot = this.xToDouble() * other.xToDouble() + this.yToDouble() * other.yToDouble();
		return Math.clamp(dot / mag, -1, 1);
	}

	default double angleTo(Vector2D other) {
		return Math.acos(cosAngleTo(other));
	}

	default boolean equals(Vector2D that) {
		return that.xToDouble() == this.xToDouble() && that.yToDouble() == this.yToDouble();
	}

	public interface ReadWriteVector2D extends Vector2D {
		void resetOrderedPair();
		ReadWriteVector2D copy();
	}

	public static final class BoxedVector2D implements ReadWriteVector2D {
		public Number x, y;

		public BoxedVector2D(Number x, Number y) {
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
		public String xToString() {	return "" + x;	}

		@Override
		public int yToInt() {	return y.intValue();	}

		@Override
		public float yToFloat() {	return y.floatValue();	}

		@Override
		public double yToDouble() {	return y.doubleValue();	}

		@Override
		public String yToString() {	return "" + y;	}

		@Override
		public Vector2D.BoxedVector2D copy() {	return new BoxedVector2D(x, y);	}

		@Override
		public void resetOrderedPair() {
			x=y=0;
		}
	}

	public static abstract class AbstractDouble extends Number {
		@Override
		public int intValue() {	return (int) doubleValue();	}

		@Override
		public long longValue() {	return (long) doubleValue();	}

		@Override
		public float floatValue() {	return (float) doubleValue();	}
	};

}
