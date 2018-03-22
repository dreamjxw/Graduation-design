$.ajax({
    type:'post',
    data:'{"sendId":"2"}',
    url:'http://zhouhao.ngrok.cc/message/messagePage',
    contentType:'application/json',
    success:function(res){
        console.log(res);
        var arrData=res;
        for(var i=0;i<arrData[0].length;i++){
            var goodsTPL = '<div class="first-news">\n ' +
                '<div class="left-head-portrait">\n' +
                '<img src="" class="left-image">\n ' +
                '</div>\n ' +
                 '<a class="right-box" href="message_details                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ">\n'+
                '<div >\n' +
                '<div class="sender-name">\n' +
                '<span class="nick-name"></span>\n' +
                '<span class="identity"></span>\n' +
                '</div>\n' +
                '<p class="friend-news"></p>\n' +
                '</div>\n' +
                 '</a>\n'+
                '</div> \n';
            $('section').html($('section')[0].innerHTML+goodsTPL);
        }
        for(var i=0;i<arrData.length;i++) {
            for (var j = 0; j < arrData[i].length; j++) {
                for (var attr in arrData[i][j]) {
                    if(attr == 'nickname'){
                        $('.nick-name').eq(j).html(arrData[i][j][attr]);
                        // console.log(arrData[i][j][attr]);
                    }
                    if(attr == 'headimgurl'){
                        $('.left-image').eq(j)[0].src=arrData[i][j][attr];
                    }
                    if(attr == 'userIdentity'){
                        $('.identity').eq(j).html(arrData[i][j][attr]);
                    }
                    if(attr == 'messageContent'){
                        $('.friend-news').eq(j).html(arrData[i][j][attr]);
                    }
                    if(attr == 'messageStatus' && arrData[i][j][attr] == 1){
                        // alert("1");
                        $('.friend-news').eq(j).html('<span id="red-spot" style="display:inline-block;background-color: red;width:0.05rem;height:0.05rem;border-radius:50%;position:absolute;right:0rem;"></span>')
                    }
                }

            }
        }
    },
    error:function(){
    }
});
