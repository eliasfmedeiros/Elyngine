package coordinate;

import coordinate.Vector2D.ReadWriteVector2D;

public interface IntegerVector2D extends ReadWriteVector2D {

	public static Vector2D displacement(IntegerVector2D initial,IntegerVector2D terminal) {
		return new AbstractVector2D.Boxed(
				new AbstractInteger() {
					@Override
					public int intValue() {
						return terminal.xToInt() - initial.xToInt();
					}
				},
				new AbstractInteger() {
					@Override
					public int intValue() {
						return terminal.yToInt() - initial.yToInt();
					}
				}
			);
	}
	
	@Override default float xToFloat() { return (float) xToInt(); }
	@Override default double xToDouble() { return (double) xToInt(); }
	@Override default String xToString() { return ""+xToInt(); }

	@Override default float yToFloat() { return (float) yToInt(); }
	@Override default double yToDouble() { return (double) yToInt(); }
	@Override default String yToString() { return ""+yToInt(); }
	
	public abstract void setOrderedPair(int x, int y);
	default void setOrderedPair(IntegerVector2D base) {
		this.setOrderedPair(base.xToInt(),base.yToInt());
	}

	default IntegerVector2D add(int x, int y){
		this.setOrderedPair(this.xToInt()+x,this.yToInt()+y);
		return this;
	}
	default IntegerVector2D add(IntegerVector2D other) {
		return this.add(other.xToInt(),other.yToInt());
	}

	default IntegerVector2D subtract(int x, int y){
		return this.add(-x,-y);
	}
	default IntegerVector2D subtract(IntegerVector2D other) {
		return this.subtract(other.xToInt(),other.yToInt());
	}

	default IntegerVector2D hadamardMultiply(int x, int y){
		this.setOrderedPair(this.xToInt()*x,this.yToInt()*y);
		return this;
	}
	default IntegerVector2D hadamardMultiply(double x, double y){
		this.setOrderedPair((int)(this.xToDouble()*x),(int)(this.yToDouble()*y));
		return this;
	}

    default IntegerVector2D hadamardMultiply(IntegerVector2D other){
		return this.hadamardMultiply(other.xToInt(),other.yToInt());
	}
	default IntegerVector2D hadamardMultiply(Vector2D other){
		return this.hadamardMultiply(other.xToDouble(),other.yToDouble());
	}

	default IntegerVector2D multiply(int scalar){
		return this.hadamardMultiply(scalar, scalar);
	}
	default IntegerVector2D multiply(double scalar){
		return this.hadamardMultiply(scalar, scalar);
	}

	default long dot(IntegerVector2D other) { // 1: igual. 0: perpendicular (direita ou esquerda). -1: oposto. Para magnitudes com produto 1, o que é difícil em caso de vetores de inteiros
		return this.xToInt() * other.xToInt() + this.yToInt() * other.yToInt();
	}
	default long cross(IntegerVector2D leftPerpendicular) { // 1: totalmente perpendicular à esquerda. 0: alinhado (igual ou oposto). -1: totalmente perpendicular à direita. Para magnitudes com produto 1, o que é difícil em caso de vetores de inteiros
		return this.xToInt() * leftPerpendicular.yToInt() - this.yToInt() * leftPerpendicular.xToInt();
	}
	
	default void resetOrderedPair() {
		this.setOrderedPair(0, 0);
	} 

	public static abstract class AbstractInteger extends Number {

		@Override
		public long longValue() {	return (long) intValue();	}

		@Override
		public float floatValue() {	return (float) intValue();	}

		@Override
		public double doubleValue() { return (double) intValue();	}
	};
}
