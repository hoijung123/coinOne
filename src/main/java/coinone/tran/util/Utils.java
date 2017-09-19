package coinone.tran.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String timeStamp2Date(String seconds,String format) {
		if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
			return "";
		}
		seconds = new Long(new Long(seconds).longValue() /1000).toString();
		if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds+"000")));
	}
	
	public static String timeStamp2Date(String seconds) {
		
		return timeStamp2Date(seconds,"");
	}	
}
