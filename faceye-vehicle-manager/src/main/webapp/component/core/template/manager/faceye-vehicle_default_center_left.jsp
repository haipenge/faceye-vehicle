<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-file"></i><span>车辆</span></a>
	<ul class="sub-menu">
	    <li class="<%=JspUtil.isActive(request, "/vehicle|/customer")%>"><a href="/customer/customer/home"><fmt:message key="customer.customer.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/vehicle|/customer")%>"><a href="/vehicle/vehicle/home"><fmt:message key="vehicle.vehicle.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/vehicle|/customer")%>"><a href="/vehicle/licensePlate/home"><fmt:message key="vehicle.licensePlate.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/vehicle|/customer")%>"><a href="/vehicle/vehicleColor/home"><fmt:message key="vehicle.vehicleColor.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/vehicle|/customer")%>"><a href="/vehicle/brand/home"><fmt:message key="vehicle.brand.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/vehicle|/area")%>"><a href="/vehicle/area/home">辖区管理</a></li>
	</ul>
</li>
