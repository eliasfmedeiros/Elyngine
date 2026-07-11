package internal;
import java.util.Collection;
import java.util.Iterator;
import internal.UnidirectionalNode.StackerNode;

public class UniDeque<T> implements Collection<T> {

	private StackerNode<T> lastNode=null; // Implicit CONSTRUCTOR initialization
	
    //#region basic
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'size'");
	}

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
