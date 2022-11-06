package com.autumn.component.model.jgpush;

public class JgPushModel {

	private String title;
	
	private String content;

	private String txid;
	
	private String alias;
	
	private int apptype;

	private Long recordId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getApptype() {
		return apptype;
	}

	public void setApptype(int apptype) {
		this.apptype = apptype;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return "JgPushModel [title=" + title + ", content=" + content
				+ ", txid=" + txid + ", alias=" + alias + ", apptype="
				+ apptype + ", recordId=" + recordId + "]";
	}

}
