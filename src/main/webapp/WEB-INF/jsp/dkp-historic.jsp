<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<table class="pure-table pure-table-horizontal pure-table-striped dkp-history">
			<thead>
				<tr>
					<th>Date</th>
					<th>Raison</th>
					<th>Valeur (${player.dkp})</th>
					<th>Auteur</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${player.historic}" var="h">
					<tr>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${h.date}"/></td>
						<td>${h.reason}</td>
						<td>${h.value}</td>
						<td>${h.author}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:template>