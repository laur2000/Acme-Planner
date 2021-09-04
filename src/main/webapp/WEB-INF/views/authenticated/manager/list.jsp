
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.manager.task.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.manager.task.form.label.periodStart" path="periodStart"/>
	<acme:form-moment code="authenticated.manager.task.form.label.periodEnd" path="periodEnd"/>
	<acme:form-double code="authenticated.manager.task.form.label.workload" path="workload"/>
	<acme:form-textarea code="authenticated.manager.task.form.label.description" path="description"/>	
	<acme:form-url code="authenticated.manager.task.form.label.link" path="link"/>
	<acme:form-checkbox code="authenticated.manager.task.form.label.state" path="state"/>
	
			
	<acme:form-submit test="${command == 'show' && finished == 'false'}" code="authenticated.manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'show' && finished == 'false'}" code="authenticated.manager.task.form.button.delete" action="/manager/task/delete"/>
	<acme:form-submit test="${command == 'create'}" code="authenticated.manager.task.form.button.create" action="/manager/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'delete'}" code="authenticated.manager.task.form.button.delete" action="/manager/task/delete"/>		
	<acme:form-return code="authenticated.manager.task.form.button.return"/>	
</acme:form>