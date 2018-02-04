package com.dp.exercise.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	private int id;
	private String username;
	private String password;
	private String email;

	public User(){}

	public User(String user, String password, String email){
		this.username = user;
		this.password = password;
		this.email = email;
	}

	public User(String user, String password){
		this.username = user;
		this.password = password;
		this.email = "";
	}

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
