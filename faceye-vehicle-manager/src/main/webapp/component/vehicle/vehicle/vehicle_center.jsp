<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="vehicle.vehicle.manager"></fmt:message>
		<c:if test="${not empty customer }">
			<a class="btn btn-primary" href="<c:url value="/vehicle/vehicle/input?customerId=${customer.id}"/>"> 车辆登记 </a>
		</c:if>
		<c:if test="${empty customer}">
		<a href="<c:url value="/customer/customer/input"/>" class="btn btn-success">录车</a>
		</c:if>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/vehicle/vehicle/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="like|serialNum" value="${searchParams.serialNum}" placeholder="<fmt:message key="vehicle.vehicle.serialNum"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="like|cheJiaBianHao" value="${searchParams.cheJiaBianHao}" placeholder="<fmt:message key="vehicle.vehicle.cheJiaBianHao"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="like|dianJiBianHao" value="${searchParams.dianJiBianHao}" placeholder="<fmt:message key="vehicle.vehicle.dianJiBianHao"></fmt:message>"
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
							<th>手机号</th>
							<th><fmt:message key='vehicle.vehicle.customer'></fmt:message></th>
							<th>车牌号</th>
							<!-- 
							<th><fmt:message key='vehicle.vehicle.serialNum'></fmt:message></th> -->
							<th><fmt:message key='vehicle.vehicle.statusText'></fmt:message></th>
							
							<!-- 
							<th><fmt:message key='vehicle.vehicle.faPiaoImgUrl'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.cheJiaImgUrl'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.dianJiImgUrl'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.heGeZhengImgUrl'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.vehicleImgUrl'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.vehicleColor'></fmt:message></th>
							 -->
							<th><fmt:message key='vehicle.vehicle.brand'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.typeText'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.buyDate'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.cheJiaBianHao'></fmt:message></th>
							<th><fmt:message key='vehicle.vehicle.dianJiBianHao'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th>用户信息</th>
							<th>扫码记录</th>
							<th><fmt:message key="global.view" /></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="vehicle">
							<tr id="${vehicle.id}">
								<td><input type="checkbox" name="check-single" value="${vehicle.id}"></td>
								<td>${vehicle.customer.mobile }</td>
								<td>${vehicle.customer.name}</td>
								<td>${vehicle.licensePlate.plateNum }</td>
								<!-- 
								<td>${vehicle.serialNum}</td> -->
								<td>${vehicle.statusText}</td>
								<!-- 
								<td>${vehicle.faPiaoImgUrl}</td>
								<td>${vehicle.cheJiaImgUrl}</td>
								<td>${vehicle.dianJiImgUrl}</td>
								<td>${vehicle.heGeZhengImgUrl}</td>
								<td>${vehicle.vehicleImgUrl}</td>
								<td>${vehicle.vehicleColor.name}</td>
								 -->
								<td>${vehicle.brand.name}</td>
								<td>${vehicle.typeText}</td>
								<td><fmt:formatDate value="${vehicle.buyDate}" pattern="yyyy-MM-dd"/></td>
								<td>${vehicle.cheJiaBianHao}</td>
								<td>${vehicle.dianJiBianHao}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/customer/customer/edit/${vehicle.customer.id}"/>">用户信息</a></td>
								<td><a href="<c:url value="/vehicle/scanLog/home?EQ|vehicle.$id=${vehicle.id}"/>">扫码记录</a></td>
								<td><a href="<c:url value="/vehicle/vehicle/detail/${vehicle.id}.html"/>"><fmt:message key="global.view" /></a></td>
								<td><a href="<c:url value="/vehicle/vehicle/edit/${vehicle.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="#" class="record-remove"><fmt:message key="global.remove" /></a> <!--
									<a
										href="<c:url value="/vehicle/vehicle/remove/${vehicle.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a>
									--></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/vehicle/vehicle/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
