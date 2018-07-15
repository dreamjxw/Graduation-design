// var instance = axios.create({
//     header:{contentType: 'application/x-www-form-urlencoded'}
// })
// new Vue({
    
//         el:"#page",
//         data:{
//             tabId:0,
//             wineId:0,
//             imgList:[
//                 {url:"./img/index/banner_01.jpg"},
//                 {url:"./img/index/banner_02.jpg"},
//                 {url:"./img/index/banner_03.jpg"},
//                 {url:"./img/index/banner_04.jpg"},
//                 {url:"./img/index/banner_05.jpg"},
//             ],

//         },
//         mounted () {
//             var slideshow = $('.banner').swiper ({
//                 pagination: '.pagination',
//                 paginationClickable: true,
//                 speed: 2000,
//                 loop: true,
//                 observer:true,
//                 observeParents:true,
//                 autoplayDisableOnInteraction : false,
//                 autoplay:1500
//             });
//         var that = this;
//         instance({
//             method:"POST",
//             url:"http://dreamjxw.imwork.net:80/jxw/design/recommend/banner.htm",
//             contentType: 'Access-Control-Allow-Origin',
//         }).then(function(res){
//             console.log(res.data);

//         },function(error){
//             console.log(res);
//         })
//         }
// })