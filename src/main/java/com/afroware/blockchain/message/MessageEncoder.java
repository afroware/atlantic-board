package com.afroware.blockchain.message;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

  public String encode(Message message) throws EncodeException {

    JsonObject jsonObject = Json.createObjectBuilder()
        .add("data", Json.createValue(message.getData().toString()) )
        .add("type", Json.createValue(message.getMessageType().name())).build();
    return jsonObject.toString();

  }

  public void init(EndpointConfig ec) {
    System.out.println("MessageEncoder - init method called");
  }

  public void destroy() {
    System.out.println("MessageEncoder - destroy method called");
  }

}
