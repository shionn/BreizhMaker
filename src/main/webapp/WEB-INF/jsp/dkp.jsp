<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<table class="pure-table pure-table-horizontal dkp">
			<thead>
				<tr>
					<th>Personnage</th>
					<th>Classe</th>
					<th>DKP</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${players}" var="p">
					<tr class="clazz">
						<td>${p.name}</td>
						<td><img src='<spring:url value="/img/${p.clazz}.jpg"/>'/></td>
						<td>${p.dkp}</td>
						<td>
							<a class="pure-button button-error button-xsmall" href='<spring:url value="/dkp/rm/${p.id}"/>'>-</a>
							<a class="pure-button button-success button-xsmall" href='<spring:url value="/dkp/add/${p.id}"/>'>+</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:template>