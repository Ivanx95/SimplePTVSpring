package one.main.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.main.model.Sale;
import one.main.model.User;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long>{

}
