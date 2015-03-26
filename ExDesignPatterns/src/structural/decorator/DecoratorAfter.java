package structural.decorator;

public class DecoratorAfter {
	
	public static void main(String[] args) {
		new DecoratorAfter();
	}
	
	public DecoratorAfter() {
		LCD lcd = new Border(new VerticalSB(new Window()));
		lcd.draw();
	}

	interface LCD {
		void draw();
	}
	
	class Window implements LCD {

		@Override
		public void draw() {
			System.out.println("Window");
		}
		
	}
	
	class Decorator implements LCD {

		private LCD lcd;
		
		public Decorator(LCD lcd) {
			this.lcd = lcd;
		}
		
		@Override
		public void draw() {
			lcd.draw();
		}
		
	}
	
	class Border extends Decorator {

		public Border(LCD lcd) {
			super(lcd);
		}

		@Override
		public void draw() {
			System.out.println("Border");
			super.draw();
		}
		
	}
	
	class VerticalSB extends Decorator {

		public VerticalSB(LCD lcd) {
			super(lcd);
		}

		@Override
		public void draw() {
			System.out.println("VerticalSB");
			super.draw();
		}
		
	}
	
	class HorizontalSB extends Decorator {

		public HorizontalSB(LCD lcd) {
			super(lcd);
		}

		@Override
		public void draw() {
			System.out.println("HorizontalSB");
			super.draw();
		}
		
	}
	
}
