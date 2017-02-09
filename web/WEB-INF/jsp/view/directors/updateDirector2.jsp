<spring:message code="title.updateDirector2" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
 	<h2><spring:message code="updateDirector.directorData"/></h2>
	<form:form method="POST" action="updateDirector2" modelAttribute="director">
  	<table>
  	<tr>
        <td><form:label path="id"><spring:message code="personal.id"/>:</form:label></td>
        <td><form:input path="id" disabled="true"/></td>
    </tr>
    <tr>
        <td><form:label path="firstName"><spring:message code="personal.firstName"/>:</form:label></td>
        <td><form:input path="firstName" disabled="true"/></td>
    </tr>
     <tr>
        <td><form:label path="lastName"><spring:message code="personal.lastName"/>:</form:label></td>
        <td><form:input path="lastName" disabled="true"/></td>
    </tr>
    <tr>
        <td><form:label path="birthDate"><spring:message code="personal.birthDateDisplay"/>:</form:label></td>
        <td><form:input path="birthDate" /></td>
        <td><form:errors path="birthDate" class="errors"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="form.valid"/>"/>
        </td>
    </tr>
</table>               
    <form:hidden path="id" />
    <form:hidden path="firstName" />
    <form:hidden path="lastName" />
</form:form>
  
  </jsp:attribute>
  
  

</template:main>




