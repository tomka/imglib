/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * @author Dan White & Tom Kazimiers
 */
package mpicbg.imglib.algorithm.math;

import mpicbg.imglib.cursor.Cursor;
import mpicbg.imglib.image.Image;
import mpicbg.imglib.type.numeric.RealType;

/**
 * This class contains some basic {@link Image} statistics
 * calculations.
 */
public class ImageStatistics {
	/**
	 * Calculates the mean of an image.
	 * 
	 * @param img The image to calculate the mean of
	 * @return The mean of the image passed
	 */
	public static <T extends RealType<T>> double getImageMean(Image<T> img) {
		  double sum = 0;
		  Cursor<T> cursor = img.createCursor();
		  while (cursor.hasNext()) {
			  cursor.fwd();
			  T type = cursor.getType();
			  sum += type.getRealDouble();
		  }
		  cursor.close();
		  return sum / img.getNumPixels();
	  }
	
	/**
	 * Calculates the min of an image.
	 * 
	 * @param img The image to calculate the min of
	 * @return The min of the image passed
	 */
	public static <T extends RealType<T>> double getImageMin(Image<T> img) {
		  double min = img.createType().getMaxValue();
		  Cursor<T> cursor = img.createCursor();
		  while (cursor.hasNext()) {
			  cursor.fwd();
			  T type = cursor.getType();
			  double currValue = type.getRealDouble();
			  if (currValue < min)
				  min = currValue;
		  }
		  cursor.close();
		  return min;
	  }
	
	/**
	 * Calculates the max of an image.
	 * 
	 * @param img The image to calculate the max of
	 * @return The max of the image passed
	 */
	public static <T extends RealType<T>> double getImageMax(Image<T> img) {
		  double max = img.createType().getMinValue();
		  Cursor<T> cursor = img.createCursor();
		  while (cursor.hasNext()) {
			  cursor.fwd();
			  T type = cursor.getType();
			  double currValue = type.getRealDouble();
			  if (currValue > max)
				  max = currValue;
		  }
		  cursor.close();
		  return max;
	  }
}