package com.jxw.design.enums;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 15:51
 * <p>
 * 用户等级类型
 */
public enum UserLevelType {
    /**
     * 青铜会员门槛分数
     */
    BRONZE_VIP_SCORE(0L, "青铜会员"),
    /**
     * 白银会员门槛分数
     */
    SILVER_VIP_SCORE(10L, "白银会员"),
    /**
     * 黄金会员门槛分数
     */
    GOLD_VIP_SCORE(100L, "黄金会员"),
    /**
     * 铂金会员门槛分数
     */
    PLATINUM_VIP_SCORE(1000L, "铂金会员"),
    /**
     * 钻石会员门槛分数
     */
    DIAMONDS_VIP_SCORE(10000L, "钻石会员");
    private Long score;
    private String level;

    UserLevelType(Long score, String level) {
        this.score = score;
        this.level = level;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
