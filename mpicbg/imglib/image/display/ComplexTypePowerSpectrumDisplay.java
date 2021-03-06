/**
 * Copyright (c) 2009--2010, Stephan Preibisch & Stephan Saalfeld
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the Fiji project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Stephan Preibisch & Stephan Saalfeld
 */
package mpicbg.imglib.image.display;

import mpicbg.imglib.cursor.Cursor;
import mpicbg.imglib.image.Image;
import mpicbg.imglib.type.numeric.ComplexType;

public class ComplexTypePowerSpectrumDisplay<T extends ComplexType<T>> extends Display<T>
{
	public ComplexTypePowerSpectrumDisplay( final Image<T> img )
	{
		super(img);
	}	

	@Override
	public void setMinMax()
	{
		final Cursor<T> c = img.createCursor();
		
		if ( !c.hasNext() )
		{
			min = -Float.MAX_VALUE;
			max = Float.MAX_VALUE;
			return;
		}
		
		c.fwd();
		min = max = getComplexDisplayValue( c.getType() );

		while ( c.hasNext() )
		{
			c.fwd();

			final double value = getComplexDisplayValue( c.getType() );
			
			if ( value > max )
				max = value;			
			else if ( value < min )
				min = value;
		}
		
		c.close();
	}
	
	protected float getComplexDisplayValue( final T c ) { return c.getPowerFloat();	}	

	@Override
	public float get32Bit( final T c ) { return getComplexDisplayValue( c ); }
	@Override
	public float get32BitNormed( final T c ) { return normFloat( getComplexDisplayValue( c ) ); }
	
	@Override
	public byte get8BitSigned( final T c) { return (byte) Math.round( normFloat( getComplexDisplayValue( c ) ) * 255 ); }
	@Override
	public short get8BitUnsigned( final T c) { return (short)Math.round( normFloat( getComplexDisplayValue( c ) ) * 255 ); }		
	
}
