package com.example.frist.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class OrderItem extends SectionEntity<OrderGoodItem> {

	public OrderItem(OrderGoodItem t) {
		super(t);
	}
	public OrderItem(boolean isHeader, String header) {
		super(isHeader, header);
		isHeader=true;
		header=getOrgName();
	}
	private String Id;// 订单号
	private String Type;// 类型
	private String DateTime;// 订单时间
	private String TotalPrice;// 总价(优惠后)
	private String TotalFeePrice;// 总价（优惠前）
	private String FreightPrice;
	private String State;// 状态
	private String OrderNo;
	private String Ostatus;
	private String OrgId;
	public String getTotalFeePrice() {
		return TotalFeePrice;
	}
	public void setTotalFeePrice(String totalFeePrice) {
		TotalFeePrice = totalFeePrice;
	}
	private String OrgName;
	private String OrgTel;
	private String OrgUid;
	private String OrgTypeName;
	private String OrgType;
	private String Otype;
	private String DispId;
	private String PickId;
	private String VoucherPrice;
	private String ScorePrice;

    public String getVoucherPrice() {
		return VoucherPrice;
	}
	public void setVoucherPrice(String voucherPrice) {
		VoucherPrice = voucherPrice;
	}
	public String getScorePrice() {
		return ScorePrice;
	}
	public void setScorePrice(String scorePrice) {
		ScorePrice = scorePrice;
	}
	public String getPickId() {
		return PickId;
	}
	public void setPickId(String pickId) {
		PickId = pickId;
	}
	public String getPickTel() {
		return PickTel;
	}
	public void setPickTel(String pickTel) {
		PickTel = pickTel;
	}
	public String getPickAddress() {
		return PickAddress;
	}
	public void setPickAddress(String pickAddress) {
		PickAddress = pickAddress;
	}
	public String getReceiver() {
		return Receiver;
	}
	public void setReceiver(String receiver) {
		Receiver = receiver;
	}
	public String getReceiverTel() {
		return ReceiverTel;
	}
	public void setReceiverTel(String receiverTel) {
		ReceiverTel = receiverTel;
	}
	public String getReceiverAddr() {
		return ReceiverAddr;
	}
	public void setReceiverAddr(String receiverAddr) {
		ReceiverAddr = receiverAddr;
	}
	public String getReceiverHouseNo() {
		return ReceiverHouseNo;
	}
	public void setReceiverHouseNo(String receiverHouseNo) {
		ReceiverHouseNo = receiverHouseNo;
	}
	private String PickTel;
	private String PickAddress;
	private String Receiver;
	private String ReceiverTel;
	private String ReceiverAddr;
	private String ReceiverHouseNo;

	private String Backremarks;//增加 驳回原因


	public String getBackremarks() {
		return Backremarks;
	}
	public void setBackremarks(String backremarks) {
		Backremarks = backremarks;
	}


	private List<OrderGoodItem> GoodList;//商品列表
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public List<OrderGoodItem> getGoodList() {
		return GoodList;
	}
	public void setGoodsList(List<OrderGoodItem> goodList) {
		GoodList = goodList;
	}
	public String getOstatus() {
		return Ostatus;
	}
	public void setOstatus(String ostatus) {
		Ostatus = ostatus;
	}
	public String getOrgId() {
		return OrgId;
	}
	public void setOrgId(String orgId) {
		OrgId = orgId;
	}
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	public String getOrgTel() {
		return OrgTel;
	}
	public void setOrgTel(String orgTel) {
		OrgTel = orgTel;
	}
	public String getOrgUid() {
		return OrgUid;
	}
	public void setOrgUid(String orgUid) {
		OrgUid = orgUid;
	}
	public void setGoodList(List<OrderGoodItem> goodList) {
		GoodList = goodList;
	}
	public String getFreightPrice() {
		return FreightPrice;
	}
	public void setFreightPrice(String freightPrice) {
		FreightPrice = freightPrice;
	}
	public String getOrgTypeName() {
		return OrgTypeName;
	}
	public void setOrgTypeName(String orgTypeName) {
		OrgTypeName = orgTypeName;
	}
	public String getOrgType() {
		return OrgType;
	}
	public void setOrgType(String orgType) {
		OrgType = orgType;
	}
	public String getOtype() {
		return Otype;
	}
	public void setOtype(String otype) {
		Otype = otype;
	}
	public String getDispId() {
		return DispId;
	}
	public void setDispId(String dispId) {
		DispId = dispId;
	}

}
