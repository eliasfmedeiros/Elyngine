package coordinate;

/**
 * Represents a generic 2D vector with numeric components.
 *
 * <p>This class is used as a mathematical foundation for spatial calculations
 * in the engine, such as position, movement and physics.</p>
 *
 * <p>It is parameterized to support different numeric types (Integer, Float,
 * Double, etc.), allowing flexibility between precision and performance.</p>
 *
 * <p>Concrete implementations are responsible for defining how arithmetic
 * operations are handled for each numeric type.</p>
 *
 * @param <T> numeric type used for the vector components
 */
public abstract class Vector2D<T extends Number> {
    //#region Constant ZERO in subtypes
    /** Zero vector for Integer type. */
    public static final Vector2D<Integer> INT_ZERO = new Vector2D<Integer>() {
        @Override public Integer getNumberX() { return 0; }
        @Override public Integer getNumberY() { return 0; }
    };
    /** Zero vector for Long type. */
    public static final Vector2D<Long> LONG_ZERO = new Vector2D<Long>() {
        @Override public Long getNumberX() { return 0L; }
        @Override public Long getNumberY() { return 0L; }
    };
    /** Zero vector for Float type. */
    public static final Vector2D<Float> FLOAT_ZERO = new Vector2D<Float>() {
        @Override public Float getNumberX() { return 0f; }
        @Override public Float getNumberY() { return 0f; }
    };
    /** Zero vector for Double type. */
    public static final Vector2D<Double> DOUBLE_ZERO = new Vector2D<Double>() {
        @Override public Double getNumberX() { return 0d; }
        @Override public Double getNumberY() { return 0d; }
    };
    /** Zero vector for Byte type. */
    public static final Vector2D<Byte> BYTE_ZERO = new Vector2D<Byte>() {
        @Override public Byte getNumberX() { return (byte) 0; }
        @Override public Byte getNumberY() { return (byte) 0; }
    };
    /** Zero vector for Short type. */
    public static final Vector2D<Short> SHORT_ZERO = new Vector2D<Short>() {
        @Override public Short getNumberX() { return (short) 0; }
        @Override public Short getNumberY() { return (short) 0; }
    };
    //#endregion
    
    /** Returns the X component of the vector. */
    public abstract T getNumberX();
    /** Returns the Y component of the vector. */
    public abstract T getNumberY();
    
    /**
     * Returns the vector magnitude (length) using Euclidean distance.
     */
    public double magnitude() {
        return Math.hypot( this.getNumberX().doubleValue(), this.getNumberY().doubleValue() );
    }
    
    /**
     * Compares this vector with another object for value equality.
     * Returns true if both X and Y components are equal.
     */
    @Override
    public boolean equals(Object o) {
        if(o!=null && o instanceof Vector2D){
            Vector2D<? extends Number> that=(Vector2D<? extends Number>)o;
            if( that.getNumberX().equals( this.getNumberX() ) && that.getNumberY().equals( this.getNumberY() ) )
                return true;
        }
        return false;
    }
    /**
     * Returns a string representation of the vector in the format:
     * Vector2D{x=..., y=...}
     */
    @Override
    public String toString() {
        return Vector2D.class.getSimpleName() + '{' +"x="+this.getNumberX()+", y="+this.getNumberY() + '}';
    }
}
