package com.jxw.design.service;

import com.jxw.design.model.Wine;
import com.jxw.design.model.resp.BannerPictureResp;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/6 14:33
 * @Description 个性推荐接口
 */
public interface RecommendService {
    /**
     * 轮播图
     *
     * @return
     */
    List<BannerPictureResp> bannerPicture();

    /**
     * 推荐酒单
     *
     * @param userId
     * @return
     */
    List<Wine> recommendWine(String userId);

    /**
     * 私人酒窖
     *
     * @param userId
     * @return
     */
    List<Wine> privateWine(String userId);

    /**
     * 热榜
     *
     * @return
     */
    List<Wine> hotListWine();
}
