package com.test;

public class TestCase {

	public static void main(String[] args){
		
		Responser responser = new Responser(65535,65534);
		
		MsgReceiver receiver = new MsgReceiver(60000);
		
		while(true)
		{
			System.out.println(receiver.receive());
			System.out.println(responser.getUserList().get(0));
		}
	}
}
