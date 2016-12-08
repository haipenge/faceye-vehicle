<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/licensePlate/licensePlate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/vehicle/licensePlate/licensePlate.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>生成车牌</h3>
	</div>
	<div class="content">
		<form role="from" class="form-horizontal" action="<c:url value="/vehicle/licensePlate/generate"/>">
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="key">车牌首字母 </label>
					<div class="col-md-6">
						<input type="text" name="key" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" form="count">生成数量</label>
					<div class="col-md-6">
						<input type="text" name="count" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-6">
						<button type="submit" class="btn btn-primary btn-block">生成车牌</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

