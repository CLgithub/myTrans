package com.cl.mytrans.receiver;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JList;

public class Receiver implements Runnable{
	private List list;
	private BufferedReader bReader;

	public Receiver(List list, BufferedReader bReader) {
		this.list = list;
		this.bReader = bReader;
	}

	public void run() {
		while(true){
			try {
				String rString=bReader.readLine();
				list.add("对方："+rString);
//				list.setToolTipText("对方："+rString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
