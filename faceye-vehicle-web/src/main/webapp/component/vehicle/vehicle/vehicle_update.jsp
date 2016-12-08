<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty vehicle.id}">
					<fmt:message key="vehicle.vehicle.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="vehicle.vehicle.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/vehicle/vehicle/save" method="post" role="form" cssClass="form-horizontal" commandName="vehicle">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.statusText"/>
					</label>
					<div class="col-md-6">
					     <form:input path="statusText" cssClass="form-control"/>
					   <form:errors path="statusText" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.status"/>
					</label>
					<div class="col-md-6">
					     <form:input path="status" cssClass="form-control"/>
					   <form:errors path="status" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.faPiaoImgUrl"/>
					</label>
					<div class="col-md-6">
					     <form:input path="faPiaoImgUrl" cssClass="form-control"/>
					   <form:errors path="faPiaoImgUrl" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.cheJiaImgUrl"/>
					</label>
					<div class="col-md-6">
					     <form:input path="cheJiaImgUrl" cssClass="form-control"/>
					   <form:errors path="cheJiaImgUrl" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.dianJiImgUrl"/>
					</label>
					<div class="col-md-6">
					     <form:input path="dianJiImgUrl" cssClass="form-control"/>
					   <form:errors path="dianJiImgUrl" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.heGeZhengImgUrl"/>
					</label>
					<div class="col-md-6">
					     <form:input path="heGeZhengImgUrl" cssClass="form-control"/>
					   <form:errors path="heGeZhengImgUrl" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.vehicleImgUrl"/>
					</label>
					<div class="col-md-6">
					     <form:input path="vehicleImgUrl" cssClass="form-control"/>
					   <form:errors path="vehicleImgUrl" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.vehicleColor"/>
					</label>
					<div class="col-md-6">
					      <form:select path="vehicleColor.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${vehicleColors}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="vehicleColor.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="vehicle.vehicleColor"></fmt:message></option>
					        <c:forEach items ="${vehicleColors}" var="vehicleColor">
					          <option value="${vehicleColor.id}">${vehicleColor.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="vehicleColor" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.brand"/>
					</label>
					<div class="col-md-6">
					      <form:select path="brand.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${brands}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="brand.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="vehicle.brand"></fmt:message></option>
					        <c:forEach items ="${brands}" var="brand">
					          <option value="${brand.id}">${brand.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="brand" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.typeText"/>
					</label>
					<div class="col-md-6">
					     <form:input path="typeText" cssClass="form-control"/>
					   <form:errors path="typeText" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.type"/>
					</label>
					<div class="col-md-6">
					     <form:input path="type" cssClass="form-control"/>
					   <form:errors path="type" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.buyDate"/>
					</label>
					<div class="col-md-6">
					     <form:input path="buyDate" cssClass="form-control"/>
					   <form:errors path="buyDate" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.cheJiaBianHao"/>
					</label>
					<div class="col-md-6">
					     <form:input path="cheJiaBianHao" cssClass="form-control"/>
					   <form:errors path="cheJiaBianHao" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.dianJiBianHao"/>
					</label>
					<div class="col-md-6">
					     <form:input path="dianJiBianHao" cssClass="form-control"/>
					   <form:errors path="dianJiBianHao" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.customer"/>
					</label>
					<div class="col-md-6">
					      <form:select path="customer.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${customers}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="customer.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="customer.customer"></fmt:message></option>
					        <c:forEach items ="${customers}" var="customer">
					          <option value="${customer.id}">${customer.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="customer" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.serialNum"/>
					</label>
					<div class="col-md-6">
					     <form:input path="serialNum" cssClass="form-control"/>
					   <form:errors path="serialNum" cssClass="error"/>
					</div>
				</div>
				<!--@generate-entity-jsp-property-update@-->
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
