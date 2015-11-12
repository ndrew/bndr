package bndr;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//System.out.println("ServletContextListener destroyed");
	}
 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// fix for bean validation in JSF
//		ApplicationFactory applicationFactory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);

//		applicationFactory.getApplication().addDefaultValidatorId(BeanValidator.VALIDATOR_ID);
//		applicationFactory.getApplication().addValidator(BeanValidator.VALIDATOR_ID, BeanValidator.class.getName());
	}
}
