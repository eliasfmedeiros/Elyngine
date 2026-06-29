package internal;

public interface UnidirectionalNode<T> {
	
	T getContent();

	void setContent(T content);

	UnidirectionalNode<T> getNext();

	void setNext(UnidirectionalNode<T> node);
	

    default boolean hasContent() {
        return this.getContent() != null;
    }

    default boolean hasNext() {
        return this.getNext() != null;
    }

	public interface StackerNode<T> extends UnidirectionalNode<T> {
	    default T peek() {
	        return this.getNext().getContent();
	    }

	    default void pushNext(UnidirectionalNode<T> node) {
	        node.setNext(this.getNext());
	        this.setNext(node);
	    }

	    default UnidirectionalNode<T> popNext() {
	        UnidirectionalNode<T> removed = this.getNext();
	        if (removed == null) return null;
	        this.setNext(removed.getNext());
	        return removed;
	    }
	}
}
