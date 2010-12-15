package imglib.mpicbg.imglib.cursor.special.meta;

import mpicbg.imglib.type.ComparableType;

/**
 * A predicate that always evaluates to true.
 */
public class AlwaysTruePredicate<T extends ComparableType<T>> implements Predicate<T> {
	public boolean evaluate(T value) {
		return true;
	}
}
