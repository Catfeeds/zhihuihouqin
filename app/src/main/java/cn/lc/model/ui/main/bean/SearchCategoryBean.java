package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class SearchCategoryBean {

    /**
     * msg : success
     * typelist : [{"id":2,"typename":"摄影"},{"id":1,"typename":"文学"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<TypelistBean> typelist;

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

    public List<TypelistBean> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<TypelistBean> typelist) {
        this.typelist = typelist;
    }

    public static class TypelistBean {
        /**
         * id : 2
         * typename : 摄影
         */

        private int id;
        private String typename;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
