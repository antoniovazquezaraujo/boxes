/*
	                          BOXES
	              A minimalist color box framework
	         Copyright (C) 2014 Antonio Vazquez Araujo
	          (antoniovazquezaraujo[at]gmail[dot]com)
	          
	This file is part of Boxes.

    Boxes is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Boxes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Boxes.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.formatic.boxes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;
import com.formatic.boxes.widgets.Box;
import com.formatic.boxes.widgets.BoxList;
import com.formatic.boxes.widgets.BoxList.LayoutType;
import com.formatic.boxes.widgets.TextBox;

public class ScreenTest {

	ScreenModel screen;

	@Before
	public void setup() {
		screen = new ScreenModel();
	}

	@After
	public void clear() {
		screen = null;
	}

	@Test
	public void testSetClip() {
		Dimension dimension = new Dimension(2, 2);
		Point position = new Point(0, 0);
		screen.setPointAndSize(position, dimension);
		assertTrue(position.x == screen.getPosition().x);
		assertTrue(position.y == screen.getPosition().y);
		assertTrue(dimension.width == screen.getSize().width);
		assertTrue(dimension.height == screen.getSize().height);
	}

	@Test
	public void testGetColor() {
		Color c = new Color(1, 2, 3, 4);
		screen.setColor(c);
		assertTrue(c.equals(screen.getColor()));
	}

	@Test
	public void testContains() {
		Point v = new Point(7, 7);
		Box b = new Box(8, 8, 1, 1, new Color(0, 0, 0, 1));
		assertFalse(b.contains(v));
		v.x++;
		v.y++;
		assertTrue(b.contains(v));
		v.x++;
		v.y++;
		assertFalse(b.contains(v));
	}

	@Test
	public void testDrawIntInt() {
		Color c = new Color(0, 0, 1, 1);
		screen.setColor(c);
		screen.fill(0, 0, 3, 3);
		assertTrue(screen.getColor(2, 2).equals(c));
	}

	@Test
	public void testDrawIntIntColor() {
		Color c = new Color(0, 0, 1, 1);
		screen.fill(0, 0, 3, 3, c);
		assertTrue(screen.getColor(2, 2).equals(c));
	}

	@Test
	public void testDrawIntIntIntInt() {
		Color c = new Color(0, 0, 1, 1);
		screen.setColor(c);
		screen.fill(0, 0, 2, 2);
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				assertTrue(screen.getColor(x, y).equals(c));
			}
		}
	}

	@Test
	public void testDrawIntIntIntIntColor() {
		Color rare = new Color(1, 1, 1, 1);
		screen.setColor(rare);

		Color c = new Color(0, 0, 1, 1);
		screen.fill(0, 0, 2, 2, c);
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				assertTrue(screen.getColor(x, y).equals(c));
			}
		}
		assertTrue(screen.getColor().equals(rare));
	}

	@Test
	public void testCut() {
		screen.fill(0, 0, 8, 8, new Color(0, 0, 0, 1));
		Dimension d = new Dimension(2, 2);
		Point p = new Point(3, 3);
		screen.applyClip(p, d);
		Color c = new Color(0, 0, 1, 1);
		screen.fill(0, 0, 8, 8, c);
		for (int x = 3; x < 5; x++) {
			for (int y = 3; y < 5; y++) {
				assertTrue(screen.getColor(x, y).equals(c));
			}
		}
	}

	@Test
	public void testCut2() {
		screen.fill(0, 0, 8, 8, new Color(0, 0, 0, 0));
		Color c = new Color(1, 1, 1, 1);
		screen.fill(0, 0, 1, 1, c);
		assertTrue(screen.getColor(0, 0).equals(c));
		Dimension d = new Dimension(1, 1);
		Point p = new Point(3, 3);
		screen.applyClip(p, d);
		screen.fill(0, 0, 1, 1, c);
		assertTrue(screen.getColor(3, 3).equals(c));
	}

	@Test
	public void testCut3() {
		Color red = new Color(1, 0, 0, 1);
		Color green = new Color(0, 1, 0, 1);
		screen.fill(0, 0, 8, 8, red);
		assertTrue(screen.getColor(7, 7).equals(red));

		Dimension d = new Dimension(3, 3);
		Point p = new Point(3, 3);
		screen.applyClip(p, d);
		assertTrue(screen.getColor(7, 7).equals(red));
		screen.fill(0, 0, 8, 8, green);
		assertTrue(screen.getColor(7, 7).equals(red));

		assertTrue(screen.getColor(0, 0).equals(red));
		assertTrue(screen.getColor(3, 3).equals(green));
		assertTrue(screen.getColor(5, 5).equals(green));
	}

	@Test
	public void testCut4() {
		screen.fill(0, 0, 8, 8, new Color(0, 0, 0, 0));
		Dimension d = new Dimension(7, 7);
		Point p = new Point(1, 1);
		screen.applyClip(p, d);
		Dimension d2 = new Dimension(4, 4);
		Point p2 = new Point(1, 1);
		screen.applyClip(p2, d2);
		Color c = new Color(1, 1, 1, 1);
		screen.fill(0, 0, 8, 8, c);
		assertTrue(screen.getColor(4, 4).equals(c));
		assertTrue(screen.getColor(4, 5).equals(c));
		assertTrue(screen.getColor(5, 4).equals(c));
		assertTrue(screen.getColor(5, 5).equals(c));
		screen.resetPositionAndSize();
		screen.fill(0, 0, 8, 8, c);
		assertTrue(screen.getColor(0, 0).equals(c));
		assertTrue(screen.getColor(0, 7).equals(c));
		assertTrue(screen.getColor(7, 0).equals(c));
		assertTrue(screen.getColor(7, 7).equals(c));
	}

	@Test
	public void testCut5() {
		ScreenModel model = new ScreenModel();
		model.setPosition(new Point(3, 3));
		Point p = new Point(2, 2);
		model.translate(p);
		assertTrue(p.equals(new Point(5, 5)));
		model.setPosition(new Point(0, 0));
		model.applyClip(3, 3, 4, 4);
		assertTrue(model.getSize().equals(new Dimension(4, 4)));
		assertTrue(model.getPosition().equals(new Point(3, 3)));
		model.fill(1, 1, 2, 2, new Color(1, 1, 1, 1));
		assertTrue(model.getColor(4, 4).equals(new Color(1, 1, 1, 1)));
		assertFalse(model.getColor(5, 5).equals(new Color(1, 1, 1, 1)));
		assertFalse(model.getColor(6, 6).equals(new Color(1, 1, 1, 1)));
		// System.out.println (model);

	}

	@Test
	public void testCut6() {
		Color red = new Color(1, 0, 0, 1);
		Color green = new Color(0, 1, 0, 1);
		Color blue = new Color(0, 0, 1, 1);

		BoxList topBox;
		topBox = new BoxList(LayoutType.VERTICAL);
		topBox.setColor(red);
		Dimension r = new Dimension(8, 8);
		assertTrue(topBox.getSize().equals(r));

		topBox.add(new Box(2, 2, green));
		r.setSize(2, 2);
		assertTrue(topBox.getSize().equals(r));
		assertTrue(topBox.get(0).getSize().equals(r));

		topBox.add(new Box(2, 2, blue));
		r.setSize(2, 4);
		assertTrue(topBox.getSize().equals(r));

		r.setSize(2, 2);
		assertTrue(topBox.get(0).getSize().equals(r));
		r.setSize(2, 2);
		assertTrue(topBox.get(0).getSize().equals(r));

		topBox.add(new Box(2, 2, red));
		r.setSize(2, 2);
		assertTrue(topBox.get(2).getSize().equals(r));

	}

	@Test
	public void testCut7() {
		Color red = new Color(1, 0, 0, 1);
		Color green = new Color(0, 1, 0, 1);
		Color blue = new Color(0, 0, 1, 1);

		BoxList topBox;
		topBox = new BoxList();
		topBox.setColor(red);
		topBox.setSize(8, 8);
		TextBox t = new TextBox(Font.TYPE_33, "A");
		t.setPosition(0, 0);
		topBox.add(t);
		assertTrue(topBox.get(0).getPosition().x == 0);
		assertTrue(topBox.get(0).getPosition().y == 0);
		assertTrue(topBox.get(0).getSize().width == 3);
		assertTrue(topBox.get(0).getSize().height == 3);

		ScreenModel screenModel = new ScreenModel();
		for (Box box : topBox.boxes) {
			screenModel.applyClip(box.getPosition(), box.getSize());
			assertTrue(box.getPosition().x == 0);
			assertTrue(screenModel.getSize().width == 3);
			assertTrue(screenModel.getSize().height == 3);
			box.update(screenModel);
			screenModel.resetPositionAndSize();
		}
		t.setPosition(-1, 0);
		screenModel = new ScreenModel();
		for (Box box : topBox.boxes) {
			screenModel.applyClip(box.getPosition(), box.getSize());
			assertTrue(box.getPosition().x == -1);
			assertTrue(box.getSize().width == 3);
			assertTrue(screenModel.getPosition().x == -1);
			assertTrue(screenModel.getSize().width == 3);
			assertTrue(screenModel.getSize().height == 3);
			box.update(screenModel);
			screenModel.resetPositionAndSize();
			System.out.println(screenModel);
		}

	}

	@Test
	public void testLayout1() {
		Color red = new Color(1, 0, 0, 1);
		Color green = new Color(0, 1, 0, 1);
		Color blue = new Color(0, 0, 1, 1);

		BoxList topBox;
		topBox = new BoxList(LayoutType.VERTICAL);
		topBox.setColor(red);

		topBox.add(new Box(2, 2, green));

		assertTrue(topBox.get(0).getPosition().equals(new Point(0, 0)));

		BoxList topBox2;
		topBox2 = new BoxList(LayoutType.VERTICAL);
		topBox2.setColor(red);
		topBox2.add(new Box(2, 2, blue));
		topBox.add(topBox2);

		topBox2.add(new Box(2, 2, red));
		assertTrue(topBox2.get(1).getPosition().equals(new Point(0, 2)));
		assertTrue(topBox2.get(1).getSize().equals(new Dimension(2, 2)));

		assertTrue(topBox2.get(0).getPosition().equals(new Point(0, 0)));
		assertTrue(topBox2.get(0).getSize().equals(new Dimension(2, 2)));

		ScreenModel screenModel = new ScreenModel();
		for (Box box : topBox.boxes) {
			// System.out.println(screenModel);
			screenModel.applyClip(box.getPosition(), box.getSize());
			box.update(screenModel);
			// System.out.println(screenModel);
			screenModel.resetPositionAndSize();
		}

	}

	@Test
	public void testFont1() {
		Font f = new Font(Font.TYPE_24);
		assertTrue(f.getWidth() == 2);
		assertTrue(f.getHeight() == 4);
		f = new Font(Font.TYPE_57);
		assertTrue(f.getWidth() == 5);
		assertTrue(f.getHeight() == 7);

	}

	@Test
	public void outOfScreen() {
		Color red = new Color(1, 0, 0, 1);
		Color green = new Color(0, 1, 0, 1);
		Color blue = new Color(0, 0, 1, 1);

		BoxList topBox;
		topBox = new BoxList(LayoutType.VERTICAL);
		topBox.setColor(red);
		Dimension r = new Dimension(8, 8);
		assertTrue(topBox.getSize().equals(r));

		topBox.setSize(9, 9);
		assertTrue(topBox.getSize().equals(new Dimension(9, 9)));

		topBox.setSize(0, 0);
		assertTrue(topBox.getSize().equals(new Dimension(0, 0)));

		Box b = new Box(10, 10);
		topBox.add(b);
		assertTrue(topBox.getSize().equals(new Dimension(10, 10)));

		b.setSize(1, 1);
		assertTrue(topBox.getSize().equals(new Dimension(10, 10)));

		topBox.layout();
		assertTrue(topBox.getSize().equals(new Dimension(1, 1)));

		Box b2 = new Box(1, 1);
		topBox.add(b2);
		assertTrue(topBox.getSize().equals(new Dimension(1, 2)));

		BoxList l2 = new BoxList();
		assertTrue(l2.getSize().equals(new Dimension(8, 8)));

		Box b3 = new Box(10, 10, 10, 10, green);
		l2.add(b3);
		assertTrue(l2.getSize().equals(new Dimension(8, 8)));

		b3.setSize(5, 5);
		l2.layout();
		assertTrue(l2.getSize().equals(new Dimension(8, 8)));

	}

}
