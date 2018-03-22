/**
 * Created by Administrator on 2017/8/17.
 */
$.ajax({
    type:'post',
    data:'{"sendId":"3","receiveId":"2"}',
    url:'http://zhouhao.ngrok.cc/message/chatPage',
    contentType:'application/json',
    success:function(res) {
        console.log(res);
        var arrData = res;

        // for(var i=0;i<arrData.length;i++){
        // var goodsTPL ='<div class="section-first-one ">\n'+
        //     '<div class="left-image">\n'+
        //     '<img src="" class="king-image">\n'+
        //     '</div>\n'+
        //     '<div class="right-wine-list" >\n'+
        //     '<div class="red-wine-name">\n'+
        //     '<p class="pet-name"></p>\n'+
        //     '<span class="num-collect">num</span>人关注\n'+
        //     '</div>\n'+
        //     '</div>\n'+
        //     '</div>\n';
        // $('section').html($('section')[0].innerHTML+goodsTPL);
        // }
        for(var i=0;i<arrData.length;i++) {
            for (var j = 0; j < arrData[i].length; j++) {
                for (var attr in arrData[i][j]) {
                    console.log(arrData[i][j][attr]);

                }
            }
        }
    },
    error:function(){
        alert(123);
    }
});
$('#send').click(function( ){
    if($('#line-bottom').val()!=' '){
        $('<div id="time">').html();
        $('<span id="content-message"></span></br>').html($('#line-bottom').val()).appendTo('#section-content');
        $('#line-bottom').val(' ');
    }else{
        alert('内容不能为空');
    }
});
// function p(s) {
//     return s < 10 ? '0' + s: s;
// }
//
// var myDate = new Date();
// //获取当前月
// var month=myDate.getMonth()+1;
// //获取当前日
// var date=myDate.getDate();
// var h=myDate.getHours();       //获取当前小时数(0-23)
// var m=myDate.getMinutes();     //获取当前分钟数(0-59)
// var s=myDate.getSeconds();
//
// var now = p(month)+"-"+p(date)+" "+p(h)+':'+p(m)+":"+p(s);
