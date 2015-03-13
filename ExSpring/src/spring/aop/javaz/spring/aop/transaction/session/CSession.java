package javaz.spring.aop.transaction.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SUCCESS\tungo
 * @date Aug 14, 2014 10:22:18 AM
 */
public class CSession implements Serializable { // Lockable

	private static final long serialVersionUID = 1L;
	
	private List<String> list = new ArrayList<>();
	private boolean complete = Boolean.TRUE;

	/**
	 * @param t
	 */
	public void put(String t) {
		list.add(t);
	}

	public void flush() {
		list.clear();
	}

	public void clean() {
		list.clear();
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
