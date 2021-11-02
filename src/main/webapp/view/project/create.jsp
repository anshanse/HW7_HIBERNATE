<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create project</title>
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
    <c:import url="/view/project/navbarProject.jsp"/>
    <main>
	  <c:if test="${empty entity}" >
		  <h1>Create project</h1>
		  <form action="/project/create" method="post" class="modal-form">
			<label class="modal-field">
			  Enter name
			  <input type="text" name="name" class="modal-input" required />
			</label>
			<label class="modal-field">
			  Enter cost
			  <input type="text" name="cost" class="modal-input" pattern="\d+" />
			</label>
			<label class="modal-field">
			  Enter start date (yyyy-mm-dd)
			  <input type="text" name="startDate" class="modal-input" pattern="\d{4}-\d{2}-\d{2}"/>
			</label>
			<button type="submit" class="modal-submit-btn btn">Create!</button>
		  </form>	  	  
	  </c:if>
	  
		<c:if test="${not empty existEntity}">
		<p>Project with name <a href="${pageContext.request.contextPath}/project/get?id=${existCompany.id}">${existEntity.name}</a> exists</p>
		</c:if>
		
	  <c:if test="${not empty entity}" >	  
			<h1> Was create project with parametrs:</h1>
			<table class="table">
			<thead>
			  <tr>
				<th>ID</th>
				<th>Name</th>
				<th>Cost</th>
				<th>Start date</th>
			  </tr>
			</thead>
			<tbody>
				
			  <tr>
				<td>${entity.id}</td>
				<td>${entity.name}</td>
				<td>${entity.cost}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.startDate}" /></td>
				</tr>
			</tbody>
			</table>
	</c:if>
    </main>
  </body>
</html>
