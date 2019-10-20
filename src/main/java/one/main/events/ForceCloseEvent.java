package one.main.events;

import org.springframework.context.ApplicationEvent;

public class ForceCloseEvent extends ApplicationEvent{

	private String message;
	public ForceCloseEvent(Object source,String message) {
		super(source);
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
