<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/licensePlate/licensePlate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/licensePlate/licensePlate.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="vehicle.licensePlate.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/vehicle/licensePlate/input"/>"> <fmt:message key="vehicle.licensePlate.add"></fmt:message>
		</a> <a href="<c:url value="/vehicle/licensePlate/toGenerate"/>" class="btn btn-success">批量生成车牌</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/vehicle/licensePlate/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="like|plateNum" value="${searchParams.plateNum}" placeholder="<fmt:message key="vehicle.licensePlate.plateNum"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<select name="EQ|status" class="form-control input-sm">
								<option value="">选择状态</option>
								<c:forEach var="status" items="${plateStatus}">
									<option value="${status.key}" <c:if test="${searchParams.status eq status.key}">selected</c:if>>${status.value }</option>
								</c:forEach>
							</select>
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-3">
							<button type="submit" class="btn btn-sm btn-primary" name="submit" value="query">
								<fmt:message key="global.search"></fmt:message>
							</button>
							<button type="submit" class="btn btn-sm btn-info" name="submit" value="export">导出</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<button class="btn btn-success btn-sm make-licensePlages">制牌</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='vehicle.licensePlate.plateNum'></fmt:message></th>
							<th><fmt:message key='vehicle.licensePlate.serialNum'></fmt:message></th>
							<th><fmt:message key='vehicle.licensePlate.signNum'></fmt:message></th>
							<th><fmt:message key='vehicle.licensePlate.statusText'></fmt:message></th>
							<th>二维码</th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view" /></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="licensePlate">
							<tr id="${licensePlate.id}">
								<td><input type="checkbox" name="check-single" value="${licensePlate.id}"></td>
								<td>${licensePlate.plateNum}</td>
								<td>${licensePlate.serialNum}</td>
								<td>${licensePlate.signNum}</td>
								<td>${licensePlate.statusText}</td>
								<td><img src="<c:url value="/images/qrcode_for_gh_83502fd74e75_344.jpg"/>" class="qr-code img-responsive" style="width:25px;height:25px;"></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/vehicle/licensePlate/detail/${licensePlate.id}.html"/>"><fmt:message key="global.view" /></a></td>
								</a>
								</td>
								<td><a href="#" class="record-remove"><fmt:message key="global.remove" /></a> 
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
			<f:page page="${page}" url="/vehicle/licensePlate/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
