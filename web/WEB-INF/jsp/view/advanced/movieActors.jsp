<spring:message code="title.movieActors" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
	<h2><spring:message code="advancedQueries.movieActors"/></h2>
	<form:form method="POST" action="movieActors" 
									modelAttribute="movieKey">     
    <table>
    <tr>
        <td><form:label path="title"><spring:message code="movie.title"/></form:label></td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="releaseDate"><spring:message code="movie.releaseDate"/></form:label></td>
        <td><form:input path="releaseDate" /></td>
        <td><form:errors path="releaseDate" class="errors" /></td>
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