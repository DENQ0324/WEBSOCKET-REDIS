package com.lensyn.ispbs.system.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lensyn.ispbs.entity.Response;
import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.service.RainService;
import com.lensyn.ispbs.system.config.ServerEncoder;

@ServerEndpoint(value = "/websocket/{moduleName}", encoders = ServerEncoder.class)
@Component
public class WebSocketRainServer {

	public static CopyOnWriteArraySet<WebSocketRainServer> webSocketSet = new CopyOnWriteArraySet<>();

	private Session session;

	// 此处是解决无法注入的关键
	private static ApplicationContext applicationContext;
	// 你要注入的service或者dao
	private RainService rainService;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		WebSocketRainServer.applicationContext = applicationContext;
	}

	@OnOpen
	public void onOpen(@PathParam("moduleName") String moduleName, Session session) {
		try {
			this.session = session;
			webSocketSet.add(this);
			switch (moduleName) {
			case "rain":
				RainMessage();
				break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, EncodeException {
		// 群发消息
		for (WebSocketRainServer item : webSocketSet) {
//			item.sendMessage(message);
		}
	}

	public void sendMessage(Response<?> response) throws IOException, EncodeException {
		this.session.getBasicRemote().sendObject(response);
	}
	
	/**
	 * @Title: RainMessage
	 * @author: DENQ
	 * @date: 2018年9月25日 下午5:02:48
	 * @Description: TODO(获取雨量信息)
	 */

	private void RainMessage() {
		// TODO Auto-generated method stub
		try{
		rainService = applicationContext.getBean(RainService.class);
		/**
		 * 推送云图数据
		 */
		Map<String, List<String>> img = rainService.getCloudImg();
		Response<Object> imgList = new Response<>().success(img);
		imgList.setType("cloudImg");
		for (WebSocketRainServer item : webSocketSet) {
			item.sendMessage(imgList);
		}
		/**
		 * 推送雨量信息
		 */
		Map<String, String> map = new HashMap<String, String>();
		List<AreaInfoData> areaInfoList = rainService.getAreaRainInfo(map);
			Response<Object> areaList = new Response<>().success(areaInfoList);
			areaList.setType("rainInfo");
			for (WebSocketRainServer item : webSocketSet) {
				item.sendMessage(areaList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
