package ReadComm;
import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

import xzw.InfoBean;

/**
 * 串口工具类
 * @author xzw
 *
 */
public class CommUtil implements Runnable,SerialPortEventListener{

	private InputStream inputStream;
	private String commPort;
	private int dataBits;
	private int stopBits;
	private int parity;
	public String str;
	public Thread thread;
	
	private final BlockingQueue<Object> result = new ArrayBlockingQueue<Object>(1);
	
//	public static void main(String[] args) {
//		CommUtil comUtil = new CommUtil("9600", 8, 1, 0);
//	}
	

	public CommUtil(String commPort, int dataBits, int stopBits, int parity) {
		super();
		this.commPort = commPort;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
	}
	
	public InfoBean getDataFromSer(){
		SerialPort serialPort = null;
		InfoBean infoBean = new InfoBean();
		try {
			CommPortIdentifier comPort = CommPortIdentifier.getPortIdentifier(commPort);
			serialPort = (SerialPort) comPort.open(commPort,2000);
			serialPort.setSerialPortParams(9600,dataBits,stopBits,parity);
			serialPort.notifyOnDataAvailable(true);
			serialPort.notifyOnBreakInterrupt(true);
			serialPort.enableReceiveFraming(500);
			serialPort.addEventListener(this);
			thread = new Thread(this);
			thread.start();
			String str = (String) result.poll();
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyListenersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[1024];
			StringBuffer strBuf = new StringBuffer();
			try {
				while (inputStream.available() > 0) {
					 inputStream.read(readBuffer);
				}
				strBuf.append(new String(readBuffer));
			} catch (IOException e) {
			}
			str = strBuf.toString();
			result.add(str);
			System.out.println("~~~~~~~~~~~");
			System.out.println(str);
			break;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

