package com.jxw.design.service;

import com.jxw.design.model.resp.ShowWineResp;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 14:41
 * @Description 严选品牌
 */
public interface SelectedBrandService {
    /**
     * 严选品牌（红葡萄酒）
     *
     * @return
     */
    List<ShowWineResp> redWineBrand();

    /**
     * 严选品牌（白葡萄酒）
     *
     * @return
     */
    List<ShowWineResp> whiteWineBrand();

}