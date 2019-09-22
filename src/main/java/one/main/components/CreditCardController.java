package one.main.components;

import one.main.components.exceptions.MethodPayException;

public class CreditCardController extends PayMethodController {

	
	@Override
	public void pay() throws MethodPayException {
		System.err.println("Paying");
		
	}

}
