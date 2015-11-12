package bndr;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import bndr.controller.Session;

@ManagedBean(name = "app")
@ApplicationScoped
public class App {

	private String data;
	
	public App() {
		
	}
	
	public void jsonStatus() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		Session session = (Session)context.getApplication().evaluateExpressionGet(context, "#{auth}", Session.class);

		String data = "{\"status\": \"not-logged-in\"}";
		
		if (session.isUserLoggedIn()) {
			if (null == this.data) {
				data = "{\"status\": \"waiting for app\"}";
				this.data = "foo";
			} else {
				data = "{\"status\": \"done\"}";
			}
		}
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.setResponseContentType("application/json");
		externalContext.setResponseCharacterEncoding("UTF-8");
		externalContext.getResponseOutputWriter().write(data);
		facesContext.responseComplete();
	}
	
}
