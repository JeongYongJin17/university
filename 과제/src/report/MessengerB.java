package report;
import javax.swing.*;

import report.Message.MyFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessengerB {
	protected JTextField textField;
	protected JTextArea textArea;

	DatagramSocket socket;
	DatagramPacket packet;
	InetAddress address = null;
	
	final int myPort = 6000;
	final int otherPort = 5000;
	
	public MessengerB() throws IOException{
		MyFrame f = new MyFrame();
		address = InetAddress.getByName("192.168.0.27");
		socket= new DatagramSocket(myPort);
	}
	
	public void process() {
		while(true) {
			try {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf,buf.length);
				socket.receive(packet);
				
				textArea.append("RECIEVED: "+ new String(buf)+ "\n");
				
			}catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	class MyFrame extends JFrame implements ActionListener{
		public MyFrame() {
			super("MessengerB");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			textField = new JTextField(30);
			textField.addActionListener(this);
			
			textArea = new JTextArea(10,30);
			textArea.setEditable(false);
			
			add(textField,BorderLayout.PAGE_END);
			add(textArea, BorderLayout.CENTER );
			pack();
			setVisible(true);
		}
		public void actionPerformed(ActionEvent evt) {
			String s = textField.getText();
			byte[] buffer = s.getBytes();
			DatagramPacket packet;
			
			packet = new DatagramPacket(buffer, buffer.length, address,otherPort);
			try {
				socket.send(packet);
			}catch (IOException e) {
				e.printStackTrace();
			}
			textArea.append("SENT: "+ s +"\n");
			textField.selectAll();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		
	}
	public static void main(String[] args) throws IOException{
		MessengerB f = new MessengerB();
		f.process();
	}
}
