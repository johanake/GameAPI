package app.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Fetches the time until the end date in milliseconds.
 * 
 * @author Lucas Borg 2018-04-30
 *
 */
public class DateAndTime {

	/**
	 * Calculates the time until the end date.
	 * 
	 * @param endDate
	 *            The end date.
	 * @return The time until the end date in milliseconds.
	 */
	public Object convertEndDateToMillisec(Date endDate) {
		if (endDate != null) {
			long millisec = 0;
			Calendar cal = cal = Calendar.getInstance(TimeZone.getDefault());
			Date date = cal.getTime();
			millisec = endDate.getTime() - date.getTime();
			return millisec;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		DateAndTime dat = new DateAndTime();
		Calendar cal = Calendar.getInstance();
		cal.getInstance(TimeZone.getDefault());
		cal.set(2018, 4, 1, 0, 0);
		Date date = cal.getTime();
		System.out.println(dat.convertEndDateToMillisec(date));
	}
}
