package pl.Dominik.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.Dominik.spring.dao.UserDAO;
import pl.Dominik.spring.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO;
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
	
	public void setUserDAO(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	
	@Transactional(readOnly = true) //atrybuty tranzakcji

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		pl.Dominik.spring.model.User user=userDAO.findByUserName(username);
		List<GrantedAuthority> authorities=buildUserAuthority(user.getUserRole());
		return buildUserForAuthentication(user,authorities);
	}
	
	//autentykacja
	public User buildUserForAuthentication(pl.Dominik.spring.model.User user, List<GrantedAuthority> authorities){
		return new User(user.getUsername(),user.getPassword(),true,true,true,true,authorities);		
	}
	
	//budowanie autoryzacji
	public List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){
		Set<GrantedAuthority> setAuthorities=new HashSet<GrantedAuthority>();
		
		for(UserRole userRole:userRoles){
			setAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		List<GrantedAuthority> result=new ArrayList<GrantedAuthority>(setAuthorities);
		return result;
	}

}
