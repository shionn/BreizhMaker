<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<c:forEach items="${rosters}" var="players" varStatus="i">
			<table class="pure-table pure-table-horizontal">
				<thead>
					<tr>
						<th colspan="3">Roster ${i.index}</th>
					</tr>
					<tr>
						<th>Personnage</th>
						<th>Classe</th>
						<th>Rang</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${players}" var="p">
						<tr class="${p.clazz}">
							<td>${p.name}</td>
							<td><img class="class" src='<spring:url value="/img/${p.clazz}.jpg"/>'/></td>
							<td>${p.rank}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
	</jsp:attribute>
</t:template>