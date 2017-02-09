<spring:message code="title.createMovie" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
  	<h2><spring:message code="createMovie.movieData"/></h2>
  	<form:form method="POST" action="createMovie" modelAttribute="movie">
   	<table>
    <tr>
        <td><form:label path="title"><spring:message code="movie.title"/></form:label></td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" class="errors" /></td>
    </tr>
     <tr>
        <td><form:label path="releaseDate"><spring:message code="movie.releaseDate"/></form:label></td>
        <td><form:input path="releaseDate" /></td>
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
	</form:form>
 
  </jsp:attribute>
  
</template:main>




