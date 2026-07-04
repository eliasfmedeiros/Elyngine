package internal;
import java.util.ArrayList;
import java.util.function.Consumer;

public final class Trigger<T> extends ArrayList<Consumer<T>> implements Consumer<T>{

    public Trigger(){}
    public Trigger(ArrayList<Consumer<T>> consumers) {
        this.addAll(consumers);
    }
    
    @Override
    public void accept( T message ) {
        for( Consumer<T> c : this) {
            c.accept( message );
        }
    }

    public boolean subscribe( Consumer<T> consumer ) {
        return this.add( consumer );
    }

    public void subscribe( int index, Consumer<T> element ) {
        this.add( index, element );
    }

    public boolean unsubscribe( Consumer<T> consumer ) {
        return this.remove( consumer );
    }
        
    @Override
    public String toString() {
        int thisSize = this.size();
        StringBuilder sb = new StringBuilder()
            .append("Trigger[")
            .append(thisSize)
            .append("]{");
        for (int i = 0; i < thisSize; i++) 
            sb.append("[")
                .append(i)
                .append("]:")
                .append(this.get(i))
                .append(",");
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }
    
}
