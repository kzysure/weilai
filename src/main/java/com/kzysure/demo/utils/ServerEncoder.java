package com.kzysure.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kzysure.demo.VO.WebsocketVO;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServerEncoder implements Encoder.Text<WebsocketVO> {

  @Override
  public String encode(WebsocketVO websocketVO) throws EncodeException {
    Gson gson = new Gson();
    String s = gson.toJson(websocketVO);
    return s;
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }
}
