package org.npu.etms.resthandlers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.npu.etms.domain.Project;
import org.npu.etms.exceptions.ProjectAlreadyExistException;
import org.npu.etms.exceptions.UnknownManagerException;
import org.npu.etms.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/etmsRest")
public class ProjectRestHandler {
	@Autowired
	private ProjectService projectService;

	@GET
	@Path("/project/{id}")
	@Produces("application/xml, application/json")
	public Project getProject(@PathParam("id") int id) {
		Project prj = null;
		prj = projectService.findProjectById(id);
		return prj;
	}

	@POST
	@Path("/project")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addProject(Project newProject) {
		ResponseBuilder respBuilder;
		try {
			projectService.createNewProject(newProject);
			respBuilder = Response.status(Status.CREATED);
			respBuilder.entity(newProject);
		} catch (UnknownManagerException managerEx) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} catch (ProjectAlreadyExistException prjEx) {
			respBuilder = Response.status(Status.NOT_FOUND);
		}
	
		return respBuilder.build();
	}

	@PUT
	@Path("/project/{id}")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response updateStudent(@PathParam("id") int id, Project newProject) {
		ResponseBuilder respBuilder;
		int numRowsAffected;

		numRowsAffected = projectService.updateProjectById(newProject);
		if (numRowsAffected == 0) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
			respBuilder = Response.status(Status.OK);
		}		
		return respBuilder.build();
	}

	@DELETE
	@Path("/project/{id}")
	public Response deleteStudent(@PathParam("id") int id) {
		int numRowsAffected;
		ResponseBuilder respBuilder;

		numRowsAffected = projectService.removeProjectById(id);
		if (numRowsAffected == 0) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
		}
		return respBuilder.build();
	}
}