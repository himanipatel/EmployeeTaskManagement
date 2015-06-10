package org.npu.etms.dao.jdbc;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.npu.etms.dao.EmployeeTaskDAO;
import org.npu.etms.domain.EmployeeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("employeeTaskDaoJdbc")
public class EmployeeTaskDaoJdbcImpl implements EmployeeTaskDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@Autowired
	@Qualifier("employeeTaskRowMapper")
	private EmployeeTaskRowMapper empTaskRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("emptask")
				.usingGeneratedKeyColumns("taskid")
				.usingColumns("employeeid", "projectid", "description",
						"startDate", "duedate", "status",
						"reasonformissingduedate", "category", "createddate");
	}

	@Override
	public EmployeeTask findPendingTaskByProjectId(int projectId) {
		String sql = "SELECT * FROM emptask "
				+ "WHERE projectId = :projectId and status = :status";
		MapSqlParameterSource params = new MapSqlParameterSource("projectId",
				projectId);
		params.addValue("status", "pending");
		EmployeeTask employeeTask = namedTemplate.queryForObject(sql, params,
				empTaskRowMapper);
		return employeeTask;
	}

	@Override
	public int insertEmployeeTask(EmployeeTask empTask) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(empTask);
		Number newId = jdbcInsert.executeAndReturnKey(params);
		int taskId = newId.intValue();
		empTask.setTaskId(taskId);
		return taskId;
	}
}