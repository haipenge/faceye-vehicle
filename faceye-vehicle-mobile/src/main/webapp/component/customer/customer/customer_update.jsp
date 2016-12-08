<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="content-padded">
	<form:form action="/customer/customer/save" method="post" commandName="customer">
		<form:hidden path="id" />
		<form:hidden path="webUserId" />
		<form:hidden path="type"/>
		<form:hidden path="name" />
		<fieldset class="form-group">
			<form:input path="mobile" cssClass="form-control" placeholder="请输入手机号" />
			<form:errors path="mobile" cssClass="error" />
		</fieldset>
		<!--@generate-entity-jsp-property-update@-->
		<fieldset class="form-group">
			<button type="submit" class="btn btn-primary btn-block">提交</button>
		</fieldset>
	</form:form>
</div>