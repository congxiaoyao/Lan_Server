package com.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author congxiaoyao
 * @version 0.0.1
 */
public class Responser extends Thread {

	private int srcPort; // 接受全网广播使用的端口号
	private int dstPort; // 回应服务器ip使用的端口号

	private DatagramSocket socket;
	private DatagramPacket packet;

	private List<User> userList;

	/**
	 * 
	 * @param srcPort
	 *            接受全网广播使用的端口号
	 * @param dstPort
	 *            回应服务器ip使用的端口号
	 */
	public Responser(int srcPort, int dstPort) {

		this.srcPort = srcPort;
		this.dstPort = dstPort;
		userList = new ArrayList<User>();
		start();
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("running");

			try {
				packet = new DatagramPacket(new byte[1024], 1024, null, srcPort);
				socket = new DatagramSocket(srcPort);
				socket.receive(packet);
				socket.close();

				String massage = byteToString(packet.getData());

				if (!isRequest(massage.substring(0, 3)))
					continue;
				
				replytoClient((Inet4Address) packet.getAddress());
				
				recordUser(massage.substring(3, massage.length()), packet.getAddress().getHostAddress());
				

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void replytoClient(Inet4Address clientAddress) {

		System.out.println("客户端ip:" + clientAddress.getHostAddress());

		String reply = "002";
		packet = new DatagramPacket(reply.getBytes(), reply.length(),
				clientAddress, dstPort);

		try {
			socket = new DatagramSocket(dstPort);
			socket.send(packet);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void recordUser(String username, String userIP) {
		userList.add(new User(username, userIP, new Date()));

	}

	public static String byteToString(byte[] bs) {
		String result = new String(bs);
		return result.trim();
	}

	public boolean isRequest(String s) {
		return s.equals("001");
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}