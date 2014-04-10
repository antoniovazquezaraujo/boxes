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
package com.formatic.boxes.games;

import com.badlogic.gdx.graphics.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.commands.ChangeCommand;
import com.formatic.boxes.widgets.Box;
import com.formatic.boxes.widgets.BoxContainer;
import com.formatic.boxes.widgets.events.BoxEventListener;

enum ChessColor {
	WHITE, BLACK
}

class Piece extends Box {
	protected int changeTarget;
	private Board board;
	ChessColor chessColor;

	Piece(int x, int y, ChessColor color) {
		setX(x);
		setY(y);
		setColor(new Color(0, 0, 0, 1));
		chessColor = color;
		changeTarget = chessColor == ChessColor.WHITE	? ChangeCommand.COLOR_R
														: ChangeCommand.COLOR_G;
		addAnimations();
	}

	ChessColor getPieceColor() {
		return chessColor;
	}

	void addAnimations() {

	}

	public void setBoard(Board board) {
		this.board = board;
	}

}

class King extends Piece {
	King(int x, int y, ChessColor color) {
		super(x, y, color);
		setAlpha(1.0f);
	}

	@Override
	void addAnimations() {
		int times = 1;
		float rangeTime = 1;
		float fromValue = 0.7f;
		float toValue = 0.7001f;
		int step = 0;
		ChangeCommand change = new ChangeCommand(times, rangeTime, fromValue,
													toValue, step, changeTarget);
		addCommand(change);
	}
}

class Queen extends Piece {
	Queen(int x, int y, ChessColor color) {
		super(x, y, color);
	}

	@Override
	void addAnimations() {
		int times = -1;
		float rangeTime = 50;
		float fromValue = 0.7f;
		float toValue = 0.5f;
		int step = 1;
		ChangeCommand change = new ChangeCommand(times, rangeTime, fromValue,
													toValue, step, changeTarget);
		addCommand(change);
	}

}

class Horse extends Piece {
	Horse(int x, int y, ChessColor color) {
		super(x, y, color);
		setAlpha(0.5f);
	}

	@Override
	void addAnimations() {
		int times = 3;
		float rangeTime = 0.1f;
		float fromValue = 0.7f;
		float toValue = 0.7001f;
		int step = 1;
		ChangeCommand change = new ChangeCommand(times, rangeTime, fromValue,
													toValue, step, changeTarget);
		addCommand(change);
		fromValue = 0.5001f;
		toValue = 0.5f;
		change = new ChangeCommand(times, rangeTime, fromValue, toValue, step,
									changeTarget);
		addCommand(change);
	}

}

class Tower extends Piece {
	Tower(int x, int y, ChessColor color) {
		super(x, y, color);
		setAlpha(1.0f);
	}

	@Override
	void addAnimations() {
		int times = 2;
		float rangeTime = 20;
		float fromValue = 0.7f;
		float toValue = 0.7001f;
		int step = 0;
		ChangeCommand change = new ChangeCommand(times, rangeTime, fromValue,
													toValue, step, changeTarget);
		addCommand(change);
		fromValue = 0.5001f;
		toValue = 0.5f;
		change = new ChangeCommand(times, rangeTime, fromValue, toValue, step,
									changeTarget);
		addCommand(change);

	}

}

class Pawn extends Piece {
	Pawn(int x, int y, ChessColor color) {
		super(x, y, color);
		setAlpha(0.5f);
	}

	@Override
	void addAnimations() {
		int times = 1;
		float rangeTime = 1;
		float fromValue = 0.7f;
		float toValue = 0.7001f;
		int step = 0;
		ChangeCommand change = new ChangeCommand(times, rangeTime, fromValue,
													toValue, step, changeTarget);
		addCommand(change);

	}

}

class Bishop extends Piece {

	Bishop(int x, int y, ChessColor color) {
		super(x, y, color);
		setAlpha(1.0f);
	}

