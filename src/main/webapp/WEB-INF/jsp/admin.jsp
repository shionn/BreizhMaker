<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
	<spring:url value="/admin/depreciation" var="url"/>
	<form:form method="POST" class="pure-form pure-form-aligned" action="${url}">
		<fieldset>
			<legend>Dépréciation</legend>
			<div class="pure-control-group">
				<label for="value">Valeur</label>
				<input name="value" type="text" value="20" required="required"> %
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Ajouter</button>
			</div>
		</fieldset>
	</form:form>

	<spring:url value="/admin/create-player" var="url"/>
	<form:form method="POST" class="pure-form pure-form-aligned" action="${url}">
		<fieldset>
			<legend>Creation joueur</legend>
			<div class="pure-control-group">
				<label for="pseudo">Pseudo</label>
				<input name="pseudo" type="text" placeholder="pseudo" required="required">
			</div>
			<div class="pure-control-group">
				<label for="class">Class</label>
				<select name="class">
					<c:forEach items="${playerclasses}" var="c">
						<option value="${c}">${c}</option>
					</c:forEach>
				</select>
			</div>
			<div class="pure-control-group">
				<label for="rank">Grade</label>
				<select name="rank">
					<c:forEach items="${playerranks}" var="r">
						<option value="${r}">${r}</option>
					</c:forEach>
				</select>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Valider</button>
			</div>
		</fieldset>
	</form:form>
</jsp:attribute>
</t:template>