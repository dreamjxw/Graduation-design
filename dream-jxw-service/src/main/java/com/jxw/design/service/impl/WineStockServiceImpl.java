package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.WineStockDao;
import com.jxw.design.dao.slave.WineStockSlaveDao;
import com.jxw.design.model.WineStock;
import com.jxw.design.service.WineStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/8 13:02
 * @Description
 */
@Service
public class WineStockServiceImpl implements WineStockService {
    private final Logger logger = LoggerFactory.getLogger(WineStockServiceImpl.class);
    @Autowired
    private WineStockDao wineStockDao;
    @Autowired
    private WineStockSlaveDao wineStockSlaveDao;

    @Override
    public int updateStock(Long wineId, Long wineReal) {
        logger.info("【扣减库存】请求扣减酒品库存，请求数据:{}", new Gson().toJson(wineReal));
        WineStock wineStock = wineStockSlaveDao.selectByWineId(wineId);
        if (wineStock.getWineStock() - wineStock.getWineReal() < wineReal) {
            logger.warn("库存不足无法下单，请管理员及时补充货源");
            return 0;
        }
        return wineStockDao.updateStock(wineId, wineStock.getWineReal() + wineReal, wineStock.getWineAlwaysReal() +
                wineReal);
    }

    @Override
    public boolean checkStock(Long wineId, Long wineReal) {
        logger.info("【检查库存】请求检查库存，请求参数:{}", new Gson().toJson(wineId));
        WineStock wineStock = wineStockSlaveDao.selectByWineId(wineId);
        if (wineStock.getWineStock() - wineStock.getWineReal() < wineReal) {
            logger.warn("库存不足无法下单，请管理员及时补充货源");
            return false;
        }
        return true;
    }

    @Override
    public int insertStock(Long wineId, Long stock) {
        return wineStockDao.insertStock(wineId, stock, stock);
    }

    @Override
    public int upStock(Long wineId, Long stock) {
        WineStock wineStock = wineStockSlaveDao.selectByWineId(wineId);
        logger.info("【增加库存】查询到该酒品库存信息,:{}", new Gson().toJson(wineStock));
        if (wineStock.getWineAlwaysStock() == null) {
            wineStock.setWineAlwaysStock(0L);
        }
        if (wineStock.getWineStock() == null) {
            wineStock.setWineStock(0L);
        }
        Long newAlwaysStock = wineStock.getWineAlwaysStock() + stock;
        Long newStock = wineStock.getWineStock() + stock;
        logger.info("【增加库存】更新既往库存数:{},更新新的库存数:{}", new Gson().toJson(newAlwaysStock), new Gson().toJson(newStock));
        return wineStockDao.upStock(wineId, newStock, newAlwaysStock);
    }

    @Override
    public int deleteStock(Long wineId) {
        return wineStockDao.deleteStock(wineId);
    }

    @Override
    public WineStock selectStock(Long wineId) {
        return wineStockSlaveDao.selectByWineId(wineId);
    }
}
