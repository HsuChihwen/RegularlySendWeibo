

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.comm.SerialPort;

import org.tiling.scheduling.examples.iterators.DailyIterator;
import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import xzw.InfoBean;
import ReadComm.CommUtil;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t = new test(7, 0, 0);//每天早上7点定时
		t.start();
	}

    private final Scheduler scheduler = new Scheduler();
    private final SimpleDateFormat dateFormat =
        new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
    private final int hourOfDay, minute, second;

    public test(int hourOfDay, int minute, int second) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
    }

    public void start() {
        scheduler.schedule(new SchedulerTask() {
            public void run() {
            	sendWeibo();
            }
            private void sendWeibo() {
                System.out.println("Wake up! " +
                    "It's " + dateFormat.format(new Date()));
                //getInformationFromComm
//                CommUtil commUtil = new CommUtil();
//                SerialPort serialPort = commUtil.openComm("COM2", SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//                InfoBean infoBean = commUtil.getDataFromComm(serialPort);
//                commUtil.closeComm(serialPort);
//                //sendWeibo
//                Weibo weibo = new Weibo();
//        		String accessToken = "2.00fMLLrB0rQD1Sec530d68f4TyQEyC";
//        		weibo.setToken(accessToken);
//        		Timeline timeLine = new Timeline();
//        		timeLine.client.setToken(accessToken);
//        		String str = "室内温度："+infoBean.getTempertaure()+"℃ ,室内PM2.5:"+infoBean.getParticulateMatter()+"mg/m³";
//        		try {
//        			Status status = timeLine.UpdateStatus(str);
//        		} catch (WeiboException e) {
//        			// TODO Auto-generated catch block
//        			e.printStackTrace();
//        		}
            }
        }, new DailyIterator(hourOfDay, minute, second));
    }
}
