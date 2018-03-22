package com.jxw.design.service.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.jxw.design.dao.master.ShopCartDao;
import com.jxw.design.dao.slave.ShopCartSlaveDao;
import com.jxw.design.dao.slave.WineSlaveDao;
import com.jxw.design.model.ShopCart;
import com.jxw.design.model.Wine;
import com.jxw.design.model.req.ShopCartReq;
import com.jxw.design.service.ShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 19:44
 * @Description
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {
    private static Logger logger = LoggerFactory.getLogger(ShopCartServiceImpl.class);
    @Autowired
    private ShopCartDao shopCartDao;
    @Autowired
    private ShopCartSlaveDao shopCartSlaveDao;
    @Autowired
    private WineSlaveDao wineSlaveDao;

    @Override
    public int insertShopCart(ShopCartReq shopCartReq) {
        logger.info("【购物车系统】商品请求添加购物车，请求数据:{}", new Gson().toJson(shopCartReq));
        ShopCart shopCart = shopCartSlaveDao.checkRepeat(shopCartReq.getUserId(), shopCartReq.getWineId());
        if (shopCart == null) {
            logger.info("【购物车系统】购物车中无该商品，新添加");
            return shopCartDao.insertShopCart(shopCartReq);
        }
        logger.info("【购物车系统】购物车中有该商品，修改数量");
        int newWineNum = shopCartReq.getWineNum() + shopCart.getWineNum();
        logger.info("【购物车系统】购物车中已有该商品" + shopCart.getWineNum() + "件，新添加" + shopCartReq.getWineNum() + "件，更新后购物车中商品" +
                newWineNum + "件");
        shopCartReq.setWineNum(newWineNum);
        return updateShopCart(shopCartReq);
    }

    @Override
    public List<ShopCart> selectByUserId(String userId) {
        logger.info("【购物车系统】请求查询购物车，请求用户ID:{}", new Gson().toJson(userId));
        List<ShopCart> shopCarts = shopCartSlaveDao.selectByUserId(userId);
        if (CollectionUtils.isEmpty(shopCarts)){
            logger.info("【购物车系统】该用户购物车为空");
            return null;
        }
        Integer[] wineId = new Integer[shopCarts.size()];
        for (int i = 0; i < wineId.length; i++) {
            wineId[i] = shopCarts.get(i).getWineId().intValue();
        }
        List<Wine> wines = wineSlaveDao.selectWineByWineIdBatch(wineId);
        logger.info("【购物车系统】查询到购物车中商品的详细信息,:{}", new Gson().toJson(wines));
        List<ShopCart> cartArrayList = Lists.newArrayList();
        ShopCart shopCart;
        for (int i = 0; i < wineId.length; i++) {
            shopCart = new ShopCart(shopCarts.get(i).getId(), shopCarts.get(i).getUserId(), shopCarts.get(i)
                    .getWineId(), wines.get(i).getWineName(), wines.get(i).getWineScore(), wines.get(i).getWineImg(),
                    wines.get(i).getWinePrice(), shopCarts.get(i).getWineNum());
            cartArrayList.add(shopCart);
        }
        logger.info("查询到购物车信息,:{}", new Gson().toJson(cartArrayList));
        return cartArrayList;
    }

    @Override
    public int updateShopCart(ShopCartReq shopCartReq) {
        logger.info("【购物车系统】请求更改购物车信息，请求数据:{}", new Gson().toJson(shopCartReq));
        return shopCartDao.updateShopCart(shopCartReq);
    }

    @Override
    public int deleteShopCart(ShopCartReq shopCartReq) {
        logger.info("【购物车系统】请求删除购物车商品，请求数据:{}", new Gson().toJson(shopCartReq));
        return shopCartDao.deleteShopCart(shopCartReq);
    }

    @Override
    public int deleteAllShopCart(String userId) {
        logger.info("【购物车系统】用户请求清空购物车，请求数据:{}", new Gson().toJson(userId));
        return shopCartDao.deleteAllShopCart(userId);
    }
}
