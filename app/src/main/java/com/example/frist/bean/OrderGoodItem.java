package com.example.frist.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.Serializable;


public class OrderGoodItem  implements Serializable{

	private static final long serialVersionUID = -982176300266456747L;
	private String Id;//商品ID
	private String Title;//商品标题
	private String Info;//说明
	private String Count;//购买数量
	private String Price;//单价
	private String OrderNo;//订单ID
//	private String totalPrice;//总价
	private String State;//状态
	private String ImgUrl;//图片地址
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getImgUrl() {
		return ImgUrl;
	}
	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
