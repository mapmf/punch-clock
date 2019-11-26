package com.marcos.punchclock.model.util;

import java.util.Calendar;
import java.util.Date;

import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.util.DateUtil;

/**
 * 
 * This class calculates the employee hours of work
 * 
 * @author Marcos André
 *
 */

public class WorkingPunchClockInterval {

	private static final double SUNDAY = 1;
	private static final double SATURDAY = 7;
	private static final int HOUR_TO_START_COUNT_ADDITIONAL_NIGHT = 22;
	private static final int HOUR_TO_END_COUNT_NIGHT_ADDITIONAL = 6;
	private static final double SATURDAY_MODIFIER = 1.5;
	private static final double SUNDAY_MODIFIER = 2;
	private static final double ADDITIONAL_NIGHT_MODIFIER = 0.2;

	private Date inDate;
	private Date outDate;

	public WorkingPunchClockInterval(PunchClock inPunchClock, PunchClock outPunchClock) {
		this.inDate = inPunchClock.getDate();
		this.outDate = outPunchClock.getDate();
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public double calculateHours() {

		double intervalInHours = getBasicIntervalInHours();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(inDate);

		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == SUNDAY) {

			intervalInHours = intervalInHours * SUNDAY_MODIFIER;

		} else if (dayOfWeek == SATURDAY) {

			intervalInHours = intervalInHours * SATURDAY_MODIFIER;

		} else {

			intervalInHours = intervalInHours + calculateAdditionalNight(inDate, outDate);
		}

		return intervalInHours;
	}

	public double getBasicIntervalInHours() {

		return DateUtil.getIntervalInHours(inDate, outDate);
	}

	private double calculateAdditionalNight(Date start, Date end) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);

		calendar.set(Calendar.HOUR_OF_DAY, HOUR_TO_START_COUNT_ADDITIONAL_NIGHT);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date startNightAdditional = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, HOUR_TO_END_COUNT_NIGHT_ADDITIONAL);
		calendar.add(Calendar.DAY_OF_MONTH, 1);

		Date endNightAdditional = calendar.getTime();

		boolean hasNightAdditional = false;

		if (start.after(startNightAdditional)) {
			startNightAdditional = start;
			hasNightAdditional = true;
		}

		if (end.after(startNightAdditional) && end.before(endNightAdditional)) {
			endNightAdditional = end;
			hasNightAdditional = true;
		}

		double additionalNight = 0.0;

		if (hasNightAdditional) {

			double additionalNightInterval = DateUtil.getIntervalInHours(startNightAdditional, endNightAdditional);

			additionalNight = additionalNightInterval * ADDITIONAL_NIGHT_MODIFIER;
		}

		return additionalNight;
	}

}
