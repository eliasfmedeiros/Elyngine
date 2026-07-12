package internal;
import java.util.Collection;
import java.util.Iterator;
import internal.UnidirectionalNode.StackerNode;

public class UniDeque<T> implements Collection<T> {

	private StackerNode<T> lastNode=null; // Implicit CONSTRUCTOR initialization
	
    //#region internal infrastructure
	public boolean isEmpty() {
		return lastNode==null;
	}

	private StackerNode<T> getFirstNode() { 
		return (StackerNode<T>) this.lastNode.getNext();
	}

	private void addFirstNode(StackerNode<T> node)  {
		if(this.isEmpty()) lastNode=node; 
		else node.setNext(lastNode.getNext());		
		lastNode.setNext(node);
	}
	
	public StackerNode<T> dump() {
		StackerNode<T> dump = this.lastNode;
		this.lastNode=null; 
		return dump;
	}
	
	public void clear() {
		this.lastNode.setNext(null); // para gerar erro caso alguém esteja iterando a coleção
		this.dump();
	}
	
	private StackerNode<T> forgetFirstNode() {   
		if(this.isEmpty()) return null;
		if(lastNode.getNext()!=lastNode) return (StackerNode<T>) lastNode.popNext();
		return this.dump();
	}

	private void moveFirstNodeToLast() {
		lastNode=this.getFirstNode();
	}
    //#endregion

    //#region basic
	public int size() {
		if(this.isEmpty()) return 0;
		int i=1;
		for(StackerNode<T> node=getFirstNode();node!=lastNode;node=(StackerNode<T>) node.getNext()) i++;
		return i;
	}

    public T getFirst() {
        return this.isEmpty()?null:this.getFirstNode().getContent();
    }

	public T getLast() { 
        return this.isEmpty()?null:lastNode.getContent();
	}
    	
	public void addFirst(T content) {
		this.addFirstNode(new UniNode<>(content));
	}

	public void addLast(T content) {
		this.addFirst(content);
		this.moveFirstNodeToLast();
	}
	
	public T removeFirst() {
		return this.forgetFirstNode().getContent();
	}
	
	public StackerNode<T> removeFirstNode() {
		StackerNode<T> removed=this.forgetFirstNode();
		removed.setNext(removed);
		return removed;
	}
    //#endregion

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'contains'");
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iterator'");
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toArray'");
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toArray'");
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'add'");
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addAll'");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
	}

}
