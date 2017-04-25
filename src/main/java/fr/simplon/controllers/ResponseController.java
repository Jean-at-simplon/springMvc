package fr.simplon.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.domain.Personne;

@RestController
public class ResponseController {
	/*
	 * Retourne un texte brut
	 */
	@RequestMapping(value="/a01",method=RequestMethod.GET)
	public String a01(){
		return "Bonjour Yanis";
	}
	
	/*
	 * Retourne un texte en utf-8
	 */
	@RequestMapping(value="/a02", method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	public String a02(){
		return "<h1>Caractère accentué : àéèâäîïüô</h1>";
	}
	/*
	 * Retourne un texte en Xml
	 */
	
	@RequestMapping(value="/a03", method=RequestMethod.GET, produces="text/xml;charset=utf-8")
	public String a03(){
		String yanis = "<yanis><yanis><yanis>Yanis est le plus beau</yanis></yanis></yanis>";
		return yanis;
	}
	/*
	 * Retourne un texte en Json
	 */
	@RequestMapping(value="/a04", method=RequestMethod.GET)
	public Map<String,Object> a04(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yanis", "Le plus beau");
		map.put("papa", "Le plus gentil");
		map.put("chiffre", new int[]{1,2,3,4,5});
		return map;
	}
	/*
	 * Retourne un objet eb Json
	 */
	@RequestMapping(value="/a05",method=RequestMethod.GET)
	public Personne a05(){
		return new Personne(1,"Yanis",98);
	}
	/*
	 * Retourne un objet vide
	 */
	@RequestMapping(value="/a06", method=RequestMethod.GET)
	public void a06(){
	}
	/*
	 * Retourne une page html
	 */
	@RequestMapping(value="/a07", method=RequestMethod.GET, produces="text/html;charset=utf-8" )
	public String a07(){
		String texte = "<h1>Yanis est le plus beau</h1>";
		return texte;
	}
	@RequestMapping(value="/a13")
	public void a13(HttpServletResponse response) throws IOException{
		response.setStatus(666);
		response.addHeader("header1", "Un entete");
		response.addHeader("Content-type", "text/html;charset=utf-8");
		response.addHeader("title", "La page de Yanis");
		String yanis="<h1>Yanis est le plus beau</h1>";
		response.getWriter().write(yanis);
	}

}
