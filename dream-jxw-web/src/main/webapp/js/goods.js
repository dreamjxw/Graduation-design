$(".footer-list li").eq(2).on('touchstart',function(){
	$('.pop-up').css("display","block").siblings().css("opacity","0.6");
})
$('.check-number .check').on('touchstart',function(){
	$('.pop-up').css("display","block").siblings().css("opacity","0.6");
})
$('.error').on('touchstart',function(){
	$('.pop-up').css("display","none").siblings().css("opacity","1");
})

$(".goods-number .minus").on('touchstart',function(){
	
	if(parseInt($('.goods-number span').html())<=1){
		$('.goods-number span').html(1)
	}
	else{
		$('.goods-number span').html(parseInt($('.goods-number span').html())-1);
	}
})
$(".goods-number .add").on('touchstart',function(){
	$('.goods-number span').html(parseInt($('.goods-number span').html())+1)
})