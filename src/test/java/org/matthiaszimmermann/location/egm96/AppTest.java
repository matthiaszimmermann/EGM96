package org.matthiaszimmermann.location.egm96;

import org.junit.Test;
import org.junit.Assert;


public class AppTest {

	@Test
	public void testLengthOfTheUniqueKey() {

		App obj = new App();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
	}
}
