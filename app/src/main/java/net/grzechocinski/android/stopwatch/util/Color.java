package net.grzechocinski.android.stopwatch.util;

import net.grzechocinski.android.stopwatch.R;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public enum Color {

	WHITE(R.color.white), YELLOW(R.color.yellow), GREEN(R.color.green);

	private int colorResId;

	private Color(int colorResId) {
		this.colorResId = colorResId;
	}

	public int asResId(){
		return colorResId;
	}
}
