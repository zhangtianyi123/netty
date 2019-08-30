package zty.pracise.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;

/**
 * 早期JDK提供阻塞的网络API（BIO服务端）
 * 
 * @author zhangtianyi
 *
 */
public class BIOModel {

	public static void main(String[] args) throws IOException {
		BIOModel server = new BIOModel();
		server.run(9201);
	}

	public void run(int portNumber) throws IOException {
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while (true) {
			System.out.println("阻塞的获取连接");
			Socket clientSocket = serverSocket.accept();
			System.out.println("获取连接");
			new Thread(() -> {
				try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					String request, response;
					while ((request = in.readLine()) != null) {
						response = handle(request);
						out.println(response);
						System.out.println("阻塞的读取数据");
					}
				} catch (IOException e){
	                e.printStackTrace();
				}
			}).start();
		}
	}

	private String handle(String request) throws IOException {
		return "response for " + request;
	}

}
