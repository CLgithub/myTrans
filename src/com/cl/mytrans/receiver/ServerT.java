package com.cl.mytrans.receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cl.mytrans.c.MyControl;

public class ServerT implements Runnable{
	private ServerSocket serverSocket;
	
	

	public ServerT(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void run() {
		try {
			while(true){
				Socket socket=serverSocket.accept();
				MyControl myControl=new MyControl(socket);
				myControl.startSession();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

}