	@Override
	void addAnimations() {
		int times = 1;
		float rangeTime = 150;
		float fromValue = 0.8f;
		float toValue = 0.4f;
		int step = 1;
		ChangeCommand change = new ChangeCommand(times, rangeTime, fromValue,
													toValue, step, changeTarget);
		addCommand(change);
		toValue = 0.8f;
		fromValue = 0.4f;

		change = new ChangeCommand(times, rangeTime, fromValue, toValue, step,
									changeTarget);
		addCommand(change);

	}

}

class CheckersBoard extends BoxContainer {
	Color black = new Color(0.8f, 0.8f, 0.8f, 1);
	Color white = new Color(0.9f, 0.9f, 0.9f, 1);

	public CheckersBoard() {
		boolean flip = true;
		for (int row = 0; row < 8; row++) {
			flip = !flip;
			for (int col = 0; col < 8; col++) {
				add(new Box(col, row, 1, 1, flip ? black : white));
				flip = !flip;
			}
		}
	}

	@Override
	public Box boxAtPos(Point p) {
		return null;
	}

}

class Board extends BoxContainer {
	CheckersBoard checkersBoard;

	public void addPiece(Piece piece) {
		super.add((Box) piece);
		piece.setBoard(this);
	}

	public void removePiece(Piece p) {
		Piece atPos = pieceAtPos(p.getPosition().x, p.getPosition().y);
		if (atPos != null) {
			remove(atPos);
		}
	}

	Board() {
		checkersBoard = new CheckersBoard();
		add(checkersBoard);
		addPiece(new Tower(0, 0, ChessColor.WHITE));
		addPiece(new Tower(0, 7, ChessColor.BLACK));
		addPiece(new Tower(7, 0, ChessColor.WHITE));
		addPiece(new Tower(7, 7, ChessColor.BLACK));
		addPiece(new Horse(1, 0, ChessColor.WHITE));
		addPiece(new Horse(1, 7, ChessColor.BLACK));
		addPiece(new Horse(6, 0, ChessColor.WHITE));
		addPiece(new Horse(6, 7, ChessColor.BLACK));
		addPiece(new Bishop(2, 0, ChessColor.WHITE));
		addPiece(new Bishop(2, 7, ChessColor.BLACK));
		addPiece(new Bishop(5, 0, ChessColor.WHITE));
		addPiece(new Bishop(5, 7, ChessColor.BLACK));

		addPiece(new King(3, 0, ChessColor.WHITE));
		addPiece(new King(3, 7, ChessColor.BLACK));
		addPiece(new Queen(4, 0, ChessColor.WHITE));
		addPiece(new Queen(4, 7, ChessColor.BLACK));

		for (int n = 0; n < 8; n++) {
			addPiece(new Pawn(n, 1, ChessColor.WHITE));
			addPiece(new Pawn(n, 6, ChessColor.BLACK));
		}
		setBoxEventListener(new BoxEventListener() {

			private Piece movingPiece;

			@Override
			public boolean onZoomOut(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onTouchCancel(int x, int y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onTouch(int x, int y) {
				Piece p = pieceAtPos(x, y);
				if (p != null) {
					movingPiece = p;
				}
				return false;
			}

			@Override
			public boolean onRelease(int x, int y) {
				movingPiece = null;
				return false;
			}

			@Override
			public boolean onDrag(int x, int y, int newX, int newY) {
				if (movingPiece != null) {
					Piece enemy = pieceAtPos(newX, newY);
					if (enemy != null) {
						if (enemy.getPieceColor() != movingPiece.getPieceColor()) {
							removePiece(enemy);
							movingPiece.setX(newX);
							movingPiece.setY(newY);
						}
					}else{
						movingPiece.setX(newX);
						movingPiece.setY(newY);
					}
				}
				return false;
			}
		});
	}

	Piece pieceAtPos(int x, int y) {
		Box p = boxAtPos(new Point(x, y));
		if (p == checkersBoard) {
			return null;
		}
		return (Piece) p;
	}
}

public class Cheso extends BoxContainer {
	public Cheso() {
		add(new Board());
	}
}
