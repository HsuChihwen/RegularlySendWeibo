package xzw;

public class InfoBean {
	private String tempertaure;		//环境温度
	private String humidity;		//环境湿度
	private String particulateMatter;//PM2.5
	public String getTempertaure() {
		return tempertaure;
	}
	public void setTempertaure(String tempertaure) {
		this.tempertaure = tempertaure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getParticulateMatter() {
		return particulateMatter;
	}
	public void setParticulateMatter(String particulateMatter) {
		this.particulateMatter = particulateMatter;
	}
	@Override
	public String toString() {
		return "InfoBean [tempertaure=" + tempertaure + ", humidity="
				+ humidity + ", particulateMatter=" + particulateMatter + "]";
	}
	
	
}
