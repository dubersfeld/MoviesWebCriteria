<spring:message code="title.getSingleMovie" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
	<form:form method="POST" action="getSingleMovie" modelAttribute="movieKey">     
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
        <td colspan="2">
            <input type="submit" value="<spring:message code="form.valid"/>"/>
        </td>
    </tr>
    </table>
	</form:form>

  </jsp:attribute>
  
</template:main>




