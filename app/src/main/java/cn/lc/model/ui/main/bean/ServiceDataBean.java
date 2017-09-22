package cn.lc.model.ui.main.bean;

/**
 * 类描述：服务Bean
 * 作者：Shixhe On 2017/9/22 0022
 */

public class ServiceDataBean {

    private int id;
    private int isAdd;
    private String serviceName;
    private int sourceID;

    public ServiceDataBean() {
    }

    public ServiceDataBean(int id, int isAdd, String serviceName, int sourceID) {
        this.id = id;
        this.isAdd = isAdd;
        this.serviceName = serviceName;
        this.sourceID = sourceID;
    }

    public ServiceDataBean(int id, int isAdd, String serviceName) {
        this.id = id;
        this.isAdd = isAdd;
        this.serviceName = serviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(int isAdd) {
        this.isAdd = isAdd;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }
}
