package com.capgemini.employeeapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Employee addEmployee(Employee employee) {
		int count = jdbcTemplate.update("INSERT INTO employees  VALUES(?,?,?,?) ", new Object[] { employee.getEmployeeId(),
				employee.getEmployeeName(), employee.getEmployeeDepartment(), employee.getSalary() });
		if (count != 0)

			return employee;
		else
			return null;
	}

	public Employee updateEmployee(Employee employee) {
		int count = jdbcTemplate.update(
				"UPDATE employees SET employee_Name=?,employee_Department=? ,employee_salary=? Where employeeId=?",
				new Object[] { employee.getEmployeeName(), employee.getEmployeeDepartment(), employee.getSalary(),
						employee.getEmployeeId() });

		return count != 0 ? employee : findEmployeebyId(employee.getEmployeeId());
	}

	public boolean deleteEmployee(int employeeId) {
		int count = jdbcTemplate.update("delete employee FROM employees WHERE employeeId=?",
				new Object[] { employeeId });
		return count != 0;
	}

	public Employee findEmployeebyId(int employeeId) {
		return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE employeeId=?", new Object[] { employeeId },
				new EmployeeRowMapper());
	}

	public List<Employee> findAllEmployees() {
		return jdbcTemplate.query("SELECT * FROM employees", new Object[] {}, new EmployeeRowMapper());
	}

	private class EmployeeRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt(1));
			employee.setEmployeeName(rs.getString(2));
			employee.setEmployeeDepartment(rs.getString(3));
			employee.setSalary(rs.getDouble(4));
			return employee;
		}

	}
}
