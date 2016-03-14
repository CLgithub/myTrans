package com.cl.mytrans.ui;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cl.mytrans.c.MyControl;
import com.cl.mytrans.receiver.ServerT;

/**
 * 启动窗口，相当于qq主面板
 * @author L
 * @date 2016年3月14日
 */
public class InitW extends JFrame{
	ServerSocket serverSocket;
	public static void main(String[] args) {
		InitW initW=new InitW();
	}
	public InitW() {
		JPanel jPanel=new JPanel();
		TextField ipText= new TextField(20);
		String mess="请填入联系人ip";
		JLabel hintMess=new JLabel(mess,JLabel.CENTER);
		JButton button=new JButton("确定");
		
		jPanel.add(hintMess);
		jPanel.add("ipText",ipText);
		jPanel.add(button);
		
		this.add(jPanel);
		/**************构建主视图框架*****************/
		this.setResizable(false);
		this.setVisible(true);
		this.setBounds(360,120,260,110);
		this.validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("myTrans");
//		//启动服务线程，便于其他客户端连接本机
		try {
			serverSocket=new ServerSocket(8099);
			new Thread(new ServerT(serverSocket)).start();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		//绑定监听
		button.addActionListener(new ActionListener() {
			//点击确定按钮,得到控制类，去创建会话
			public void actionPerformed(ActionEvent e) {
				try {
					Socket socket=new Socket(ipText.getText(), 8099);
					MyControl myControl=new MyControl(socket);
					myControl.startSession();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	

}
