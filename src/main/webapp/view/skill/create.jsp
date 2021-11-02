<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create skill</title>
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
    <c:import url="/view/skill/navbarSkill.jsp"/>
    <main>
	  <c:if test="${empty entity}" >
		  <h1>Create skill</h1>
		  <form action="/skill/create" method="post" class="modal-form">
			<label class="modal-field">
			  Enter name
			  <input type="text" name="name" class="modal-input" required />
			</label>
			<label class="modal-field">
			  Enter grade
			  <input type="text" name="grade" class="modal-input" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Create!</button>
		  </form>	  	  
	  </c:if>
	  
		<c:if test="${not empty existEntity}">
		<p>skill with name <a href="${pageContext.request.contextPath}/skill/get?id=${existCompany.id}">${existEntity.name}</a> exists</p>
		</c:if>
		
	  <c:if test="${not empty entity}" >	  
			<h1> Was create skill with parametrs:</h1>
			<table class="table">
			<thead>
			  <tr>
				<th>ID</th>
				<th>Name</th>
				<th>Grade</th>
			  </tr>
			</thead>
			<tbody>
				
			  <tr>
				<td>${entity.id}</td>
				<td>${entity.name}</td>
				<td>${entity.grade}</td>
				</tr>
			</tbody>
			</table>
	</c:if>
    </main>
  </body>
</html>
