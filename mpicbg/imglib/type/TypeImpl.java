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
 * @author Stephan Preibisch & Stephan Saalfeld
 */

package mpicbg.imglib.type;

public abstract class TypeImpl<T extends TypeImpl<T>> implements Type<T>
{
	protected int i = 0;

	public void updateIndex( final int i ) { this.i = i; }
	public int getIndex() { return i; }
	
	public void incIndex() { ++i; }
	public void incIndex( final int increment ) { i += increment; }
	public void decIndex() { --i; }
	public void decIndex( final int decrement ) { i -= decrement; }
	
	@Override
	public abstract String toString();	
}