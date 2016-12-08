<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="customer.customer.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.headImgUrl"></fmt:message></td>
					<td>${customer.headImgUrl}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.personalId"></fmt:message></td>
					<td>${customer.personalId}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.email"></fmt:message></td>
					<td>${customer.email}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.sexText"></fmt:message></td>
					<td>${customer.sexText}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.sex"></fmt:message></td>
					<td>${customer.sex}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.mobile"></fmt:message></td>
					<td>${customer.mobile}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.name"></fmt:message></td>
					<td>${customer.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="customer.customer.nickName"></fmt:message></td>
					<td>${customer.nickName}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/customer/customer/edit/${customer.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/customer/customer/remove/${customer.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/customer/customer/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
