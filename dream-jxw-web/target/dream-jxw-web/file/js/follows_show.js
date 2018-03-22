/**
 * Created by 13510 on 2017/8/16.
 * by 张乐
 */

//跳转页面之后优先展示其中一个选项卡
$.ajax({
    type:'post',
    data:'{"type":"2"}',
    url:'http://oomforjmm.imwork.net:12965/personal/getList',
    contentType:'application/json',
    success:function(res){
        var a=JSON.parse(res);
        request(a);
    },
    error:function(){
        alert(456);
    }
});
//点击关注/粉丝给后台传不同的数据,并请求数据
$('.attention-title').click(function(){
    showAttention();
    $.ajax({
        type:'post',
        data:'{"type":"1","currentPage":"1"}',
        contentType:'application/json',
        url:'http://oomforjmm.imwork.net:12965/personal/getList',//////////////
        success:function(res){
            //console.log(res);
            var a=JSON.parse(res);
            request(a);
        }
    });
});
$('.fans-title').click(function(){
    showFans();
    $.ajax({
        type:'post',
        data:'{"type":"0","currentPage":"1"}',
        contentType:'application/json',
        url:'http://oomforjmm.imwork.net:12965/personal/getList',//////////////
        success:function(res){
            //console.log(res);
            var a=JSON.parse(res);
            request(a);
        }
    });
});

//点击关注/粉丝，添加样式，分别展示
function showAttention(){
    $('.attention-title').find('div').css('border-bottom','0.05rem #e8b958 solid');
    $('.fans-title').find('div').css('border-bottom','0');
    $('.attention').css('display','block');
    $('.fans').css('display','none');
}
function showFans(){
    $('.fans-title').find('div').css('border-bottom','0.05rem #e8b958 solid');
    $('.attention-title').find('div').css('border-bottom','0');
    $('.attention').css('display','none');
    $('.fans').css('display','block');
}


//处理获取到的数据
function request(data){
    var jsonData=data;
    var sAttention='';
    var sFans='';
    for(var attr in jsonData) {
        if(attr == 'type'){
            if(jsonData[attr]=='1'){
                showAttention();
            }else{
                showFans();
            }
        }else if(attr=='attentionList'){
            //先创建关注页面的li
            for(var i=0;i<jsonData[attr].length;i++){
                sAttention+='<li><a href="others_center.html">\n'+///////////////////跳转href
                                '<span class="attention-pic"><img src="img/follows_show/fans_pic.png"></span>\n'+
                                '<span class="attention-name">关注昵称</span>\n'+
                             '</a></li>';
            }
            $('.attention').html(sAttention);
            //渲染li
            for(var i=0;i<jsonData[attr].length;i++){
                for(var attr1 in jsonData[attr][i]){
                    if(attr1=='attentionHeadimg') {
                        $('.attention-pic').eq(i).find('img').attr('src', jsonData[attr][i][attr1]);
                    }else if(attr1=='attentionName'){
                        $('.attention-name').eq(i).html(jsonData[attr][i][attr1]);
                    }else if(attr1=='attentionId'){
                        //给li绑定此人id,方便点击给后台交互
                        $('.attention li').eq(i)[0].attentionId=jsonData[attr][i][attr1];
                    }
                }
            }
            //点击li，给后台标记
            $('.attention li').click(function(){
                $.ajax({
                    type:'post',
                    url:'http://oomforjmm.imwork.net:12965/personal/other',
                    data:this.attentionId,//将关注人id用json对象传给后台
                    contentType:'application.json'
                });
            });
        }else if(attr=='fansList'){
            //创建粉丝页面的li
            for(var i=0;i<jsonData[attr].length;i++){
                sFans+='<li><a href="others_center.html">\n'+
                    '<span class="fans-pic"><img src="img/follows_show/fans_pic.png" style="width:1.34rem;height:1.34rem;"></span>\n'+
                    '<span class="fans-name">粉丝昵称</span>'+
                    '</a></li>';
            }
            $('.fans').html(sFans);
            //渲染li
            for(var i=0;i<jsonData[attr].length;i++){
                for(var attr1 in jsonData[attr][i]){
                    if(attr1=='fansHeadimg') {
                        $('.fans-pic').eq(i).find('img')[0].src = jsonData[attr][i][attr1];
                    }else if(attr1=='fansName'){
                        $('.fans-name').html(jsonData[attr][i][attr1]);
                    }
                }
            }
            //点击li，给后台标记
            $('.fans li').click(function(){
                $.ajax({
                    type:'post',
                    url:'http://oomforjmm.imwork.net:12965/personal/other',
                    data:this.attentionId,//将关注人id用json对象传给后台
                    contentType:'application/json'
                });
            });
        }
    }
}
