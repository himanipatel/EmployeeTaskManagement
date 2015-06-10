package org.npu.etms.services;

import org.npu.etms.dao.EmployeeTaskDAO;
import org.npu.etms.domain.EmployeeTask;
import org.npu.etms.domain.Project;
import org.npu.etms.exceptions.UnknownProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskService")
@Transactional(readOnly = true)
public class EmployeeTaskServiceImpl implements EmployeeTaskService {

	@Autowired
	private ProjectService projectService;

	@Autowired
	@Qualifier("employeeTaskDaoJdbc")
	private EmployeeTaskDAO employeeTaskDao;

	@Transactional(readOnly = false)
	@Override
	public int assignProjectToEmployee(EmployeeTask employeeTask) {
		Project findProject = projectService.findProjectById(employeeTask
				.getProjectId());
		if (findProject == null) {
			throw new UnknownProjectException("No such project found.");
		} else {

			// insert new record
			int taskId = employeeTaskDao.insertEmployeeTask(employeeTask);
			
			// update team member count in project table
			int teammember = findProject.getTeamMember();
			teammember = teammember + 1;
			findProject.setTeamMember(teammember);
			projectService.updateProjectById(findProject);

			return taskId;
		}
	}
}
