package org.npu.etms.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.npu.etms.domain.EmployeeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("employeeTaskRowMapper")
public class EmployeeTaskRowMapper implements RowMapper<EmployeeTask> {

	@Autowired
	@Qualifier("projectRowMapper")
	private ProjectRowMapper prjMapper;

	@Autowired
	@Qualifier("employeeRowMapper")
	private EmployeeRowMapper empMapper;

	@Override
	public EmployeeTask mapRow(ResultSet resultSet, int row)
			throws SQLException {
		EmployeeTask empTask = new EmployeeTask();
		empTask.setTaskId(resultSet.getInt("taskId"));
		empTask.setDescription(resultSet.getString("description"));
		empTask.setActualEndDate(resultSet.getDate("actualEndDate"));
		empTask.setActualStartDate(resultSet.getDate("actualStartDate"));
		empTask.setStartDate(resultSet.getDate("startDate"));
		empTask.setDueDate(resultSet.getDate("dueDate"));
		empTask.setStatus(resultSet.getString("status"));
		empTask.setCategory(resultSet.getString("category"));
		empTask.setReasonOfMissingDueDate(resultSet
				.getString("reasonOfMissingDueDate"));
		empTask.setCreatedDate(resultSet.getDate("createdDate"));
		empTask.setModifiedDate(resultSet.getDate("modifiedDate"));
		empTask.setProjectId(resultSet.getInt("projectId"));
		empTask.setEmployeeId(resultSet.getInt("employeeId"));
		return empTask;
	}
}
