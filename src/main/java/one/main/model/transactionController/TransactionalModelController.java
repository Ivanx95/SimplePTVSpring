package one.main.model.transactionController;

import one.main.components.aop.LogExecutionTime;

public abstract class TransactionalModelController<T> {

	
	@LogExecutionTime
	public abstract void start(T t);
	
	@LogExecutionTime
	public abstract   T finish(); 
		
	
	
}
