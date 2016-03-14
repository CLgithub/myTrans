package com.cl.mytrans.c;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.cl.mytrans.receiver.Receiver;
import com.cl.mytrans.ui.MainWindows;

public class MyControl {
	private Socket socket;
	private List list;
	private JButton sendB;
	private JTextField sendText;
	private DataOutputStream dos;
	private BufferedReader bReader;

	public MyControl(Socket socket) {
		this.socket=socket;
	}

	public void startSession() throws IOException {
		//启动窗口
		MainWindows mWindows = new MainWindows(socket);
		list = mWindows.getList();
		sendText = mWindows.getSendText();
		sendB = mWindows.getSendB();
		dos=mWindows.getDos();
		bReader=mWindows.getbReader();
		//启动发送者
		startSender();
		//启动监听者线程
		Receiver receiver=new Receiver(list,bReader);
		new Thread(receiver).start();
		
	}

	private void startSender() {
		sendB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dos.writeBytes(sendText.getText() + System.getProperty("line.separator"));
					dos.flush();
					list.add("我" + sendText.getText() + System.getProperty("line.separator"));
					sendText.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
