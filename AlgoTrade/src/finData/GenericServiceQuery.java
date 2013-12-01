package finData;

import java.io.*;
import java.net.*;

public abstract class GenericServiceQuery {
	private InputStream URLStream; 
	private String connstr;
	public GenericServiceQuery(QueryDetail query){
		connstr = connstrConstructor(query);
		setURLStream(connstr);
	}
	public abstract String connstrConstructor(QueryDetail query);
	public void setURLStream (String connstr){
		
		try {
			URLStream = new URL(connstr).openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
			
	}
	
	public InputStream getURLStream(){
		return URLStream;
	}
}
