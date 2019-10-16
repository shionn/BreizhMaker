<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>BreizhMaker</title>
</head>
<body>
	<form:form>
		<input type="text" name="url" placeholder="url"/>
		<input type="submit" name="Go">
	</form:form>
	<c:if test="${item!=null}">
		<h1>${item.id}</h1>
		<table style="border: solid 1px black;padding: 1px;">
			<tr>
				<th>id</th>
				<th>name</th>
				<c:forEach items="${statTypes}" var="t">
					<th>${t}</th>
				</c:forEach>
			</tr>
			<tr>
				<td>${item.id}</td>
				<td>${item.name}</td>
				<c:forEach items="${statTypes}" var="t">
					<td>${item.getStatValue(t)}</td>
				</c:forEach>
			</tr>
		</table>
		<h2>csv</h2>
		<pre style="border: solid 1px black;padding: 4px;">${item.asTabs}</pre>
		<h2>brut</h2>
		<pre style="border: solid 1px black;padding: 4px;"><c:forEach items="${item.stats}" var="s">${s.type} ${s.value}
</c:forEach></pre>
	</c:if>
</body>
</html>