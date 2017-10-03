package com.zt.study;

import com.bbk.im.tlv.annotation.TLVAttribute;
import com.bbk.im.tlv.util.PrintBean;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	
	public Message() {
//		this.created = new Date();
//		this.modify = this.created;
	}
	
    @TLVAttribute(tag = 1, description = "消息Id")
    protected Long messageId;

    @TLVAttribute(tag = 2, description = "客户端消息Id")
	private String msgId;

    @TLVAttribute(tag = 3, description = "账号ID")
    protected Long accountId;

    @TLVAttribute(tag = 4, description = "设备注册Id")
    protected Long registId;

    @TLVAttribute(tag = 5, description = "会话Id")
    protected Long dialogId;

    @TLVAttribute(tag = 6, description = "消息同步序号")
    protected Long syncKey;

    @TLVAttribute(tag = 7, description = "消息类型")
	private Short msgType;

    protected Date created;

    protected Date modify;

    @TLVAttribute(tag = 8, description = "消息")
    protected byte[] message;
    
    @TLVAttribute(tag = 9, description = "创建时间")

	private Long createdTime;

    static final long serialVersionUID = 1L;


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId=messageId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId=accountId;
    }

    public Long getRegistId() {
        return registId;
    }

    public void setRegistId(Long registId) {
        this.registId=registId;
    }

    public Long getDialogId() {
        return dialogId;
    }

    public void setDialogId(Long dialogId) {
        this.dialogId=dialogId;
    }

    public Long getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(Long syncKey) {
        this.syncKey=syncKey;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created=created;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify=modify;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message=message;
    }

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}
	
	@Override
	public String toString() {
		return PrintBean.getString(this);
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
}