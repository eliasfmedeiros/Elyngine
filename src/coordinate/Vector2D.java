package coordinate;

public abstract class Vector2D {      

    public static double degreesToRadians(double degrees) { 
        return Math.toRadians(degrees);
    }

    public static double radiansToDegrees(double radians) { 
        return Math.toDegrees(radians);
    }

    public abstract byte xToByte();
    public abstract short xToShort();
    public abstract int xToInt();
    public abstract long xToLong();
    public abstract float xToFloat();
    public abstract double xToDouble();
    protected abstract String xToString();

    public abstract byte yToByte();
    public abstract short yToShort();
    public abstract int yToInt();
    public abstract long yToLong();
    public abstract float yToFloat();
    public abstract double yToDouble();
    protected abstract String yToString();
        
    public  Vector2D asReadOnly() {
        Vector2D that = this;
        return new Vector2D() {
            @Override public byte xToByte() { return that.xToByte(); }
            @Override public short xToShort() { return that.xToShort(); }
            @Override public int xToInt() { return that.xToInt(); }
            @Override public long xToLong() { return that.xToLong(); }
            @Override public float xToFloat() { return that.xToFloat(); }
            @Override public double xToDouble() { return that.xToDouble(); }
            @Override protected String xToString() { return that.xToString(); }
            
            @Override public byte yToByte() { return that.yToByte(); }
            @Override public short yToShort() { return that.yToShort(); }
            @Override public int yToInt() { return that.yToInt(); }
            @Override public long yToLong() { return that.yToLong(); }
            @Override public float yToFloat() { return that.yToFloat(); }
            @Override public double yToDouble() { return that.yToDouble(); }
            @Override protected String yToString() { return that.yToString(); }

            @Override public Vector2D asReadOnly() { return this; }  
        };
    }

    protected Double2D toDouble2D() { return new Double2D(xToDouble(), yToDouble()); }
    protected Float2D toFloat2D() { return new Float2D(xToFloat(), yToFloat()); }

    public boolean isZero() { return this.xToDouble()==0d&&this.yToDouble()==0d; }    

    public double magnitudeSquared() {  
        double x = this.xToDouble(), y = this.yToDouble();
        return x * x + y * y;
    }
    
    /** Returns the vector magnitude (length) using Euclidean distance. */
    public double magnitude() {  
        return Math.sqrt(magnitudeSquared());
    }    
    
    public  double cosAngleTo(Vector2D other) {    
        double dot=this.xToDouble() * other.xToDouble() + this.yToDouble() * other.yToDouble();
        return Math.clamp( dot / (this.magnitude() * other.magnitude()), -1,1);
    }
    
    public double angleTo(Vector2D other) {   
        return Math.acos(cosAngleTo(other));
    }
    
    public Vector2D getPointerTo(Vector2D target) {  // sobrescrever para parametro com subtipo
        Vector2D that = this;
        return new Vector2D() {
            private double x(){return target.xToDouble()-that.xToDouble(); }
            private double y(){return target.yToDouble()-that.yToDouble(); }

            @Override public byte xToByte() { return (byte)(x()); }
            @Override public short xToShort() { return (short)(x()); }
            @Override public int xToInt() { return (int) x(); }
            @Override public long xToLong() { return (long) x(); }
            @Override public float xToFloat() { return (float) x(); }
            @Override public double xToDouble() { return (double)x(); }
            @Override protected String xToString() { return ""+x(); }

            @Override public byte yToByte() { return (byte)(y()); }
            @Override public short yToShort() { return (short)(y()); }
            @Override public int yToInt() { return (int) y(); }
            @Override public long yToLong() { return (long) y(); }
            @Override public float yToFloat() { return (float) y(); }
            @Override public double yToDouble() { return (double)y(); }
            @Override protected String yToString() { return ""+y(); }

            @Override public Vector2D asReadOnly() { return this; }
        };
    }
    
    @Override
    public boolean equals(Object o) {   
        if(o==null || !(o instanceof Vector2D)) return false;
        return equals((Vector2D)o);
    }
    
    private boolean equals(Vector2D that){   
        return that.xToDouble()==this.xToDouble() && that.yToDouble()==this.yToDouble();
    }

    /** Returns a string representation of the vector in the format: Vector2D{x=..., y=...}  */
    @Override
    public String toString() {
        return Vector2D.class.getSimpleName() + '{' +"x="+xToString()+", y="+yToString()+ '}';
    }
}
