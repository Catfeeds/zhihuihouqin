package com.moe.wl.ui.home.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/18 0018.
 */

public class EnergyCostQueryBean {

    /**
     * energyTotal : {"buildId":22,"buildName":"老干部局","creatTime":"2017-12","eId":null,"energyCost":1007.5842,"energyType":1}
     * energyList : [{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-13","eId":null,"energyCost":527.5936,"energyType":1},{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-14","eId":null,"energyCost":235.596,"energyType":1},{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-15","eId":null,"energyCost":244.3946,"energyType":1}]
     * errCode : 0
     * msg : success
     */

    private EnergyTotalBean energyTotal;
    private int errCode;
    private String msg;
    private List<EnergyListBean> energyList;

    public EnergyTotalBean getEnergyTotal() {
        return energyTotal;
    }

    public void setEnergyTotal(EnergyTotalBean energyTotal) {
        this.energyTotal = energyTotal;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<EnergyListBean> getEnergyList() {
        return energyList;
    }

    public void setEnergyList(List<EnergyListBean> energyList) {
        this.energyList = energyList;
    }

    public static class EnergyTotalBean {
        /**
         * buildId : 22
         * buildName : 老干部局
         * creatTime : 2017-12
         * eId : null
         * energyCost : 1007.5842
         * energyType : 1
         */

        private int buildId;
        private String buildName;
        private String creatTime;
        private Object eId;
        private double energyCost;
        private int energyType;

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public Object getEId() {
            return eId;
        }

        public void setEId(Object eId) {
            this.eId = eId;
        }

        public double getEnergyCost() {
            return energyCost;
        }

        public void setEnergyCost(double energyCost) {
            this.energyCost = energyCost;
        }

        public int getEnergyType() {
            return energyType;
        }

        public void setEnergyType(int energyType) {
            this.energyType = energyType;
        }
    }

    public static class EnergyListBean {
        /**
         * buildId : 22
         * buildName : 老干部局
         * creatTime : 2017-12-13
         * eId : null
         * energyCost : 527.5936
         * energyType : 1
         */

        private int buildId;
        private String buildName;
        private String creatTime;
        private Object eId;
        private double energyCost;
        private int energyType;

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public Object getEId() {
            return eId;
        }

        public void setEId(Object eId) {
            this.eId = eId;
        }

        public double getEnergyCost() {
            return energyCost;
        }

        public void setEnergyCost(double energyCost) {
            this.energyCost = energyCost;
        }

        public int getEnergyType() {
            return energyType;
        }

        public void setEnergyType(int energyType) {
            this.energyType = energyType;
        }
    }
}
