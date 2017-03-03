package com.shtoone.chenjiang.mvp.model.entity.bean;

import com.shtoone.chenjiang.mvp.model.entity.db.ORIGData;
import com.shtoone.chenjiang.mvp.model.entity.db.RTData;
import com.shtoone.chenjiang.mvp.model.entity.db.SZXData;

import java.util.List;

/**
 * Author： hengzwd on 2017/2/24.
 * Email：hengzwdhengzwd@qq.com
 */

public class ALLDATA {



    private List<RTData> resultDatas;
    private List<ORIGData> originalDatas;
    private List<SZXData> szxZhuBiaoDatas;

    public List<SZXData> getSzxZhuBiaoDatas() {
        return szxZhuBiaoDatas;
    }

    public void setSzxZhuBiaoDatas(List<SZXData> szxZhuBiaoDatas) {
        this.szxZhuBiaoDatas = szxZhuBiaoDatas;
    }

    public List<RTData> getResultDatas() {
        return resultDatas;
    }

    public void setResultDatas(List<RTData> resultDatas) {
        this.resultDatas = resultDatas;
    }

    public List<ORIGData> getOriginalDatas() {
        return originalDatas;
    }

    public void setOriginalDatas(List<ORIGData> originalDatas) {
        this.originalDatas = originalDatas;
    }
}
