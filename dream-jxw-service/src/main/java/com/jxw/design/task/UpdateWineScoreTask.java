package com.jxw.design.task;

import com.google.gson.Gson;
import com.jxw.design.dao.master.WineDao;
import com.jxw.design.dao.slave.WineStockSlaveDao;
import com.jxw.design.exceptions.TaskRunErrorException;
import com.jxw.design.exceptions.UpdateFailException;
import com.jxw.design.model.WineStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24 17:10
 */
@Service("updateWineScoreTask")
public class UpdateWineScoreTask extends AbstractTask {
    private final Logger logger = LoggerFactory.getLogger(UpdateWineScoreTask.class);
    public static final String TASK_NAME = "更新红酒评分定时任务";
    @Autowired
    private WineStockSlaveDao wineStockSlaveDao;
    @Autowired
    private WineDao wineDao;

    @Override
    public String taskName() {
        return TASK_NAME;
    }

    @Override
    protected void run() {
        try {
            List<WineStock> wineStocks = wineStockSlaveDao.selectAllWineStock();
            logger.info("【酒品评分】已获取所有酒品库存信息", new Gson().toJson(wineStocks));
            Long wineAlwaysReal;
            Long wineAlwaysStock;
            for (WineStock wineAlwaysStocks : wineStocks) {
                wineAlwaysReal = wineAlwaysStocks.getWineAlwaysReal();
                wineAlwaysStock = wineAlwaysStocks.getWineAlwaysStock();
                double format = (double) wineAlwaysReal / wineAlwaysStock;
                double score1 = format * 10 > 3.0 ? format * 10 : 3.0;
                String aDouble = new DecimalFormat("0.00").format(score1);
                double parseDouble = Double.parseDouble(aDouble);
                logger.info("【酒品评分】开始更新第" + wineAlwaysStocks.getId() + "个酒品评分,评分数:{}", new Gson().toJson(parseDouble));
                int i = wineDao.updateWineScore(wineAlwaysStocks.getWineId(), parseDouble);
                if (i > 0) {
                    logger.info("【酒品评分】第" + wineAlwaysStocks.getId() + "个酒品评分更新成功,评分数:{}", new Gson().toJson
                            (parseDouble));
                } else {
                    logger.info("【酒品评分】第" + wineAlwaysStocks.getId() + "个酒品评分更新失败,评分数:{}", new Gson().toJson
                            (parseDouble));
                    new UpdateFailException("第" + wineAlwaysStocks.getId() + "个酒品评分更新失败");
                }
            }
        } catch (Exception e) {
            logger.error("[task]更新红酒评分定时任务线程异常", e);
            throw new TaskRunErrorException("[task]更新红酒评分定时任务线程异常", e);
        }
    }

    @Override
    public String monitorTaskExceptionTag() {
        return null;
    }
}
