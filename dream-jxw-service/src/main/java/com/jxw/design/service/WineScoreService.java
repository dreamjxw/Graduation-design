package com.jxw.design.service;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/21  18:37
 */
public interface WineScoreService {
    /**
     * 计算并更新红酒评分
     *
     * @return
     */
    void countAndUpdateWineScore();

    /**
     * 检查库存是否充足
     */
    void checkStock();
}
