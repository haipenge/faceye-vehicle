<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/vehicle/licensePlate/licensePlate.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/vehicle/licensePlate/licensePlate.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="vehicle.licensePlate.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/vehicle/licensePlate/input"/>">
				<fmt:message key="vehicle.licensePlate.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
			<div class="content">
				<form action="<c:url value="/vehicle/licensePlate/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|serialNum" value="${searchParams.serialNum}"
									placeholder="<fmt:message key="vehicle.licensePlate.serialNum"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|signNum" value="${searchParams.signNum}"
									placeholder="<fmt:message key="vehicle.licensePlate.signNum"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|plateNum" value="${searchParams.plateNum}"
									placeholder="<fmt:message key="vehicle.licensePlate.plateNum"></fmt:message>"
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
								  <th><fmt:message key='vehicle.licensePlate.serialNum'></fmt:message></th>
								  <th><fmt:message key='vehicle.licensePlate.signNum'></fmt:message></th>
								  <th><fmt:message key='vehicle.licensePlate.plateNum'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="licensePlate">
								<tr id="${licensePlate.id}">
									<td><input type="checkbox" name="check-single" value="${licensePlate.id}"></td>
									  <td>${licensePlate.serialNum}</td>
									  <td>${licensePlate.signNum}</td>
									  <td>${licensePlate.plateNum}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/vehicle/licensePlate/detail/${licensePlate.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/vehicle/licensePlate/edit/${licensePlate.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td>
									<a href="#" class="record-remove"><fmt:message key="global.remove"/></a>
									<!--
									<a
										href="<c:url value="/vehicle/licensePlate/remove/${licensePlate.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a>
									--></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/vehicle/licensePlate/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
