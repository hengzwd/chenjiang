package com.shtoone.chenjiang.mvp.model.entity.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Author： hengzwd on 2017/2/24.
 * Email：hengzwdhengzwd@qq.com
 */

public class SZXData extends DataSupport implements Serializable {
    private long id;
    private String shuizhunxianmingzi;
    private String shangchuanshijian;
    private String yiqipinpai;
    private String yiqixinghao;
    private String wedu;
    private String sijingrenyuan;
    private String tianqi;
    private String qiya;
    private String shebeixuliehao;
    private String guanceleixing;
    private String ysshuizhunxianbianhao;
    private String beizhu;
    private String gongzuojidianmcxl;
    private String gongdianid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppSzxId() {
        return appSzxId;
    }

    public void setAppSzxId(String appSzxId) {
        this.appSzxId = appSzxId;
    }

    private String appSzxId;

    public String getShuizhunxianmingzi() {
        return shuizhunxianmingzi;
    }

    public void setShuizhunxianmingzi(String shuizhunxianmingzi) {
        this.shuizhunxianmingzi = shuizhunxianmingzi;
    }

    public String getShangchuanshijian() {
        return shangchuanshijian;
    }

    public void setShangchuanshijian(String shangchuanshijian) {
        this.shangchuanshijian = shangchuanshijian;
    }

    public String getYiqipinpai() {
        return yiqipinpai;
    }

    public void setYiqipinpai(String yiqipinpai) {
        this.yiqipinpai = yiqipinpai;
    }

    public String getYiqixinghao() {
        return yiqixinghao;
    }

    public void setYiqixinghao(String yiqixinghao) {
        this.yiqixinghao = yiqixinghao;
    }

    public String getWedu() {
        return wedu;
    }

    public void setWedu(String wedu) {
        this.wedu = wedu;
    }

    public String getSijingrenyuan() {
        return sijingrenyuan;
    }

    public void setSijingrenyuan(String sijingrenyuan) {
        this.sijingrenyuan = sijingrenyuan;
    }

    public String getTianqi() {
        return tianqi;
    }

    public void setTianqi(String tianqi) {
        this.tianqi = tianqi;
    }

    public String getQiya() {
        return qiya;
    }

    public void setQiya(String qiya) {
        this.qiya = qiya;
    }

    public String getShebeixuliehao() {
        return shebeixuliehao;
    }

    public void setShebeixuliehao(String shebeixuliehao) {
        this.shebeixuliehao = shebeixuliehao;
    }

    public String getGuanceleixing() {
        return guanceleixing;
    }

    public void setGuanceleixing(String guanceleixing) {
        this.guanceleixing = guanceleixing;
    }

    public String getYsshuizhunxianbianhao() {
        return ysshuizhunxianbianhao;
    }

    public void setYsshuizhunxianbianhao(String ysshuizhunxianbianhao) {
        this.ysshuizhunxianbianhao = ysshuizhunxianbianhao;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getGongzuojidianmcxl() {
        return gongzuojidianmcxl;
    }

    public void setGongzuojidianmcxl(String gongzuojidianmcxl) {
        this.gongzuojidianmcxl = gongzuojidianmcxl;
    }

    public String getGongdianid() {
        return gongdianid;
    }

    public void setGongdianid(String gongdianid) {
        this.gongdianid = gongdianid;
    }


}
