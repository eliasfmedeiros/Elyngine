package internal;

public interface UnidirectionalNode<T> {
	
	T getContent();

	void setContent(T content);

	UnidirectionalNode<T> getNext();

	void setNext(UnidirectionalNode<T> link);
	

    default boolean hasContent() {
        return this.getContent() != null;
    }

    default boolean hasNext() {
        return this.getNext() != null;
    }

}