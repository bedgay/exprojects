package javaz.spring.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public enum Type {
		A, B, C
	}

	private Type type;
	private Object data;

	public CustomEvent(Object source) {
		super(source);
	}

	public CustomEvent(Object source, Type type) {
		super(source);
		this.type = type;
	}
	
	public CustomEvent(Object source, Object data) {
		super(source);
		this.data = data;
	}

	public CustomEvent(Object source, Type type, Object data) {
		super(source);
		this.type = type;
		this.data = data;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
