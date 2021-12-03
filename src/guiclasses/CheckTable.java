package guiclasses;

import java.util.Date;

import javafx.scene.control.CheckBox;

public class CheckTable {
	int confNum;
	int reqID;
	String name;
	String price;
	Date time;
	String status;
	CheckBox select;
	
	public int getConfNum() {
		return confNum;
	}
	public void setConfNum(int confNum) {
		this.confNum = confNum;
	}
	public int getReqID() {
		return reqID;
	}
	public void setReqID(int reqID) {
		this.reqID = reqID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CheckBox getSelect() {
		return select;
	}
	public void setSelect(CheckBox select) {
		this.select = select;
	}

	public CheckTable(int confNum, int reqID, String name, String price, Date time, String status, CheckBox select) {
		super();
		this.confNum = confNum;
		this.reqID = reqID;
		this.name = name;
		this.price = price;
		this.time = time;
		this.status = status;
		this.select = select;
	}
}