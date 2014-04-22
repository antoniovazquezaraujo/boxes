package com.formatic.boxes.gradients;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.formatic.boxes.Color;
import com.formatic.boxes.Point;
import com.formatic.boxes.gradients.ColorGradient.Repeatable;
import com.formatic.boxes.gradients.ColorGradient.Target;
import com.formatic.boxes.gradients.SquareGradient.ColumnOrder;
import com.formatic.boxes.gradients.SquareGradient.RowOrder;

public class SquareGradientTest {
	SquareGradient gradient;
	@Before
	public void setup() {
		gradient= new SquareGradient(new Point(0,0), new Point(6,6), 0, 1, Target.HUE, Repeatable.RESTARTING, 0.1f);
	}

	@After
	public void clear() {
		gradient = null;
	}
	@Test
	public void test1() {
//		gradient.setColumnOrder(ColumnOrder.FROM_CENTER);
//		gradient.setRowOrder(RowOrder.FROM_CENTER);
		gradient.setStartValue(0.5f);
		for(int col=0; col<6; col++){
			for(int row=0; row<6; row++){
				Color color = new Color(1,1,1, Color.Mode.HSB);
				gradient.update(new Point(col, row), color);
				//System.out.print(gradient.data[col][row]);
				System.out.printf("%f ", gradient.data[col][row]);
				System.out.printf("(%f %f %f)", color.r, color.g, color.b);
			}
			System.out.println();
		}
		
	}

}
