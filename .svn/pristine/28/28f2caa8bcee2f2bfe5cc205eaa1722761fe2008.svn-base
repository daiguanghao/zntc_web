
	$(function () {
		mui.init();
		(function($) {
			
			//删除车辆
			var btnArray = ['确认', '取消'];
			$('#carlist').on('tap', '.mui-btn', function(event) {
							var elem = this;
							var li = elem.parentNode.parentNode;
							mui.confirm('确认删除该条车辆信息？', '', btnArray, function(e) {
								if (e.index == 0) {
									li.parentNode.removeChild(li);
								} else {
									setTimeout(function() {
										$.swipeoutClose(li);
									}, 0);
								}
							});
						});
						
						
			//选择时间
			var btns = $('.btn');
				btns.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var _self = this;
						var id = this.getAttribute('id');
						if(_self.picker) {
							_self.picker.show(function (rs) {
								jQuery("#"+id).val(rs.text)
								_self.picker.dispose();
								_self.picker = null;
							});
						} else {
							var optionsJson = this.getAttribute('data-options') || '{}';
							var options = JSON.parse(optionsJson);
							
							/*
							 * 首次显示时实例化组件
							 * 示例为了简洁，将 options 放在了按钮的 dom 上
							 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
							 */
							_self.picker = new $.DtPicker(options);
							_self.picker.show(function(rs) {
								/*
								 * rs.value 拼合后的 value
								 * rs.text 拼合后的 text
								 * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
								 * rs.m 月，用法同年
								 * rs.d 日，用法同年
								 * rs.h 时，用法同年
								 * rs.i 分（minutes 的第二个字母），用法同年
								 */
								jQuery("#"+id).val(rs.text)
								/* 
								 * 返回 false 可以阻止选择框的关闭
								 * return false;
								 */
								/*
								 * 释放组件资源，释放后将将不能再操作组件
								 * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
								 * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
								 * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
								 */
								_self.picker.dispose();
								_self.picker = null;
							});
						}
						
					}, false);
				});
			
		})(mui);
		
		//选择车辆
		$('#carlist').on('tap', '.mui-slider-handle', function(event) {
			var Img = $(this).find("img");
			var Img2 =$("[ischoose=1]").find("img");
			Img2.attr("src","../img/index/Group7C2.png");
			Img2.parent().attr("ischoose","0");
			Img.attr("src","../img/index/Group7C.png");
			Img.parent().attr("ischoose","1");
		});
})