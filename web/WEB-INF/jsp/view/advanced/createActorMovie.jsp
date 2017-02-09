<spring:message code="title.createActorMovie" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
	<form:form method="POST" action="createActorMovie" 
									modelAttribute="actorMovie">     
    <table>
    <tr>
        <td><form:label path="actorId"><spring:message code="createActorMovie.actorId"/></form:label></td>
        <td><form:input path="actorId" /></td>
        <td><form:errors path="actorId" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="movieId"><spring:message code="createActorMovie.filmId"/></form:label></td>
        <td><form:input path="movieId" /></td>
        <td><form:errors path="movieId" class="errors" /></td>
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




