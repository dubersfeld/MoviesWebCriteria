<spring:message code="title.createActorMovie" var="pageTitle" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>
  
  	<form:form method="POST" action="createActorMovieTrans" 
									modelAttribute="actorMovieTrans">     
    <table>
    <tr>
        <td><form:label path="firstName">First Name</form:label></td>
        <td><form:input path="firstName" /></td>
        <td><form:errors path="firstName" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" /></td>
        <td><form:errors path="lastName" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="birthDate">Birth Date</form:label></td>
        <td><form:input path="birthDate" /></td>
        <td><form:errors path="birthDate" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td>
        <td><form:errors path="title" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="releaseDate">Release Date</form:label></td>
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




