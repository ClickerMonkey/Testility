/* 
 * NOTICE OF LICENSE
 * 
 * This source file is subject to the Open Software License (OSL 3.0) that is 
 * bundled with this package in the file LICENSE.txt. It is also available 
 * through the world-wide-web at http://opensource.org/licenses/osl-3.0.php
 * If you did not receive a copy of the license and are unable to obtain it 
 * through the world-wide-web, please send an email to pdiffenderfer@gmail.com 
 * so we can send you a copy immediately. If you use any of this software please
 * notify me via my website or email, your feedback is much appreciated. 
 * 
 * @copyright   Copyright (c) 2011 Magnos Software (http://www.magnos.org)
 * @license     http://opensource.org/licenses/osl-3.0.php
 * 				Open Software License (OSL 3.0)
 */

package org.magnos.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


/**
 * The base class for unit testing.
 * 
 * @author Philip Diffenderfer
 *
 */
public class BaseTest 
{

	// A random number generator.
	protected Random rnd = new Random();
	
	// A stopwatch for tracking time. If several threads will be tracking time
	// they each must instantiate they're own Stopwatch.
	protected Stopwatch watch = new Stopwatch();

	/**
	 * Pauses the invoking thread by the specified milliseconds.
	 * 
	 * @param millis
	 * 		The amount of time to pause the threads execution.
	 */
	protected static final void sleep(long millis) 
	{
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A task which runs concurrently with a set of tasks.
	 * 
	 * @author Philip Diffenderfer
	 *
	 */
	protected static class GroupTask implements Runnable
	{
		private Runnable runner;
		private GroupTask(Runnable runnable) {
			runner = runnable;
		}
		public void run() {
			try {
				startBarrier.await();	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			runner.run();
			stopBarrier.countDown();
		}

		private static CyclicBarrier startBarrier;
		private static CountDownLatch stopBarrier;
		private static List<GroupTask> groupList = new ArrayList<GroupTask>();

		public static void initialize(int groups) {
			groupList.clear();
			startBarrier = new CyclicBarrier(groups + 1);
			stopBarrier = new CountDownLatch(groups);
		}
		public static void add(Runnable runnable) {
			groupList.add(new GroupTask(runnable));
		}
		public static void add(Runnable runnable, int times) {
			while (--times >= 0) {
				add(runnable);
			}
		}
		public static void begin() {
			for (GroupTask task : groupList) {
				new Thread(task).start();
			}
			try {
				startBarrier.await();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void finish() {
			try {
				stopBarrier.await();	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void execute() {
			begin();
			finish();
		}
	}

	/**
	 * Broadcasts the name of the calling method.
	 */
	protected void broadcast() 
	{
		Exception e = new Exception();
		StackTraceElement[] trace = e.getStackTrace();
		System.out.format("IN %s\n", trace[1].getMethodName());
	}
	
	/**
	 * A simple Stopwatch class.
	 * 
	 * @author Philip Diffenderfer
	 *
	 */
	public static class Stopwatch 
	{
		// The time (in nanoseconds) the watch was started.
		private long startTime;
		private long endTime;
	
		/**
		 * Starts the stop watch. 
		 */
		public void start() {
			startTime = System.nanoTime();
			endTime = Long.MAX_VALUE;
		}
		/**
		 * Starts the stop watch after displaying the given formatted message.
		 */
		public void start(String format, Object ... args) {
			System.out.format(format, args);
			start();
		}
		/**
		 * Returns how many milliseconds have elapsed since the watch was started.
		 */
		public long millis() {
			return (Math.min(endTime, System.nanoTime()) - startTime) / 1000000L;
		}
		/**
		 * Returns how many nanoseconds have elapsed since the watch was started.
		 */
		public long nanos() {
			return (Math.min(endTime, System.nanoTime()) - startTime);
		}
		/**
		 * Returns how many seconds have elapsed since the watch has started.
		 */
		public double seconds() {
			return (Math.min(endTime, System.nanoTime()) - startTime) * 0.000000001;
		}
		/**
		 * Stops the stop watch.
		 */
		public void stop() {
			endTime = System.nanoTime();
		}
		/**
		 * Stops the stop watch and display a formatted message with the elapsed
		 * time in seconds.
		 */
		public void stop(String message) {
			stop();
			System.out.format(message, seconds());
		}
	}



}
