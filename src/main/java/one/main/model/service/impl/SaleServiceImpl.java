package one.main.model.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
		
		
		return new Sale(10L, "Ivan", "Yuni", LocalDateTime.now(), new BigDecimal(100));
	}

	@Override
	public Collection<Sale> getAll() {
		// TODO Auto-generated method stub
		return Arrays.asList(new Sale(10L, "Ivan", "Yuni", LocalDateTime.now(), new BigDecimal(100)));
	}

	@Override
	@LogExecutionTime
	public Sale create() {
		// TODO Auto-generated method stub

		return new Sale(10L, "Ivan", "Yuni", LocalDateTime.now(), new BigDecimal(100));
	}

}
