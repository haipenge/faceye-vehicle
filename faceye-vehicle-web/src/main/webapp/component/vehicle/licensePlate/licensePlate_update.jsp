<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/licensePlate/licensePlate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/licensePlate/licensePlate.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty licensePlate.id}">
					<fmt:message key="vehicle.licensePlate.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="vehicle.licensePlate.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/vehicle/licensePlate/save" method="post" role="form" cssClass="form-horizontal" commandName="licensePlate">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.licensePlate.serialNum"/>
					</label>
					<div class="col-md-6">
					     <form:input path="serialNum" cssClass="form-control"/>
					   <form:errors path="serialNum" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.licensePlate.signNum"/>
					</label>
					<div class="col-md-6">
					     <form:input path="signNum" cssClass="form-control"/>
					   <form:errors path="signNum" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.licensePlate.plateNum"/>
					</label>
					<div class="col-md-6">
					     <form:input path="plateNum" cssClass="form-control"/>
					   <form:errors path="plateNum" cssClass="error"/>
					</div>
				</div>
				<!--@generate-entity-jsp-property-update@-->
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
