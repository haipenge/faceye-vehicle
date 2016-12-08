<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/licensePlate/licensePlate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/licensePlate/licensePlate.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty licensePlate.id}">
					<fmt:message key="vehicle.licensePlate.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="vehicle.licensePlate.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="row">
		<div class="col-md-8">
			<form role="form" method="post" class="form-horizontal">
				<fieldset>
					<label class="col-md-2 control-label" for="plateNum">车牌</label>
					<div class="col-md-10">
						<input name="plateNum" class="form-control" type="text" readonly>
					</div>
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">上牌</button>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="col-md-4">
			<h3>待选车牌</h3>
			<c:forEach items="${licensePlates.content}" var="licensePlate">
				<p>${licensePlate.plateNum}</p>
			</c:forEach>
		</div>
	</div>
</div>
