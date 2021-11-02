<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Find company</title>
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
    <c:import url="/view/company/navbarcompany.jsp"/>
    <main>
		<c:if test="${empty entity}" >
		<h1>Find company to edit</h1>
		<form action="/company/updateFind" method="get" class="modal-form">
			<label class="modal-field">
			  Enter company ID
			  <input type="text" name="entityId" class="modal-input" pattern="\d+"/>
			</label>
			<label class="modal-field">
			  Enter company name
			  <input type="text" name="entityName" class="modal-input" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Search!</button>		
		</form>
		</c:if>
		
		<c:if test="${not empty entity}" >
		<h1>Update data</h1>
		
		<form action="/company/update" method="put" class="modal-form">
			<label class="modal-field" style="display:none;">
			  ID
			  <input type="text" name="entityId" class="modal-input" value="${entity.id}" />
			</label>
			<label class="modal-field">
			  Name
			  <input type="text" name="entityName" class="modal-input" value="${entity.name}" />
			</label>
			<label class="modal-field">
			  City
			  <input type="text" name="entityCity" class="modal-input" value="${entity.city}" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Update!</button>
		</form>
		</c:if>
		
		<div class="results">
			<c:if test="${not empty company}" >	
		  
			<table class="table">
			<thead>
			  <tr>
				<th>ID</th>
				<th>Name</th>
				<th>City</th>
			  </tr>
			</thead>
			<tbody>
			  <tr>
				<td>${company.id}</td>
				<td>${company.name}</td>
				<td>${company.city}</td>
			  </tr>
			</tbody>
		  </table>
			
			</c:if>
			${message}
		</div>
    </main>
  </body>
</html>
