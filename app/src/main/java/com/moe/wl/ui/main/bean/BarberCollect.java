package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class BarberCollect {

    /**
     * errCode : 0
     * list : [{"address":"教育局大厦101","brief":"从事美发业6年，高级发型设计。\n特长：专业韩式裁剪，头皮理疗、治疗脱发生发","id":21,"mobile":"","name":"冯立国","photo":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710231508752778201.jpg","positionid":5,"score":null,"valid":1},{"address":"教育局大厦101","brief":"从事美发行业14年，资深设计师。\n特长：男女短发私人订制。\n学习简历：\n2003毕业于北京新仙娜美发学院；\n2008进修于上海文峰沙宣特训班，每个季度参与技术交流会；\n2013进修欧莱雅烫染集锦1-4星课程；\n2015进修北京麒太学院私人订制课程","id":19,"mobile":"18515826536","name":"单承高","photo":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710231508751503990.jpg","positionid":5,"score":null,"valid":1}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean extends BarberProductCollect.ListBean {
        /**
         * address : 教育局大厦101
         * brief : 从事美发业6年，高级发型设计。
         特长：专业韩式裁剪，头皮理疗、治疗脱发生发
         * id : 21
         * mobile :
         * name : 冯立国
         * photo : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710231508752778201.jpg
         * positionid : 5
         * score : null
         * valid : 1
         */

        private String address;
        private String brief;
        private int id;
        private String mobile;
        private String name;
        private String photo;
        private int positionid;
        private double score;
        private int valid;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getPositionid() {
            return positionid;
        }

        public void setPositionid(int positionid) {
            this.positionid = positionid;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }
    }
}
