package com.jxw.design.service.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.jxw.design.dao.slave.OrderSlaveDao;
import com.jxw.design.dao.slave.WineSlaveDao;
import com.jxw.design.model.Order;
import com.jxw.design.model.OrderGoods;
import com.jxw.design.model.Wine;
import com.jxw.design.model.resp.BannerPictureResp;
import com.jxw.design.service.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.jxw.design.utils.SortMap.sortMapByValue;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/6 14:52
 * @Description
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    private static Logger logger = LoggerFactory.getLogger(RecommendServiceImpl.class);
    private final int recommendNum = 6;
    @Autowired
    private WineSlaveDao wineSlaveDao;
    @Autowired
    private OrderSlaveDao orderSlaveDao;

    @Override
    public List<BannerPictureResp> bannerPicture() {
        logger.info("【个性推荐】获取banner图");
        List<BannerPictureResp> pictureResps = wineSlaveDao.selectWineScoreTop();
        if (CollectionUtils.isEmpty(pictureResps)) {
            logger.info("【个性推荐】获取banner图为空");
            return null;
        }
        logger.info("【个性推荐】获取banner图成功,返回参数:{}", new Gson().toJson(pictureResps));
        return pictureResps;
    }

    @Override
    public List<Wine> recommendWine(String userId) {
        logger.info("【个性推荐】获取推荐酒单，请求参数userId:{}", new Gson().toJson(userId));
        List<Order> orders = orderSlaveDao.selectByUserIdNearToday(userId);
        if (CollectionUtils.isEmpty(orders)) {
            logger.info("【个性推荐】该用户无下单记录，推荐评分最高的6种酒品");
            List<Wine> wines = wineSlaveDao.selectWineScoreDesc();
            List<Wine> subList = wines.subList(0, recommendNum);
            return subList;
        }
        List<Long> orderGoodsIdList = Lists.newArrayList();
        for (Order order : orders) {
            List<OrderGoods> orderGoods = order.getOrderGoods();
            for (OrderGoods orderGood : orderGoods) {
                orderGoodsIdList.add(orderGood.getWineId());
            }
        }
        int[] data = new int[orderGoodsIdList.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = orderGoodsIdList.get(i).intValue();
        }
        Map<Integer, Integer> sortMapByValue = sortMapByValue(data);
        Set<Integer> keySet = sortMapByValue.keySet();
        Integer[] array = keySet.toArray(new Integer[keySet.size()]);
        int length = array.length > recommendNum ? recommendNum : array.length;
        Integer[] copyOfRange = Arrays.copyOfRange(array, array.length - length, array.length);
        List<Wine> wines = wineSlaveDao.selectWineByWineIdBatch(copyOfRange);
        if (wines.size() < recommendNum) {
            logger.info("【个性推荐】推荐数不足6，以评分高的酒品补充");
            List<Wine> winesB = wineSlaveDao.selectWineScoreDesc();
            List<Wine> wineC = winesB.subList(0, recommendNum - wines.size());
            wines.addAll(wineC);
        }
        logger.info("【个性推荐】获取推荐酒单成功，返回数据:{}", new Gson().toJson(wines));
        return wines;
    }

    @Override
    public List<Wine> privateWine(String userId) {
        logger.info("【个性推荐】获取私人酒窖,请求参数userId:{}", new Gson().toJson(userId));
        List<Order> orderList = orderSlaveDao.selectByUserId(userId);
        if (CollectionUtils.isEmpty(orderList)) {
            logger.info("【个性推荐】该用户无下单记录，推荐评分最高的6种酒品");
            List<Wine> wines = wineSlaveDao.selectWineScoreDesc();
            List<Wine> subList = wines.subList(0, recommendNum);
            return subList;
        }
        List<Long> orderGoodsIdList = Lists.newArrayList();
        for (Order order : orderList) {
            List<OrderGoods> orderGoods = order.getOrderGoods();
            for (OrderGoods orderGood : orderGoods) {
                orderGoodsIdList.add(orderGood.getWineId());
            }
        }
        int[] data = new int[orderGoodsIdList.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = orderGoodsIdList.get(i).intValue();
        }
        Map<Integer, Integer> sortMapByValue = sortMapByValue(data);
        Set<Integer> keySet = sortMapByValue.keySet();
        Integer[] array = keySet.toArray(new Integer[keySet.size()]);
        Wine wine = wineSlaveDao.selectByWineId(Long.valueOf(array[array.length - 1]));
        if (wine != null) {
            logger.warn("【个性推荐】返回酒品列表为空,推荐评分最高的6种酒品");
            List<Wine> winesA = wineSlaveDao.selectWineScoreDesc();
            List<Wine> subList = winesA.subList(0, recommendNum);
            return subList;
        }
        String wineClass = wine.getWineClass();
        String wineBrand = wine.getWineBrand();
        String wineAddress = wine.getWineAddress();
        List<Wine> winesList = wineSlaveDao.selectPrivateWine(wineClass, wineBrand, wineAddress);
        if (winesList.size() < recommendNum) {
            logger.info("【个性推荐】推荐数不足6，以评分高的酒品补充");
            List<Wine> winesB = wineSlaveDao.selectWineScoreDesc();
            List<Wine> wineC = winesB.subList(0, recommendNum - winesList.size());
            winesList.addAll(wineC);
        }
        logger.info("【个性推荐】获取私人酒窖,返回数据:{}", new Gson().toJson(winesList));
        return winesList;
    }

    @Override
    public List<Wine> hotListWine() {
        logger.info("【个性推荐】请求获取热销榜");
        List<Wine> wines = wineSlaveDao.selectWineScoreDesc();
        if (CollectionUtils.isEmpty(wines)) {
            logger.info("【个性推荐】获取热销榜为空");
            return null;
        }
        logger.info("【个性推荐】成功获取热销榜:{}", new Gson().toJson(wines));
        return wines;
    }
}
