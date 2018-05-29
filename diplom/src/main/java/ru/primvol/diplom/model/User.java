package ru.primvol.diplom.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "second_name")
	private String secondName;
	
	@Column(name = "third_name")
	private String thirdName;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "pass")
	private String pass;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "type_of_user") //0 - гость, 1 - волонтер, 2 - координатор/агент
	private Integer typeOfUser;
	
	@Column(name = "date_of_last")
	private Date dateOfLast;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "email")
	private String email;
	
	protected User() {
		
	}
	
	public User(String email, String firstName, String secondName, String pass) {
		this.email = email;
		this.firstName = firstName;
		this.secondName = secondName;
		this.pass = pass;
		this.typeOfUser = 0;
	}
	
	public User(String email, String pass) {
		this.email=email;
		this.pass=pass;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
	
	public String getThirdName() {
		return thirdName;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfLast(Date dateOfLast) {
		this.dateOfLast = dateOfLast;
	}
	
	public Date getDateOfLast() {
		return dateOfLast;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setEmail (String email) {
		this.email = email;
		this.status = false;
		//нужен код по отправке сообщения на почту
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean changePass(String newPass, String oldPass) {
		if (oldPass.equals(this.pass)) {
			this.pass = newPass;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setPass(String pass) {
		this.pass=pass;
	}
	public String getPass() {
		return pass;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
		
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setTypeOfUser(Integer typeOfUser) {
		this.typeOfUser = typeOfUser;
	}
	
	public Integer getTypeOfUser() {
		return typeOfUser;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s %s", firstName, secondName);
	}
	
}
