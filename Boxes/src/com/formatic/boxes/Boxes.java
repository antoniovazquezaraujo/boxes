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

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.formatic.boxes.widgets.Selector;

public class Boxes implements ApplicationListener {
	ScreenModel screenModel;
	ScreenRenderer screenRenderer;
	ScreenController screenController;
	public static Selector topBox;
	GestureDetector gestureDetector;

	@Override
	public void create() {
		screenModel = new ScreenModel();
		topBox = new Selector();
		Demo d = new Demo();
		d.populate(topBox);

		screenRenderer = new ScreenRenderer(screenModel);
		screenController = new ScreenController(screenRenderer, topBox);
		setGestureDetector();

	}

	private void setGestureDetector() {
		gestureDetector = new GestureDetector(1, 0.1f, 1.1f, 10000,
												screenController);
		Gdx.input.setInputProcessor(gestureDetector);

	}

	@Override
	public void dispose() {
		screenRenderer.dispose();
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render() {
		//screenModel.clear();
		topBox.update(screenModel);
		screenRenderer.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
