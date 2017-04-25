package fr.simplon.domain;

import javax.validation.constraints.NotNull;

public class ActionModel01 {
	
	@NotNull
	private Integer a;
	
	@NotNull
	private double b;

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}
	
	

}
