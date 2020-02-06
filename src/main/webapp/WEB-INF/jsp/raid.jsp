<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<c:forEach items="${runnings}" var="raid" varStatus="i">
			<form:form method="POST" class="pure-form pure-form-aligned">
				<input type="hidden" name="id" value="${raid.id}">
				<table>
					<tbody>
						<c:forEach items="${raid.entries}" var="entry">
							<tr>
								<td>${entry.player.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form:form>
		</c:forEach>
		<spring:url value="/raid/add" var="url"/>
		<form:form method="POST" class="pure-form pure-form-aligned" action="${url}">
			<fieldset>
				<legend>Creer un Raid</legend>
				<div class="pure-control-group">
					<label for="name">Nom</label>
					<input type="text" name="name" required="required">
				</div>
				<div class="pure-control-group">
					<label for="start">Debut</label>
					<input type="datetime" name="start" required="required">
				</div>
				<div class="pure-control-group">
					<label for="end">Fin</label>
					<input type="datetime" name="end" required="required">
				</div>
				<div class="pure-controls">
					<button type="submit" class="pure-button pure-button-primary">Ajouter un raid</button>
				</div>
			</fieldset>
		</form:form>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script type="text/javascript">
			$("input[type=datetime]").datetimepicker({dateFormat:"dd/mm/yy", timeFormat:"HH:mm"});
		</script>
	</jsp:attribute>
</t:template>