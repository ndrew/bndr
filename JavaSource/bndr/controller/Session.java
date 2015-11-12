package bndr.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author ndrw
 */
@ManagedBean (name = "auth")
@SessionScoped
public class Session {

	/**
	 * 
	 */
	public Session() {}
	

	/**
	 * @param event
	 */
	public void onLogin(ActionEvent event) {
		// usually for component development
		if ("baz".equals(this.username)) { // this.isUserLoggedIn()
		    System.out.println("Quack!");
			 /*FacesContext context  = FacesContext.getCurrentInstance();
		     UIViewRoot view = context.getViewRoot();
		     
		     HtmlInputText loginEl = (HtmlInputText)view.findComponent("login:username");
		     loginEl.setValue("Quack!"); */
			
		    
			
		} 
	}
	
	
	
	public String login() {
		if (this.isUserLoggedIn()) {
			return "success";
		} else {
			return "error";
		}
		
	}
	
	public void validateLogin(ComponentSystemEvent event) {
		if (!this.isUserLoggedIn() && !"baz".equals(this.username)) {
			// how to know that @NotEmpty validations failed  
			
			UIComponent component = event.getComponent(); // a form
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Summary");
			msg.setDetail("Quack-quack, motherfucker — incorrect login!");
		
			// send message
			context.addMessage(component.getClientId(context), msg);
		}
	}

	
	/*
	 * properties
	 */
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty(message = "Login shouldn't be empty")
	private String username;
	
	@NotEmpty(message = "Password shouldn't be empty")
	private String password;

	//////////////
	// helpers
	
	public boolean isUserLoggedIn() {
		return null != this.username && "foo".equals(this.username);
	}
	
	
	public void ajaxTest(AjaxBehaviorEvent event) {
		System.out.println("FOOOO!");
		
		HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(button.getClientId());

		button.setValue("Quack-quack!");
	}
	
	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void check(ComponentSystemEvent event) throws IOException {
		//
		// <!> Server side-routing done in the JSF pre-render step.
		//
		if (!this.isUserLoggedIn()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)externalContext.getRequest();
			String url = request.getRequestURI();
			System.out.println("BNDR: authentication check: " + url);

			if (!url.endsWith("login.jsf")) { // the URL is any other then login page
				String redirectURL = "login.jsf";
		
				System.out.println("BNDR: — user is not logged in!");
				System.out.println("BNDR: — redirecting to " + redirectURL );

				externalContext.redirect(redirectURL);
			}
		}
	}

}
