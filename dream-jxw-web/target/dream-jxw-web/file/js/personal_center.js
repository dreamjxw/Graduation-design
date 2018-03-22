/**
 * Created by 13510 on 2017/8/6.
 */

$.ajax({
    type:'post',
    data:'{"openid":"oEqFyvx8TdK-ETuv8esDuRJyDx7M"}',
    url:'http://oomforjmm.imwork.net:12965/personal/do',
    contentType:'application/json',
    success:function(res){
        // alert(res);
        console.log(res);
        var jsonData=JSON.parse(res);
        for(var attr in jsonData) {
            if (attr == 'user') {
                for (var attr1 in jsonData[attr]) {
                    if (attr1 == 'nickname') {
                        $('.user-name').html(jsonData[attr][attr1]);
                    }else if (attr1 == 'attentionNum') {
                        $('.follow-num').html(jsonData[attr][attr1]);
                    }else if (attr1 == 'fansNum') {
                        $('.following-num').html(jsonData[attr][attr1]);
                    }else if (attr1 == 'headimgurl') {
                        $('.user-pic img').attr('src', jsonData[attr][attr1]);
                    }
                }
            }else if(attr == 'placeRatio'){
                $('.place-ratio').html(jsonData[attr]);
            }else if(attr == 'place'){
                $('.place').html(jsonData[attr]);
            }else if(attr == 'brandRatio'){
                $('.brand-ratio').html(jsonData[attr]);
            }else if(attr == 'brand'){
                $('.brand').html(jsonData[attr]);
            }else if(attr == 'timeRatio'){
                $('.year-ratio').html(jsonData[attr]);
            }else if(attr == 'time'){
                $('.year').html(jsonData[attr]);
            }
        }
    },
    error:function(){
        $('.center-section').html('未能加载');
    }
});
//关注或粉丝被点击的时候请求ajax，跳转页面
$('.follow').click(function(){
    $.ajax({
        type:'post',
        data:'1',
        url:'http://oomforjmm.imwork.net:12965/personal/toList',
        contentType:'application/json'
    });
});
$('.following').click(function(){
    $.ajax({
        type:'post',
        data:'0',
        url:'http://oomforjmm.imwork.net:12965/personal/toList',
        contentType:'application/json'
    });
});
