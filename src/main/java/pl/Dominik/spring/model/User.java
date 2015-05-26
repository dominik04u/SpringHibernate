package pl.Dominik.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=4, max=20, message="Nazwa u¿ytkownika powinna zawieraæ od 4 do 20 znaków!")
	private String username;
	
	@Size(min=6, max=20, message="Has³o powinno zawieraæ od 6 do 20 znaków!")
	private String password;
	
	@NotEmpty(message="Adres email jest wymagany")
	@Email(message="Adres email jest nieprawid³owy!")
	private String email;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<UserRole> userRole=new HashSet<UserRole>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user",cascade=CascadeType.REMOVE)
	private Set<Book> book=new HashSet<Book>(0);
	
	public Set<Book> getBook(){
		return book;
	}
	
	public void setBook(Set<Book> book){
		this.book=book;
	}
	
	public Set<UserRole> getUserRole(){
		return userRole;
	}
	
	public void SetUserRole(Set<UserRole> userRole){
		this.userRole=userRole;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	
}
