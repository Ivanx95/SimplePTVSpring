package one.main.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.main.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
