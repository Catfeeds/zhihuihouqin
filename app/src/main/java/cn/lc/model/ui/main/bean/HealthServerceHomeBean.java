package cn.lc.model.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public class HealthServerceHomeBean {

    /**
     * msg : success
     * errCode : 0
     * infolist : [{"createtime":"2018-08-16 00:00","id":1,"source":"健康","title":"健康每一天","url":"http://www.baidu.com"}]
     * picture : http://www.baidu.com
     */

    private String msg;
    private int errCode;
    private String picture;
    private List<InfolistBean> infolist;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<InfolistBean> getInfolist() {
        return infolist;
    }

    public void setInfolist(List<InfolistBean> infolist) {
        this.infolist = infolist;
    }

    public static class InfolistBean implements Serializable{
        /**
         * createtime : 2018-08-16 00:00
         * id : 1
         * source : 健康
         * title : 健康每一天
         * url : http://www.baidu.com
         */

        private String createtime;
        private int id;
        private String source;
        private String title;
        private String url;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
