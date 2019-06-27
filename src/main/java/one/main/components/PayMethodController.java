package one.main.components;

import one.main.components.exceptions.MethodPayException;

public abstract class PayMethodController {
	
	
	public abstract void pay() throws MethodPayException;
}
