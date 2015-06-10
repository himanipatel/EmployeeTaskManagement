package org.npu.etms.services;

import java.util.List;

import org.npu.etms.domain.Project;

public interface ProjectService {
	public int createNewProject(Project newProject);

	public Project findProjectById(int projectId);
	
	public int updateProjectById(Project project);
	
	public int removeProjectById(int projectId);
	
	public List<Project> findAllProjects();
}
