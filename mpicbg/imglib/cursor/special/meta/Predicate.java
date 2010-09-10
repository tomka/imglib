package imglib.mpicbg.imglib.cursor.special.meta;

import mpicbg.imglib.type.ComparableType;


/**
 * An interface to check a value against arbitrary conditions.
 */
public interface Predicate<T extends ComparableType<T>> {

	// evaluate a predicate check for a given value
	boolean evaluate(T value);
}
