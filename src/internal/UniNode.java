package internal;

import internal.UnidirectionalNode.StackerNode;

public class UniNode<T> implements StackerNode<T> {
    
    public static String hashCodeToHexString(Object o) {
        return Integer.toHexString(o.hashCode());
    }

    private T content;
    private UnidirectionalNode<T> next = this;

    public UniNode(T content, UnidirectionalNode<T> next) {
        this.content = content;
        this.next = next;
    }

    public UniNode(T content) {
        this.content = content;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public UnidirectionalNode<T> getNext() {
        return next;
    }

    @Override
    public void setNext(UnidirectionalNode<T> link) {
        this.next = link;
    }

    public void pushNext(T content) {
        this.setNext(new UniNode<>(content, this.getNext()));
    }

    @Override
    public String toString() {
        UnidirectionalNode<T> next=this.getNext();
        return this.getClass().getSimpleName() + "(HashCode:" + hashCodeToHexString(this) + "){content:"
                + this.getContent() + ","
                + (next == null ? "Next:null" : "Next.HashCode:"+hashCodeToHexString(next)) + '}';
    }

}
