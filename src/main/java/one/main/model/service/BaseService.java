package one.main.model.service;

import java.util.Collection;

public interface BaseService<T,I> {
	
	T find(I identifier);
	
	Collection<T> getAll();
}
