package imglib.mpicbg.imglib.cursor.special.meta;

import mpicbg.imglib.type.ComparableType;

/**
 * A predicate that is only true iff the value is above the threshold.
 */
public class AboveThresholdPredicate<T extends ComparableType<T>> implements Predicate<T> {
	T threshold;

	public AboveThresholdPredicate(T threshold) {
		this.threshold = threshold;
	}

	public boolean evaluate(T value) {
		return value.compareTo(threshold) > 0;
	}
}
