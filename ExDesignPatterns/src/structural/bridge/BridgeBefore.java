package structural.bridge;


public class BridgeBefore {
	
	public static void main(String[] args) {
		new BridgeBefore();
	}
	
	public BridgeBefore() {
		System.out.println("----Before apply Bridge Pattern----");
		new GoodRestaurant().specialFood(Local.SAIGON);
	}
	
	public enum Local {
		HANOI, HUE, SAIGON, OTHER
	}
	
	class GoodChef {
		
		/**
		 * 
		 */
		public void makeSaiGonSpecialFood() {
			System.out.println("Sai Gon special food.");
		}
		
		/**
		 * 
		 */
		public void makeHueSpecialFood() {
			System.out.println("Hue special food.");
		}
		
		/**
		 * 
		 */
		public void makeHaNoiSpecialFood() {
			System.out.println("Ha Noi special food.");
		}
		
		/**
		 * 
		 */
		public void makeKoreanFood() {
			System.out.println("Korean food.");
		}

	}
	
	class GoodRestaurant {
		
		public void specialFood(Local local) {
			GoodChef chef = new GoodChef();
			
			switch(local) {
				case HANOI : chef.makeHaNoiSpecialFood(); break;
				case HUE : chef.makeHueSpecialFood(); break;
				case SAIGON : chef.makeSaiGonSpecialFood(); break;
				default : chef.makeKoreanFood(); break;
			}
		}

	}
}
