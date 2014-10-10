package xzw;


import java.text.SimpleDateFormat;

import javax.comm.SerialPort;

import org.apache.log4j.Logger;
import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;
import org.tiling.scheduling.examples.iterators.DailyIterator;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import ReadComm.CommUtil;

public class Welcome implements Runnable{

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Welcome wel = new Welcome(17, 33, 0);//每天早上7点定时
//		wel.start();
//	}

	private final Scheduler scheduler = new Scheduler();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
    private final int hourOfDay, minute, second;
    private final String comNum;

    public Welcome(int hourOfDay, int minute, int second,String comNum) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        this.comNum = comNum;
    }

    public Logger log = Logger.getLogger(Welcome.class);
    
    public void run() {
    	log.info("定时时间："+hourOfDay+":"+minute+":"+second);
    	log.info("打开串口:"+comNum);
        scheduler.schedule(new SchedulerTask() {
            public void run() {
            	sendWeibo();
            }
            private void sendWeibo() {
//                System.out.println("Wake up! " +
//                    "It's " + dateFormat.format(new Date()));
                // Start a new thread to sound an alarm...
                //getInformationFromComm
//                CommUtil commUtil = new CommUtil();
//                SerialPort serialPort = commUtil.openComm(comNum, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//                InfoBean infoBean = commUtil.getDataFromComm(serialPort);
//                System.out.println(infoBean);
//                System.out.println("~~~~~~~~~~~~~~~~~~~~~");
//                commUtil.closeComm(serialPort);
                //sendWeibo
                Weibo weibo = new Weibo();
        		String accessToken = "2.00fMLLrB0rQD1Sec530d68f4TyQEyC";
        		weibo.setToken(accessToken);
        		Timeline timeLine = new Timeline();
        		timeLine.client.setToken(accessToken);
//        		String str = "室内温度："+infoBean.getTempertaure()+"℃ ,室内PM2.5:"+infoBean.getParticulateMatter()+"ug/m³";
//        		System.out.println(str);
//        		try {
//        			Status status = timeLine.UpdateStatus(str);
        			log.info("微博发送成功！");
//        		} catch (WeiboException e) {
//        			e.printStackTrace();
        			log.info("微博发送失败！");
//        			log.info(e.getMessage());
//        		}
            }
        }, new DailyIterator(hourOfDay, minute, second));
    }
}
