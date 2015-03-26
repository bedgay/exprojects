package structural.adapter;

import structural.adapter.after.Line;
import structural.adapter.after.Retangle;
import structural.adapter.after.Shape;
import structural.adapter.before.LegacyLine;
import structural.adapter.before.LegacyRetangle;

/**
 * @author SUCCESS\tungo
 * @url http://sourcemaking.com/design_patterns/adapter
 */
public class AdapterClient {
	
	public static void main(String[] args) {
		System.out.println("----Before apply Adapter----");
		before();
		System.out.println("----After apply Adapter----");
		after();
	}
	
	public static void before() {
		new LegacyLine().display(0, 1, 2, 3);
		new LegacyRetangle().display(0, 1, 2, 3);
	}
	
	public static void after() {
		Shape shape = new Line();
		shape.draw(0, 1, 2, 3);

		shape = new Retangle();
		shape.draw(0, 1, 2, 3);
	}

}
