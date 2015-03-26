package structural.privateclassdata;

import java.util.Date;

@SuppressWarnings("unused")
public class ClassDataBefore {
	
	public static void main(String[] args) {
		new ClassDataBefore();
	}
	
	public ClassDataBefore() {
		new SearchController("Design Pattern").search();
	}
	
	class SearchController {
		
		private String pattern;
		private Date fromDate;
		private Date toDate;
		private Integer page;
		private Integer pageSize;
		private String sortOrder;
		
		public SearchController(String pattern) {
			this.pattern = pattern;
		}
		
		public SearchController(String pattern, Date fromDate, Date toDate,
				Integer page, Integer pageSize, String sortOrder) {
			this.pattern = pattern;
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.page = page;
			this.pageSize = pageSize;
			this.sortOrder = sortOrder;
		}

		public void search() {
			System.out.println("Do search with pattern: " + pattern);
		}
		
	}

}
