
abstract class ColorGradient {
	Point from, to;
	color fromColor, toColor;
	boolean repeats;
	float totalDistance;
	abstract color getColor(Point location);
	ColorGradient(Point from, Point to, color fromColor, color toColor, boolean repeats) {
		this.from = from;
		this.to = to;
		this.fromColor=fromColor;
		this.toColor = toColor;
		this.repeats = repeats;
	}
}
ColorGradient randomGradient(){
	ColorGradient ret=null;
	if(random(10) > 5){
		ret = new RadialGradient(randomPoint(), randomPoint(), randomColor(), randomColor(), false);
	}else{
		ret = new LinearGradient(randomPoint(), randomPoint(), randomColor(), randomColor(), false);
	}
	return ret;
}
class RadialGradient extends ColorGradient {
	RadialGradient(Point from, Point to, color fromColor, color toColor, boolean repeats) {
		super(from, to, fromColor, toColor, repeats);
		totalDistance = dist(from.x, from.y, to.x, to.y);
	}
	color getColor(Point location) {
		float distance = dist(location.x, location.y, from.x, from.y);
		float d = distance/totalDistance;
		float r = lerp(red(fromColor), red(toColor), d);
		float g = lerp(green(fromColor), green(toColor), d);
		float b = lerp(blue(fromColor), blue(toColor), d);
		return color(r, g, b); 
	}
}
class LinearGradient extends ColorGradient {
	LinearGradient(Point from, Point to, color fromColor, color toColor, boolean repeats) {
		super(from, to, fromColor, toColor, repeats);
		totalDistance = dist(from.x, from.y, to.x, to.y);
	}
	color getColor(Point location) {
		//float distance = dist(location.x, location.y, from.x, from.y);
		//Cómo calculo la distancia a la perpendicular???
		float distance = distToLine(from, to, location);
		float d= distance/totalDistance;
		float r= lerp(red(fromColor), red(toColor), d);
		float g= lerp(green(fromColor), green(toColor), d);
		float b= lerp(blue(fromColor), blue(toColor), d);
		return color(r, g, b);
	}
	float distToLine(Point p1, Point p2, Point p) {
		//          (y1-y2)x + (x2-x1)y + (x1y2-x2y1)
		//d(P,L) = --------------------------------
		//         sqrt( (x2-x1)pow2 + (y2-y1)pow2 )

		double ch = (p1.y - p2.y) * p.x + (p2.x - p1.x) * p.y + (p1.x * p2.y - p2.x * p1.y);
		double del = sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p1.y, 2));
		double d = ch / del;
		return (float)d;
	}
}

