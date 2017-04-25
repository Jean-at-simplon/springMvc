package fr.simplon.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//bean pour récupérer les données de session d'un utilisateur
@Component
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionModel {
	
	private int compteur;
	
	public int getCompteur(){
		return compteur;
	}
	
	public void setCompteur (int compteur){
		this.compteur = compteur;
	}

}
