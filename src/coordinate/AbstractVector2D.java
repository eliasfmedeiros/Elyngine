package coordinate;

public abstract class AbstractVector2D implements Vector2D {	

	@Override
	public String toString() {
		return Vector2D.toString(this);
	}

	public static final class Boxed extends AbstractVector2D implements Vector2D.ReadWriteVector2D {
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
		public AbstractVector2D.Boxed copy() {	return new Boxed(x, y);	}
	
		@Override
		public void resetOrderedPair() {
			x=y=0;
		}
	}

	public static abstract class Double extends Number {
		@Override
		public int intValue() {	return (int) doubleValue();	}
	
		@Override
		public long longValue() {	return (long) doubleValue();	}
	
		@Override
		public float floatValue() {	return (float) doubleValue();	}
	}

}