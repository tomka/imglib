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
package mpicbg.imglib.interpolation.linear;

import mpicbg.imglib.image.Image;
import mpicbg.imglib.interpolation.InterpolatorFactory;
import mpicbg.imglib.outofbounds.OutOfBoundsStrategyFactory;
import mpicbg.imglib.type.numeric.RealType;

public class LinearInterpolatorFactory<T extends RealType<T>> extends InterpolatorFactory<T>
{
	public LinearInterpolatorFactory( final OutOfBoundsStrategyFactory<T> outOfBoundsStrategyFactory )
	{
		super(outOfBoundsStrategyFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinearInterpolator<T> createInterpolator( final Image<T> img )
	{
		if ( img.getNumDimensions() == 1 )
		{
			return new LinearInterpolator1D<T>( img, this, outOfBoundsStrategyFactory );
		}
		else if ( img.getNumDimensions() == 2 )
		{
			return new LinearInterpolator2D<T>( img, this, outOfBoundsStrategyFactory );
		}
		else if ( img.getNumDimensions() == 3 )	
		{
			if ( RealType.class.isInstance( img.createType() ))
				/* inconvertible types due to javac bug 6548436: return (LinearInterpolator<T>)new LinearInterpolator3DFloat( (Image<FloatType>)img, (LinearInterpolatorFactory<FloatType>)this, (OutOfBoundsStrategyFactory<FloatType>)outOfBoundsStrategyFactory ); */
				return (LinearInterpolator<T>)new LinearInterpolator3DRealType( (Image)img, (LinearInterpolatorFactory)this, (OutOfBoundsStrategyFactory)outOfBoundsStrategyFactory );
			else
				return new LinearInterpolator3D<T>( img, this, outOfBoundsStrategyFactory );
		}
		else
		{
			return new LinearInterpolator<T>( img, this, outOfBoundsStrategyFactory );
		}
	}
}
