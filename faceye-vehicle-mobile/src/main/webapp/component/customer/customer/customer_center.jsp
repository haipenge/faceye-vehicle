<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/customer/customer/customer.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="customer.customer.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/customer/customer/input"/>">
				<fmt:message key="customer.customer.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
			<div class="content">
				<form action="<c:url value="/customer/customer/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|headImgUrl" value="${searchParams.headImgUrl}"
									placeholder="<fmt:message key="customer.customer.headImgUrl"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|personalId" value="${searchParams.personalId}"
									placeholder="<fmt:message key="customer.customer.personalId"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|email" value="${searchParams.email}"
									placeholder="<fmt:message key="customer.customer.email"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|sexText" value="${searchParams.sexText}"
									placeholder="<fmt:message key="customer.customer.sexText"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|sex" value="${searchParams.sex}"
									placeholder="<fmt:message key="customer.customer.sex"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|mobile" value="${searchParams.mobile}"
									placeholder="<fmt:message key="customer.customer.mobile"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|name" value="${searchParams.name}"
									placeholder="<fmt:message key="customer.customer.name"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|nickName" value="${searchParams.nickName}"
									placeholder="<fmt:message key="customer.customer.nickName"></fmt:message>"
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
								  <th><fmt:message key='customer.customer.headImgUrl'></fmt:message></th>
								  <th><fmt:message key='customer.customer.personalId'></fmt:message></th>
								  <th><fmt:message key='customer.customer.email'></fmt:message></th>
								  <th><fmt:message key='customer.customer.sexText'></fmt:message></th>
								  <th><fmt:message key='customer.customer.sex'></fmt:message></th>
								  <th><fmt:message key='customer.customer.mobile'></fmt:message></th>
								  <th><fmt:message key='customer.customer.name'></fmt:message></th>
								  <th><fmt:message key='customer.customer.nickName'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="customer">
								<tr id="${customer.id}">
									<td><input type="checkbox" name="check-single" value="${customer.id}"></td>
									  <td>${customer.headImgUrl}</td>
									  <td>${customer.personalId}</td>
									  <td>${customer.email}</td>
									  <td>${customer.sexText}</td>
									  <td>${customer.sex}</td>
									  <td>${customer.mobile}</td>
									  <td>${customer.name}</td>
									  <td>${customer.nickName}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/customer/customer/detail/${customer.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/customer/customer/edit/${customer.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td>
									<a href="#" class="record-remove"><fmt:message key="global.remove"/></a>
									<!--
									<a
										href="<c:url value="/customer/customer/remove/${customer.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a>
									--></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/customer/customer/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
