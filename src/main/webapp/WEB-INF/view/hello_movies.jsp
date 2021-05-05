<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head></head>

<body>
	<h1>Hello Movies</h1>
	<br>
	<h2>${discoverMovies[1].title}</h2>
	<br>
	<h1>Movie id: ${discoverMovies[1].id}</h1>
	<form:form method="POST" action="/movies/search" modelAttribute="formStringHandler">
	    <form:label path="content">Content</form:label>
	    <form:input path="content"/>
	    <input type="submit"/>
	</form:form>
</body>
</html>