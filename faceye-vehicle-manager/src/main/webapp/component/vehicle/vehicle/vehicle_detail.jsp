<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<%
	String img = HostUtil.getProperty("image.host");
	request.setAttribute("img", img);
%>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="vehicle.vehicle.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="bg-title-col" colspan="6"><b>上牌员</b></td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col">姓名:</td>
					<td class="width-p-30" colspan="2">${vehicle.staff.name}</td>
					<td class="width-p-20 bg-title-col">手机号</td>
					<td class="width-p-30" colspan="2">${vehicle.staff.mobile }</td>
				</tr>
				<tr>
					<td class="bg-title-col" colspan="6"><b>用户信息</b></td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.customer"></fmt:message></td>
					<td class="width-p-30" colspan="2">${vehicle.customer.name}</td>
					<td class="width-p-20 bg-title-col">手机号</td>
					<td class="width-p-30" colspan="2">${vehicle.customer.mobile }</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col">身份证</td>
					<td class="width-p-30" colspan="5">${vehicle.customer.personalId}</td>
				</tr>
				<tr>
					<td class="bg-title-col" colspan="6"><b>车牌信息</b></td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col">车牌号</td>
					<td class="width-p-30" colspan="2">${vehicle.licensePlate.plateNum }</td>
					<td class="width-p-20 bg-title-col">状态</td>
					<td class="width-p-30" colspan="2">${vehicle.licensePlate.statusText }</td>
				</tr>
				<tr>
					<td class="bg-title-col" colspan="4">车辆信息</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.vehicleColor"></fmt:message></td>
					<td colspan="2">${vehicle.vehicleColor.name}</td>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.brand"></fmt:message></td>
					<td class="width-p-30" colspan="2">${vehicle.brand.name}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.typeText"></fmt:message></td>
					<td class="width-p-30" colspan="2">${vehicle.typeText}</td>

					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.buyDate"></fmt:message></td>
					<td class="width-p-30" colspan="2"><fmt:formatDate value="${vehicle.buyDate}" pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.cheJiaBianHao"></fmt:message></td>
					<td class="width-p-30" colspan="2">${vehicle.cheJiaBianHao}</td>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.dianJiBianHao"></fmt:message></td>
					<td class="width-p-30" colspan="2">${vehicle.dianJiBianHao}</td>
				</tr>
				<tr>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.serialNum"></fmt:message></td>
					<td class="width-p-30" colspan="2">${vehicle.serialNum}</td>
					<td class="width-p-20 bg-title-col"><fmt:message key="vehicle.vehicle.statusText"></fmt:message></td>
					<td class="width-p-30" colspan="2"'>${vehicle.statusText}</td>
				</tr>
				<tr>
					<td class="bg-title-col">身份证</td>
					<td class="bg-title-col"><fmt:message key="vehicle.vehicle.faPiaoImgUrl"></fmt:message></td>
					<td class="bg-title-col"><fmt:message key="vehicle.vehicle.cheJiaImgUrl"></fmt:message></td>
					<td class="bg-title-col"><fmt:message key="vehicle.vehicle.dianJiImgUrl"></fmt:message></td>
					<td class="bg-title-col"><fmt:message key="vehicle.vehicle.heGeZhengImgUrl"></fmt:message></td>
					<td class="bg-title-col"><fmt:message key="vehicle.vehicle.vehicleImgUrl"></fmt:message></td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${not empty vehicle.personalIdImgUrl }">
								<!--  
								<img src="/UploadServlet?getfile=${vehicle.faPiaoImgUrl}" class="img-small center-block img-responsive img-thumbnail">
							    -->
								<img src="${img}${vehicle.personalIdImgUrl}" class="img-small center-block img-responsive img-thumbnail">
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
							</c:otherwise>
						</c:choose></td>

					<td><c:choose>
							<c:when test="${not empty vehicle.faPiaoImgUrl }">
								<!--  
								<img src="/UploadServlet?getfile=${vehicle.faPiaoImgUrl}" class="img-small center-block img-responsive img-thumbnail">
							    -->
								<img src="${img}${vehicle.faPiaoImgUrl}" class="img-small center-block img-responsive img-thumbnail">
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${not empty vehicle.cheJiaImgUrl }">
								<!--  <img src="/UploadServlet?getfile=${vehicle.cheJiaImgUrl}" class="img-small center-block img-responsive img-thumbnail">-->
								<img src="${img}${vehicle.cheJiaImgUrl}" class="img-small center-block img-responsive img-thumbnail">
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${not empty vehicle.dianJiImgUrl }">
								<!-- 
								<img src="/UploadServlet?getfile=${vehicle.dianJiImgUrl}" class="img-small center-block img-responsive img-thumbnail">
								-->
								<img src="${img}${vehicle.dianJiImgUrl}" style="width: 100%; height: auto;" class="img-small center-block img-responsive img-thumbnail">
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/images/upload.jpg"/>" style="width: 100%; height: auto;" class="img-small center-block img-responsive img-thumbnail">
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${not empty vehicle.heGeZhengImgUrl }">
								<!-- 
								<img src="/UploadServlet?getfile=${vehicle.heGeZhengImgUrl}" class="img-small center-block img-responsive img-thumbnail">
								-->
								<img src="${img }${vehicle.heGeZhengImgUrl}" style="width: 100%; height: auto;" class="img-small center-block img-responsive img-thumbnail">"
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/images/upload.jpg"/>" style="width: 100%; height: auto;" class="img-small center-block img-responsive img-thumbnail">
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${not empty vehicle.vehicleImgUrl }">
								<!-- 
								<img src="/UploadServlet?getfile=${vehicle.vehicleImgUrl}" class="img-small center-block img-responsive img-thumbnail">
								-->
								<img src="${img }${vehicle.vehicleImgUrl}" style="width: 100%; height: auto;" class="img-small center-block img-responsive img-thumbnail">
							</c:when>
							<c:otherwise>
								<img src="<c:url value="/images/upload.jpg"/>" style="width: 100%; height: auto;" class="img-small center-block img-responsive img-thumbnail">
							</c:otherwise>
						</c:choose></td>
				</tr>
			<!--@generate-entity-jsp-property-detail@-->
				</table>
		</div>
	</div>
	<div class="content">
		<a href="<c:url value="/vehicle/vehicle/edit/${vehicle.id}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit" /></a> <a
			href="<c:url value="/vehicle/vehicle/remove/${vehicle.id}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove" /></a> <a
			href="<c:url value="/vehicle/vehicle/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back" /></a>
	</div>
</div>
