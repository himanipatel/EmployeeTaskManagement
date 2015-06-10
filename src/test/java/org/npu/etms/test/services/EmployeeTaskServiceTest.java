package org.npu.etms.test.services;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.npu.etms.domain.EmployeeTask;
import org.npu.etms.domain.Project;
import org.npu.etms.services.EmployeeTaskService;
import org.npu.etms.services.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:emsapp-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeTaskServiceTest {

	@Autowired
	private EmployeeTaskService taskService;
	private EmployeeTask setupEmpTask;

	@Autowired
	private ProjectService projectService;

	@Before
	public void setup() {
		setupEmpTask = new EmployeeTask();
		setupEmpTask.setEmployeeId(2);
		setupEmpTask.setProjectId(1);
		setupEmpTask.setDescription("implement streaming functionality.");
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = formatter.parse("07/31/2014");
			endDate = formatter.parse("08/15/2014");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		setupEmpTask.setStartDate(startDate);
		setupEmpTask.setDueDate(endDate);
		setupEmpTask.setStatus("New");
		setupEmpTask.setCategory("development");
	}

	@Test
	public void testAssignProjectToEmployee() {
		setupEmpTask.setEmployeeId(2);
		taskService.assignProjectToEmployee(setupEmpTask);
	}

	@Test
	public void testBadEmployeeTaskAssignment() {
		Project project = projectService.findProjectById(1);
		int oldTMCount = project.getTeamMember();

		setupEmpTask.setEmployeeId(3);
		try {
			taskService.assignProjectToEmployee(setupEmpTask);
		} catch (Exception ex) {
			System.out.println("Exception is: " + ex);
		}
		project = projectService.findProjectById(1);
		int newTMCount = project.getTeamMember();

		assertEquals(newTMCount, oldTMCount);
	}
}