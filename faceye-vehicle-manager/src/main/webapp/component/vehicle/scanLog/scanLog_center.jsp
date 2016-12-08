<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/vehicle/scanLog/scanLog.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/vehicle/scanLog/scanLog.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="vehicle.scanLog.manager"></fmt:message>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		
		<div class="content">
		  <div id="msg"></div>
	       
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<!--@generate-entity-jsp-property-desc@-->
							<th>扫码人</th>
							<th>扫码人手机号</th>
							<th>被扫车牌</th>
							<th>时间</th>
							<th>经纬度</th>
							<th>地址</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="scanLog">
							<tr>
								<!--@generate-entity-jsp-property-value@-->
								<td>${scanLog.customer.name}</td>
								<td>${scanLog.customer.mobile}</td>
								<td>${scanLog.vehicle.licensePlate.plateNum}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${scanLog.createDate }"/></td>
								<td>${scanLog.longitude},${scanLog.latitude}</td>
								<td></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/vehicle/scanLog/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
