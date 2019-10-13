package one.main.model.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.main.components.aop.LogExecutionTime;
import one.main.model.Sale;
import one.main.model.repository.SaleRepository;
import one.main.model.service.SaleService;

@Service
public class SaleServiceImpl  implements SaleService{


    Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);
	@Autowired
	private SaleRepository saleRepository;
	
	@Override
	@LogExecutionTime
	public Optional<Sale> find(Long identifier) {
		
		
		return saleRepository.findById(identifier);
	}

	@Override
	public Collection<Sale> getAll() {
		// TODO Auto-generated method stub
		return (Collection<Sale>) saleRepository.findAll();
	}

	//Better return nothing i
	@Override
	@LogExecutionTime
	public Sale create(Sale sale) {
		// TODO Auto-generated method stub

		return saleRepository.save(sale);
			
		
	}

}
