package com.luv2code.springdemo.mvc;

/**
 *
 * @author Frederick Bertram
 *
package com.luv2code.springdemo.mvc;
 * A class to store information about customers
 */
import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.InheritanceType;
import org.hibernate.validator.constraints.NotBlank;

import java.math.*;




@MappedSuperclass
public class Customer {
	@NotNull(message="Is required")
	@NotBlank(message="Required")
	@Id
	@Column(name="login")
    private String login;
	@NotNull(message="Is required")
	@NotBlank(message="Required")
	@Column(name="password")
    private String password;
	@NotNull(message="Is required")
	@NotBlank(message="Required")
	@Column(name="gender")
    private String gender;
	@NotNull(message="Is required")
	@DecimalMax("8000000000000000")
	@DecimalMin("1")
	@Column(name="income")
    private Integer income;
	@NotNull(message="Is required")
	@DecimalMin("1")
	@DecimalMax("150")
	@Column(name="age")
    private Integer age;
	@Column(name="type")
	@Transient
    private String type;
	@Transient
    protected Customers customers;

    Customer(Customers c) {
        customers = c;
    }
    Customer() {
    	
    }
    public void setCustomers(Customers c) {
    	customers=c;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIncome(Integer income) {
        this.income = (income);
    }

    public String getType() {
        return type;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public Integer getIncome() {
        return income;

    }

    public Integer getAge() {
        return age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String chooseUsername(String username) {
        if (customers.loginTaken(username)) {
            return "Login Taken";
        } else {
            generatePassword();
            this.setLogin(username);
            customers.addCustomer(this, username);
            return "Password Created";

        }
    }

    protected String generatePassword() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();
        int randomLen = 1 + random.nextInt(9);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s += c;
        }
        setPassword(s);
        return s;
    }

}
