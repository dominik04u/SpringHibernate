package pl.Dominik.spring.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name="books",uniqueConstraints=@UniqueConstraint(columnNames={"user_id","title","author","year"}))
public class Book {

	private Integer id;
	private User user;
	
	@Column(name="title",nullable=false,length=50)
	@Field(index=Index.YES, analyze=Analyze.YES,store=Store.NO)
	@Size(min=2, max=50,message="Tytu³ ksi¹¿ki powinien zawieraæ od 2 do 50 znaków")
	private String title;
	
	@Column(name="author",nullable=false,length=50)
	@Field(index=Index.YES, analyze=Analyze.YES,store=Store.NO)
	@Size(min=2, max=50,message="Autor powinien zawieraæ od 2 do 50 znaków")
	private String author;
	
	private int year;
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",unique=true, nullable=false)
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author=author;
	}
	
	@Column(nullable=false)
	public int getYear(){
		return year;
	}
	
	public void setYear(int year){
		this.year=year;
	}
}
