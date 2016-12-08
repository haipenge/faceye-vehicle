<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/vehicle/vehicle/vehicle.css"/>" />
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload.css"/>">
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui.css"/>">
<%
	String img = HostUtil.getProperty("image.host");
	request.setAttribute("img", img);
%>
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-noscript.css"/>">
</noscript>
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui-noscript.css"/>">
</noscript>
<script type="text/javascript" src="<c:url value="/js/component/vehicle/vehicle/vehicle.js"/>"></script>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="header">
			<h3>车辆登记</h3>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="content">
					<form:form action="/vehicle/vehicle/save" method="post" role="form" cssClass="form-horizontal" commandName="vehicle" enctype="multipart/form-data">
						<form:hidden path="id" />
						<form:hidden path="customer.id" />
						<form:hidden path="staff.id"/>
						<fieldset>
							<legend>车主信息</legend>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.name" />
								</label>
								<div class="col-md-10">
									<form:input path="customer.name" cssClass="form-control" />
									<form:errors path="customer.name" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.personalId" />
								</label>
								<div class="col-md-10">
									<form:input path="customer.personalId" cssClass="form-control" />
									<form:errors path="customer.personalId" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.mobile" />
								</label>
								<div class="col-md-10">
									<form:input path="customer.mobile" cssClass="form-control" readonly="true" />
									<form:errors path="customer.mobile" cssClass="error" />
								</div>
							</div>
							<!-- 
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.email" />
							</label>
							<div class="col-md-10">
								<form:input path="customer.email" cssClass="form-control" />
								<form:errors path="customer.email" cssClass="error" />
							</div>
						</div>
						 -->
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.sex" />
								</label>
								<div class="col-md-10">
									<form:radiobuttons path="customer.sex" items="${sexs}" itemValue="key" itemLabel="value" cssClass="radio-inline" />
									<form:errors path="customer.sex" cssClass="error" />
								</div>
							</div>
							<!-- 
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.nickName" />
							</label>
							<div class="col-md-10">
								<form:input path="customer.nickName" cssClass="form-control" />
								<form:errors path="customer.nickName" cssClass="error" />
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-2 fileupload-buttonbar">
								<form:hidden path="customer.headImgUrl" cssClass="form-control" />
								<form:errors path="customer.headImgUrl" cssClass="error" />
							</div>

						</div>
						-->
						</fieldset>
						<fieldset>
							<legend>车辆信息</legend>
							<!-- 
							<div class="form-group">
								<label class="col-md-2 control-label">车主</label>
								<div class="com-md-10" style="margin-top: 10px;">
									<span style="margin-left: 15px;">${vehicle.customer.name}</span><span class="span-suffix">(身份证:${vehicle.customer.personalId }&nbsp;&nbsp;手机:${vehicle.customer.mobile})</span>
								</div>
							</div>
							 -->
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.serialNum" />
								</label>
								<div class="col-md-10">
									<form:input path="serialNum" cssClass="form-control" />
									<form:errors path="serialNum" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.cheJiaBianHao" />
								</label>
								<div class="col-md-10">
									<form:input path="cheJiaBianHao" cssClass="form-control" />
									<form:errors path="cheJiaBianHao" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.dianJiBianHao" />
								</label>
								<div class="col-md-10">
									<form:input path="dianJiBianHao" cssClass="form-control" />
									<form:errors path="dianJiBianHao" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> 颜色 </label>
								<div class="col-md-10">
									<form:select path="vehicleColor.id" cssClass="form-control">
										<form:option value="0" label="选择颜色" />
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
									<form:errors path="vehicleColor" cssClass="error" />
								</div>
							</div>
							<div class="form-group">

								<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.brand" />
								</label>
								<div class="col-md-10">
									<form:select path="brand.id" cssClass="form-control">
										<form:option value="0" label="选择品牌" />
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
									<form:errors path="brand" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> 类型 </label>
								<div class="col-md-10">
									<form:select path="type" cssClass="form-control">
										<form:options items="${types}" itemValue="key" itemLabel="value" />
									</form:select>
									<form:errors path="type" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> 状态 </label>
								<div class="col-md-10">
									<form:select path="status" cssClass="form-control">
										<form:options items="${status}" itemValue="key" itemLabel="value" />
									</form:select>
									<form:errors path="status" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="name"> <spring:message code="vehicle.vehicle.buyDate" />
								</label>
								<div class="input-group date datetime col-md-6" style="padding-left: 15px; padding-right: 15px;" data-min-view="2" data-date-format="yyyy-mm-dd">
									<form:input path="buyDate" cssClass="form-control" />
									<span class="input-group-addon btn btn-primary"><span class="glyphicon glyphicon-th"></span></span>
									<form:errors path="buyDate" cssClass="error" />
								</div>
							</div>

							<!--  
							<div class="form-group">
								<label class="col-md-2 control-label" for="name">上传图片 </label>
								<div class="col-md-2">
									<div class="content center-block" id="faPiaoImgUrl">
										<c:choose>
											<c:when test="${not empty vehicle.faPiaoImgUrl }">
												<img src="/UploadServlet?getfile=${vehicle.faPiaoImgUrl}" class="img-small center-block img-responsive img-thumbnail">
											</c:when>
											<c:otherwise>
												<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="content text-center">
										<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>发票图片</span> <input type="file" name="files[]" multiple
											id="faPiao-upload">
										</span> <span class="fileupload-process"></span>
									</div>
								</div>
								<div class="col-md-2">
									<div class="content" id="cheJiaImgUrl">
										<c:choose>
											<c:when test="${not empty vehicle.cheJiaImgUrl }">
												<img src="/UploadServlet?getfile=${vehicle.cheJiaImgUrl}" class="img-small center-block img-responsive img-thumbnail">
											</c:when>
											<c:otherwise>
												<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="content text-center">
										<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>车架图片</span> <input type="file" name="cheJiaFile" id="cheJia-upload">
										</span> <span class="fileupload-process"></span>
									</div>

								</div>
								<div class="col-md-2">
									<div class="content" id="dianJiImgUrl">
										<c:choose>
											<c:when test="${not empty vehicle.dianJiImgUrl }">
												<img src="/UploadServlet?getfile=${vehicle.dianJiImgUrl}" class="img-small  center-block img-responsive img-thumbnail">
											</c:when>
											<c:otherwise>
												<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="content text-center">
										<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>电机图片</span> <input type="file" name="dianJiFile" id="dianJi-upload">
										</span> <span class="fileupload-process"></span>
									</div>
								</div>
								<div class="col-md-2">
									<div class="content" id="heGeZhengImgUrl">
										<c:choose>
											<c:when test="${not empty vehicle.heGeZhengImgUrl }">
												<img src="/UploadServlet?getfile=${vehicle.heGeZhengImgUrl}" class="img-small center-block img-responsive img-thumbnail">
											</c:when>
											<c:otherwise>
												<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="content text-center">
										<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>合格证图片</span> <input type="file" name="heGeZhengFile"
											id="heGeZheng-upload">
										</span> <span class="fileupload-process"></span>
									</div>
								</div>
								<div class="col-md-2">
									<div class="content" id="vehicleImgUrl">
										<c:choose>
											<c:when test="${not empty vehicle.vehicleImgUrl }">
												<img src="/UploadServlet?getfile=${vehicle.vehicleImgUrl}" class="img-small  center-block img-responsive img-thumbnail">
											</c:when>
											<c:otherwise>
												<img src="<c:url value="/images/upload.jpg"/>" class="img-small center-block img-responsive img-thumbnail">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="content text-center">
										<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>车辆图片</span> <input type="file" name="vehicleFile"
											id="vehicle-upload">
										</span> <span class="fileupload-process"></span>
									</div>
								</div>
							</div>
							-->
							<form:hidden path="personalIdImgUrl"/>
							<form:hidden path="faPiaoImgUrl" cssClass="form-control" />
							<form:hidden path="cheJiaImgUrl" cssClass="form-control" />
							<form:hidden path="dianJiImgUrl" cssClass="form-control" />
							<form:hidden path="heGeZhengImgUrl" cssClass="form-control" />
							<form:hidden path="vehicleImgUrl" cssClass="form-control" />
						</fieldset>
						<fieldset>
							<legend>车牌信息</legend>
							<form:hidden path="licensePlate.id" />
                            <div class="from-group">
                            <label class="col-md-2 control-label" for="name"> 车牌号
								</label>
								<div class="col-md-10">
									<form:input path="licensePlate.plateNum" cssClass="form-control" readonly="true" />
									<form:errors path="licensePlate.plateNum" cssClass="error" />
								</div>
                            </div>
						</fieldset>
						<fieldset>
							<!--@generate-entity-jsp-property-update@-->
							<div class="form-group">
								<div class="col-md-offset-2 col-md-10">
									<button type="submit" class="btn btn-primary btn-block">提交</button>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
			<div class="col-md-6">
				<c:if test="${not empty vehicle.personalIdImgUrl }">
					<div class="panel panel-default">
						<div class="panel-heading">身份证</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty vehicle.personalIdImgUrl && fn:endsWith(vehicle.personalIdImgUrl, 'jpg')}">
									<img src="${img}${vehicle.personalIdImgUrl}" style="width: 100%; height: auto;" class="img-responsive img-small">
								</c:when>
								<c:otherwise>
									<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty vehicle.dianJiImgUrl }">
					<div class="panel panel-default">
						<div class="panel-heading">电机</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty vehicle.dianJiImgUrl &&  fn:endsWith(vehicle.dianJiImgUrl, 'jpg')}">
									<img src="${img}${vehicle.dianJiImgUrl}" style="width: 100%; height: auto;" class="img-responsive img-small">
								</c:when>
								<c:otherwise>
									<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty vehicle.cheJiaImgUrl}">
					<div class="panel panel-default">
						<div class="panel-heading">车架</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty vehicle.cheJiaImgUrl &&  fn:endsWith(vehicle.cheJiaImgUrl, 'jpg')}">
									<img src="${img}${vehicle.cheJiaImgUrl}" style="width: 100%; height: auto;" class="img-responsive img-small">
								</c:when>
								<c:otherwise>
									<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty vehicle.vehicleImgUrl}">
					<div class="panel panel-default">
						<div class="panel-heading">车辆</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty vehicle.vehicleImgUrl &&  fn:endsWith(vehicle.vehicleImgUrl, 'jpg')}">
									<img src="${img}${vehicle.vehicleImgUrl}" style="width: 100%; height: auto;" class="img-responsive img-small">
								</c:when>
								<c:otherwise>
									<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty vehicle.faPiaoImgUrl}">
					<div class="panel panel-default">
						<div class="panel-heading">发票</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${not empty vehicle.faPiaoImgUrl &&  fn:endsWith(vehicle.faPiaoImgUrl, 'jpg')}">
									<img src="${img}${vehicle.faPiaoImgUrl}" style="width: 100%; height: auto;" class="img-responsive img-small">
								</c:when>
								<c:otherwise>
									<img src="<c:url value="/images/default_vehicle.jpg"/>" class="img-responsive img-thumbnail">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:if>

			</div>
		</div>
	</div>
</div>
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
<div class="card template-upload fade">
            <span class="preview"></span>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
    </div>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
<div class="content">
{% for (var i=0, file; file=o.files[i]; i++) { %}
   <img src="{%=file.url%}" alt="." class="card-img-top img-responsive img-thumbnail img-small">
{% } %}
</div>
</script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"/>"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/tmpl.min.js"/>"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/load-image.min.js"/>"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/canvas-to-blob.min.js"/>"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.iframe-transport.js"/>"></script>
<!-- The basic File Upload plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload.js"/>"></script>
<!-- The File Upload processing plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-process.js"/>"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-image.js"/>"></script>
<!-- The File Upload audio preview plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-audio.js"/>"></script>
<!-- The File Upload video preview plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-video.js"/>"></script>
<!-- The File Upload validation plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-validate.js"/>"></script>
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-ui.js"/>"></script>
<!-- The main application script -->
<script src="<c:url value="/js/component/vehicle/vehicle/upload.js"/>"></script>
