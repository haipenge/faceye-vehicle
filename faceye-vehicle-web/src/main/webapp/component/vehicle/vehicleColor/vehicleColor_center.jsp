<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/vehicle/vehicleColor/vehicleColor.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/vehicle/vehicleColor/vehicleColor.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="vehicle.vehicleColor.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/vehicle/vehicleColor/input"/>">
				<fmt:message key="vehicle.vehicleColor.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
			<div class="content">
				<form action="<c:url value="/vehicle/vehicleColor/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|name" value="${searchParams.name}"
									placeholder="<fmt:message key="vehicle.vehicleColor.name"></fmt:message>"
									class="form-control input-sm">
							</div>
							<!--@generate-entity-jsp-query-detail@-->
							<div class="col-md-1">
								<button type="submit" class="btn btn-sm btn-primary">
									<fmt:message key="global.search"></fmt:message>
								</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="content">
				<button class="btn btn-primary btn-sm multi-remove">
					<fmt:message key="global.remove"></fmt:message>
				</button>
				<div classs="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" name="check-all"></th>
								  <th><fmt:message key='vehicle.vehicleColor.name'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="vehicleColor">
								<tr id="${vehicleColor.id}">
									<td><input type="checkbox" name="check-single" value="${vehicleColor.id}"></td>
									  <td>${vehicleColor.name}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/vehicle/vehicleColor/detail/${vehicleColor.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/vehicle/vehicleColor/edit/${vehicleColor.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td>
									<a href="#" class="record-remove"><fmt:message key="global.remove"/></a>
									<!--
									<a
										href="<c:url value="/vehicle/vehicleColor/remove/${vehicleColor.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a>
									--></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/vehicle/vehicleColor/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
