/**
 * Created by 13510 on 2017/8/16.
 */
//别人页面点击关注
var openPerson,otherPerson;
$.ajax({
    type:'post',
    url:'http://oomforjmm.imwork.net:12965/personal/test2',/////////////////该url用来测试
    contentType:'application/json',
    success:function(res){
        openPerson='';
        otherPerson='';
        console.log(res);
        var jsonData=JSON.parse(res);
        for(var attr in jsonData){
            if(attr=='isAt'){
                //进别人页面看是否关注，1为关注，0为未关注,关注为true,未关注为false
                if(jsonData[attr]=='1'){
                    $('.add-attention').html('已关注').css('color','#e8b958');
                    bIsAt=true;
                }else{
                    $('.add-attention').html('+关注').css('color','#444444');
                    bIsAt=false;
                }
            }else if(attr=='otherId'){
                //由于前期的问题，openPerson存储的为页面展示人的id
                openPerson=jsonData[attr];
            }else if(attr=='user'){
                for(var attr1 in jsonData[attr]){
                    if(attr1=='headimgurl'){
                        $('.user-pic img').attr('src',jsonData[attr][attr1]);
                    }else if(attr1=='nickname'){
                        $('.user-name').html(jsonData[attr][attr1]);
                    }else if(attr1=='attentionNum'){
                        $('.follow-num').html(jsonData[attr][attr1]);
                    }else if(attr1=='fansNum'){
                        $('.following-num').html(jsonData[attr][attr1]);
                    }else if (attr1== 'sex'){
                        if (jsonData[attr][attr1]=='1'){
                            $('.center-wine-class .title .text').html('他所购买的酒品中');
                        }else{
                            $('.center-wine-class .title .text').html('她所购买的酒品中');
                        }
                    }else if(attr1=='openid'){
                        //由于前期的问题，otherPerson存储的为浏览该页面人的id
                        otherPerson=jsonData[attr][attr1];
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
            }else if(attr == 'yearRatio'){
                $('.year-ratio').html(jsonData[attr]);
            }else if(attr == 'time'){
                $('.year').html(jsonData[attr]);
            }
        }
    },
    error:function(){
        //$('.center-section').html('未能加载');
    }
});

//点击关注或者取关，对后台传输数据
var timer,bIsAt;
$('.add-attention').click(function(){
    if(bIsAt){//此时为true,点击取关
        $.ajax({
            type:'post',
            data:'{"openId":"'+otherPerson+'","attentionId":"'+openPerson+'","attentionName":"'+$('.user-name').html()+'","attentionHeadimg":"'+$('.user-pic img').attr('src')+'"}',//////////otherPerson,openPerson,$('.user-name').html(),$('.user-pic img')[0].src
            url:'http://oomforjmm.imwork.net:12965/attention/unfollow',
            contentType:'application/json',
            success:function(res){
                if(res=='succ'){
                    $('.add-attention').html('+关注').css('color','#444444');
                    $('.following-num').html($('.following-num').html()-1);
                }
            }
        });
    }else{//此时为false，取关点击之后加关注
        $.ajax({
            type:'post',
            data:'{"openid":"'+otherPerson+'","attentionId":"'+openPerson+'"}',/////////需要给变量浏览人otherPerson，被浏览人openPerson的id
            url:'http://oomforjmm.imwork.net:12965/attention/attention',
            contentType:'application/json',
            success:function(res){
                if(res=='succ'){
                    $('.add-attention').html('已关注').css('color','#e8b958');
                    $('.following-num').html(parseInt($('.following-num').html())+1);
                    clearInterval(timer);
                    addOneAnimation();
                }
            }
        });
    }
    bIsAt=!bIsAt;
});
//实现一个点击关注之后，旁边出现一个+1的效果
function addOneAnimation(){
    $('<span class="addOne">').html('+1').css({'font-size':'0.2rem','color':'#e8b958','opacity':'1','position':'relative','top':'0'}).appendTo($('.follow-following'));
    timer=setInterval(function(){
        var opacityNum=$('.addOne')[0].style.opacity-0.05;
        var topNum=parseInt($('.addOne')[0].style.top)-1;
        $('.addOne').css({'opacity':opacityNum,'top':topNum+'px'});
        if(opacityNum==0){
            clearInterval(timer);
            $('.addOne').remove();
        }
    },50);
}

