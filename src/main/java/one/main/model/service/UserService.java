package one.main.model.service;

import one.main.jfx.model.SaleFX;
import one.main.model.User;

public interface UserService extends BaseService<User, Long> {
	

	
	public User simpleAccess(User user); 
		
	public User getLoggedUser();
	
	public void setLoggedUser(User loggedUser);
}
