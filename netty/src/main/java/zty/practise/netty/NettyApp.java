package zty.practise.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import zty.practise.netty.util.HttpTemplate;

@RestController
@SpringBootApplication
public class NettyApp {

	@Autowired 
	private HttpTemplate httpTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(NettyApp.class, args);
	}
	
	@GetMapping("/message/send")
	public String send(){
		httpTemplate.sendMessageToServer();
		return "success";
	}
}
