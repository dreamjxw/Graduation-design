package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.WineDao;
import com.jxw.design.dao.slave.WineSlaveDao;
import com.jxw.design.model.Wine;
import com.jxw.design.service.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/25 20:47
 */
@Service
public class WineServiceImpl implements WineService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private WineSlaveDao wineSlaveDao;
    @Autowired
    private WineDao wineDao;

    @Override
    public Wine selectWineByWineId(Long wineId) {
        logger.info("【酒品系统】查询酒品详情,请求参数:{}", new Gson().toJson(wineId));
        Wine wine = wineSlaveDao.selectByWineId(wineId);
        logger.info("【酒品系统】查询酒品详情,请求结果:{}", new Gson().toJson(wine));
        return wine;
    }

    @Override
    public List<Wine> searchWine(String content) {
        logger.info("【搜索系统】请求查询酒品信息,请求参数:{}", new Gson().toJson(content));
        return wineSlaveDao.searchWine(content);
    }

    @Override
    public int insertWine(Wine wine) {
        return wineDao.insertWine(wine);
    }

    @Override
    public int deleteWine(Wine wine) {
        return wineDao.deleteWine(wine);
    }

    @Override
    public int updateWine(Wine wine) {
        return wineDao.updateWine(wine);
    }
}
