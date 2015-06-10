package org.npu.etms.services;

import java.util.List;

import org.npu.etms.dao.ProjectDAO;
import org.npu.etms.domain.Employee;
import org.npu.etms.domain.Project;
import org.npu.etms.exceptions.ProjectAlreadyExistException;
import org.npu.etms.exceptions.UnknownManagerException;
import org.npu.etms.exceptions.UnknownProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	@Qualifier("projectDaoJdbc")
	private ProjectDAO projectDao;
	@Autowired
	private EmployeeService employeeService;

	@Transactional(readOnly = false)
	@Override
	public Project findProjectById(int projectId) {
		List<Project> prjList = projectDao.findProjectByProjectId(projectId);
		if (prjList != null && prjList.size() > 0) {
			return prjList.get(0);
		} else {
			throw new UnknownProjectException(
					"No Project found with project id" + projectId);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public int createNewProject(Project newProject) {
		Employee findEmp = employeeService.findEmployeeById(newProject
				.getManagerId());
		if (findEmp == null) {
			throw new UnknownManagerException("No such manager found.");
		} else {
			List<Project> prj = projectDao.findProjectByName(
					newProject.getName(), false, 0);
			if (prj != null && prj.size() > 0) {
				throw new ProjectAlreadyExistException(
						"Project name is already exist.");
			} else {
				int projectId = projectDao.insertProject(newProject);
				return projectId;
			}
		}
	}

	@Transactional
	@Override
	public int updateProjectById(Project project) {
		Employee findEmp = employeeService.findEmployeeById(project
				.getManagerId());
		if (findEmp == null) {
			throw new UnknownManagerException("No such manager found.");
		} else {
			List<Project> prj = projectDao.findProjectByName(project.getName(),
					true, project.getProjectId());
			if (prj != null && prj.size() > 0) {
				throw new ProjectAlreadyExistException(
						"Project name is already exist.");
			} else {

				int rowAffected = projectDao.updateProjectById(project);
				return rowAffected;
			}
		}
	}

	@Transactional
	@Override
	public int removeProjectById(int projectId) {
		int rowAffected = projectDao.removeProjectById(projectId);
		return rowAffected;
	}

	@Transactional(readOnly = false)
	@Override
	public List<Project> findAllProjects() {
		List<Project> prjList = null;
		prjList = projectDao.findAllProjects();
		return prjList;
	}
}