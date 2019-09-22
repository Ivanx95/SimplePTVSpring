package one.main.controller.base;

import one.main.components.aop.LogExecutionTime;

public interface TransactionalController<T> {

	@LogExecutionTime
	public T execute();
}
