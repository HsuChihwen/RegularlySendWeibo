package UI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xzw.Welcome;

public class mainUi{
	public static void main(String[] args) {
		new mainUi();
	}

	public mainUi(){
		Init();
	}

	public String[] comNum = {"COM1","COM2","COM3","COM4"};
	
	//定时时间
	private JTextField time = new JTextField();
	//串口号
	private JComboBox com = new JComboBox(comNum);
	//按钮
	private JButton button = new JButton("确定");
	
	private JLabel timeLabel = new JLabel("定时时间:");
	private JLabel comLabel = new JLabel("串口号:");
	
	private JPanel jPanel = new JPanel();
	private JFrame jFrame = new JFrame();
	public void Init(){
		jFrame.setBounds(600, 50, 225, 400);
		jFrame.add(jPanel);
		
		
		jPanel.add(timeLabel);
		jPanel.setLayout(null);
		
		jPanel.add(timeLabel);
		timeLabel.setBounds(25, 100,150, 30);
		
		jPanel.add(time);
		time.setBounds(25, 125, 150, 20);
		
		jPanel.add(comLabel);
		comLabel.setBounds(25, 150,150, 20);
		
		jPanel.add(com);
		com.setBounds(25, 175, 150, 20);
		com.setEditable(true);
		
		jPanel.add(button);
		button.setBounds(70, 260, 60, 25);
		
		regMouseListener mouseLis = new regMouseListener();
		button.addMouseListener(mouseLis);
		
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	class regMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			//鼠标点击后 开启处理线程
			if(e.getSource()==button){
				String strTime = time.getText();
				String str[] = strTime.split(":");
				int hourOfDay = Integer.parseInt(str[0]);
				int minute = Integer.parseInt(str[1]);
				int second = Integer.parseInt(str[2]);
				String strCom = (String) com.getSelectedItem();
				Welcome wel = new Welcome(hourOfDay, minute, second, strCom);
				wel.run();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
