package com.shtoone.chenjiang.mvp.model.entity.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Author： hengzwd on 2017/2/24.
 * Email：hengzwdhengzwd@qq.com
 */

public class ORIGData extends DataSupport implements Serializable {

    private long id;
    private String shuizhunxianluid;
    private String qianhoushibiaojifu;
    private String qianhoushidianmingcheng;
    private String qianhoushijuli;
    private String qianhoushichidushu;
    private String guancetime;
    private String shunxu;
    private String chengguobiaoid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChengguobiaoid() {
        return chengguobiaoid;
    }

    public void setChengguobiaoid(String chengguobiaoid) {
        this.chengguobiaoid = chengguobiaoid;
    }

    public String getShuizhunxianluid() {
        return shuizhunxianluid;
    }

    public void setShuizhunxianluid(String shuizhunxianluid) {
        this.shuizhunxianluid = shuizhunxianluid;
    }

    public String getQianhoushibiaojifu() {
        return qianhoushibiaojifu;
    }

    public void setQianhoushibiaojifu(String qianhoushibiaojifu) {
        this.qianhoushibiaojifu = qianhoushibiaojifu;
    }

    public String getQianhoushidianmingcheng() {
        return qianhoushidianmingcheng;
    }

    public void setQianhoushidianmingcheng(String qianhoushidianmingcheng) {
        this.qianhoushidianmingcheng = qianhoushidianmingcheng;
    }

    public String getQianhoushijuli() {
        return qianhoushijuli;
    }

    public void setQianhoushijuli(String qianhoushijuli) {
        this.qianhoushijuli = qianhoushijuli;
    }

    public String getQianhoushichidushu() {
        return qianhoushichidushu;
    }

    public void setQianhoushichidushu(String qianhoushichidushu) {
        this.qianhoushichidushu = qianhoushichidushu;
    }

    public String getGuancetime() {
        return guancetime;
    }

    public void setGuancetime(String guancetime) {
        this.guancetime = guancetime;
    }

    public String getShunxu() {
        return shunxu;
    }

    public void setShunxu(String shunxu) {
        this.shunxu = shunxu;
    }
}
