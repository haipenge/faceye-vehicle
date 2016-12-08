/**
 * 说明:车辆->vehicle -> 电动车 Vehicle js 脚本 作者:haipenge
 */
var Vehicle = {
	init : function() {
		/**
		 * 提交
		 */
		Vehicle.weixinConfigRequest();
		$('#vehicle-submit').click(function() {
			Vehicle.submit();
		});

		$('.img-upload').click(function() {
			var id = $(this).attr('id');
			WeixinImgUpload.chooseImage(id);
		});
	},
	weixinConfigRequest : function() {
		var url = location.href;
		url = url.split('#')[0];
		$.ajax({
			url : '/vehicle/vehicle/buildWeixinConfigRequestObject',
			dataType : 'json',
			type : 'post',
			data : {
				url : url
			},
			success : function(data, textStatus, xhr) {
				WeixinImgUpload.buildWeixinConfigRequest(data);
			}
		});
	},
	/**
	 * Form表单提交
	 */
	submit : function() {
		var name = $('input[name="customer.name"]').val();
		var personalId = $('input[name="customer.personalId"]').val();
		var mobile = $('input[name="customer.mobile"]').val();
		var plateNum = $('input[name="licensePlate.plateNum"]').val();
		var areaId = $('select[name="area.id"]').val();
		// 身份证图片
		var personalIdImgUrl = $('input[name="personalIdImgUrl"]').val();
		var cheJiaImgUrl=$('input[name="cheJiaImgUrl"]').val();
		var dianJiImgUrl=$('input[name="dianJiImgUrl"]').val();
		var msg = '';
		if (personalIdImgUrl == '') {
			if (name == '') {
				msg += '姓名不能为空.';
			}
			if (personalId == '') {
				msg += '身份证不能为空.';
			}
		}
		if (mobile == '') {
			msg += '手机号不能为空.';
		}
		if (plateNum == '') {
			msg += '车牌不能为空.';
		}
		if (areaId == '' || areaId == '0') {
			msg += '辖区必选.'
		}
		if(dianJiImgUrl==''&&cheJiaImgUrl==''){
			msg+='车架、电机图片至少上传一张.'
		}
		if (msg != '') {
			var m = new Msg({
				msg : msg,
				type : 'warning'
			});
			m.show();
			$('html, body').animate({
				scrollTop : 0
			}, 'slow');
		} else {
			$('#vehicle-form').submit();
		}
	},
	/**
	 * 回填Img
	 */
	resetFormImg:function(){
		// 身份证图片
		var personalIdImgUrl = $('input[name="personalIdImgUrl"]').val();
		var cheJiaImgUrl=$('input[name="cheJiaImgUrl"]').val();
		var dianJiImgUrl=$('input[name="dianJiImgUrl"]').val();
		var vehicleImgUrl=$('input[name="vehicleImgUrl"]').val();
		var faPiaoImgUrl = $('input[name="faPiaoImgUrl"]').val();
		if(personalIdImgUrl!=''){
			$('#shenfenzhen' + ' .tips').empty().append(
			'<small class="text-muted">身份证</small>');
		}
		if(cheJiaImgUrl!=''){
			$('#chejia' + ' .tips').empty().append(
			'<small class="text-muted">车架</small>');
		}
		if(dianJiImgUrl!=''){
			$('#dianji' + ' .tips').empty().append(
			'<small class="text-muted">电机</small>');
		}
		if(vehicleImgUrl!=''){
			$('#cheliang' + ' .tips').empty().append(
			'<small class="text-muted">车辆</small>');
		}
		if(faPiaoImgUrl!=''){
			$('#fapiao' + ' .tips').empty().append(
			'<small class="text-muted">车辆</small>');
		}
	}
};

var WeixinImgUpload = {
	buildWeixinConfigRequest : function(data) {
		// alert(data.appId + ':' + data.timestamp + ':' + data.noncestr + ":"
		// + data.signature);
		wx.config({
			debug : false,// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : data.appId, // 必填，公众号的唯一标识
			timestamp : data.timestamp, // 必填，生成签名的时间戳
			nonceStr : data.noncestr, // 必填，生成签名的随机串
			signature : data.signature,// 必填，签名，见附录1
			jsApiList : [ 'chooseImage', 'previewImage', 'uploadImage' ]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		// wx.previewImage({
		// current: '', // 当前显示图片的http链接
		// urls: [] // 需要预览的图片http链接列表
		// });
		// wx.uploadImage({
		// localId: '', // 需要上传的图片的本地ID，由chooseImage接口获得
		// isShowProgressTips: 1, // 默认为1，显示进度提示
		// success: function (res) {
		// var serverId = res.serverId; // 返回图片的服务器端ID
		// }
		// });
	},
	chooseImage : function(elId) {
		wx
				.chooseImage({
					count : 1, // 默认9
					sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
					sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
					success : function(res) {
						// alert('choose image succss,localIds is:' + res.localIds);
						var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
						if (localIds) {
							$
									.each(
											localIds,
											function(i, n) {
												var id = localIds[i];
												// alert(id);
												wx
														.uploadImage({
															localId : id, // 需要上传的图片的本地ID，由chooseImage接口获得
															isShowProgressTips : 1, // 默认为1，显示进度提示
															success : function(res) {
																$('#' + elId + " .img")
																		.empty()
																		.append(
																				'<img src="'
																						+ id
																						+ '" class="card-img-top" style="width:100%;height:150px;max-height:150px;">');
																var serverId = res.serverId; // 返回图片的服务器端ID
																$('#' + elId + ' .tips').parent().css({
																	"padding-top" : "2px",
																	"padding-bottom" : "2px",
																	"margin-top" : "0px",
																	"margin-bottom" : "0px",
																	"margin-left" : "5px"
																});
																if (elId == 'shenfenzhen') {
																	$('input[name="personalIdImgUrl"]').val(serverId);
																	$('#' + elId + ' .tips').empty().append(
																			'<small class="text-muted">身份证</small>');
																} else if (elId == 'chejia') {
																	$('input[name="cheJiaImgUrl"]').val(serverId);
																	$('#' + elId + ' .tips').empty().append(
																			'<small class="text-muted">车架图</small>');
																} else if (elId == 'fapiao') {
																	$('input[name="faPiaoImgUrl"]').val(serverId);
																	$('#' + elId + ' .tips').empty().append(
																			'<small class="text-muted">发票图</small>');
																} else if (elId == 'hegezheng') {
																	$('input[name="heGeZhengImgUrl"]').val(serverId);
																	$('#' + elId + ' .tips').empty().append(
																			'<small class="text-muted">合格证</small>');
																} else if (elId == 'cheliang') {
																	$('input[name="vehicleImgUrl"]').val(serverId);
																	$('#' + elId + ' .tips').empty().append(
																			'<small class="text-muted">车辆图</small>');
																} else if (elId == 'dianji') {
																	$('input[name="dianJiImgUrl"]').val(serverId);
																	$('#' + elId + ' .tips').empty().append(
																			'<small class="text-muted">电机图</small>');
																}
																// var serverId = res.serverId; // 返回图片的服务器端ID
																// var serverIds=$('input[name="serverIds"]').val();
																// serverIds+=",";
																// serverIds+=serverId;
																// $('input[name="serverIds"]').val(serverIds);
																// alert(serverId);
															}
														});
											});
						}
					}
				});
	}
};

$(document).ready(function() {
//	alert('in input');
	$('input[name="customer.name"]').focus();
	Vehicle.init();
});
