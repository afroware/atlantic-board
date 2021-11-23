package com.afroware.blockchain.message;

public class Message {

	public Message() {
		super();
	}

	public Message(Enum<?> messageType, Object data) {
		super();
		this.messageType = messageType;
		this.data = data;
	}


	Enum<?> messageType;

	Object data;

	/**
	 * @return the messageType
	 */
	public Enum<?> getMessageType() {
		return messageType;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(Enum<?> messageType) {
		this.messageType = messageType;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
