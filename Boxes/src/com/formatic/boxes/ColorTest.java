package com.formatic.boxes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ColorTest {
	Color color;

	@Before
	public void setUp() throws Exception {
		color = new Color(Color.Mode.HSB);
	}

	@After
	public void tearDown() throws Exception {
		color = null;
	}

	@Test
	public void test1() {
		color.setSaturation(1.0f);
		color.setBrightness(1.0f);
		for (int n = 0; n < 360; n++) {
			color.setHue(((float) (n)) * 1 / 360);
			for (int m = 0; m < 100; m++) {
				color.setSaturation(((float) (m)) / 100);
				System.out.println(color.getHue() + ","+color.getSaturation()+":" + color.r + ","
						+ color.g + "," + color.b);
			}
		}

	}

}
