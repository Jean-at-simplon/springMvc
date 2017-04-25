package fr.simplon.domain;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public class ActionModel02 {
	
	@NotNull(message = "La donnée est obligatoire")
	@AssertFalse(message = "Seule la valeur [False] est acceptée")
	private boolean assertfalse;
	
	@NotNull(message = "La donnée est obligatoire")
	@AssertTrue(message = "Seule la valeur [true] est acceptée")
	private Boolean assertTrue;
	
	@NotNull(message = "La donnée est obligatoire")
	@Future(message = "Il faut une date postérieure à aujourd'hui")
	private Date dateInFuture;
	
	@NotNull(message = "La donnée est obligatoire")
	@Past(message = "Il faut une date antérieure à aujourd'hui")
	private Date dateInPast;
	
	@NotNull(message = "La donnée est obligatoire")
	@Max(value = 100, message = "Maximum 100")
	private Integer intMax100;
	
	@NotNull(message = "La donnée est obligatoire")
	@Min(value = 10, message = "Minimum 10")
	private Integer intMin10;
	
	@NotNull(message = "La donnée est obligatoire")
	@NotBlank(message = "La chaîne doit être non blanche")
	private String strNotBlank;
	
	@NotNull(message = "La donnée est obligatoire")
	@Size(min = 4, max = 6, message = "La chaîne doit avoir entre 4 et 6 caractères")
	private String strBetween4and6;
	
	@NotNull(message = "La donnée est obligatoire")
	@Pattern(regexp = "^\\d{2}:\\d{2}:\\d{2}$", message = "Le format doit être hh:mm:ss")
	private String hhmmss;
	
	@NotNull(message = "La donnée est obligatoire")
	@Email(message = "Adresse invalide")
	private String email;
	
	@NotNull(message = "La donnée est obligatoire")
	@Length(max = 4, min = 4, message = "La chaîne doit avoir 4 caractères exactement")
	private String str4;
	
	 @Range(min = 10, max = 14, message = "La valeur doit être dans l'intervalle [10,14]")
	 @NotNull(message = "La donnée est obligatoire")
	 private Integer int1014;
	 
	 @URL(message = "URL invalide")
	 private String url;

	public boolean isAssertfalse() {
		return assertfalse;
	}

	public void setAssertfalse(boolean assertfalse) {
		this.assertfalse = assertfalse;
	}

	public Boolean getAssertTrue() {
		return assertTrue;
	}

	public void setAssertTrue(Boolean assertTrue) {
		this.assertTrue = assertTrue;
	}

	public Date getDateInFuture() {
		return dateInFuture;
	}

	public void setDateInFuture(Date dateInFuture) {
		this.dateInFuture = dateInFuture;
	}

	public Date getDateInPast() {
		return dateInPast;
	}

	public void setDateInPast(Date dateInPast) {
		this.dateInPast = dateInPast;
	}

	public Integer getIntMax100() {
		return intMax100;
	}

	public void setIntMax100(Integer intMax100) {
		this.intMax100 = intMax100;
	}

	public Integer getIntMin10() {
		return intMin10;
	}

	public void setIntMin10(Integer intMin10) {
		this.intMin10 = intMin10;
	}

	public String getStrNotBlank() {
		return strNotBlank;
	}

	public void setStrNotBlank(String strNotBlank) {
		this.strNotBlank = strNotBlank;
	}

	public String getStrBetween4and6() {
		return strBetween4and6;
	}

	public void setStrBetween4and6(String strBetween4and6) {
		this.strBetween4and6 = strBetween4and6;
	}

	public String getHhmmss() {
		return hhmmss;
	}

	public void setHhmmss(String hhmmss) {
		this.hhmmss = hhmmss;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public Integer getInt1014() {
		return int1014;
	}

	public void setInt1014(Integer int1014) {
		this.int1014 = int1014;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	 
}
