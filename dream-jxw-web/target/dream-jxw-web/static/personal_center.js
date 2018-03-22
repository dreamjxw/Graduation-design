/**
 * Created by 13510 on 2017/8/6.
 */
var bOn=true;
$('.add-attention').on('click',function(){
    if(bOn){
        $('.following-num').html(parseInt($('.following-num').html())+1);
        $(this).css('color','#e8b958').html('取消关注');
        $("<span class='addOne'>").html('+1').css({'font-size':'0.2rem','color':'#e8b958','opacity':'1','position':'relative','top':'0'}).appendTo($('.follow-following'));
        var timer=setInterval(function(){
            var opacityNum=$('.addOne')[0].style.opacity-0.05;
            var topNum=parseInt($('.addOne')[0].style.top)-1;
            $('.addOne').css({'opacity':opacityNum,'top':topNum+'px'});
            if(opacityNum==0){
                clearInterval(timer);
                $('.addOne').remove();
            }
        },50);
    }else{
        clearInterval(timer);
        $('.following-num').html(parseInt($('.following-num').html())-1);
        $(this).css('color','#444444').html('+关注');
    }
    bOn=!bOn;
});
$.ajax({
    type:'post',
    data:"{'openid':'oEqFyv-wke-VwiQXpgOX_beyHrEY'}",
    url:'/personal/do',
    contentType:'application/json',
    success:function(res){
        alert(res);
        var arrData=JSON.parse(res);
        for(var i=0;i<arrData.length;i++){
            for(var attr in arrData[i]){
                if(attr==nickname){
                    $("#user-name").html(arrData[attr]);
                    $(".attention").css('display','none');
                    $(".indent").css('display','block');
                } else{
                    $("#user-name").html(arrData[attr]);
                    $(".attention").css('display','flex');
                    $(".indent").css('display','none');
                }
                if(attr==attentionNum){
                    $(".follow-num").html(arrData[attr]);
                }
                if(attr==fansNum){
                    $(".follow-num").html(arrData[attr]);
                }
                if(attr==headimgsrc){
                    $(".follow-num").html(arrData[attr]);
                }
            }
        }
    },
    error:function(){
        alert(123);
    }
});

