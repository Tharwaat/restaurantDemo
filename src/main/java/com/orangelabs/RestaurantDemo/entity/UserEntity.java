package com.orangelabs.RestaurantDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {
	
	private static final String EnumType = null;

	// Columns
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="role")
	private UserType role;

	// Constructors
	public UserEntity(String name, String email, String password, String mobileNumber, UserType role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.role = role;
	}
	
	public UserEntity() {
		
	}
	
	// Setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}	
	
	public UserType getRole() {
		return this.role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + ", role=" + role + "]";
	}
	
	public enum UserType {
	    USER("USER"), ADMIN("ADMIN");
		private String role;
		
		private UserType(String role){
            this.role = role;
        }

        public void setRole(String role){
            this.role = role;
        }

        public String getRole(){
             return this.role;
        }
	}
	
}

