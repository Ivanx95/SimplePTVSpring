package one.main.model.service.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;

import one.main.components.aop.LogExecutionTime;
import one.main.model.Sale;
import one.main.model.service.SaleService;

@Service
public class SaleServiceImpl  implements SaleService{

	@Override
	@LogExecutionTime
	public Sale find(Long identifier) {
		
		
		return new Sale();
	}

	@Override
	public Collection<Sale> getAll() {
		// TODO Auto-generated method stub
		return Arrays.asList(new Sale());
	}

}
