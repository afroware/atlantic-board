package com.afroware.blockchain.message;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

	public Message decode(String jsonMessage) throws DecodeException {

		JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
		Message message = new Message();
		message.setData(jsonObject.getString("data"));
		message.setMessageType(MessageType.valueOf(jsonObject.getString("type")));
		return message;

	}

	public boolean willDecode(String jsonMessage) {
		try {
			// Check if incoming message is valid JSON
			Json.createReader(new StringReader(jsonMessage)).readObject();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void init(EndpointConfig ec) {
		System.out.println("MessageDecoder -init method called");
	}

	public void destroy() {
		System.out.println("MessageDecoder - destroy method called");
	}

}
