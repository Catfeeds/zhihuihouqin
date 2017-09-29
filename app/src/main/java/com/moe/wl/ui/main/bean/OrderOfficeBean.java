package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderOfficeBean {

    private PageEntity page;
    private int errCode;
    private String msg;

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
            private String code;
            private String createtime;
            private String expertarrivaltme;
            private int id;
            private String mobile;
            private int paytype;
            private String remark;
            private int status;
            private int totalprice;
            private int uid;
            private String username;
            private List<OrderDetailListEntity> orderDetailList;

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

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getExpertarrivaltme() {
                return expertarrivaltme;
            }

            public void setExpertarrivaltme(String expertarrivaltme) {
                this.expertarrivaltme = expertarrivaltme;
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

            public int getPaytype() {
                return paytype;
            }

            public void setPaytype(int paytype) {
                this.paytype = paytype;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public List<OrderDetailListEntity> getOrderDetailList() {
                return orderDetailList;
            }

            public void setOrderDetailList(List<OrderDetailListEntity> orderDetailList) {
                this.orderDetailList = orderDetailList;
            }

            public static class AddressEntity {

                private String address;
                private int id;
                private int isDefault;
                private String mobile;
                private String realname;
                private Object uid;

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

                public Object getUid() {
                    return uid;
                }

                public void setUid(Object uid) {
                    this.uid = uid;
                }
            }

            public static class OrderDetailListEntity {

                private int count;
                private String createtime;
                private int id;
                private String orderid;
                private int price;
                private int productid;
                private SkuEntity sku;
                private int skuid;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getOrderid() {
                    return orderid;
                }

                public void setOrderid(String orderid) {
                    this.orderid = orderid;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getProductid() {
                    return productid;
                }

                public void setProductid(int productid) {
                    this.productid = productid;
                }

                public SkuEntity getSku() {
                    return sku;
                }

                public void setSku(SkuEntity sku) {
                    this.sku = sku;
                }

                public int getSkuid() {
                    return skuid;
                }

                public void setSkuid(int skuid) {
                    this.skuid = skuid;
                }

                public int getTotalprice() {
                    return totalprice;
                }

                public void setTotalprice(int totalprice) {
                    this.totalprice = totalprice;
                }

                public static class SkuEntity {

                    private String cataName;
                    private String createtime;
                    private int firstcataid;
                    private int id;
                    private String mainimg;
                    private int price;
                    private int productid;
                    private int secondcataid;
                    private String skuname;
                    private int status;
                    private int store;

                    public String getCataName() {
                        return cataName;
                    }

                    public void setCataName(String cataName) {
                        this.cataName = cataName;
                    }

                    public String getCreatetime() {
                        return createtime;
                    }

                    public void setCreatetime(String createtime) {
                        this.createtime = createtime;
                    }

                    public int getFirstcataid() {
                        return firstcataid;
                    }

                    public void setFirstcataid(int firstcataid) {
                        this.firstcataid = firstcataid;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getMainimg() {
                        return mainimg;
                    }

                    public void setMainimg(String mainimg) {
                        this.mainimg = mainimg;
                    }

                    public int getPrice() {
                        return price;
                    }

                    public void setPrice(int price) {
                        this.price = price;
                    }

                    public int getProductid() {
                        return productid;
                    }

                    public void setProductid(int productid) {
                        this.productid = productid;
                    }

                    public int getSecondcataid() {
                        return secondcataid;
                    }

                    public void setSecondcataid(int secondcataid) {
                        this.secondcataid = secondcataid;
                    }

                    public String getSkuname() {
                        return skuname;
                    }

                    public void setSkuname(String skuname) {
                        this.skuname = skuname;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getStore() {
                        return store;
                    }

                    public void setStore(int store) {
                        this.store = store;
                    }
                }
            }
        }
    }
}
