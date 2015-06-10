package org.npu.etms.dao;

import java.util.List;

import org.npu.etms.domain.Project;

public interface ProjectDAO {
	public List<Project> findAllProjects();

	public int insertProject(Project newProject);

	public int updateProjectById(Project project);

	public int removeProjectById(int projectId);

	public List<Project> findProjectsByManager(int managerId);

	public List<Project> findProjectByProjectId(int projectId);

	public int getProjectCount();

	public List<Project> findProjectByName(String name, boolean checkNotForSamePrj,
			int projectId);
}
