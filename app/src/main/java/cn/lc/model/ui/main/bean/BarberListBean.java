package cn.lc.model.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class BarberListBean implements Serializable{

    /**
     * msg : success
     * errCode : 0
     * barberlist : [{"id":1,"mobile":"132098787888","name":"张三","photo":"www.baidu.com","positionName":"主任","positionid":1,"remaincount":1,"score":1,"tatalcount":null,"valid":1}]
     */

    private String msg;
    private int errCode;
    private List<BarberlistBean> barberlist;

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

    public List<BarberlistBean> getBarberlist() {
        return barberlist;
    }

    public void setBarberlist(List<BarberlistBean> barberlist) {
        this.barberlist = barberlist;
    }

    public static class BarberlistBean implements Serializable{
        @Override
        public String toString() {
            return "BarberlistBean{" +
                    "id=" + id +
                    ", mobile='" + mobile + '\'' +
                    ", name='" + name + '\'' +
                    ", photo='" + photo + '\'' +
                    ", positionName='" + positionName + '\'' +
                    ", positionid=" + positionid +
                    ", remaincount=" + remaincount +
                    ", score=" + score +
                    ", tatalcount=" + tatalcount +
                    ", valid=" + valid +
                    '}';
        }

        /**
         * id : 1
         * mobile : 132098787888
         * name : 张三
         * photo : www.baidu.com
         * positionName : 主任
         * positionid : 1
         * remaincount : 1
         * score : 1
         * tatalcount : null
         * valid : 1
         */

        private int id;
        private String mobile;
        private String name;
        private String photo;
        private String positionName;
        private int positionid;
        private int remaincount;
        private int score;
        private int tatalcount;
        private int valid;

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

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public int getPositionid() {
            return positionid;
        }

        public void setPositionid(int positionid) {
            this.positionid = positionid;
        }

        public int getRemaincount() {
            return remaincount;
        }

        public void setRemaincount(int remaincount) {
            this.remaincount = remaincount;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getTatalcount() {
            return tatalcount;
        }

        public void setTatalcount(int tatalcount) {
            this.tatalcount = tatalcount;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }
    }
}
