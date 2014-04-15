package com.formatic.boxes;

import static org.junit.Assert.*;

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
		for (int n = 0; n < 360; n+=60) {
			color.setHue(((float) (n)) * 1 / 360);
			System.out.println("hue:"+color.getHue()+"------------"); 
			for (int m = 0; m < 100; m+=25) {
				color.setSaturation(((float) (m)) / 100);
				System.out.println("Sat:"+color.getSaturation()+", Bri:"+color.getBrightness()+"   RGB:" + color.r + ","
						+ color.g + "," + color.b);
			}
		}

	}
	@Test
	public void test2() {
		float s = color.getSaturation();
		float b = color.getBrightness();
		float h = color.getHue();
		for (int n = 0; n < 360; n++) {
			color.setHue(((float) (n)) * 1 / 360);
			assertTrue(color.getSaturation() == s);
			assertTrue(color.getBrightness()== b);
		}
		h = color.getHue();
		for (int n = 0; n < 100; n++) {
			color.setSaturation(((float) (n)) * 1 / 100);
			assertTrue(color.getHue() == h);
			assertTrue(color.getBrightness()== b);
		}
		s = color.getSaturation();
		for (int n = 0; n < 100; n++) {
			color.setBrightness(((float) (n)) * 1 / 100);
			assertTrue(color.getSaturation() == s);
			assertTrue(color.getHue()== h);
		}
		
	}
	@Test
	public void test3() {
		Color a = new Color(1,0,0,0);
		Color b = new Color(a);
		assertTrue(b.getBrightness()==1.0f);
	}
}
