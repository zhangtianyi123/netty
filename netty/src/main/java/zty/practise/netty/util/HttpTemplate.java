package zty.practise.netty.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("httpTemplate")
public class HttpTemplate {

	@Autowired
	private RestTemplate restTemplate;
	
	public void sendMessageToServer() {
//		restTemplate.getForEntity("http://127.0.0.1:9201", String.class);
	}
	
}
