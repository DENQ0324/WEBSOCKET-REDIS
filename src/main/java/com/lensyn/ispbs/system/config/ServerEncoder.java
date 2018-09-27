package com.lensyn.ispbs.system.config;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lensyn.ispbs.entity.Response;

/**
 * websocket返回json数据
 * @author we
 */
public class ServerEncoder implements Encoder.Text<Response<?>> {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public void destroy() {

	}

	@Override
	public void init(EndpointConfig arg0) {

	}

	@Override
	public String encode(Response<?> response) throws EncodeException {
		try {
			return mapper.writeValueAsString(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
