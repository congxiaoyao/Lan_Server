package com.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MsgReceiver{
	
	private ServerSocket serverSocket;
	
	public MsgReceiver(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String receive()
	{
		String msg = null;
		try {
			Socket socket = serverSocket.accept();
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			msg = inputStream.readUTF();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
}
