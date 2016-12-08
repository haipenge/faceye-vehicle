<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<%
	String img = HostUtil.getProperty("image.host");
	request.setAttribute("img", img);
%>
<!-- 
<nav class="navbar navbar-light" style="background-color: #e3f2fd;margin-top:2px;">
  <a href="#" class="navbar-brand">电动车</a>
</nav>
-->
<!-- 如果是上牌员 -->
<c:if test="${ecustomer.type eq 1 }">
	<a href="<c:url value="/vehicle/vehicle/input"/>" class="btn btn-sm btn-primary pull-right" style="margin-right: 15px; margin-top: 2px; margin-bottom: 5px;">添加</a>
</c:if>
<c:choose>
	<c:when test="${not empty page.content}">
		<div class="list-group">
			<c:forEach items="${page.content}" var="vehicle" varStatus="status">
				<a class="list-group-item" href="<c:url value="/vehicle/vehicle/detail/${vehicle.id}"/>">
					<div class="row">
						<div class="col-sm-4 col-md-4 col-xs-4 m-a-0 p-a-0">
							<c:choose>
								<c:when test="${not empty vehicle.vehicleImgUrl &&  fn:endsWith(vehicleImgUrl, 'jpg')}">
									<img src="${img}${vehicle.vehicleImgUrl}" class="img-responsive img-thumbnail">
								</c:when>
								<c:otherwise>
									<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-sm-8 col-md-8 col-xs-8 m-a-0">
							<h5>${vehicle.customer.name}</h5>
							<p class="text-muted">
								手机:${vehicle.customer.mobile}<br>身份证:${vehicle.licensePlate.plateNum}<br> 状态:${vehicle.statusText }
							</p>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
		<f:page page="${page}" url="/vehicle/vehicle/home" params="<%=request.getParameterMap()%>" />
	</c:when>
	<c:otherwise>
		<div class="card">
			<img src="<c:url value="/images/default_vehicle.jpg"/>" class="card-img-top img-responsive img-thumbnail">
			<div class="card-block">
				<p class="card-text">电动车还在路上,请稍候...</p>
			</div>
		</div>
	</c:otherwise>
</c:choose>