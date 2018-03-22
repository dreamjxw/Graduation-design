package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.WineDao;
import com.jxw.design.dao.slave.WineStockSlaveDao;
import com.jxw.design.exceptions.UpdateFailException;
import com.jxw.design.model.WineStock;
import com.jxw.design.service.WineScoreService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/21  19:46
 */
@Service
public class WineScoreServiceImpl implements WineScoreService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(WineScoreServiceImpl.class);
    @Autowired
    private WineStockSlaveDao wineStockSlaveDao;
    @Autowired
    private WineDao wineDao;

    @Override
    public void countAndUpdateWineScore() {
        logger.info("【酒品评分】请求获取所有酒品库存信息");
        List<WineStock> wineStocks = wineStockSlaveDao.selectAllWineStock();
        logger.info("【酒品评分】已获取所有酒品库存信息", new Gson().toJson(wineStocks));
        Long wineAlwaysReal;
        Long wineAlwaysStock;
        for (WineStock wineAlwaysStocks : wineStocks) {
            wineAlwaysReal = wineAlwaysStocks.getWineAlwaysReal();
            wineAlwaysStock = wineAlwaysStocks.getWineAlwaysStock();
            String format = new DecimalFormat("0.00").format(wineAlwaysReal / wineAlwaysStock);
            double score = Double.parseDouble(format) * 10 > 3.0 ? Double.parseDouble(format) * 10 : 3.0;
            logger.info("【酒品评分】开始更新第" + wineAlwaysStocks.getId() + "个酒品评分,评分数:{}", new Gson().toJson(score));
            int i = wineDao.updateWineScore(wineAlwaysStocks.getWineId(), score);
            if (i > 0) {
                logger.info("【酒品评分】第" + wineAlwaysStocks.getId() + "个酒品评分更新成功,评分数:{}", new Gson().toJson(score));
            } else {
                logger.info("【酒品评分】第" + wineAlwaysStocks.getId() + "个酒品评分更新失败,评分数:{}", new Gson().toJson(score));
                new UpdateFailException("第" + wineAlwaysStocks.getId() + "个酒品评分更新失败");
            }
        }
    }

    @Override
    public void checkStock() {
    }
}
