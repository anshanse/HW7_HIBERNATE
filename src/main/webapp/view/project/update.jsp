<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update project</title>
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
		<h1>Find project to edit</h1>
		<form action="/project/updateFind" method="get" class="modal-form">
			<label class="modal-field">
			  Enter project ID
			  <input type="text" name="entityId" class="modal-input" pattern="\d+"/>
			</label>
			<label class="modal-field">
			  Enter project name
			  <input type="text" name="entityName" class="modal-input" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Search!</button>		
		</form>
		</c:if>
		
		<c:if test="${not empty entity}" >
		<h1>Update data</h1>
		
		<form action="/project/update" method="put" class="modal-form">
			<label class="modal-field" style="display:none;">
			  ID
			  <input type="text" name="entityId" class="modal-input" value="${entity.id}" />
			</label>
			<label class="modal-field">
			  Name
			  <input type="text" name="entityName" class="modal-input" value="${entity.name}" />
			</label>
			<label class="modal-field">
			  Cost
			  <input type="text" name="cost" class="modal-input" value="${entity.cost}" pattern="\d+" />
			</label>
			<label class="modal-field">
			  Start date (yyyy-mm-dd)
			  <input type="text" name="startDate" class="modal-input" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${entity.startDate}" />" pattern="\d{4}-\d{2}-\d{2}" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Update!</button>
		</form>
		</c:if>
		
		<div class="results">
			${message}
		</div>
    </main>
  </body>
</html>
