<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="content" fragment="true"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css"
		integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47"
		crossorigin="anonymous">
<title>BreizhMaker</title>
</head>
<body>
	<div class="pure-menu pure-menu-horizontal">
		<a href="#" class="pure-menu-heading pure-menu-link">BreizhMaker</a>
		<ul class="pure-menu-list">
			<li class="pure-menu-item"><a href='<spring:url value="/dkp"/>' class="pure-menu-link">DKP</a></li>
			<li class="pure-menu-item"><a href="#" class="pure-menu-link">Raid</a></li>
			<li class="pure-menu-item"><a href='<spring:url value="/admin"/>' class="pure-menu-link">Admin</a></li>
		</ul>
	</div>
	<jsp:invoke fragment="content" />
</body>
</html>

