<spring:message code="title.updateMovie2" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
  	<h2><spring:message code="updateMovie.movieData"/></h2>
	<form:form method="POST" action="updateMovie2" modelAttribute="movie">
   	<table>
   	<tr>
        <td><form:label path="id"><spring:message code="movie.id"/></form:label></td>
        <td><form:input path="id" disabled="true"/></td>
        <td><form:errors path="id" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="title"><spring:message code="movie.title"/></form:label></td>
        <td><form:input path="title" disabled="true"/></td>
        <td><form:errors path="title" class="errors" /></td>
    </tr>
     <tr>
        <td><form:label path="releaseDate"><spring:message code="movie.releaseDate"/></form:label></td>
        <td><form:input path="releaseDate" disabled="true"/></td>
        <td><form:errors path="releaseDate" class="errors"/></td>
    </tr>
    <tr>
        <td><form:label path="directorId"><spring:message code="movie.directorId"/></form:label></td>
        <td><form:input path="directorId" /></td>
        <td><form:errors path="directorId" class="errors"/></td>
    </tr>
    <tr>
        <td><form:label path="runningTime"><spring:message code="movie.runningTime"/></form:label></td>
        <td><form:input path="runningTime" /></td>
        <td><form:errors path="runningTime" class="errors"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="form.valid"/>"/>
        </td>
    </tr>
  	</table> 
  	<td><form:hidden path="id" /></td>
  	<td><form:hidden path="title" /></td>
  	<td><form:hidden path="releaseDate"/></td> 
	</form:form>
   
  </jsp:attribute>
  
</template:main>