/**
 * Copyright (c) 2009--2010, Stephan Preibisch
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
 * @author Stephan Preibisch
 */
package mpicbg.imglib.cursor.dynamic;

import mpicbg.imglib.container.dynamic.DynamicContainer;
import mpicbg.imglib.cursor.LocalizableByDimCursor;
import mpicbg.imglib.cursor.Localizable;
import mpicbg.imglib.cursor.special.LocalNeighborhoodCursor;
import mpicbg.imglib.cursor.special.LocalNeighborhoodCursorFactory;
import mpicbg.imglib.cursor.special.RegionOfInterestCursor;
import mpicbg.imglib.image.Image;
import mpicbg.imglib.type.Type;

public class DynamicLocalizableByDimCursor<T extends Type<T>> extends DynamicLocalizableCursor<T> implements LocalizableByDimCursor<T>
{
	final protected int[] step;
	final int tmp[];
	
	int numNeighborhoodCursors = 0;
	
	public DynamicLocalizableByDimCursor( final DynamicContainer<T,?> container, final Image<T> image, final T type ) 
	{
		super( container, image, type );
		
		step = container.getSteps();
		tmp = new int[ numDimensions ];
	}	
	
	@Override
	public synchronized LocalNeighborhoodCursor<T> createLocalNeighborhoodCursor()
	{
		if ( numNeighborhoodCursors == 0)
		{
			++numNeighborhoodCursors;
			return LocalNeighborhoodCursorFactory.createLocalNeighborhoodCursor( this );
		}
		else
		{
			System.out.println("ArrayLocalizableByDimCursor.createLocalNeighborhoodCursor(): There is only one one special cursor per cursor allowed.");
			return null;
		}
	}

	@Override
	public synchronized RegionOfInterestCursor<T> createRegionOfInterestCursor( final int[] offset, final int[] size )
	{
		if ( numNeighborhoodCursors == 0)
		{
			++numNeighborhoodCursors;
			return new RegionOfInterestCursor<T>( this, offset, size );
		}
		else
		{
			System.out.println("ArrayLocalizableByDimCursor.createRegionOfInterestCursor(): There is only one special cursor per cursor allowed.");
			return null;
		}
	}
	
	@Override
	public void fwd( final int dim )
	{
		internalIndex += step[ dim ];
		accessor.updateIndex( internalIndex );

		++position[ dim ];	
	}

	@Override
	public void move( final int steps, final int dim )
	{
		internalIndex += step[ dim ] * steps;
		accessor.updateIndex( internalIndex );

		position[ dim ] += steps;	
	}
	
	@Override
	public void bck( final int dim )
	{
		internalIndex -= step[ dim ];
		accessor.updateIndex( internalIndex );
 
		--position[ dim ];
	}
		
	@Override
	public void moveRel( final int[] vector )
	{
		for ( int d = 0; d < numDimensions; ++d )
			move( vector[ d ], d );
	}

	@Override
	public void moveTo( final int[] position )
	{		
		for ( int d = 0; d < numDimensions; ++d )
		{
			final int dist = position[ d ] - getPosition( d );
			
			if ( dist != 0 )				
				move( dist, d );
		}
	}

	@Override
	public void moveTo( final Localizable localizable )
	{
		localizable.getPosition( tmp );
		moveTo( tmp );
	}

	@Override
	public void setPosition( final Localizable localizable )
	{
		localizable.getPosition( tmp );
		setPosition( tmp );
	}
	
	@Override
	public void setPosition( final int[] position )
	{
		internalIndex = container.getPos( position );
		accessor.updateIndex( internalIndex );
		
		for ( int d = 0; d < numDimensions; ++d )
			this.position[ d ] = position[ d ];		
	}

	@Override
	public void setPosition( final int position, final int dim )
	{
		this.position[ dim ] = position;

		internalIndex = container.getPos( this.position );
		accessor.updateIndex( internalIndex );
	}
}
