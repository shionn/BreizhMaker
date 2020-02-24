<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
	<form:form method="POST" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="pseudo">Personnage</label>
				<img src='<spring:url value="/img/${player.clazz}.jpg"/>' style="height:18px"/>
				${player.name}
			</div>
			<div class="pure-control-group">
				<label for="dkp">DKP</label>
				<input type="text" name="dkp" value="${player.dkp}" readonly="readonly">
			</div>
			<div class="pure-control-group">
				<label for="value">${type}</label>
				<input type="number" name="value" required="required" min="0">
			</div>
			<div class="pure-control-group">
				<label for="reason">Raison</label>
				<input type="text" name="reason" required="required" >
			</div>
				<div class="pure-control-group">
					<label for="date">Date</label>
					<input type="datetime" name="date" value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${date}"/>">
				</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Valider</button>
			</div>
		</fieldset>
	</form:form>
</jsp:attribute>
</t:template>