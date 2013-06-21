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
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * A runner to control the order of execution for JUnit test methods.<br/>
 * 
 * <h1>Example:</h1>
 * <pre>
 *	&#64;RunWith(value=OrderedRunner.class)
 *	public class TestOrdered extends BaseTest 
 *	{
 * 		&#64;Test &#64;Ordered(index = 3)
 * 		public void test3() 
 * 		{
 * 			broadcast();
 *		}
 * 		&#64;Test &#64;Ordered(index = 2)
 * 		public void test2a() 
 * 		{
 * 			broadcast();
 * 		}
 * 		&#64;Test &#64;Ordered(index = 2)
 * 		public void test2b() 
 * 		{
 * 			broadcast();
 * 		}
 * 		&#64;Test &#64;Ordered(index = 1)
 * 		public void test1() 
 * 		{
 * 			broadcast();
 * 		}
 * 	}
 * </pre>
 * Will produce the output:
 * <pre>
 * IN test1
 * IN test2a
 * IN test2b
 * IN test3
 * </pre>
 * 
 * @author Philip Diffenderfer
 *
 */
public class OrderedRunner extends BlockJUnit4ClassRunner 
{

	/**
	 * Instantiates a new OrderedRunner
	 * 
	 * @param klass
	 * 		The class being ran.
	 * @throws InitializationError
	 * 		An error occurred initializing the runner.
	 */
	public OrderedRunner(Class<?> klass) throws InitializationError 
	{
		super(klass);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<FrameworkMethod> computeTestMethods() 
	{
		List<FrameworkMethod> list = super.computeTestMethods();
		List<FrameworkMethod> copy = new ArrayList<FrameworkMethod>(list);
		Collections.sort(copy, new Comparator<FrameworkMethod>() {
			// Compare one method to another.
			public int compare(FrameworkMethod o1, FrameworkMethod o2) {
				Ordered ot1 = o1.getAnnotation(Ordered.class);
				Ordered ot2 = o2.getAnnotation(Ordered.class);
				// Both must be non-null
				if (ot1 != null && ot2 != null) {
					return ot1.index() - ot2.index();
				}
				// Else consider them unorderable
				return 0;
			}
		});
		return copy;
	}

}