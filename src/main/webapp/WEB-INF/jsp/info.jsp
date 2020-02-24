<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template>
	<jsp:attribute name="content">
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th colspan="3" style="text-align:center">Valeur des loots</th>
				</tr>
				<tr>
					<th>#</th>
					<th>MC / Ony</th>
					<th>BWL</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Hors Set</td>
					<td>100 DKP</td>
					<td>150 DKP</td>
				</tr>
				<tr>
					<td>Set</td>
					<td>50 DKP</td>
					<td>75 DKP</td>
				</tr>
				<tr>
					<td>Set LQE</td>
					<td>40 DKP</td>
					<td>-</td>
				</tr>
			</tbody>
		</table>
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th colspan="2" style="text-align:center">Gain en raid</th>
				</tr>
				<tr>
					<th>#</th>
					<th>Valeur</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Bonus à l'heure</td>
					<td>5 DKP</td>
				</tr>
				<tr>
					<td>Valeur d'un boss</td>
					<td>2 DKP</td>
				</tr>
				<tr>
					<td>Fin de Raid</td>
					<td>10 DKP</td>
				</tr>
				<tr>
					<td>Participation au raid</td>
					<td>1 DKP / 15 minute</td>
				</tr>
				<tr>
					<td>Absence Injustifié</td>
					<td>-50 DKP</td>
				</tr>
			</tbody>
		</table>
	</jsp:attribute>
</t:template>