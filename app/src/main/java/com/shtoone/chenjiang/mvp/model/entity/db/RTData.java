package com.shtoone.chenjiang.mvp.model.entity.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Author： hengzwd on 2017/2/24.
 * Email：hengzwdhengzwd@qq.com
 */

public class RTData extends DataSupport implements Serializable {

    private long id;
    private String shuizhunxianluid;
    private String gongdianid;
    private String duanmianid;
    private String cedianid;
    private String guanceshijian;
    private String xiuzhengliang;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppSzxResultId() {
        return appSzxResultId;
    }

    public void setAppSzxResultId(String appSzxResultId) {
        this.appSzxResultId = appSzxResultId;
    }

    private String cedianzhuangtai;
    private String dangqiangongkuang;
    private String tiantugaodu;
    private String isdel;
    private String xiugaijilu;
    private String beizhu;
    private String biaogao;
    private String shangcibiaogao;
    private String yuanshibiaogao;
    private String guankoubiaogao;
    private String guankouzhibandijuli;
    private String bencichenjiang;
    private String leijichenjiang;

    private String appSzxResultId;



    public String getShuizhunxianluid() {
        return shuizhunxianluid;
    }

    public void setShuizhunxianluid(String shuizhunxianluid) {
        this.shuizhunxianluid = shuizhunxianluid;
    }

    public String getGongdianid() {
        return gongdianid;
    }

    public void setGongdianid(String gongdianid) {
        this.gongdianid = gongdianid;
    }

    public String getDuanmianid() {
        return duanmianid;
    }

    public void setDuanmianid(String duanmianid) {
        this.duanmianid = duanmianid;
    }

    public String getCedianid() {
        return cedianid;
    }

    public void setCedianid(String cedianid) {
        this.cedianid = cedianid;
    }

    public String getGuanceshijian() {
        return guanceshijian;
    }

    public void setGuanceshijian(String guanceshijian) {
        this.guanceshijian = guanceshijian;
    }

    public String getXiuzhengliang() {
        return xiuzhengliang;
    }

    public void setXiuzhengliang(String xiuzhengliang) {
        this.xiuzhengliang = xiuzhengliang;
    }

    public String getCedianzhuangtai() {
        return cedianzhuangtai;
    }

    public void setCedianzhuangtai(String cedianzhuangtai) {
        this.cedianzhuangtai = cedianzhuangtai;
    }

    public String getDangqiangongkuang() {
        return dangqiangongkuang;
    }

    public void setDangqiangongkuang(String dangqiangongkuang) {
        this.dangqiangongkuang = dangqiangongkuang;
    }

    public String getTiantugaodu() {
        return tiantugaodu;
    }

    public void setTiantugaodu(String tiantugaodu) {
        this.tiantugaodu = tiantugaodu;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getXiugaijilu() {
        return xiugaijilu;
    }

    public void setXiugaijilu(String xiugaijilu) {
        this.xiugaijilu = xiugaijilu;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getBiaogao() {
        return biaogao;
    }

    public void setBiaogao(String biaogao) {
        this.biaogao = biaogao;
    }

    public String getShangcibiaogao() {
        return shangcibiaogao;
    }

    public void setShangcibiaogao(String shangcibiaogao) {
        this.shangcibiaogao = shangcibiaogao;
    }

    public String getYuanshibiaogao() {
        return yuanshibiaogao;
    }

    public void setYuanshibiaogao(String yuanshibiaogao) {
        this.yuanshibiaogao = yuanshibiaogao;
    }

    public String getGuankoubiaogao() {
        return guankoubiaogao;
    }

    public void setGuankoubiaogao(String guankoubiaogao) {
        this.guankoubiaogao = guankoubiaogao;
    }

    public String getGuankouzhibandijuli() {
        return guankouzhibandijuli;
    }

    public void setGuankouzhibandijuli(String guankouzhibandijuli) {
        this.guankouzhibandijuli = guankouzhibandijuli;
    }

    public String getBencichenjiang() {
        return bencichenjiang;
    }

    public void setBencichenjiang(String bencichenjiang) {
        this.bencichenjiang = bencichenjiang;
    }

    public String getLeijichenjiang() {
        return leijichenjiang;
    }

    public void setLeijichenjiang(String leijichenjiang) {
        this.leijichenjiang = leijichenjiang;
    }


}
