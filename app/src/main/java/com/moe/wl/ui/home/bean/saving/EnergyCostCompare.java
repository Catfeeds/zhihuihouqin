package com.moe.wl.ui.home.bean.saving;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/20 0020.
 */

public class EnergyCostCompare {

    /**
     * page : [{"currPage":1,"list":[{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-13","eId":null,"energyCost":527.5936,"energyType":1},{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-14","eId":null,"energyCost":235.596,"energyType":1},{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-15","eId":null,"energyCost":244.3946,"energyType":1}],"pageSize":10,"totalCount":128,"totalPage":13},{"currPage":1,"list":[{"buildId":24,"buildName":"招待所","creatTime":"2017-12-13","eId":null,"energyCost":62.3894,"energyType":1},{"buildId":24,"buildName":"招待所","creatTime":"2017-12-14","eId":null,"energyCost":39.3544,"energyType":1},{"buildId":24,"buildName":"招待所","creatTime":"2017-12-15","eId":null,"energyCost":38.3938,"energyType":1}],"pageSize":10,"totalCount":128,"totalPage":13}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<PageBean> page;

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

    public List<PageBean> getPage() {
        return page;
    }

    public void setPage(List<PageBean> page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * currPage : 1
         * list : [{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-13","eId":null,"energyCost":527.5936,"energyType":1},{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-14","eId":null,"energyCost":235.596,"energyType":1},{"buildId":22,"buildName":"老干部局","creatTime":"2017-12-15","eId":null,"energyCost":244.3946,"energyType":1}]
         * pageSize : 10
         * totalCount : 128
         * totalPage : 13
         */

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListBean> list;

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
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
}
