package com.jxw.design.task;

import com.google.gson.Gson;
import com.jxw.design.dao.slave.WineStockSlaveDao;
import com.jxw.design.exceptions.TaskRunErrorException;
import com.jxw.design.model.WineStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24 16:10
 */
@Service("checkStockTask")
public class CheckStockTask extends AbstractTask {
    private final Logger logger = LoggerFactory.getLogger(CheckStockTask.class);
    public static final String TASK_NAME = "检查酒品库存定时任务";
    @Autowired
    private WineStockSlaveDao wineStockSlaveDao;

    @Override
    public String taskName() {
        return TASK_NAME;
    }

    @Override
    protected void run() {
        try {
            List<WineStock> wineStocks = wineStockSlaveDao.selectAllWineStock();
            logger.info("【检查库存】已获取所有酒品库存信息", new Gson().toJson(wineStocks));
            Long wineNowReal;
            Long wineNowStock;
            for (WineStock wineStock : wineStocks) {
                wineNowReal = wineStock.getWineReal();
                wineNowStock = wineStock.getWineStock();
                logger.info("酒品" + wineStock.getWineId() + "已卖出" + wineStock.getWineReal() + "件，库存" + wineStock
                        .getWineStock());
                if (wineNowReal > Math.ceil(wineNowStock * 0.9)) {
                    logger.warn("酒品库存已不足10%,酒品id:{}", new Gson().toJson(wineStock.getWineId()));
                }
            }
        } catch (Exception e) {
            logger.error("[task]检查酒品库存定时任务线程异常", e);
            throw new TaskRunErrorException("[task]检查酒品库存定时任务线程异常", e);
        }
    }

    @Override
    public String monitorTaskExceptionTag() {
        return null;
    }
}
