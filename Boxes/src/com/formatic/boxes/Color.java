package com.formatic.boxes;

public class Color extends com.badlogic.gdx.graphics.Color{
	public static void main(String[] args) {
		Color c= new Color(Mode.HSB);
		c.setSaturation(0.5f);
		c.setBrightness(0.5f);
		for(int n=0; n<360; n++){
			c.setHue(((float)(n))*256/360);
			for(int s=0; s<100; s++){
				c.setSaturation(((float)(s))/100);
				for(int b=0; b<100; b++){
					c.setBrightness(((float)(b))/100);
					System.out.println(c.r+"\t"+c.g+"\t"+c.b+ "\t"+ c.a);
				}
			}
		}
	}
	public enum Mode {
		RGB, HSB
	}
	private Mode mode;
	private float hue, saturation, brightness;

	public Color(Color c){
		this.mode = c.mode;
		setRGB(c.r, c.g, c.b, c.a);
		updateFromRGBToHSB();
	}
	public Color(float r, float g, float b, float a) {
		setRGB(r, g, b, a);
		updateFromRGBToHSB();
	}
	
	public Color(float d1, float d2, float d3){
		this(d1,d2,d3,Mode.RGB);
	}

	public Color(float d1, float d2, float d3, Mode mode){
		this.mode=mode;
		set(d1,d2,d3);
	}
	public Color(Mode mode){
		this.mode = mode;
		hue= saturation= brightness=0.0f;
		r=g=b=0.0f;
		a=1.0f;
	}

	public void setHue(float hue){
		this.hue=hue;
		this.mode=Mode.HSB;
		updateFromHSBToRGB();
	}
	public float getHue(){
		return hue;
	}
	public void setSaturation(float saturation){
		this.saturation = saturation;
		this.mode=Mode.HSB;
		updateFromHSBToRGB();
	}
	public float getSaturation(){
		return saturation;
	}
	public void setBrightness(float brightness){
		this.brightness=brightness;
		this.mode=Mode.HSB;
		updateFromHSBToRGB();
	}
	public float getBrightness(){
		return brightness;
	}
	public void setR(float r){
		this.mode=Mode.RGB;
		this.r=r;
	}
	public void setG(float g){
		this.mode=Mode.RGB;
		this.g=g;
	}
	public void setB(float b){
		this.mode=Mode.RGB;
		this.b=b;
	}
	
	public void set(float d1, float d2, float d3){
		switch (mode) {
		case RGB:
			setRGB(d1, d2, d3);
			updateFromRGBToHSB();
			break;
		case HSB:
			setHSB(d1, d2, d3);
			updateFromHSBToRGB();
			break;
		}		
	}
	private void setHSB(float hue, float saturation, float brightness) {
		this.mode = Mode.HSB;
		this.hue=hue;
		this.saturation=saturation;
		this.brightness=brightness;
	}
	private void setRGB(float r, float g, float b) {
		setRGB(r,g,b,1.0f);
	}
	private void setRGB(float r, float g, float b, float a) {
		this.mode=Mode.RGB;
		super.set(r, g, b, a);
	}

	public void updateFromHSBToRGB() {
		assert(hue<=1.0f);
		assert(saturation<=1.0f);
		assert(brightness<=1.0f);
		assert(hue>=0.0f);
		assert(saturation>=0.0f);
		assert(brightness>=0.0f);
		float h = (hue - (float) Math.floor(hue)) * 6.0f;
		float f = h - (float) java.lang.Math.floor(h);
		float p = brightness * (1 - saturation);
		float q = brightness * (1 - f * saturation);
		float t = brightness * (1 - (1 - f) * saturation);

		switch ((int) h) {
		case 0:
			setRGB(brightness, t, p);
			break;
		case 1:
			setRGB(q, brightness, p);
			break;
		case 2:
			setRGB(p, brightness, t);
			break;
		case 3:
			setRGB(p, q, brightness);
			break;
		case 4:
			setRGB(t, p, brightness);
			break;
		case 5:
			setRGB(brightness, p, q);
			break;
		default:
			throw new RuntimeException(
					"Something went wrong when converting from HSV to RGB. Input was "
							+ hue + ", " + saturation + ", " + brightness);
		}
	}
	public void updateFromRGBToHSB() {
		float cmax = (r > g) ? r : g;
		if (b > cmax)
			cmax = b;
		float cmin = (r < g) ? r : g;
		if (b < cmin)
			cmin = b;

		brightness = ((float) cmax);// /100.0f;
		if (cmax != 0)
			saturation = ((float) (cmax - cmin)) / ((float) cmax);
		else
			saturation = 0;
		if (saturation == 0)
			hue = 0;
		else {
			float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
			float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
			float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
			if (r == cmax)
				hue = bluec - greenc;
			else if (g == cmax)
				hue = 2.0f + redc - bluec;
			else
				hue = 4.0f + greenc - redc;
			hue = hue / 6.0f;
			if (hue < 0)
				hue = hue + 1.0f;
		}
		assert(hue<=1.0f);
		assert(saturation<=1.0f);
		assert(brightness<=1.0f);
		assert(hue>=0.0f);
		assert(saturation>=0.0f);
		assert(brightness>=0.0f);

	}	
}
