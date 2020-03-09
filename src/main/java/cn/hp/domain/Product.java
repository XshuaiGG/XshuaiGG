package cn.hp.domain;

import cn.hp.utils.DateUtils;

import java.util.Date;

public class Product {
    private Integer id;//主键
    private String productNum;//产品编号
    private String productName;//产品名称
    private String cityName;//出发城市
    private Date departureTime;//出发时间
    private String departureTimeStr;// 该属性 在数据库中没有对应的字段, 作用:就是为了在jsp显示一个字符串时间格式
    private Double productPrice;//产品价格
    private String productDesc;//产品描述
    private Integer productStatus;//产品状态 0:关闭 1:开启
    private String productStatusStr;//该属性 在数据库中没有对应的字段, 作用:就是为了在jsp显示一个 关闭或开启的文字

    public String getProductStatusStr() {
        if(productStatus!=null){
            if(productStatus==0){
                productStatusStr="关闭";
            }
            if(productStatus==1){
                productStatusStr="开启";
            }
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }

    public String getDepartureTimeStr() {
        if(departureTime!=null){
            departureTimeStr = DateUtils.dateToString(departureTime);
        }
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", departureTime=" + departureTime +
                ", departureTimeStr='" + departureTimeStr + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }
}
