<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Find customer</title>
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
    <c:import url="/view/customer/navbarCustomer.jsp"/>
    <main>
		  <h1>Find customer</h1>
		  <form action="/customer/find" method="get" class="modal-form">
			<label class="modal-field">
			  Enter customer ID
			  <input type="text" name="entityId" class="modal-input" pattern="\d+"/>
			</label>
			<label class="modal-field">
			  Enter customer name
			  <input type="text" name="entityName" class="modal-input" />
			</label>
			<button type="submit" class="modal-submit-btn btn">Search!</button>
		
		
		</form>
		<div class="results">
			<c:if test="${not empty entity}" >	
		  
			<table class="table">
			<thead>
			  <tr>
				<th>ID</th>
				<th>Name</th>
			  </tr>
			</thead>
			<tbody>
			  <tr>
				<td>${entity.id}</td>
				<td>${entity.name}</td>
			  </tr>
			</tbody>
		  </table>
			
			</c:if>
			${message}
		</div>
    </main>
  </body>
</html>
