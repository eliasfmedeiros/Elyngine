package internal;

import java.util.Enumeration;

public interface Loop {
	public enum Command { CONTINUE, BREAK }
	public interface Iteration<T> { Command consume(T content); }

	public static <E> void execute(Iteration<E> it, Enumeration<E> enumeration) {
		while(it.consume(enumeration.nextElement())==Command.CONTINUE);
	}
}