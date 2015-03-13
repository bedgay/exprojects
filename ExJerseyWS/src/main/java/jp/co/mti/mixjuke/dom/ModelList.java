package jp.co.mti.mixjuke.dom;

import java.util.ArrayList;
import java.util.List;

public class ModelList<T extends AbstractDomain> {
	
	private Long total = 0L;
	private List<T> list = new ArrayList<T>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
