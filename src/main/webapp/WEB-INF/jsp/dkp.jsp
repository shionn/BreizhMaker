<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<table class="pure-table pure-table-horizontal">
			<thead>
				<tr>
					<th><a href='<spring:url value="/dkp/sort/name"/>' style="text-decoration: none; color: black;">Personnage</a></th>
					<th><a href='<spring:url value="/dkp/sort/clazz"/>' style="text-decoration: none; color: black;">Classe</a></th>
					<th>Rang</th>
					<th><a href='<spring:url value="/dkp/sort/dkp"/>' style="text-decoration: none; color: black;">DKP</a></th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${players}" var="p">
					<tr class="${p.clazz}">
						<td>${p.name}</td>
						<td><img class="class" src='<spring:url value="/img/${p.clazz}.jpg"/>'/></td>
						<td>${p.rank}</td>
						<td>${p.dkp}</td>
						<td>
							<a href='<spring:url value="/dkp/${p.id}"/>'>Historique</a>
							<a class="pure-button button-error button-xsmall" href='<spring:url value="/dkp/rm/${p.id}"/>'>-</a>
							<a class="pure-button button-success button-xsmall" href='<spring:url value="/dkp/add/${p.id}"/>'>+</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:template>