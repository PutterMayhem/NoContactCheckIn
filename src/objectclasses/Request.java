package objectclasses;

import java.util.Date;

public class Request {
	int req_id;
	String type;
	Date requestDate;
	Date requestFulfilledDate;
	boolean fulfilled;
	int conf_id;
	int item_id;

	public Request() {

	}

	public Request(int req_id, String type, Date requestDate, Date requestFulfilledDate, int conf_id, int item_id) {
		super();
		this.req_id = req_id;
		this.type = type;
		this.requestDate = requestDate;
		this.requestFulfilledDate = requestFulfilledDate;
		this.conf_id = conf_id;
		this.item_id = item_id;
		fulfilled = false;
	}

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getRequestFulfilledDate() {
		return requestFulfilledDate;
	}

	public void setRequestFulfilledDate(Date requestFulfilledDate) {
		this.requestFulfilledDate = requestFulfilledDate;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public int getConf_id() {
		return conf_id;
	}

	public void setConf_id(int conf_id) {
		this.conf_id = conf_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

}
