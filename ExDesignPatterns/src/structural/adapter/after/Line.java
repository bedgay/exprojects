package structural.adapter.after;

import structural.adapter.before.LegacyLine;

/**
 * @author SUCCESS\tungo
 *
 */
public class Line implements Shape {

	/* (non-Javadoc)
	 * @see structural.adapter.after.Shape#draw(int, int, int, int)
	 */
	@Override
	public void draw(int stNum, int ndNum, int rdNum, int forthNum) {
		new LegacyLine().display(stNum, ndNum, rdNum, forthNum);		
	}

}
