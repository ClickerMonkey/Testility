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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.magnos.test.BaseTest;
import org.magnos.test.Ordered;
import org.magnos.test.OrderedRunner;

/**
 * 
 * @author Philip Diffenderfer
 *
 */
@RunWith(value=OrderedRunner.class)
public class TestOrdered extends BaseTest 
{

	@Test @Ordered(index = 3)
	public void test3() 
	{
		broadcast();
	}

	@Test @Ordered(index = 2)
	public void test2a() 
	{
		broadcast();
	}

	@Test @Ordered(index = 2)
	public void test2b() 
	{
		broadcast();
	}

	@Test @Ordered(index = 1)
	public void test1() 
	{
		broadcast();
	}
	
}
