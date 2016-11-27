package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestSpring extends UnitTestBase {
	public TestSpring() {
		super("dispatcherServlet-servlet1.xml");
	}

	@Test
	public void test() {
		Cont cont = super.getBean(Cont.class);
		cont.abc();
	}
}
