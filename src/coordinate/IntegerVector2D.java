package coordinate;

public abstract class IntegerVector2D  extends Vector2D  {
    
    @Override public float xToFloat() { return (float) xToInt(); }
    @Override public double xToDouble() { return (double) xToInt(); }
    @Override protected String xToString() { return ""+xToInt(); }

    @Override public float yToFloat() { return (float) yToInt(); }
    @Override public double yToDouble() { return (double) yToInt(); }
    @Override protected String yToString() { return ""+yToInt(); }
    
	public Vector2D displacementTo(IntegerVector2D target) {
		IntegerVector2D that = this;
		return new Vector2D.Boxed(
				new IntegerSupplier() {
					@Override
					public int intValue() {
						return target.xToInt() - that.xToInt();
					}
				},
				new IntegerSupplier() {
					@Override
					public int intValue() {
						return target.yToInt() - that.yToInt();
					}
				}
			);
	}
    
    public abstract void setOrderedPair(int x, int y);
    public void setOrderedPair(double x, double y){
        setOrderedPair((int)x, (int)y);
    }
    public void setOrderedPair(IntegerVector2D  base) {
        this.setOrderedPair(base.xToInt(),base.yToInt());
    }

    public IntegerVector2D  add(int x, int y){
        this.setOrderedPair(this.xToInt()+x,this.yToInt()+y);
        return this;
    }
    public IntegerVector2D  add(IntegerVector2D  other) {
        return this.add(this.xToInt()+other.xToInt(),this.yToInt()+other.yToInt());
    }

    public IntegerVector2D  subtract(int x, int y){
        return this.add(-x,-y);
    }
    public IntegerVector2D  subtract(IntegerVector2D  other) {
        return this.add(-other.xToInt(),-other.yToInt());
    }

    public IntegerVector2D  hadamardMultiply(int x, int y){
        this.setOrderedPair(this.xToInt()*x,this.yToInt()*y);
        return this;
    }
    public IntegerVector2D  hadamardMultiply(double x, double y){
        this.setOrderedPair(this.xToDouble()*x,this.yToDouble()*y);
        return this;
    }
    public IntegerVector2D  hadamardMultiply(IntegerVector2D  other){
        return this.hadamardMultiply(other.xToInt(),other.yToInt());
    }
    public IntegerVector2D  hadamardMultiply(FloatingVector2D  other){
        return this.hadamardMultiply(other.xToDouble(),other.yToDouble());
    }

    public IntegerVector2D  multiply(int scalar){
        return this.hadamardMultiply(scalar, scalar);
    }
    public IntegerVector2D  multiply(double scalar){
        return this.hadamardMultiply(scalar, scalar);
    }

    public long dot(IntegerVector2D  other) { // 1: igual. 0: perpendicular (direita ou esquerda). -1: oposto. Para magnitudes com produto 1, o que é difícil em caso de vetores de inteiros
        return  this.xToInt() * other.xToInt() +  this.yToInt() * other.yToInt();
    }
    public long cross(IntegerVector2D  leftPerpendicular) { // 1: totalmente perpendicular à esquerda. 0: alinhado (igual ou oposto). -1: totalmente perpendicular à direita. Para magnitudes com produto 1, o que é difícil em caso de vetores de inteiros
        return  this.xToInt() * leftPerpendicular.yToInt() -  this.yToInt() * leftPerpendicular.xToInt();
    }
    
    public void resetOrderedPair() {
        this.setOrderedPair(0, 0);
    }  

    static abstract class IntegerSupplier extends Number {

		@Override
		public long longValue() {	return (long) intValue();	}

		@Override
		public float floatValue() {	return (float) intValue();	}

        @Override
        public double doubleValue() { return (double) intValue();    }

	};
}
