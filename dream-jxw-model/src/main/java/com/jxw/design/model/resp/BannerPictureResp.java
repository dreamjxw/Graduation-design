package com.jxw.design.model.resp;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/6 14:37
 * @Description 轮播图resp
 */
public class BannerPictureResp {
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
