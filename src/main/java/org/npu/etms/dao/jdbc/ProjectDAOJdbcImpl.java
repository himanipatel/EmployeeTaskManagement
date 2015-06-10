package org.npu.etms.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.npu.etms.dao.ProjectDAO;
import org.npu.etms.domain.Employee;
import org.npu.etms.domain.Project;
import org.npu.etms.exceptions.ProjectAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("projectDaoJdbc")
public class ProjectDAOJdbcImpl implements ProjectDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@Autowired
	@Qualifier("projectRowMapper")
	private ProjectRowMapper prjRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("project")
				.usingGeneratedKeyColumns("projectId")
				.usingColumns("name", "managerId", "teammember", "description");
	}

	@Override
	public int getProjectCount() {
		String sql = "select count(*) FROM project";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<Project> findAllProjects() {
		String sql = "SELECT * FROM Project";
		List<Project> projectList = jdbcTemplate.query(sql, prjRowMapper);
		return projectList;
	}

	@Override
	public int insertProject(Project newProject) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(
				newProject);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		int projectId = newId.intValue();
		newProject.setProjectId(projectId);
		return projectId;

	}

	@Override
	public List<Project> findProjectByName(String name,
			boolean checkNotForSamePrj, int projectId) {
		String sql = "";
		MapSqlParameterSource params;
		if (checkNotForSamePrj) {
			sql = "SELECT * FROM project WHERE name = :name and projectId <> :projectId";
			params = new MapSqlParameterSource("name", name);
			params.addValue("projectId", projectId);
		} else {
			sql = "SELECT * FROM project WHERE name = :name";
			params = new MapSqlParameterSource("name", name);
		}

		List<Project> project = namedTemplate.query(sql, params, prjRowMapper);
		return project;
	}

	@Override
	public int updateProjectById(Project project) {
		String queryStr = "UPDATE project set name = :name,managerId = :managerId ,teammember = :teammember,description = :description where projectid = :projectid";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("projectid",
				project.getProjectId());
		params.addValue("name", project.getName());
		params.addValue("teammember", project.getTeamMember());
		params.addValue("managerId", project.getManagerId());
		params.addValue("description", project.getDescription());
		rowsAffected = namedTemplate.update(queryStr, params);
		return rowsAffected;
	}

	@Override
	public int removeProjectById(int projectId) {
		String queryStr = "DELETE FROM project WHERE projectId = :projectId";
		int rowsAffected;
		MapSqlParameterSource params = new MapSqlParameterSource("projectId",
				projectId);
		rowsAffected = namedTemplate.update(queryStr, params);
		return rowsAffected;
	}

	@Override
	public List<Project> findProjectsByManager(int managerId) {
		String sql = "SELECT * FROM project WHERE managerId = :managerId";
		MapSqlParameterSource params = new MapSqlParameterSource("managerId",
				managerId);

		List<Project> projectList = namedTemplate.query(sql, params,
				prjRowMapper);
		return projectList;
	}

	@Override
	public List<Project> findProjectByProjectId(int projectId) {
		String sql = "SELECT * FROM project WHERE projectId = :projectId";
		MapSqlParameterSource params = new MapSqlParameterSource("projectId",
				projectId);
		List<Project> project = namedTemplate.query(sql, params, prjRowMapper);
		return project;
	}
}