package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"email","username"})})
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Email may not be blank")
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotNull(message = "Username may not be null")
	@NotBlank(message = "Username may not be blank")
	@Size(min=2, max=30)
	@Column(unique = true, nullable = false)
    private String username;
	
	@NotBlank(message = "First name may not be blank")
    private String firstname;
	
	@NotBlank(message = "Last name may not be blank")
    private String lastname;
	
	private String role;
	
	@NotNull(message = "Password may not be null")
	@NotBlank(message = "Password may not be blank")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", username=" + username + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", role=" + role + ", password=" + password + "]";
	}
}
