package zty.pracise.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * BIO的客户端
 * @author zhangtianyi
 *
 */
public class BIOClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 9201);
			System.out.println("请输入信息");
			//异步获取服务端响应
			new ReadMsg(socket).start();
			PrintWriter pw = null;
			//写数据到服务端
			while (true) {
				pw = new PrintWriter(socket.getOutputStream());
				pw.println(new Scanner(System.in).next());
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static class ReadMsg extends Thread {
		Socket socket;

		public ReadMsg(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.printf("%s\n", line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}