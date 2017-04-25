package fr.simplon.controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.simplon.domain.ActionModel01;
import fr.simplon.domain.ApplicationModel;
import fr.simplon.domain.ContainerModel;
import fr.simplon.domain.Personne;
import fr.simplon.domain.SessionModel;

@RestController
@SessionAttributes({"container"})
public class ActionModelController {
	
	/*
	 * Récupérer des parametres avec GET
	 */
	@RequestMapping(value = "/m01", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String m01(String nom, String age){
		return String.format("Hello [%s-%s]!, Greeting from Spring Boot !", nom, age);
	}
	/*
	 * Récupérer des parametres avec POST
	 */
	@RequestMapping(value = "/m02",method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String m02(String nom, String age){
		return String.format("Hello [%s-%s]!, Greeting from Spring Boot !", nom, age);
	}
	/*
	 * Parametres de même nom
	 */
	@RequestMapping(value="/m03",method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String m03(String nom[]){
		return String.format("Hello [%s]!, Greeting from Spring Boot !", String.join("-",nom));
	}
	/*
	 * Mapper les parametres dans un objet java
	 */
	@RequestMapping(value="/m04",method = RequestMethod.POST)
	public Personne m04(Personne personne){
		return personne;
	}
	/*
	 * Récupérer les éléments d'une url
	 */
	@RequestMapping(value="/m05/{a}/x/{b}", method = RequestMethod.GET)
	public Map<String, String> m05(@PathVariable("a") String a, @PathVariable("b") String b){
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", a);
		map.put("b", b);
		return map;
	}
	/*
	 * Récupérer des elements d'une URL et des parametres
	 */
	@RequestMapping(value="/m06/{a}/x/{b}", method = RequestMethod.GET)
	public Map<String, Object> m06(@PathVariable("a") Integer a, @PathVariable("b") Double b, Double c){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", a);
		map.put("b", b);
		map.put("c", c);
		return map;
	}
	/*
	 * Acceder à la totalité de la requete
	 */
	@RequestMapping(value="/m07", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String m07(HttpServletRequest request){
		Enumeration<String> headerNames = request.getHeaderNames();
		StringBuffer buffer = new StringBuffer();
		while (headerNames.hasMoreElements()){
			String name = headerNames.nextElement();
			buffer.append(String.format("%s : %s\n", name,request.getHeader(name)));
		}
		return buffer.toString();
	}
	/*
	 * Acces à l'objet "Writer"
	 */
	@RequestMapping(value="/m08",method=RequestMethod.GET)
	public void m08(Writer writer) throws IOException{
		writer.write("Hello everybody");
	}
	/*
	 * Acceder à un entete HTTP
	 */
	@RequestMapping(value = "/m09", method=RequestMethod.GET)
	public String m09(@RequestHeader("User-Agent") String userAgent){
		return userAgent;
	}
	/*
	 * Affecter un cookie
	 */
	@RequestMapping(value="/m10", method=RequestMethod.GET)
	public void m10(HttpServletResponse response){
		response.addCookie(new Cookie("cookie1","Donotforgetme"));
	}
	/*
	 * Lire un cookie
	 */
	@RequestMapping(value="/m11", method=RequestMethod.GET)
	public String m11(@CookieValue("cookie1") String cookie1){
		return cookie1;
	}
	/*
	 * Acceder au corps d'un POST
	 */
	@RequestMapping(value="/m12", method = RequestMethod.POST)
	public String m12(@RequestBody String requestBody){
		return requestBody;
	}
	/*
	 * Récupérer les valeurs d'un POST en json
	 */
	@RequestMapping(value="/m13", method = RequestMethod.POST, consumes = "application/json")
	public String m13(@RequestBody Personne personne){
		return personne.toString();
	}
	/*
	 * Récupérer les valeurs d'un POST en json V2
	 */
	@RequestMapping(value="/m14", method = RequestMethod.POST, consumes = "text/plain")
	public String m14(@RequestBody String requestBody) throws JsonParseException, JsonMappingException, IOException{
		Personne personne = new ObjectMapper().readValue(requestBody, Personne.class);
		return personne.toString();
	}
	/*
	 * Récupérer une session
	 */
	@RequestMapping(value="/m15", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String m15(HttpSession session){
		//On récupère l'objet clef (le compteur) de la session
		Object compteur = session.getAttribute("compteur");
		//Convertit en entier pour l'incrementer
		int intCompteur = compteur == null?0 : (Integer) compteur;
		intCompteur++;
		//On le remet dans la session
		session.setAttribute("compteur", intCompteur);
		//On retourne le resultat de l'action
		return String.valueOf(intCompteur);
	}
	
	/*
	 * gérer un objet de portée (scope) session(Autowired)
	 */
	@Autowired
	private SessionModel session;
	@RequestMapping(value = "/m16", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String m16(){
		session.setCompteur(session.getCompteur()+1);
		return String.valueOf(session.getCompteur());
	}
	/*
	 * gérer un objet de portée application (autowired)
	 */
	@Autowired
	private ApplicationModel application;
	@RequestMapping(value = "/m17", method = RequestMethod.GET, produces = " text/plain;charset=UTF-8")
	public String m17(){
		return String.valueOf(application.getCompteur().incrementAndGet());
	}
	/*
	 * Autre méthode pour récupérer des objets de portée session
	 * 1° : utilisation de @SessionAttribute
	 */
	@RequestMapping(value="/m18", method=RequestMethod.GET)
	public void m18 (HttpSession session){
		//ici on met la clé (container) dans la session
		session.setAttribute("container", new ContainerModel());
	}
	/*
	 * 2° : utilisation de @ModelAttribute
	 */
	@RequestMapping(value = "/m19", method=RequestMethod.GET)
	public String m19(@ModelAttribute("container") ContainerModel container){
		container.setCompteur(1 + container.getCompteur());
		return String.valueOf(container.getCompteur());
	}
	/*
	 * Injection d'information avec [@ModelAttribute]
	 * l'attribut "p" fera parti de toous les modeles de vue
	 */
	@ModelAttribute("p")
	public Personne getPersonne(){
		return new Personne(1,"Yanis", 102);
	}
	/*
	 * Instanciation de @ModelAttribute
	 * sera injecté s'il est dans la session
	 * sera injecté si le controleur a défini une méthode pour cet attribut
	 * peut provenir des champs de l'URL s'il existe un convertisseur String-->type de l'attribut
	 * sinon est construit avec le constructeur par défault
	 * ensuite les attributs du modèle sont initialisés
	 * avec les paramètres du GET ou du POST
	 * le résultat final fera parti du modèle produit par l'action
	 * 
	 * L'attribut "p" est injecté dans les arguments
	 */
	@RequestMapping(value = "/m20", method = RequestMethod.GET)
	public Personne m20(@ModelAttribute("p") Personne personne) {
		return personne;
	}
	/*
	 * L'attribut "p" fait automatiquement parti du modèle "M" de la vue "V"
	 */
	@RequestMapping(value="/m21", method=RequestMethod.GET)
	public String m21(Model model){
		return model.toString();
	}
	/*
	 * L'attribut du modele est initialisé avec les parametres du GET ou du POST
	 * Il fait parti du modele mais n'est pas initialisé
	 */
	@RequestMapping(value="/m22", method=RequestMethod.GET)
	public String m22(@ModelAttribute("param1")String p1, Model model){
		return model.toString();
	}
	/*
	 * L'attribut du modele est mis explicitement dans le modele
	 */
	@RequestMapping(value="/m23", method = RequestMethod.GET)
	public String m23 (String p2, Model model){
		model.addAttribute("param2", p2);
		return model.toString();
	}
	/*
	 * Les regles changent si le parametre de l'action est un objet
	 * L'attribut du modele est automatiquement mis dans l'objet
	 */
	@RequestMapping(value="/m23b", method = RequestMethod.GET)
	public String m23b(@ModelAttribute("unePersonne")Personne personne1, Model model){
		return model.toString();
	}
	/*
	 * La personne p1 est automatiquement mis dans le modele
	 * avec pour cle le nom de sa classe avec le 1°caractere en minuscule
	 */
	@RequestMapping(value="/m23c", method = RequestMethod.GET)
	public String m23c(Personne p1, Model model){
		return model.toString();
	}
	/*
	 * Validation d'un modèle
	 */
	@RequestMapping(value="/m24", method  = RequestMethod.GET)
	public Map<String, Object> m24(@Valid ActionModel01 data, BindingResult result){
		Map<String, Object> map = new HashMap<String, Object>();
		//Gestion des erreurs
		if(result.hasErrors()){
			StringBuffer buffer = new StringBuffer();
			//parcours de la liste des erreurs
			for(FieldError error : result.getFieldErrors()){
				buffer.append(String.format("[%s, %s,%s,%s,%s]",error.getField(),error.getRejectedValue(),
				String.join("-", error.getCodes()),error.getCode(),error.getDefaultMessage()));
			}
			map.put("error", buffer.toString());
		} else {
			//pas d'erreurs
			Map<String,Object> mapData = new HashMap<String,Object>();
			mapData.put("A", data.getA());
			mapData.put("B", data.getB());
			map.put("data", mapData);
		}
		return map;
	}
	/*
	 * Gestion des messages d'erreurs
	 */
	@RequestMapping(value="/m25",method=RequestMethod.GET)
	public Map<String, Object> m25(@Valid ActionModel01 data, BindingResult result, HttpServletRequest request)
	throws Exception{
		//Le dictionnaire des résultats
		Map<String,Object> map = new HashMap<String,Object>();
		//Le contexte de l'application Spring
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		//Locale
		Locale locale = RequestContextUtils.getLocale(request);
		//Les erreurs
		if(result.hasErrors()){
			StringBuffer buffer = new StringBuffer();
			for (FieldError error : result.getFieldErrors()) {
				// recherche du msg d'erreur à partir des codes d'erreurs
				// le msg est cherché dans les fichiers de messages
				// les codes d'erreur sous forme de tableau
				String[] codes = error.getCodes();
				String listCodes = String.join("-", codes);
				// recherche
				String msg=null;
				int i=0;
				while(msg==null && i<codes.length){
					try {
						msg = ctx.getMessage(codes[i], null, locale);
					} catch (Exception e){
						
					}
					i++;
				}
				//A t-on trouvé
				if (msg==null){
					throw new Exception(String.format("Indiquez un message pour l'un des codes [%s]", listCodes));
				}
				//On a trouve - On ajoute le message d'erreur à la chaine des messages d'erreurs
				buffer.append(String.format("[%s:%s:%s:%s]", locale.toString(), error.getField(), error.getRejectedValue(),
				String.join(" - ", msg)));
			}
			map.put("error", buffer.toString());
		} else {
			// ok
			Map<String, Object> mapData = new HashMap<String, Object>();
			mapData.put("a", data.getA());
			mapData.put("b", data.getB());
			map.put("data", mapData);
			}
		return map;
	}
	/*
	 * Injection de la locale dans le modele de l'action
	 */
	@RequestMapping(value="/m26",method=RequestMethod.GET)
	public String m26(Locale locale){
		return String.format("locale = %s", locale.toString());
	}
	
}
