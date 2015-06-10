package org.npu.etms.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.npu.etms.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("projectRowMapper")
public class ProjectRowMapper implements RowMapper<Project> {

	@Autowired
	@Qualifier("employeeRowMapper")
	private EmployeeRowMapper empMapper;

	@Override
	public Project mapRow(ResultSet resultSet, int row) throws SQLException {
		Project project = new Project();
		project.setProjectId(resultSet.getInt("projectId"));
		project.setName(resultSet.getString("name"));
		project.setManagerId(resultSet.getInt("managerId"));
		project.setTeamMember(resultSet.getInt("teamMember"));
		project.setDescription(resultSet.getString("description"));
		return project;
	}

}
