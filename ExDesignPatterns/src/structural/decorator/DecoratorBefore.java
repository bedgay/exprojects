package structural.decorator;

public class DecoratorBefore {
	
	public static void main(String[] args) {
		new DecoratorBefore();
	}
	
	public DecoratorBefore() {
		new WindowWithBorderAndHorizontalSB().draw();
	}
	
	class Winodw {
		public void draw() {
			System.out.println("Window");
		}
	}
	
	class Border {
		public void draw() {
			System.out.println("Border");
		}
	}
	
	class VerticalSB {
		public void draw() {
			System.out.println("VerticalSB");
		}
	}
	
	class HorizontalSB {
		public void draw() {
			System.out.println("HorizontalSB");
		}
	}
	
	class WindowWithBorderAndHorizontalSB {
		Border border = new Border();
		HorizontalSB horizontalSB = new HorizontalSB();
		public void draw() {
			border.draw();
			horizontalSB.draw();
			System.out.println("Window");
		}
	}

}
