<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee App</title>
</head>
<body bgcolor="cyan">
<h1> Add New Employee</h1>
<form:form action="addEmployee" method="post" modelAttribute="employee">
<form:label path="employeeId">EmployeeId:</form:label>
<form:input path="employeeId"/><br><br>

<form:label path="employeeName">Employee Name:</form:label>
<form:input path="employeeName"/><br><br>

<form:label path="employeeDepartment">Employee Department:</form:label>
<form:input path="employeeDepartment"/><br><br>

<form:label path="salary">Employee Salary:</form:label>
<form:input path="salary"/><br><br>
<input type="submit" value="Add Employee"/>
</form:form>
</body>
</html>