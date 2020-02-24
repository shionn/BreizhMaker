<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<c:forEach items="${runnings}" var="raid" varStatus="i">
			<spring:url value="/raid/update" var="url"/>
			<form:form method="POST" class="pure-form pure-form-aligned" modelAttribute="raid" action="${url}">
				<fieldset>
					<legend>${raid.name}</legend>
					<input type="hidden" name="id" value="${raid.id}">
					<div class="pure-control-group">
						<label for="name">Nom</label>
						<input type="text" name="name" value="${raid.name}">
					</div>
					<div class="pure-control-group">
						<label for="start">Debut</label>
						<input type="datetime" name="start" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${raid.start}"/>">
					</div>
					<div class="pure-control-group">
						<label for="end">Fin</label>
						<input type="datetime" name="end" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${raid.end}"/>" >
					</div>
					<div class="pure-control-group">
						<label for="number">Nb boss tué</label>
						<input type="text" name="boss" value="${raid.boss}">
					</div>
					<div class="pure-controls">
						<label for="end">
							<input type="checkbox" name="running" <c:if test="${raid.running}"> checked="checked"</c:if>>
							En cours (une fois décoché le raid n'est plus éditable et les dkp sont ajouté).
						</label>
					</div>
					<div class="pure-controls">
						<button type="submit" class="pure-button pure-button-primary">Sauvegarder</button>
					</div>
					<table class="pure-table pure-table-horizontal">
						<thead>
							<tr>
								<th colspan="4">Membre participant (<a href='<spring:url value="/raid/edit/member/${raid.id}"/>'>Editer / Ajouter</a>)</th>
							</tr>
							<tr>
								<th>Membre</th>
								<th>Classe</th>
								<th>Rang</th>
								<th>Dkp</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${raid.players}" var="e" varStatus="i">
								<tr class="${e.player.clazz}">
									<td>
										<input type="hidden" name="players[${i.index}].player.id" value="${e.player.id}">
										${e.player.name}
									</td>
									<td><img class="class" src='<spring:url value="/img/${e.player.clazz}.jpg"/>'/></td>
									<td>${e.player.rank}</td>
									<td>${e.player.dkp}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</fieldset>
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