<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
<jsp:attribute name="content">
	<spring:url value="/admin/edit-player/${player.id}" var="url"/>
	<form:form method="POST" class="pure-form pure-form-aligned" action="${url}">
		<fieldset>
			<legend>Edition joueur</legend>
			<div class="pure-control-group">
				<label for="name">Pseudo</label>
				<input name="name" type="text" required="required" value="${player.name}">
			</div>
			<div class="pure-control-group">
				<label for="class">Class</label>
				<select name="class">
					<c:forEach items="${playerclasses}" var="c">
						<option value="${c}" <c:if test="${player.clazz==c}">select="selected"</c:if>>${c}</option>
					</c:forEach>
				</select>
			</div>
			<div class="pure-control-group">
				<label for="rank">Grade</label>
				<select name="rank">
					<c:forEach items="${playerranks}" var="r">
						<option value="${r}" <c:if test="${player.rank==r}">select="selected"</c:if>>${r}</option>
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