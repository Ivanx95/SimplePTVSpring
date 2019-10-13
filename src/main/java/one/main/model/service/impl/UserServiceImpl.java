package one.main.model.service.impl;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.main.model.User;
import one.main.model.repository.UserRepository;
import one.main.model.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private EntityManager entityManager;
	
	private User loggedUser;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> find(Long identifier) {
		// TODO Auto-generated method stub
		return userRepository.findById(identifier);
	}

	@Override
	public User create(User bean) {
		// TODO Auto-generated method stub
		return userRepository.save(bean);
	}

	@Override
	public Collection<User> getAll() {
		// TODO Auto-generated method stub
		return (Collection<User>) userRepository.findAll();
	}

	@Transactional
	@Override
	public User simpleAccess(User user) {
		Session session=entityManager.unwrap(Session.class);
		
		CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
		
		CriteriaQuery<User> cr = criteriaBuilder.createQuery(User.class);
		Root<User> root=cr.from(User.class);
		
		Predicate[] predicates = new Predicate[2];
		predicates[0] = criteriaBuilder.equal(root.get("user"), user.getUser());
		predicates[1] = criteriaBuilder.equal(root.get("password"), user.getPassword());
		
		cr.select(root).where(predicates);
		
		javax.persistence.Query query = session.createQuery(cr);
		User result = (User) query.getSingleResult();
		
		return result;
	}

	@Override
	public User getLoggedUser() {
		return loggedUser;
	}

	@Override
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	
}
