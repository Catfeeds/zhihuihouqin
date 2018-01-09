package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/7 0007.
 */

public class BuildNumList {

    /**
     * msg : success
     * data : [{"buildName":"洗衣房","buildType":6,"id":30,"typeName":"其他建筑"},{"buildName":"保安","buildType":3,"id":29,"typeName":"宿舍"},{"buildName":"传达室","buildType":1,"id":28,"typeName":"办公建筑"},{"buildName":"基建","buildType":1,"id":27,"typeName":"办公建筑"},{"buildName":"东配殿","buildType":3,"id":26,"typeName":"宿舍"},{"buildName":"和乐堂","buildType":1,"id":25,"typeName":"办公建筑"},{"buildName":"招待所","buildType":7,"id":24,"typeName":"宾馆饭店建筑"},{"buildName":"修缮","buildType":1,"id":23,"typeName":"办公建筑"},{"buildName":"老干部局","buildType":4,"id":22,"typeName":"综合建筑"},{"buildName":"票务室","buildType":6,"id":21,"typeName":"其他建筑"},{"buildName":"车队","buildType":6,"id":20,"typeName":"其他建筑"},{"buildName":"医务室","buildType":5,"id":19,"typeName":"医疗卫生建筑"},{"buildName":"理发室","buildType":6,"id":18,"typeName":"其他建筑"},{"buildName":"文印中心","buildType":1,"id":17,"typeName":"办公建筑"},{"buildName":"快件室","buildType":1,"id":16,"typeName":"办公建筑"},{"buildName":"政务大厅","buildType":1,"id":15,"typeName":"办公建筑"},{"buildName":"郑王府","buildType":1,"id":14,"typeName":"办公建筑"},{"buildName":"信访处","buildType":1,"id":13,"typeName":"办公建筑"},{"buildName":"西楼4号楼","buildType":3,"id":12,"typeName":"宿舍"},{"buildName":"西楼3号楼","buildType":3,"id":11,"typeName":"宿舍"},{"buildName":"西楼2号楼","buildType":3,"id":10,"typeName":"宿舍"},{"buildName":"西楼1号楼","buildType":3,"id":9,"typeName":"宿舍"},{"buildName":"小食堂","buildType":2,"id":8,"typeName":"食堂"},{"buildName":"礼堂","buildType":1,"id":7,"typeName":"办公建筑"},{"buildName":"居民楼","buildType":3,"id":6,"typeName":"宿舍"},{"buildName":"中心配电室","buildType":6,"id":5,"typeName":"其他建筑"},{"buildName":"南楼","buildType":1,"id":4,"typeName":"办公建筑"},{"buildName":"大食堂","buildType":2,"id":3,"typeName":"食堂"},{"buildName":"业务楼","buildType":1,"id":2,"typeName":"办公建筑"},{"buildName":"北楼","buildType":1,"id":1,"typeName":"办公建筑"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * buildName : 洗衣房
         * buildType : 6
         * id : 30
         * typeName : 其他建筑
         */

        private String buildName;
        private int buildType;
        private int id;
        private String typeName;

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public int getBuildType() {
            return buildType;
        }

        public void setBuildType(int buildType) {
            this.buildType = buildType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
