<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty customer.id}">
					<fmt:message key="customer.customer.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="customer.customer.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/customer/customer/save" method="post" role="form" cssClass="form-horizontal" commandName="customer">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.headImgUrl"/>
					</label>
					<div class="col-md-6">
					     <form:input path="headImgUrl" cssClass="form-control"/>
					   <form:errors path="headImgUrl" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.personalId"/>
					</label>
					<div class="col-md-6">
					     <form:input path="personalId" cssClass="form-control"/>
					   <form:errors path="personalId" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.email"/>
					</label>
					<div class="col-md-6">
					     <form:input path="email" cssClass="form-control"/>
					   <form:errors path="email" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.sexText"/>
					</label>
					<div class="col-md-6">
					     <form:input path="sexText" cssClass="form-control"/>
					   <form:errors path="sexText" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.sex"/>
					</label>
					<div class="col-md-6">
					     <form:input path="sex" cssClass="form-control"/>
					   <form:errors path="sex" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.mobile"/>
					</label>
					<div class="col-md-6">
					     <form:input path="mobile" cssClass="form-control"/>
					   <form:errors path="mobile" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.name"/>
					</label>
					<div class="col-md-6">
					     <form:input path="name" cssClass="form-control"/>
					   <form:errors path="name" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.nickName"/>
					</label>
					<div class="col-md-6">
					     <form:input path="nickName" cssClass="form-control"/>
					   <form:errors path="nickName" cssClass="error"/>
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
