package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	//Today's date
	public static String getToday() {
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //m: minute, M: Month
		
		String today = sdf.format(time);
		return today;
	}
	
	//Today's date and time
	public static String getTodayTime() {
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //m: minute, M: Month
		
		String today = sdf.format(time);
		return today;
	}
}
