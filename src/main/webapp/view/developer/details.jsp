<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Developer detail</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/modern-normalize/1.0.0/modern-normalize.min.css"
    />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Raleway:wght@700&family=Roboto:wght@400;500;700;900&display=swap"
      rel="stylesheet"
    />
    <style>
            <%@include file="../css/styles.css" %>
    </style>
  </head>
  <body>
    <c:import url="/view/developer/navbarDev.jsp"/>
    <main>
	
      <table class="table">
			<thead>
			  <tr>
				<th>ID</th>
				<th>Name</th>
				<th>Age</th>
				<th>Gender</th>
				<th>Salary</th>
				<th>Other information</th>
			  </tr>
			</thead>
			<tbody>
				
			  <tr>
				<td>${entity.id}</td>
				<td>${entity.name}</td>
				<td>${entity.age}</td>
				<td>${entity.sex}</td>
				<td>${entity.salary}</td>
				<td>${entity.info}</td>
				</tr>
			  
			</tbody>
			</table>
	  
    </main>
  </body>
</html>
