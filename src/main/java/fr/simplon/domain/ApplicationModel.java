package fr.simplon.domain;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

//bean pour récupérer les données d'une application

@Component
public class ApplicationModel {
	
	//compteur
	private AtomicLong compteur = new AtomicLong(0);

	public AtomicLong getCompteur() {
		return compteur;
	}

	public void setCompteur(AtomicLong compteur) {
		this.compteur = compteur;
	}
	
	//getters et setters
	
	

}
