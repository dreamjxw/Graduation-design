package com.jxw.design.model.resp;

import java.io.Serializable;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/6 14:37
 * @Description 轮播图resp
 */
public class BannerPictureResp implements Serializable {
    private static final long serialVersionUID = -5487865375531651074L;
    /**
     * 红酒id
     */
    private Long wineId;
    /**
     * 红酒图片
     */
    private String wineImg;

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public String getWineImg() {
        return wineImg;
    }

    public void setWineImg(String wineImg) {
        this.wineImg = wineImg;
    }
}
