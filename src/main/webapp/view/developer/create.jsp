<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create developer</title>
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
	  <c:if test="${empty entity}" >
		  <h1>Create developer</h1>
		  <form action="/developer/create" method="post" class="modal-form">
			<label class="modal-field">
			  Enter name
			  <input type="text" name="name" class="modal-input" required />
			</label>
			<label class="modal-field">
			  Enter age
			  <input type="text" name="age" class="modal-input" pattern="\d+" />
			</label>
			<label class="modal-field">
			  Enter gender (m/f)
			  <input type="text" name="sex" class="modal-input" pattern="[mf]" />
			</label>
			<label class="modal-field">
			  Enter salary
			  <input type="text" name="salary" class="modal-input" pattern="\d+"/>
			</label>
			<label class="modal-field">
			  Enter additional information
			  <input type="text" name="info" class="modal-input" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Create!</button>
		  </form>	  	  
	  </c:if>
	  
		<c:if test="${not empty existEntity}">
		<p>Developer with name <a href="${pageContext.request.contextPath}/developer/get?id=${existCompany.id}">${existEntity.name}</a> exists</p>
		</c:if>
		
	  <c:if test="${not empty entity}" >	  
			<h1> Was create developer with parametrs:</h1>
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
	</c:if>
    </main>
  </body>
</html>
