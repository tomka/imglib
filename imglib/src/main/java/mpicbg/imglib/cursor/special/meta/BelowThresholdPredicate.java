package imglib.mpicbg.imglib.cursor.special.meta;

import mpicbg.imglib.type.ComparableType;

/**
 * A predicate that is only true iff the value is below the threshold.
 */
public class BelowThresholdPredicate<T extends ComparableType<T>> implements Predicate<T> {
	T threshold;

	public BelowThresholdPredicate(T threshold) {
		this.threshold = threshold;
	}

	public boolean evaluate(T value) {
		return value.compareTo(threshold) < 0;
	}
}
