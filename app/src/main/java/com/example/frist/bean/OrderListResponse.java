package com.example.frist.bean;

import java.util.ArrayList;
import java.util.List;

public class OrderListResponse extends HttpApiResponse{

	private static final long serialVersionUID = 2524685798843150813l;
	private ArrayList<OrderItem> OrderList;

	private int Total;

	private String CurrentDateTime;

	public ArrayList<OrderItem> getOrderList() {
		return OrderList;
	}
	public void setOrderList(ArrayList<OrderItem> orderList) {
		this.OrderList = orderList;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		this.Total = total;
	}
	public String getCurrentDateTime() {
		return CurrentDateTime;
	}
	public void setCurrentDateTime(String currentDateTime) {
		this.CurrentDateTime = currentDateTime;
	}
}
