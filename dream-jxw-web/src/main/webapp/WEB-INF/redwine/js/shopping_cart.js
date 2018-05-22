// $.ajax({
// 	method:'post',
// 	url:'http://dreamjxw.imwork.net:80/jxw/design/shopcart/insert.htm',
// 	contentType: 'application/json',
// 	data:'[{"userId":"oXJnE1Rr36wJ2m_czWkvM8JP3eZ","wineId":"1", "wineNum":"8"}]',
// 	success:function(res){
// 		console.log(res);
//     var arr={"0":'../img/shopping_cart/check.png',"1":'../img/shopping_cart/check_y.png'};
// 		//console.log(res.length);  
//     	var num = res.length;
// 		for(var i=0;i<res.length;i++){
//       //var bOn = true;
// 			var goodsList = '<li>\n'+ 
// 					'<div class="section-left">\n'+
//                     	'<div class="check">\n'+
//                     		'<img src="../img/shopping_cart/check.png" style="width:0.25rem;height:0.25rem">\n'+
//                     	'</div>\n'+
//                     	'<img src="http://pics.sc.chinaz.com/files/pic/pic9/201703/bpic843.jpg" class="winePicture" style="width:1.5rem;height:1.5rem">\n'+
//                 	'</div>\n'+
//                 	'<div class="center">\n'+
//                    		'<p class="wineName">'+ res[i].wineName +'</p>\n'+
//                     	'<p>\n'+
//                     		'<span class="wineGrade">\n'+'评分 '+ res[i].wineScore +' 分' +
//                     		'</span><span class="winePrice">\n'+ '| 售价 '+ res[i].winePrice +' 元'+ '</span>\n'+
// 						'</p>\n'+
//                     '</div>\n'+
//                     '<div class="section-right">\n'+
//                     	'<div class="right">\n'+
//                          	'<div>'+'编辑'+'</div>\n'+
//                     	'</div>\n'+
// 	                    '<div class="count">\n'+
// 	                        '<div class="minus">' + '-' + '</div>\n'+
// 	                        '<div class="number">' + '1' + '</div>\n'+
// 	                        '<div class="add">' + '+' + '</div>\n'+
// 	                    '</div>\n'+ 
//                       '<input type="button" name="" class="delet" value="删除">\n'+
//                 	'</div>\n'+    
//             	'</li>';
//             $(".goods").html($('.goods').html()+goodsList);
//             $('.section-right .right').on('touchstart',function(){
//                 $(this).css("display","none");
//                 $(this).next().css("display","none");
//                 $(this).next().next().css("display","block");
//                 $('.delet').on('touchstart',function(){
//                 $(this).parent().parent().remove();
//                 });       
//             }) 

//             for(var attr in res[i]){
//               if(attr == 'wineImg'){
//                 $('.winePicture').eq(i)[0].src = res[i][attr];
//               }
//             }

// 		}
//   //  选择商品
//     var bOn = true;
//     $(".check img").eq($(".check img").length-1).on('touchstart',function(){
//       if(bOn){
//         $(".check img").each(function(i){
//           $('.check img').eq(i)[0].src=arr[0];
//         })
//         bOn = !bOn;
//       }
//       else{
//         $(".check img").each(function(i){
//           $('.check img').eq(i)[0].src=arr[1];
//         })
//         bOn = !bOn;        
//       }
//     })
//     $(".check img").each(function(i){
//       $('.check img').eq(i).bOn = true;
//       $('.check img').eq(i).on('touchstart',function(){
//           if(this.bOn){
//             $(this)[0].src=arr[1];
//              this.bOn = !this.bOn;
//           }
//           else{
//              $(this)[0].src=arr[0];
//              this.bOn = !this.bOn;
//           }
//       }) 

//     });
    
//     //加减数量
//     $('.add').each(function(i){
//       $('.add').eq(i).on('touchstart',function(){
//         $('.number').eq(i).html(parseInt($('.number').eq(i).html())+1)
//       })
//     })
//     $('.minus').each(function(i){
//       $('.minus').eq(i).on('touchstart',function(){
//         if(parseInt($('.number').eq(i).html()) > 1)
//         {
//           $('.number').eq(i).html(parseInt($('.number').eq(i).html())-1);
//         }
//         else{
//           $('.number').eq(i).html(1);
//         }
//       })
//     })      
   	

// 	},
// 	error:function(){
		
// 	}

// })