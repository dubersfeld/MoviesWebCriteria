<spring:message code="title.directorMovies" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
	<h2><spring:message code="advancedQueries.directorMovies"/></h2>
	<form:form method="POST" action="directorMovies" 
									modelAttribute="directorName">     
    <table>
    <tr>
        <td><form:label path="firstName"><spring:message code="personal.firstName"/></form:label></td>
        <td><form:input path="firstName" /></td>
        <td><form:errors path="firstName" class="errors" /></td>
    </tr>
     <tr>
        <td><form:label path="lastName"><spring:message code="personal.lastName"/></form:label></td>
        <td><form:input path="lastName" /></td>
        <td><form:errors path="lastName" class="errors" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="form.valid"/>"/>
        </td>
    </tr>
    </table>
	</form:form>
	
  </jsp:attribute>
  
</template:main>




