package eu.trentorise.smartcampus.universiadi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EasyTokenManger {
	
	
	private String auth_token;
	
	
	
	@Autowired
	@Value("${fix.juniper.token}")
	private String client_juniper_token;
	
	
	private String client_sc_token;
	
	
	 private static EasyTokenManger istanza = null;
	 
	    //Il costruttore private impedisce l'istanza di oggetti da parte di classi esterne
	    private EasyTokenManger() {}
	 
	    // Metodo della classe impiegato per accedere al Singleton
	    public static synchronized EasyTokenManger getEasyTokenManger() {
	        if (istanza == null) 
	            istanza = new EasyTokenManger();
	        return istanza;
	    }
	    
	    
	    
	    
	    public String getAuthToken(){
			return auth_token;
	    	
	    }
	    
	    public String getClientSmartCampusToken(){
			return client_sc_token;
	    	
	    }
	    
	    public String getClientJuniperToken(){
			return client_juniper_token;
	    	
	    }

}
