package com.cl.mytrans.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 会话主窗口
 * @author L
 * @date 2016年3月14日
 */
public class MainWindows extends JFrame {
	private JTextField sendText;
	private List list;
	private JButton sendB;
	private DataOutputStream dos;
	private BufferedReader bReader;
	public JTextField getSendText() {
		return sendText;
	}
	public List getList() {
		return list;
	}
	public JButton getSendB() {
		return sendB;
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public BufferedReader getbReader() {
		return bReader;
	}
	public MainWindows(Socket socket) throws IOException {
		dos=new DataOutputStream(socket.getOutputStream());
		bReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

		sendText= new JTextField(30);
		list=new List();
		sendB=new JButton("发送");
		this.add(list);
		JPanel jPanel=new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add("West", sendText);
		jPanel.add("East",sendB);
		
		this.add("South",jPanel);
		/**************构建主视图框架*****************/
		this.setResizable(false);
		this.setVisible(true);
		this.setBounds(560,320,460,410);
		this.validate();
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("与"+socket.getInetAddress()+"的对话");
		
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			try {
				System.out.println("MainWindows.processWindowEvent()");
				dos.close();
				bReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			super.processWindowEvent(e);
		} else {
			// 忽略其他事件，交给JFrame处理
			super.processWindowEvent(e);
		}
	}
	
	
}
