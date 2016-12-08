<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/licensePlate/licensePlate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/licensePlate/licensePlate.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3></h3>
	</div>
	<div class="row">
		<div class="col-md-8">
			<form role="form" method="post" action="<c:url value="/vehicle/licensePlate/doLicensePlateDistribute"/>" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label">车主</label>
						<input type="hidden" name="vehicleId" value="${vehicle.id }">
						<div class="col-md-10">${vehicle.customer.name}<span class="span-suffix">(电话:${vehicle.customer.mobile}&nbsp;&nbsp;车架编号:${vehicle.cheJiaBianHao})</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="plateNum">车牌</label>
						<div class="col-md-10">
						    <span class="show-plateNum"></span>
							<input name="plateNum" class="form-control" type="hidden" readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-2 col-md-10">
							<button type="submit" class="btn btn-primary btn-block">上牌</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="col-md-4">
			<h3>待选车牌</h3>
			<c:forEach items="${licensePlates.content}" var="licensePlate">
				<p class="plateNum">${licensePlate.plateNum}</p>
			</c:forEach>
		</div>
	</div>
</div>
