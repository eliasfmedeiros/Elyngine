package coordinate;

public interface FloatingVector2D extends Vector2D.ReadWriteVector2D {
	
	public abstract void setOrderedPair(double x, double y);
	default void setOrderedPair(Vector2D base){
		this.setOrderedPair(base.xToDouble(), base.yToDouble());
	}

	default FloatingVector2D add(double x, double y) {
		this.setOrderedPair(this.xToDouble() + x, this.yToDouble() + y);
		return this;
	}
	default FloatingVector2D add(Vector2D other) {
		return this.add(other.xToDouble(), other.yToDouble());
	}

	default FloatingVector2D subtract(double x, double y) {
		return this.add(-x, -y);
	}
	default FloatingVector2D subtract(Vector2D other) {
		return this.subtract(other.xToDouble(), other.yToDouble());
	}

	default FloatingVector2D hadamardMultiply(double x, double y) {
		this.setOrderedPair(this.xToDouble() * x, yToDouble() * y);
		return this;
	}
	default FloatingVector2D hadamardMultiply(Vector2D other) {
		return this.hadamardMultiply(other.xToDouble(), other.yToDouble());
	}

	default FloatingVector2D multiply(double scalar) {
		return this.hadamardMultiply(scalar, scalar);
	}

	default FloatingVector2D setMagnitude(double mag) { 
		double oldMag=this.magnitude();
		if(oldMag==0) throw new ArithmeticException("Cannot operate with zero-magnitude vector.");  
		return this.multiply(mag / oldMag);
	}

	default double dot(Vector2D other) { // 1: igual. 0: perpendicular (direita ou esquerda). -1: oposto. Para magnitudes com produto 1
		return this.xToDouble() * other.xToDouble() + this.yToDouble() * other.yToDouble();
	}
	default double cross(Vector2D leftPerpendicular) { // 1: totalmente perpendicular à esquerda. 0: alinhado (igual ou oposto). -1: totalmente perpendicular à direita. Para magnitudes com produto 1
		return this.xToDouble() * leftPerpendicular.yToDouble() - this.yToDouble() * leftPerpendicular.xToDouble();
	}

	default FloatingVector2D rotate(double radians) {	
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		this.setOrderedPair(this.xToDouble() * cos - this.yToDouble() * sin, this.xToDouble() * sin + this.yToDouble() * cos);
		return this;
	}
	default FloatingVector2D rotateDegrees(double degrees) {	
		return this.rotate(Vector2D.degreesToRadians(degrees));
	}
	
	default FloatingVector2D revolveAround(Vector2D center, double radians) {	
		double xCenter = center.xToDouble(), yCenter = center.yToDouble();
		double xDistance = this.xToDouble() - xCenter, yDistance = this.yToDouble() - yCenter;
		double cos = Math.cos(radians), sin = Math.sin(radians);
		this.setOrderedPair(
			(xDistance * cos - yDistance * sin) + xCenter,
			(xDistance * sin + yDistance * cos) + yCenter
		);
		return this;
	}
	default FloatingVector2D revolveAroundDegrees(Vector2D center, double degrees) {  
		return this.revolveAround(center, Vector2D.degreesToRadians(degrees));
	}
	
	default void resetOrderedPair() {
		this.setOrderedPair(0d, 0d);
	} 
}
