package org.npu.etms.test.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.npu.etms.dao.ProjectDAO;
import org.npu.etms.domain.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:emsapp-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectDAOTest {

	@Autowired
	@Qualifier("projectDaoJdbc")
	private ProjectDAO projectDAO;
	private Project setupProject;

	@Before
	public void setup() {
		setupProject = new Project();
		setupProject.setName("Test Automation 6");
		setupProject.setTeamMember(5);
		setupProject.setManagerId(2);
	}

	@Test
	public void testProjectCount() {
		int cnt = projectDAO.getProjectCount();
		System.out.println("Total Project:" + cnt);
	}

	@Test
	public void testFindAllProjects() {
		List<Project> projectList = projectDAO.findAllProjects();
		int prjCnt = projectDAO.getProjectCount();
		assertTrue(projectList.size() == prjCnt);
		System.out.println(projectList);
	}

	@Test
	public void testInsertProject() {
		projectDAO.insertProject(setupProject);
		System.out.println("New Project added with id:"
				+ setupProject.getProjectId());
	}

	@Test
	public void testUpdateProject() {
		List<Project> projectList = projectDAO.findProjectByProjectId(1);
		int rowAffected = 0;
		if (projectList != null && projectList.size() > 0) {
			Project prj = projectList.get(0);
			prj.setName("Video Camera Streaming 7");
			rowAffected = projectDAO.updateProjectById(prj);
		}

		assertTrue(rowAffected == 1);
	}

	@Test
	public void testDeleteProject() {
		int rowAffected = projectDAO.removeProjectById(8);
		assertTrue(rowAffected == 1);
	}

	@Test
	public void testFindProjectByName() {
		List<Project> project = projectDAO.findProjectByName("hrms",false,0);
		System.out.println(project);
	}

	@Test
	public void testFindProjectsByManager() {
		List<Project> projectList = projectDAO.findProjectsByManager(2);
		System.out.println(projectList);
	}
}