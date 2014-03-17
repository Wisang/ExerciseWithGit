package com.eom.tenthousandhour;

public class DailyEffort implements Cloneable {
	String hoursDevoted;
	String workContents;
	
	public Object clone() throws CloneNotSupportedException {
		DailyEffort dailyEffort = (DailyEffort)super.clone();
		return dailyEffort;
	}
}
