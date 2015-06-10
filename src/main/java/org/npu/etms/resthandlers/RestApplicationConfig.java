package org.npu.etms.resthandlers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.npu.etms.exceptions.ProjectAlreadyExistException;
import org.npu.etms.exceptions.UnknownManagerException;


/*   See web.xml file for Jersey configuration  */
/*   Need to register classes with @PATH annotations and other JAX-RS components */
@ApplicationPath("/")
public class RestApplicationConfig extends Application {
	private Set<Class<?>> restClassSet = new HashSet<Class<?>>();
	
	public RestApplicationConfig() {
		/* AuthorizationFilter is a servlet filter that could automatically perform authorization on all incoming requests */
//		restClassSet.add(AuthorizationFilter.class);
		restClassSet.add(JacksonFeature.class);
		restClassSet.add(ProjectRestHandler.class);
		restClassSet.add(UnknownManagerException.class);
		restClassSet.add(ProjectAlreadyExistException.class);
	}
	
	public Set<Class<?>> getClasses() {
		return restClassSet;
	}
}
