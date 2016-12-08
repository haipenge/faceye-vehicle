<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="vehicle.vehicle.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.statusText"></fmt:message></td>
					<td>${vehicle.statusText}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.status"></fmt:message></td>
					<td>${vehicle.status}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.faPiaoImgUrl"></fmt:message></td>
					<td>${vehicle.faPiaoImgUrl}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.cheJiaImgUrl"></fmt:message></td>
					<td>${vehicle.cheJiaImgUrl}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.dianJiImgUrl"></fmt:message></td>
					<td>${vehicle.dianJiImgUrl}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.heGeZhengImgUrl"></fmt:message></td>
					<td>${vehicle.heGeZhengImgUrl}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.vehicleImgUrl"></fmt:message></td>
					<td>${vehicle.vehicleImgUrl}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.vehicleColor"></fmt:message></td>
				    <td>${vehicle.vehicleColor.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.brand"></fmt:message></td>
				    <td>${vehicle.brand.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.typeText"></fmt:message></td>
					<td>${vehicle.typeText}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.type"></fmt:message></td>
					<td>${vehicle.type}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.buyDate"></fmt:message></td>
					<td>${vehicle.buyDate}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.cheJiaBianHao"></fmt:message></td>
					<td>${vehicle.cheJiaBianHao}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.dianJiBianHao"></fmt:message></td>
					<td>${vehicle.dianJiBianHao}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.customer"></fmt:message></td>
				    <td>${vehicle.customer.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.serialNum"></fmt:message></td>
					<td>${vehicle.serialNum}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/vehicle/vehicle/edit/${vehicle.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/vehicle/vehicle/remove/${vehicle.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/vehicle/vehicle/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
