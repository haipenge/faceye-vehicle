<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<%
	String img = HostUtil.getProperty("image.host");
	request.setAttribute("img", img);
%>
<div class="card card-block">
	<h5 class="card-title">${vehicle.customer.name}&nbsp;&nbsp;电动车</h5>
	<p class="card-text text-muted">
		车牌:${vehicle.licensePlate.plateNum}<br>手机:${vehicle.customer.mobile }<br> 身份证:${vehicle.customer.personalId}
	</p>
</div>
<div class="card card-block">
	<h5 class="card-title">身份证</h5>
	<c:choose>
		<c:when test="${not empty vehicle.personalIdImgUrl &&  fn:endsWith(vehicle.personalIdImgUrl, 'jpg')}">
			<img src="${img}${vehicle.personalIdImgUrl}" style="width: 100%; height: auto;">
		</c:when>
		<c:otherwise>
			<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
		</c:otherwise>
	</c:choose>
</div>
<div class="card card-block">
	<h5 class="card-title">电机</h5>
	<c:choose>
		<c:when test="${not empty vehicle.dianJiImgUrl &&  fn:endsWith(vehicle.dianJiImgUrl, 'jpg')}">
			<img src="${img}${vehicle.dianJiImgUrl}" style="width: 100%; height: auto;">
		</c:when>
		<c:otherwise>
			<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
		</c:otherwise>
	</c:choose>
</div>
<div class="card card-block">
	<h5 class="card-title">车架</h5>
	<c:choose>
		<c:when test="${not empty vehicle.cheJiaImgUrl &&  fn:endsWith(vehicle.cheJiaImgUrl, 'jpg')}">
			<img src="${img}${vehicle.cheJiaImgUrl}" style="width: 100%; height: auto;">
		</c:when>
		<c:otherwise>
			<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
		</c:otherwise>
	</c:choose>
</div>

<div class="card card-block">
	<h5 class="card-title">发票</h5>
	<c:choose>
		<c:when test="${not empty vehicle.faPiaoImgUrl &&  fn:endsWith(vehicle.faPiaoImgUrl, 'jpg')}">
			<img src="${img}${vehicle.faPiaoImgUrl}" style="width: 100%; height: auto;">
		</c:when>
		<c:otherwise>
			<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
		</c:otherwise>
	</c:choose>
	
	
</div>
<!-- 
<div class="card card-block">
	<h5 class="card-title">合格证</h5>
	<img src="${img}${vehicle.heGeZhengImgUrl}" style="width: 100%; height: auto;">
</div>
 -->
<div class="card card-block">
	<h5 class="card-title">车辆</h5>
	<c:choose>
		<c:when test="${not empty vehicle.vehicleImgUrl &&  fn:endsWith(vehicle.vehicleImgUrl, 'jpg')}">
			<img src="${img}${vehicle.vehicleImgUrl}" style="width: 100%; height: auto;">
		</c:when>
		<c:otherwise>
			<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
		</c:otherwise>
	</c:choose>
</div>
<!-- 
<div class="card">
	<div class="content">
		<a href="<c:url value="/vehicle/vehicle/edit/${vehicle.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit" /></a> <a
			href="<c:url value="/vehicle/vehicle/remove/${vehicle.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove" /></a> <a
			href="<c:url value="/vehicle/vehicle/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back" /></a>
	</div>
</div>
 -->
