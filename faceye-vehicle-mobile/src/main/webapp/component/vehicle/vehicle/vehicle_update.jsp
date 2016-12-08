<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyyMMddHHmmss"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js?v=2${now}"/>"></script>
<c:import url="/component/core/msg/msg.jsp"/>
<form:form action="/vehicle/vehicle/save" method="post" role="form" commandName="vehicle" id="vehicle-form">
	<form:hidden path="id" />
	<fieldset class="form-group">
		<form:input path="customer.name" cssClass="form-control" required placeholder="请输入姓名 不能为空 " />
	</fieldset>
	<fieldset class="form-group">
		<form:input path="customer.personalId" cssClass="form-control" required placeholder="请输入身份证号 不能为空" />
	</fieldset>
	<fieldset class="form-group">
		<form:input path="customer.mobile" cssClass="form-control" required placeholder="请输入手机号 不能为空" />
	</fieldset>
	<fieldset class="form-group">
		<form:input path="licensePlate.plateNum" cssClass="form-control" required placeholder="请输入车牌号 不能为空 " />
	</fieldset>
	<fieldset class="form-group">
		<div class="form-group">
			<label class="col-md-4 col-xs-4 control-label" for="vehicle.area">辖区 </label>
			<div class="col-md-8 col-xs-8">
				<form:select path="area.id" cssClass="form-control">
					<form:option value="0" label="选择辖区" />
					<form:options items="${areas}" itemValue="id" itemLabel="name" />
				</form:select>
			</div>
	</fieldset>
	<form:hidden path="customer.id" />
	<form:hidden path="staff.id"/>
	<form:hidden path="statusText" />
	<form:hidden path="status" />
	<form:hidden path="faPiaoImgUrl" />
	<form:hidden path="cheJiaImgUrl" />
	<form:hidden path="dianJiImgUrl" />
	<form:hidden path="heGeZhengImgUrl" />
	<form:hidden path="vehicleImgUrl" />
	<c:if test="${not empty vehicle.vehicleColor}">
		<form:hidden path="vehicleColor.id" />
	</c:if>
	<c:if test="${not empty vehicle.brand }">
		<form:hidden path="brand.id" />
	</c:if>
	<form:hidden path="typeText" />
	<form:hidden path="type" />
	<form:hidden path="buyDate" />
	<form:hidden path="cheJiaBianHao" />
	<form:hidden path="dianJiBianHao" />
	<form:hidden path="personalIdImgUrl" />
	<div class="row">
		<div class="col-sm-6 col-xs-6">
			<div class="card text-center img-upload" id="shenfenzhen">
				<div class="img"></div>
				<div class="card-block">
					<p class="card-text tips">上传身份证</p>
				</div>
			</div>
			<div class="card  text-center img-upload" id="chejia">
				<div class="img"></div>
				<div class="card-block">
					<p class="card-text tips">上传车架</p>
				</div>
			</div>
			<div class="card  text-center img-upload" id="fapiao">
				<div class="img"></div>
				<div class="card-block">
					<p class="card-text tips">上传发票</p>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-xs-6">
			<div class="card text-center img-upload" id="dianji">
				<div class="img"></div>
				<div class="card-block">
					<p class="card-text tips">上传电机</p>
				</div>
			</div>
			<!-- 
	<div class="card text-center img-upload" id="hegezheng">
		<div class="img"></div>
		<div class="card-block">
			<p class="card-text tips">点击上传合格证</p>
		</div>
	</div>
	 -->
			<div class="card text-center img-upload" id="cheliang">
				<div class="img"></div>
				<div class="card-block">
					<p class="card-text tips">上传车辆</p>
				</div>
			</div>
		</div>
	</div>
	<button type="button" id="vehicle-submit" class="btn btn-primary btn-block">提交</button>
</form:form>
<script type="text/javascript">
<!--

//-->
</script>
<style type="text/css">
.img-upload {
	height: auto;
}

.tips {
	
}
</style>
