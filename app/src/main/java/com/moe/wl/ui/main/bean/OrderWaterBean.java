package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：订水订单
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderWaterBean {

    private PageEntity page;
    private int errCode;
    private String msg;
    private String serviceMobile;

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
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

    public String getServiceMobile() {
        return serviceMobile;
    }

    public void setServiceMobile(String serviceMobile) {
        this.serviceMobile = serviceMobile;
    }

    public static class PageEntity {

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListEntity> list;

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

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {

            private AddressEntity address;
            private int addressid;
            private String cancelReasonids;
            private String createtime;
            private int id;
            private String mobile;
            private String ordercode;
            private int payStatus;
            private int payType;
            private String realname;
            private String reasonContent;
            private String remark;
            private String sendTime;
            private int sendtimeid;
            private int status;
            private int totalprice;
            private int uid;
            private List<DetailListEntity> detailList;

            public AddressEntity getAddress() {
                return address;
            }

            public void setAddress(AddressEntity address) {
                this.address = address;
            }

            public int getAddressid() {
                return addressid;
            }

            public void setAddressid(int addressid) {
                this.addressid = addressid;
            }

            public String getCancelReasonids() {
                return cancelReasonids;
            }

            public void setCancelReasonids(String cancelReasonids) {
                this.cancelReasonids = cancelReasonids;
            }

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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOrdercode() {
                return ordercode;
            }

            public void setOrdercode(String ordercode) {
                this.ordercode = ordercode;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getReasonContent() {
                return reasonContent;
            }

            public void setReasonContent(String reasonContent) {
                this.reasonContent = reasonContent;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public int getSendtimeid() {
                return sendtimeid;
            }

            public void setSendtimeid(int sendtimeid) {
                this.sendtimeid = sendtimeid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTotalprice() {
                return totalprice;
            }

            public void setTotalprice(int totalprice) {
                this.totalprice = totalprice;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public List<DetailListEntity> getDetailList() {
                return detailList;
            }

            public void setDetailList(List<DetailListEntity> detailList) {
                this.detailList = detailList;
            }

            public static class AddressEntity {

                private String address;
                private int id;
                private int isDefault;
                private String mobile;
                private String realname;
                private int uid;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIsDefault() {
                    return isDefault;
                }

                public void setIsDefault(int isDefault) {
                    this.isDefault = isDefault;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getRealname() {
                    return realname;
                }

                public void setRealname(String realname) {
                    this.realname = realname;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class DetailListEntity {

                private int count;
                private String createtime;
                private GoodsEntity goods;
                private int goodsid;
                private int id;
                private Object orderid;
                private int price;
                private int totalprice;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public GoodsEntity getGoods() {
                    return goods;
                }

                public void setGoods(GoodsEntity goods) {
                    this.goods = goods;
                }

                public int getGoodsid() {
                    return goodsid;
                }

                public void setGoodsid(int goodsid) {
                    this.goodsid = goodsid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Object getOrderid() {
                    return orderid;
                }

                public void setOrderid(Object orderid) {
                    this.orderid = orderid;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getTotalprice() {
                    return totalprice;
                }

                public void setTotalprice(int totalprice) {
                    this.totalprice = totalprice;
                }

                public static class GoodsEntity {

                    private int cid;
                    private String createtime;
                    private int id;
                    private String img;
                    private String name;
                    private int price;

                    public int getCid() {
                        return cid;
                    }

                    public void setCid(int cid) {
                        this.cid = cid;
                    }

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

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getPrice() {
                        return price;
                    }

                    public void setPrice(int price) {
                        this.price = price;
                    }
                }
            }
        }
    }
}
