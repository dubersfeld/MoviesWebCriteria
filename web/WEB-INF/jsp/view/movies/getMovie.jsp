<spring:message code="title.getMovie" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
	<form:form method="POST" action="getMovie" modelAttribute="getMovie">     
    <table>
    <tr>
        <td><form:label path="title"><spring:message code="movie.title"/></form:label></td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" class="errors" /></td>
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




