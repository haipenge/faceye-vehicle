<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload.css"/>">
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui.css"/>">
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-noscript.css"/>">
</noscript>
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui-noscript.css"/>">
</noscript>
<script type="text/javascript" src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="cl-mcont">

	<div class="block-flat">
		<div class="header">
			<h3>车主信息</h3>
		</div>
		<form:form action="/customer/customer/save" method="post" role="form" cssClass="form-horizontal" commandName="customer" id="fileupload" enctype="multipart/form-data">
			<form:hidden path="id" />
			<form:hidden path="webUserId"/>
			<div class="row">
				<div class="col-md-8">
					<fieldset>
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.name" />
							</label>
							<div class="col-md-10">
								<form:input path="name" cssClass="form-control" />
								<form:errors path="name" cssClass="error" />
							</div>
							<!-- 
							<div class="col-md-2">
								<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>上传头像</span> <input type="file" name="files[]" multiple>
								</span> <span class="fileupload-process"></span>
							</div>
							 -->
						</div>
						<!-- 
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.personalId" />
							</label>
							<div class="col-md-10">
								<form:input path="personalId" cssClass="form-control" />
								<form:errors path="personalId" cssClass="error" />
							</div>
						</div>
						 -->
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.mobile" />
							</label>
							<div class="col-md-10">
								<form:input path="mobile" cssClass="form-control" />
								<form:errors path="mobile" cssClass="error" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.email" />
							</label>
							<div class="col-md-10">
								<form:input path="email" cssClass="form-control" />
								<form:errors path="email" cssClass="error" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.sex" />
							</label>
							<div class="col-md-10">
								<form:radiobuttons path="sex" items="${sexs}" itemValue="key" itemLabel="value" cssClass="radio-inline" />
								<form:errors path="sex" cssClass="error" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> 用户类型
							</label>
							<div class="col-md-10">
								<form:radiobuttons path="type" items="${types}" itemValue="key" itemLabel="value" cssClass="radio-inline" />
								<form:errors path="type" cssClass="error" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="name"> <spring:message code="customer.customer.nickName" />
							</label>
							<div class="col-md-10">
								<form:input path="nickName" cssClass="form-control" />
								<form:errors path="nickName" cssClass="error" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-2 fileupload-buttonbar">
								<form:hidden path="headImgUrl" cssClass="form-control" />
								<form:errors path="headImgUrl" cssClass="error" />
							</div>

						</div>

						<!--@generate-entity-jsp-property-update@-->
						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<button type="submit" class="btn btn-primary btn-block">下一步</button>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4" id="header-img">
					<c:if test="${not empty customer.headImgUrl }">
						<img src="/UploadServlet?getfile=${customer.headImgUrl}" class="img-small img-responsive img-thumbnail">
					</c:if>
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
				</div>
			</div>
		</form:form>

	</div>
</div>

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
<script src="<c:url value="/js/component/customer/customer/upload.js"/>"></script>