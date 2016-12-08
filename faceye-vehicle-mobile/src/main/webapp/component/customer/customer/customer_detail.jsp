<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="card card-block bg-white">
	<c:if test="${not empty weixinUser }">
		<div class="row">
			<div class="col-xs-3 col-md-3 col-lg-2">
				<c:choose>
					<c:when test="${not empty weixinUser.headimgurl}">
						<img src="${weixinUser.headimgurl}" alt="Welcome" class="img-responsive img-rounded img-thumbnail">
					</c:when>
					<c:otherwise>
						<img src="<c:url value="/images/default_vehicle.jpg"/>" alt="欢迎你" class="img-responsive img-rounded img-thumbnail">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-xs-9 col-md-9 col-lg-10">
				<c:if test="${empty customer.name or empty weixinUser.nickname}">
					<p>
						<a href="${oAuthUrl}" class="btn btn-success">微信授权登陆</a>
						<!-- 
						<fmt:message key="emergency.mobile.welcome" />
						&nbsp;&nbsp;${weixinUser.openid}
						 -->
					</p>

				</c:if>

				<c:if test="${not empty customer.name}">
					<p>&nbsp;&nbsp;${customer.name}</p>
					<p class="text-center">
						<a href="<c:url value="/customer/customer/edit/${customer.id}"/>">完善个人资料</a>
					</p>
				</c:if>
			</div>
			<!-- 
				<div class="col-xs-3 col-md-3 col-lg-2">
					<img src="${weixinUser.headimgurl}" alt="Welcome"
						class="img-responsive img-rounded img-thumbnail">
				</div>
				<div class="col-xs-9 col-md-9 col-lg-10">${weixinUser.nickname}</div>
				-->
		</div>
	</c:if>
	<c:if test="${empty weixinUser && not empty pageContext.request.userPrincipal }">
		<div class="row">
			<p>
				<sec:authentication property="principal.username" />
			</p>
		</div>
	</c:if>
</div>

<!-- 
<div class="card card-block bg-white">
   <h6>My Orders</h6>
   
</div>
 -->
<div class="list-group">
	<a href="<c:url value="/customer/customer/detail/${customer.id }"/>" class="list-group-item">个人资料</a>
	<c:choose>
		<c:when test="${customer.type eq 0 }">
			<a href="<c:url value="/vehicle/vehicle/home"/>" class="list-group-item">我的电动车</a>
		</c:when>
		<c:when test="${customer.type eq 1 }">
			<a href="<c:url value="/vehicle/vehicle/home"/>" class="list-group-item">上牌</a>
		</c:when>
		<c:when test="${customer.type eq 2}">
		  人民警察为人民
		</c:when>
		<c:otherwise>
		来自星星的你
		</c:otherwise>
	</c:choose>
	<!-- 
	<a
		href="<c:url value="/emergency/emergencyKnowledge/home?EQ|category.$id=8"/>"
		class="list-group-item">安全课程</a> <a
		href="<c:url value="/emergency/emergencyKnowledge/home?EQ|category.$id=9"/>"
		class="list-group-item">活动通知</a> <a
		href="<c:url value="/emergency/pubInfo/getTopPubInfos"/>"
		class="list-group-item">守护者1号排行榜&nbsp;&nbsp;<font color="red">Hot!</font></a>
		 -->
</div>


