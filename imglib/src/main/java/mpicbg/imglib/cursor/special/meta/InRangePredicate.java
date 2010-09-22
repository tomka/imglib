package imglib.mpicbg.imglib.cursor.special.meta;

import mpicbg.imglib.type.ComparableType;

/**
 * A predicate that is true if, and only if, the value is above lower
 * and below upper threshold.
 */
public class InRangePredicate<T extends ComparableType<T>> implements Predicate<T> {
	T lowerThreshold;
	T upperThreshold;

	public InRangePredicate(T lowerThreshold, T upperThreshold) {
		this.lowerThreshold = lowerThreshold;
		this.upperThreshold = upperThreshold;
	}

	/**
	 * Thresholds are always exclusive. 
	 */
	public boolean evaluate(T value) {
		return (value.compareTo(lowerThreshold) > 0) && (value.compareTo(upperThreshold) < 0);
	}
}
