package listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestNGListeners.class)
public class TestNgListenersDemo {
	
	@Test
	public void test1() {
		System.out.println("Inside test 1");
		Assert.assertEquals(false, true, "Failing test deliberately");
	}
	
	@Test
	public void test2() {
		System.out.println("Inside test 2");
	}
	
	@Test
	public void test3() {
		System.out.println("Inside test 3");
	}
}