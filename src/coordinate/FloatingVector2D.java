package coordinate;

public abstract class FloatingVector2D  extends Vector2D  {
    
    public abstract void setOrderedPair(double x, double y);
    public abstract void setOrderedPair(Vector2D  base);

    public abstract FloatingVector2D  add(double x, double y);
    public abstract FloatingVector2D  add(Vector2D  other);

    public abstract FloatingVector2D  subtract(double x, double y);
    public abstract FloatingVector2D  subtract(Vector2D  other);

    public abstract FloatingVector2D  hadamardMultiply(double x, double y);
    public abstract FloatingVector2D  hadamardMultiply(Vector2D  other);

    public abstract FloatingVector2D  multiply(double scalar);

    public FloatingVector2D  setMagnitude(double newMag) { 
        double mag=this.magnitude();
        if(mag==0) throw new ArithmeticException("Cannot operate with zero-magnitude vector.");   
        return this.multiply(newMag / mag);
    }

    public double dot(Vector2D  other) { // 1: igual. 0: perpendicular (direita ou esquerda). -1: oposto. Para magnitudes com produto 1
        return  this.xToDouble() * other.xToDouble() +  this.yToDouble() * other.yToDouble();
    }
    public double cross(Vector2D  leftPerpendicular) { // 1: totalmente perpendicular à esquerda. 0: alinhado (igual ou oposto). -1: totalmente perpendicular à direita. Para magnitudes com produto 1
        return  this.xToDouble() * leftPerpendicular.yToDouble() -  this.yToDouble() * leftPerpendicular.xToDouble();
    }

    public FloatingVector2D  rotate(double radians) {    
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        this.setOrderedPair(this.xToDouble() * cos - this.yToDouble() * sin, this.xToDouble() * sin + this.yToDouble() * cos);
        return this;
    }
    public FloatingVector2D  rotateDegrees(double degrees) {    
        return this.rotate(degreesToRadians(degrees));
    }
    
    public FloatingVector2D  revolveAround(Vector2D  center, double radians) {    
        double xCenter = center.xToDouble(), yCenter = center.yToDouble();
        double xDistance = this.xToDouble() - xCenter, yDistance = this.yToDouble() - yCenter;
        double cos = Math.cos(radians), sin = Math.sin(radians);
        this.setOrderedPair(
            (xDistance * cos - yDistance * sin) + xCenter,
            (xDistance * sin + yDistance * cos) + yCenter
        );
        return this;
    }
    public FloatingVector2D  revolveAroundDegrees(Vector2D  center, double degrees) {   
        return this.revolveAround(center, degreesToRadians(degrees));
    }
    
    public void resetOrderedPair() {
        this.setOrderedPair(0, 0);
    }  
}
