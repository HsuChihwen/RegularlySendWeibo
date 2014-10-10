package SendWeibo;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class SendWeibo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Weibo weibo = new Weibo();
		String accessToken = "2.00fMLLrB0rQD1Sec530d68f4TyQEyC";
		weibo.setToken(accessToken);
		Timeline timeLine = new Timeline();
		timeLine.client.setToken(accessToken);
		try {
			Status status = timeLine.UpdateStatus("此条微博由程序自动发送！");
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
