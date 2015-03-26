package structural.privateclassdata;

import java.util.Date;

public class ClassDataAfter {
	
	public static void main(String[] args) {
		new ClassDataAfter();
	}
	
	public ClassDataAfter() {
		new SearchController("Design Pattern").search();
	}

	class SearchController {
		
		SearchData data;
		
		public SearchController(String pattern) {
			data = new SearchData(pattern);
		}
		
		public SearchController(String pattern, Date fromDate, Date toDate,
				Integer page, Integer pageSize, String sortOrder) {
			data = new SearchData(pattern, fromDate, toDate, page, pageSize, sortOrder);
		}
		
		public void search() {
			System.out.println("Do search with pattern:" + data.getPattern());
		}

	}

	class SearchData {
		private String pattern;
		private Date fromDate;
		private Date toDate;
		private Integer page;
		private Integer pageSize;
		private String sortOrder;
		
		public SearchData(String pattern) {
			this.pattern = pattern;
		}

		public SearchData(String pattern, Date fromDate, Date toDate,
				Integer page, Integer pageSize, String sortOrder) {
			this.pattern = pattern;
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.page = page;
			this.pageSize = pageSize;
			this.sortOrder = sortOrder;
		}

		public String getPattern() {
			return pattern;
		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
		}

		public Date getFromDate() {
			return fromDate;
		}

		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		public Date getToDate() {
			return toDate;
		}

		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public String getSortOrder() {
			return sortOrder;
		}

		public void setSortOrder(String sortOrder) {
			this.sortOrder = sortOrder;
		}

	}

}
