package com.formatic.boxes.widgets;

public class ColorName {
	ColorName(String name, float r, float g, float b) {
		this.name = name;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	String name;
	float r, g, b;
	static ColorName[] colorNames = {
			new ColorName("AliceBlue", 0.94f, 0.97f, 1.00f),
			new ColorName("AntiqueWhite", 0.98f, 0.92f, 0.84f),
			new ColorName("Aqua", 0.00f, 0.02f, 1.00f),
			new ColorName("Aquamarine", 0.50f, 1.00f, 0.83f),
			new ColorName("Azure", 0.94f, 1.00f, 1.00f),
			new ColorName("Beige", 0.96f, 0.96f, 0.86f),
			new ColorName("Bisque", 1.00f, 0.89f, 0.77f),
			new ColorName("Black", 0.00f, 0.00f, 0.00f),
			new ColorName("BlanchedAlmond", 1.00f, 0.92f, 0.80f),
			new ColorName("Blue", 0.00f, 1.00f, 1.00f),
			new ColorName("BlueViolet", 0.54f, 0.17f, 0.89f),
			new ColorName("Brown", 0.65f, 0.16f, 0.00f),
			new ColorName("BurlyWood", 0.87f, 0.72f, 0.53f),
			new ColorName("CadetBlue", 0.37f, 0.23f, 0.63f),
			new ColorName("Chartreuse", 0.50f, 1.00f, 0.02f),
			new ColorName("Chocolate", 0.82f, 0.41f, 0.00f),
			new ColorName("Coral", 1.00f, 0.50f, 0.00f),
			new ColorName("CorflowerBlue", 0.39f, 0.58f, 0.93f),
			new ColorName("Cornsilk", 1.00f, 0.97f, 0.86f),
			new ColorName("Crimson", 0.86f, 0.08f, 0.00f),
			new ColorName("Cyan", 0.00f, 0.02f, 1.00f),
			new ColorName("DarkBlue", 0.00f, 0.55f, 0.55f),
			new ColorName("DarkCyan", 0.00f, 0.04f, 0.55f),
			new ColorName("DarkGoldenRod", 0.72f, 0.53f, 0.00f),
			new ColorName("DarkGray", 0.66f, 0.66f, 0.66f),
			new ColorName("DarkGreen", 0.00f, 0.00f, 0.00f),
			new ColorName("DarkKhaki", 0.74f, 0.72f, 0.42f),
			new ColorName("DarkMagenta", 0.55f, 0.00f, 0.55f),
			new ColorName("DarkOliveGreen", 0.33f, 0.03f, 0.00f),
			new ColorName("DarkOrange", 1.00f, 0.55f, 0.00f),
			new ColorName("DarkOrchid", 0.60f, 0.20f, 0.80f),
			new ColorName("DarkRed", 0.55f, 0.00f, 0.00f),
			new ColorName("DarkSalmon", 0.91f, 0.59f, 0.48f),
			new ColorName("DarkSeaGreen", 0.56f, 0.74f, 0.56f),
			new ColorName("DarkSlateBlue", 0.28f, 0.00f, 0.55f),
			new ColorName("DarkSlateGray", 0.18f, 0.04f, 0.00f),
			new ColorName("DarkTurquoise", 0.00f, 0.02f, 0.82f),
			new ColorName("DarkViolet", 0.58f, 0.00f, 0.83f),
			new ColorName("DeepPink", 1.00f, 0.08f, 0.58f),
			new ColorName("DeepSkyBlue", 0.00f, 0.00f, 1.00f),
			new ColorName("DimGray", 0.41f, 0.41f, 0.41f),
			new ColorName("DodgerBlue", 0.12f, 0.17f, 1.00f),
			new ColorName("FireBrick", 0.70f, 0.13f, 0.00f),
			new ColorName("FloralWhite", 1.00f, 0.98f, 0.94f),
			new ColorName("ForestGreen", 0.13f, 0.15f, 0.00f),
			new ColorName("Fuchsia", 1.00f, 0.00f, 1.00f),
			new ColorName("Gainsboro", 0.86f, 0.86f, 0.86f),
			new ColorName("GhostWhite", 0.97f, 0.97f, 1.00f),
			new ColorName("Gold", 1.00f, 0.84f, 0.02f),
			new ColorName("GoldenRod", 0.85f, 0.65f, 0.00f),
			new ColorName("Gray", 0.50f, 0.50f, 0.50f),
			new ColorName("Green", 0.00f, 0.03f, 0.03f),
			new ColorName("GreenYellow", 0.68f, 1.00f, 0.00f),
			new ColorName("HoneyDew", 0.94f, 1.00f, 0.94f),
			new ColorName("HotPink", 1.00f, 0.41f, 0.71f),
			new ColorName("IndianRed", 0.80f, 0.36f, 0.00f),
			new ColorName("Indigo", 0.29f, 0.00f, 0.51f),
			new ColorName("Ivory", 1.00f, 1.00f, 0.94f),
			new ColorName("Khaki", 0.94f, 0.90f, 0.55f),
			new ColorName("Lavender", 0.90f, 0.90f, 0.98f),
			new ColorName("LavenderBlush", 1.00f, 0.94f, 0.96f),
			new ColorName("LawnGreen", 0.49f, 0.99f, 0.01f),
			new ColorName("LemonChffon", 1.00f, 0.98f, 0.80f),
			new ColorName("LightBlue", 0.68f, 0.85f, 0.90f),
			new ColorName("LightCoral", 0.94f, 0.50f, 0.50f),
			new ColorName("LightCyan", 0.88f, 1.00f, 1.00f),
			new ColorName("LightGoldenRodYellow", 0.90f, 0.98f, 0.82f),
			new ColorName("LightGray", 0.83f, 0.83f, 0.83f),
			new ColorName("LightGreen", 0.56f, 0.93f, 0.56f),
			new ColorName("LightPink", 1.00f, 0.71f, 0.76f),
			new ColorName("LightSalmon", 1.00f, 0.63f, 0.48f),
			new ColorName("LightSeaGreen", 0.13f, 0.31f, 0.67f),
			new ColorName("LightSkyBlue", 0.53f, 0.81f, 0.98f),
			new ColorName("LightSlateGray", 0.47f, 0.53f, 0.60f),
			new ColorName("LightSteelBlue", 0.69f, 0.77f, 0.87f),
			new ColorName("LightYellow", 1.00f, 1.00f, 0.88f),
			new ColorName("Lime", 0.00f, 0.02f, 0.02f),
			new ColorName("LimeGreen", 0.20f, 0.02f, 0.00f),
			new ColorName("Linen", 0.98f, 0.94f, 0.90f),
			new ColorName("Magenta", 1.00f, 0.00f, 1.00f),
			new ColorName("Maroon", 0.50f, 0.00f, 0.00f),
			new ColorName("MediumAquaMarine", 0.40f, 0.80f, 0.67f),
			new ColorName("MediumBlue", 0.00f, 0.80f, 0.80f),
			new ColorName("MediumOrchid", 0.73f, 0.33f, 0.83f),
			new ColorName("MediumPurple", 0.58f, 0.44f, 0.86f),
			new ColorName("MediumSeaGreen", 0.24f, 0.31f, 0.44f),
			new ColorName("MediumSlateBlue", 0.48f, 0.41f, 0.93f),
			new ColorName("MediumSpringGreen", 0.00f, 0.00f, 0.60f),
			new ColorName("MediumTurquoise", 0.28f, 0.04f, 0.80f),
			new ColorName("MediumVioletRed", 0.78f, 0.08f, 0.52f),
			new ColorName("MidnightBlue", 0.10f, 0.02f, 0.44f),
			new ColorName("MintCream", 0.96f, 1.00f, 0.98f),
			new ColorName("MistyRose", 1.00f, 0.89f, 0.88f),
			new ColorName("Moccasin", 1.00f, 0.89f, 0.71f),
			new ColorName("NavajoWhite", 1.00f, 0.87f, 0.68f),
			new ColorName("Navy", 0.00f, 0.50f, 0.50f),
			new ColorName("OldLace", 0.99f, 0.96f, 0.90f),
			new ColorName("Olive", 0.50f, 0.50f, 0.03f),
			new ColorName("OliveDrab", 0.42f, 0.56f, 0.00f),
			new ColorName("Orange", 1.00f, 0.65f, 0.02f),
			new ColorName("OrangeRed", 1.00f, 0.27f, 0.04f),
			new ColorName("Orchid", 0.85f, 0.44f, 0.84f),
			new ColorName("PaleGoldenRod", 0.93f, 0.91f, 0.67f),
			new ColorName("PaleGreen", 0.60f, 0.98f, 0.60f),
			new ColorName("PaleTurquoise", 0.69f, 0.93f, 0.93f),
			new ColorName("PaleVioletRed", 0.86f, 0.44f, 0.58f),
			new ColorName("PapayaWhip", 1.00f, 0.94f, 0.84f),
			new ColorName("PeachPff", 1.00f, 0.85f, 0.73f),
			new ColorName("Peru", 0.80f, 0.52f, 0.00f),
			new ColorName("Pink", 1.00f, 0.75f, 0.80f),
			new ColorName("Plum", 0.87f, 0.63f, 0.87f),
			new ColorName("PowderBlue", 0.69f, 0.88f, 0.90f),
			new ColorName("Purple", 0.50f, 0.00f, 0.50f),
			new ColorName("Red", 1.00f, 0.00f, 0.00f),
			new ColorName("RosyBrown", 0.74f, 0.56f, 0.56f),
			new ColorName("RoyalBlue", 0.25f, 0.02f, 0.88f),
			new ColorName("SaddleBrown", 0.55f, 0.27f, 0.00f),
			new ColorName("Salmon", 0.98f, 0.50f, 0.45f),
			new ColorName("SandyBrown", 0.96f, 0.64f, 0.00f),
			new ColorName("SeaGreen", 0.18f, 0.15f, 0.00f),
			new ColorName("SeaShell", 1.00f, 0.96f, 0.93f),
			new ColorName("Sienna", 0.63f, 0.32f, 0.00f),
			new ColorName("Silver", 0.75f, 0.75f, 0.75f),
			new ColorName("SkyBlue", 0.53f, 0.81f, 0.92f),
			new ColorName("SlateBlue", 0.42f, 0.35f, 0.80f),
			new ColorName("SlateGray", 0.44f, 0.50f, 0.56f),
			new ColorName("Snow", 1.00f, 0.98f, 0.98f),
			new ColorName("SpringGreen", 0.00f, 0.02f, 0.50f),
			new ColorName("SteelBlue", 0.27f, 0.12f, 0.71f),
			new ColorName("Tan", 0.82f, 0.71f, 0.55f),
			new ColorName("Teal", 0.00f, 0.03f, 0.50f),
			new ColorName("Thistle", 0.85f, 0.75f, 0.85f),
			new ColorName("Tomato", 1.00f, 0.39f, 0.00f),
			new ColorName("Turquoise", 0.25f, 0.09f, 0.82f),
			new ColorName("Violet", 0.93f, 0.51f, 0.93f),
			new ColorName("Wheat", 0.96f, 0.87f, 0.70f),
			new ColorName("White", 1.00f, 1.00f, 1.00f),
			new ColorName("WhiteSmoke", 0.96f, 0.96f, 0.96f),
			new ColorName("Yellow", 1.00f, 1.00f, 0.02f),
			new ColorName("YellowGreen", 0.60f, 0.80f, 0.00f) };
}
