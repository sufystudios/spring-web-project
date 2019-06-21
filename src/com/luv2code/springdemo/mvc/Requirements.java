package com.luv2code.springdemo.mvc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sufy
 */
@Entity
@Table(name="requirements")
public class Requirements {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int adv;
	public int getAdv() {
		return adv;
	}
	public void setAdv(int adv) {
		this.adv = adv;
	}

	@NotBlank(message="is required")
	@NotNull(message="is required")
	@Column(name="gender")
    private String gender;
	@NotNull(message="is required")
	@DecimalMax("150")
	@DecimalMin("1")
	@Column(name="lowage")
    private Integer lowage;
	@NotNull(message="is required")
	@DecimalMax("150")
	@DecimalMin("1")
	@Column(name="highage")
    private Integer highage;
    @NotNull(message="is required")
    @DecimalMax("80000000000")
	@DecimalMin("0")
    @Column(name="lowincome")
	private Integer lowincome;
    @NotNull(message="is required")
    @DecimalMax("80000000000")
	@DecimalMin("0")
    @Column(name="highincome")
    private Integer highincome;
    Requirements() {
    	
    }
    public Requirements(String gen, Integer lowa, Integer higa, Integer lowinc, Integer higinc) {
        gender = gen;
        lowage = (lowa);
        highage = higa;
        lowincome = (lowinc);
        highincome = (higinc);
    }

    public void setGender(String gender) {
        this.gender = gender.toLowerCase();
    }

    public void setAge(Integer lowage, Integer highage) {
        this.lowage = (lowage);
        this.highage = (highage);
    }
 
    public void setLowage(Integer lowage) {
		this.lowage = (lowage);
	}
	public void setHighage(Integer highage) {
		this.highage = (highage);
	}
	public void setLowincome(Integer lowincome) {
		this.lowincome = (lowincome);
	}
	public void setHighincome(Integer highincome) {
		this.highincome =(highincome);
	}
	public void setIncome(Integer lowincome,Integer highincome) {
        this.lowincome = (lowincome);
        this.highincome = ( highincome);
    }

    public void containsNull() throws Exception {

        boolean genderb = false;
        boolean lowageb = false;
        boolean lowagegreater = false;
        boolean highageb = false;
        boolean lowincomeb = false;
        boolean highincomeb = false;
        boolean lowincomegreater = false;
        genderb = gender == null;
        lowageb = lowage < 16 || lowage > 150;
        lowagegreater = lowage > highage;
        highageb = highage < 16 || highage > 150;
        lowincomeb = lowincome < 1 || lowincome > 800000000;
        highincomeb = highincome < 1 || highincome > 800000000;
        lowincomegreater = lowincome > highincome;

        if (gender == null || lowage < 16 || lowage > 150 || lowage > highage || highage > 150 || highage < 16 || lowincome < 1 || lowincome > highincome || highincome < 1 || highincome > 800000000) {
            throw new Exception("Error in requirements:gender is empty: " + genderb
                    + " low age out of range: " + lowageb + " low age greater than high age: " + lowagegreater
                    + " age out of range: " + highageb + "low income out of range: " + lowincomeb
                    + "high income out of range: " + highincomeb + "low income greater than high: " + lowincomegreater);
        }

    }

    public String getGender() {
        return gender;
    }

    public Integer getlowage() {
        return lowage;
    }

    public Integer gethighage() {
        return highage;
    }

    public Integer getHighincome() {
        return highincome;
    }

    public Integer getLowincome() {
        return lowincome;
    }
    public Integer getLowage() {
        return lowage;
    }

    public Integer getHighage() {
        return highage;
    }

    public Integer gethighinc() {
        return highincome;
    }

    public Integer getlowinc() {
        return lowincome;
    }
}
