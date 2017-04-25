package fr.simplon.controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import fr.simplon.domain.ActionModel02;
import fr.simplon.domain.ActionModel03;

@RestController
@SessionAttributes({"container"})
public class HibernateValidator {

	/*
	 * Validation d'un modèle avec Hibernate Validator
	 */
	@RequestMapping(value="/m27", method=RequestMethod.POST)
	public Map<String,Object> m27(@Valid ActionModel02 data, BindingResult result){
		Map<String,Object> map = new HashMap<String,Object>();
		if(result.hasErrors()){
			for(FieldError error : result.getFieldErrors()){
				map.put(error.getField(), String.format("[message:%s,code:%s]", error.getDefaultMessage(),
						String.join("|", error.getCodes())));
			}
		} else {
			map.put("data", data);
		}
		return map;
	}
	/*
	 * Externalisation des messages d'erreurs
	 */
	@CrossOrigin(origins="*")
	@RequestMapping(value="/m28", method=RequestMethod.POST)
	public Map<String, Object> m28(@Valid ActionModel03 data, 
			BindingResult result, HttpServletRequest request){
		
		Map<String, Object> map = new HashMap<String, Object>();
		//Le contexte de l'apllication Spring
		WebApplicationContext ctx = WebApplicationContextUtils.
				getWebApplicationContext(request.getServletContext());
		//locale
		Locale locale = RequestContextUtils.getLocale(request);
		//des erreurs?
		if (result.hasErrors()){
			for (FieldError error : result.getFieldErrors()){
				//recherche du message d'erreur à partir des codes erreurs
				//le msg est cherché dans les fichiers de messages
				//les codes d'erreurs sous forme de tableau
				
				String[] codes = error.getCodes();
				//sous forme de chaine
				String listCodes = String.join(" - ", codes);
				//recherche
				String msg = null;
				int i =0;
				while(msg == null && i< codes.length){
					try{
						msg = ctx.getMessage(codes[i], null, locale);
					} catch (Exception e){
						
					}
					i++;
				}
			
			//a-t-on trouvé
			if (msg == null) {
				msg = String.format("Indiquez un message pour l'un des codes [%s]", listCodes);
			}
				map.put(error.getField(), msg);
			
			}
		} else {
			//pas d'erreurs
			map.put("data", data);
		}
		
		return map;
	}
	

}
