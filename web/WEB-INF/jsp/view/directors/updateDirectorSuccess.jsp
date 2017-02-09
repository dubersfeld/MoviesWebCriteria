<spring:message code="title.successUpdateDirector" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
  
  	<h2><spring:message code="director.directorData"/></h2>
   	<table>
    <tr>
        <td><spring:message code="personal.firstName"/></td>
        <td>${director.firstName}</td>
    </tr>
    <tr>
        <td><spring:message code="personal.lastName"/></td>
        <td>${director.lastName}</td>
    </tr>
    <tr>
        <td><spring:message code="personal.birthDateDisplay"/></td>
        <td><fmt:formatDate value="${director.birthDate}" pattern="${datePattern}" type="date" /></td>
    </tr>
    <tr>
        <td><spring:message code="personal.id"/></td>
        <td>${director.id}</td>
    </tr>
	</table>  
 
  </jsp:attribute>
  
</template:main>