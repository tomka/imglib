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

package mpicbg.imglib.multithreading;

public class SimpleMultiThreading
{
	/*
	final int start = 0;
	final int end = 10;

	final AtomicInteger ai = new AtomicInteger(start);

	Thread[] threads = newThreads();
	for (int ithread = 0; ithread < threads.length; ++ithread)
	{
		threads[ithread] = new Thread(new Runnable()
		{
			public void run()
			{
				// do something....
				// for example:
				for (int i3 = ai.getAndIncrement(); i3 < end; i3 = ai.getAndIncrement())
				{
				}
			}
		});
	}
	startAndJoin(threads);
	*/

	public static void startTask(Runnable run)
	{
		Thread[] threads = newThreads();

		for (int ithread = 0; ithread < threads.length; ++ithread)
			threads[ithread] = new Thread(run);

		startAndJoin(threads);
	}

	public static void startTask(Runnable run, int numThreads)
	{
		Thread[] threads = newThreads(numThreads);

		for (int ithread = 0; ithread < threads.length; ++ithread)
			threads[ithread] = new Thread(run);

		startAndJoin(threads);
	}

	public static Thread[] newThreads()
	{
	  int nthread = Runtime.getRuntime().availableProcessors();
	  return new Thread[nthread];
	}

	public static Thread[] newThreads(int numThreads)
	{
	  return new Thread[numThreads];
	}

	public static void startAndJoin(Thread[] threads)
	{
		for (int ithread = 0; ithread < threads.length; ++ithread)
		{
			threads[ithread].setPriority(Thread.NORM_PRIORITY);
			threads[ithread].start();
		}

		try
		{
			for (int ithread = 0; ithread < threads.length; ++ithread)
				threads[ithread].join();
		} catch (InterruptedException ie)
		{
			throw new RuntimeException(ie);
		}
	}

	public static void start(Thread[] threads)
	{
		for (int ithread = 0; ithread < threads.length; ++ithread)
		{
			threads[ithread].setPriority(Thread.MIN_PRIORITY);
			threads[ithread].start();
		}
	}
	
	public static void threadHaltUnClean()
	{
		int i = 0;
		
		while ( i == 0 ) {}
	}
	
	public static void threadWait( final long milliseconds )
	{
		try
		{
			Thread.sleep( milliseconds );
		}
		catch (final InterruptedException e)
		{
			System.err.println("MultiThreading.threadWait(): Thread woken up: " + e );
		}		
	}
}
