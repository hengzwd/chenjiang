package com.shtoone.chenjiang;

import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;
import com.shtoone.chenjiang.common.AppContext;
import com.shtoone.chenjiang.common.Constants;
import com.shtoone.chenjiang.mvp.model.entity.bean.UserInfoBean;
import com.shtoone.chenjiang.mvp.model.entity.db.GongdianData;
import com.shtoone.chenjiang.mvp.model.entity.db.JidianData;
import com.shtoone.chenjiang.mvp.model.entity.db.ORIGData;
import com.shtoone.chenjiang.mvp.model.entity.db.OriginalData;
import com.shtoone.chenjiang.mvp.model.entity.db.RTData;
import com.shtoone.chenjiang.mvp.model.entity.db.ResultData;
import com.shtoone.chenjiang.mvp.model.entity.db.SZXData;
import com.shtoone.chenjiang.mvp.model.entity.db.ShuizhunxianData;
import com.shtoone.chenjiang.mvp.model.entity.db.StaffData;
import com.shtoone.chenjiang.mvp.model.entity.db.YusheshuizhunxianData;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class BaseApplication extends LitePalApplication {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static Context mContext;
    public static UserInfoBean mUserInfoBean;
    public static boolean isShowDialog = true;
    public RefWatcher mRefWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        LitePalApplication.initialize(this);
        //日志的开关和全局标签初始化
        KLog.init(true, "SHTW沉降观测");
        mContext = this;
        // 程序异常交由AppExceptionHandler来处理
//        Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));
        //创建LeakCanary对象，观察内存泄漏
        mRefWatcher = LeakCanary.install(this);
        // 在主进程初始化调用哈
        BlockCanary.install(this, new AppContext()).start();

        DataSupport.deleteAll(GongdianData.class);

        for (int i = 0; i < 10; i++) {

            GongdianData gongdianData = new GongdianData();
            gongdianData.setZxlc("@" + (i + 1));
            boolean is = gongdianData.save();
            KLog.e(is);
        }

        DataSupport.deleteAll(StaffData.class);
        for (int i = 0; i < 10; i++) {
            StaffData mStaffData = new StaffData();
            mStaffData.setUserName("哎我" + (i + 1));
            mStaffData.setUserPhone("电话：" + (i + 1));
            boolean is = mStaffData.save();
            KLog.e(is);
        }

        DataSupport.deleteAll(JidianData.class);
        for (int i = 0; i < 10; i++) {
            JidianData mJidianData = new JidianData();
            mJidianData.setBianhao("SWLFJJKDK16511");
            mJidianData.setName("CP181");
            boolean is = mJidianData.save();
            KLog.e(is);
        }


        DataSupport.deleteAll(ShuizhunxianData.class);
        for (int i = 0; i < 10; i++) {
            ShuizhunxianData f = new ShuizhunxianData();
            f.setRouteType("附和");
            boolean isis = f.save();
            KLog.e(isis);
        }
        DataSupport.deleteAll(OriginalData.class);

        for (int i = 0; i < 10; i++) {
            OriginalData mOriginalData = new OriginalData();
            mOriginalData.setNumber("备注" + (i + 1));

            boolean isis = mOriginalData.save();
            KLog.e(isis);
        }
        DataSupport.deleteAll(ResultData.class);

        for (int i = 0; i < 10; i++) {
            ResultData mResultData = new ResultData();
            mResultData.setNumber("备注" + (i + 1));
            mResultData.setState("状态" + (i + 1));
            boolean isis = mResultData.save();
            KLog.e(isis);
        }
//
        DataSupport.deleteAll(YusheshuizhunxianData.class);
        for (int i = 0; i < 10; i++) {
            YusheshuizhunxianData mYusheshuizhunxianData = new YusheshuizhunxianData();
            mYusheshuizhunxianData.setXianlubianhao("备注" + (i + 1));
            mYusheshuizhunxianData.setJidianshu("状态" + (i + 1));
            mYusheshuizhunxianData.setXianluxinxi("jd0,cd1,cd2,cd3,jd1,");
            mYusheshuizhunxianData.setRouteType("闭合");
            mYusheshuizhunxianData.setObserveType("BF");
            mYusheshuizhunxianData.setTemperature("16");
            mYusheshuizhunxianData.setPressure("700");
            mYusheshuizhunxianData.setXiugaishijian("2016-11-09");
            mYusheshuizhunxianData.setStatus(Constants.status_daishanchu);
            boolean isis = mYusheshuizhunxianData.save();
            KLog.e(isis);
        }

        for (int i = 0; i < 4; i++) {
            YusheshuizhunxianData mYusheshuizhunxianData = new YusheshuizhunxianData();
            mYusheshuizhunxianData.setXianlubianhao("备注" + (i + 1));
            mYusheshuizhunxianData.setJidianshu("状态" + (i + 1));
            mYusheshuizhunxianData.setXianluxinxi("jd0,cd1,cd2,cd3,jd1,");
//            mYusheshuizhunxianData.setEdit("1");
            mYusheshuizhunxianData.setRouteType("闭合");
            mYusheshuizhunxianData.setObserveType("BF");
            mYusheshuizhunxianData.setTemperature("16");
            mYusheshuizhunxianData.setPressure("700");
            mYusheshuizhunxianData.setXiugaishijian("2016-11-09");
//            mYusheshuizhunxianData.setMeasureState("待平差");

            boolean isis = mYusheshuizhunxianData.save();
            KLog.e(isis);
        }

        DataSupport.deleteAll(RTData.class);
        DataSupport.deleteAll(ORIGData.class);
        DataSupport.deleteAll(SZXData.class);


        for (int i = 0; i < 3; i++) {
            ORIGData origData = new ORIGData();
            RTData rtData = new RTData();
            SZXData szxData = new SZXData();

            origData.setGuancetime("giamcetime");
            origData.setQianhoushibiaojifu("qianhoushibiaojifu");
            origData.setQianhoushichidushu("qianhoushichidufu");
            origData.setQianhoushidianmingcheng("qianhoushidianmingceng");
            origData.setShuizhunxianluid(String.valueOf(i));
            origData.setQianhoushijuli("qianhoushijuli");
            origData.setShunxu("shunxu");
            origData.setToDefault("default");
            origData.setChengguobiaoid(String.valueOf(i));
            boolean og=origData.save();
            KLog.e(og);

            rtData.setToDefault("default");
            rtData.setShuizhunxianluid(String.valueOf(i));
            rtData.setBeizhu("beizhu");
            rtData.setBencichenjiang("bencichenjiang");
            rtData.setBiaogao("bianhao ");
            rtData.setCedianid("cedianid");
            rtData.setCedianzhuangtai("cedianzhuangtai");
            rtData.setDangqiangongkuang("dangqiangongkuang");
            rtData.setDuanmianid("duanmianid");
            rtData.setGongdianid("gongdinaid");
            rtData.setGuanceshijian("guanceshijian");
            rtData.setGuankoubiaogao("guankongbianhao");
            rtData.setGuankouzhibandijuli("guankongzhibandijuli");
            rtData.setIsdel("iddel");
            rtData.setTiantugaodu("tiantugaodu");
            rtData.setXiugaijilu("xiugaijuli");
            rtData.setXiuzhengliang("xiuzhengliang");
            rtData.setYuanshibiaogao("yaunshibianhao");
            rtData.setAppSzxResultId(String.valueOf(i));
            boolean rt=rtData.save();
            KLog.e(rt);


            szxData.setBeizhu("beizhu");
            szxData.setGongdianid("gongdinaid");
            szxData.setGongzuojidianmcxl("gongzuojidianmvxl");
            szxData.setGuanceleixing("guanceleixing");
            szxData.setQiya("qiya");
            szxData.setShangchuanshijian("shangchuanshijian");
            szxData.setShebeixuliehao("shebeixuliehao");
            szxData.setShuizhunxianmingzi("shuizhunxianmingzi");
            szxData.setSijingrenyuan("sijinrenyuan");
            szxData.setGongdianid("gongdinaid");
            szxData.setTianqi("tianqi");
            szxData.setYiqipinpai("yiqipinpai");
            szxData.setWedu("wendu");
            szxData.setGuanceleixing("guanceleixing");
            szxData.setYiqixinghao("yiqixinghao");
            szxData.setShangchuanshijian("shangchuanshijian");
            szxData.setYsshuizhunxianbianhao("ysshuizhunxianbianhao");
            boolean szx=szxData.save();
            KLog.e(szx);
        }
    }
}
