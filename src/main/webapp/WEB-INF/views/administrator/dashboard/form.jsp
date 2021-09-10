<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-public-tasks"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="totalNumberOfPublicTasks" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-private-tasks"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="totalNumberOfPrivateTasks" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-finished-tasks"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="totalNumberOfFinishedTasks" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-non-finished-tasks"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="totalNumberOfNonFinishedTasks" />
		</td>
	</tr>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.burse-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.burse-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratio-burse-important"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="ratioBurseImportant" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratio-burse-budget"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="ratioBurseBudgetZero" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avg-burse-eur"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="averageBurseEurBudget" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.dev-burse-eur"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="deviationBurseEurBudget" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avg-burse-usd"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="averageBurseUsdBudget" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.dev-burse-usd"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="deviationBurseUsdBudget" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.avg-burse-gbp"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="averageBurseGbpBudget" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.dev-burse-gbp"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="deviationBurseGbpBudget" />
		</td>
	</tr>
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.execution-period"/>
</h2>
<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.execution-period"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-task-execution-period"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="averageNumberOfTaskExecutionPeriods" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-number-task-execution-period"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="deviationNumberOfTaskExecutionPeriods" />
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-number-task-execution-period"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="minimumNumberOfTaskExecutionPeriods" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-number-task-execution-period"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="maximumNumberOfTaskExecutionPeriods" />
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.workloads"/>
</h2>
<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.workloads"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-number-task-workloads"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="averageNumberOfTaskWorkloads" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-number-task-workloads"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="deviationNumberOfTaskWorkloads" />
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-number-task-workloads"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="minimumNumberOfTaskWorkloads" />
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-number-task-workloads"/>
		</th>
		<td>
			<acme:form-textbox readonly="true" code="" path="maximumNumberOfTaskWorkloads" />
		</td>
	</tr>
</table>