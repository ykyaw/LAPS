package com.team1.iss.trial.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static long ONE_DAY_TOTAL_MILLION_SECONDS=1000*60*60*24;
	
    /**
     * @return current timestamp
     */
    public static long getCurrentTimestamp(){
        return new Date().getTime();
    }

   
    /**
     * convert timestamp to yyyy-MM-dd HH:mm format
     * @param timestamp
     * @return
     */
    public static String convertTimestampToTimeFormat(long timestamp){
        Timestamp ts = new Timestamp(timestamp);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String tsStr = sdf.format(ts);
        return tsStr;
    }
    
    /**
     * convert timestamp to HH:mm format
     * @param timestamp
     * @return
     */
    public static String convertTimestampToHHmm(long timestamp){
        Timestamp ts = new Timestamp(timestamp);
        DateFormat sdf = new SimpleDateFormat("HH:mm");

        String tsStr = sdf.format(ts);
        return tsStr;
    }
    
    /**
     * convert timestamp to yyyy-MM-dd format
     * @param timestamp
     * @return
     */
    public static String convertTimestampToyyyMMdd(long timestamp){
        Timestamp ts = new Timestamp(timestamp);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String tsStr = sdf.format(ts);
        return tsStr;
    }
}
