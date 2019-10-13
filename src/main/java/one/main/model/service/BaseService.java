package one.main.model.service;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<T,I> {
	
	Optional<T> find(I identifier);
	
	T create(T bean);
	
	Collection<T> getAll();
}
