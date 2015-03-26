package structural.adapter.after;

import structural.adapter.before.LegacyRetangle;

/**
 * @author SUCCESS\tungo
 *
 */
public class Retangle implements Shape {

	/* (non-Javadoc)
	 * @see structural.adapter.after.Shape#draw(int, int, int, int)
	 */
	@Override
	public void draw(int stNum, int ndNum, int rdNum, int forthNum) {
		new LegacyRetangle().display(stNum, ndNum, rdNum, forthNum);
	}

}
