package structural.bridge;


public class BridgeAfter {
	
	public static void main(String[] args) {
		new BridgeAfter();
	}
	
	public BridgeAfter() {
		System.out.println("----After apply Bridge Pattern----");
		new SaiGonRestaurant().specialFood(Boolean.TRUE);
	}
	
	abstract class Chef {
		abstract void makeSpecialFood();
		
		public void makeKoreanFood() {
			System.out.println("Korean food.");
		}
	}	
	class HaNoiChef extends Chef {
		@Override
		void makeSpecialFood() {
			System.out.println("Ha Noi special food.");
		}
	}
	class SaiGonChef extends Chef {
		@Override
		void makeSpecialFood() {
			System.out.println("Sai Gon special food.");
		}
	}
	class HueChef extends Chef {
		@Override
		void makeSpecialFood() {
			System.out.println("Hue special food.");
		}
	}
	
	abstract class Restaurant {
		public void specialFood(Boolean isSpecialLocal) {
			if (isSpecialLocal) {
				getChef().makeSpecialFood();
			} else {
				getChef().makeKoreanFood();
			}
		}
		public abstract Chef getChef();
	}
	class HaNoiRestaurant extends Restaurant {
		private Chef chef = new HaNoiChef();
		@Override
		public Chef getChef() {
			return chef;
		}
	}
	class SaiGonRestaurant extends Restaurant {
		private Chef chef = new SaiGonChef();
		@Override
		public Chef getChef() {
			return chef;
		}
	}
	class HueRestaurant extends Restaurant {
		private Chef chef = new HueChef();
		@Override
		public Chef getChef() {
			return chef;
		}
	}

}
