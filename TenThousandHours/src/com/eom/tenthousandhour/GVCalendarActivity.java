package com.eom.tenthousandhour;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 그리드뷰를 이용한 달력 예제
 * 
 * @blog http://croute.me
 * @link http://croute.me/335
 * 
 * @author croute
 * @since 2011.03.08
 */
public class GVCalendarActivity extends Activity implements
		OnItemClickListener, OnClickListener {
	public static int SUNDAY = 1;
	public static int MONDAY = 2;
	public static int TUESDAY = 3;
	public static int WEDNSESDAY = 4;
	public static int THURSDAY = 5;
	public static int FRIDAY = 6;
	public static int SATURDAY = 7;

	private TextView mTvCalendarTitle;
	private GridView mGvCalendar;

	private ArrayList<DayInfo> mDayList;
	private CalendarAdapter mCalendarAdapter;

	Calendar mLastMonthCalendar;
	Calendar mThisMonthCalendar;
	Calendar mNextMonthCalendar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gv_calendar_activity);

		Button bLastMonth = (Button) findViewById(R.id.gv_calendar_activity_b_last);
		Button bNextMonth = (Button) findViewById(R.id.gv_calendar_activity_b_next);

		mTvCalendarTitle = (TextView) findViewById(R.id.gv_calendar_activity_tv_title);
		mGvCalendar = (GridView) findViewById(R.id.gv_calendar_activity_gv_calendar);

		bLastMonth.setOnClickListener(this);
		bNextMonth.setOnClickListener(this);
		mGvCalendar.setOnItemClickListener(this);

		mDayList = new ArrayList<DayInfo>();

		// // test
		// mGvCalendar.setAdapter(new ImageAdapter(this));
	}

	private class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		@Override
		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(6, 6, 6, 6);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(R.drawable.globe);
			return imageView;
		}

		private Integer[] mThumbIds = { R.drawable.globe, };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();

		// 이번달 의 캘린더 인스턴스를 생성한다.
		mThisMonthCalendar = Calendar.getInstance();
		mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);

		TenKApplication applicationData = (TenKApplication) getApplication();

		int selectedMonth = applicationData.getSelectedMonth();
		mThisMonthCalendar.set(Calendar.MONTH, selectedMonth);

		// mThisMonthCalendar.set(Calendar.)
		getCalendar(mThisMonthCalendar);
	}

	/**
	 * 달력을 셋팅한다.
	 * 
	 * @param calendar
	 *            달력에 보여지는 이번달의 Calendar 객체
	 */
	private void getCalendar(Calendar calendar) {
		int lastMonthStartDay;
		int dayOfMonth;
		int thisMonthLastDay;

		mDayList.clear();

		// 이번달 시작일의 요일을 구한다. 시작일이 일요일인 경우 인덱스를 1(일요일)에서 8(다음주 일요일)로 바꾼다.)
		dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
		thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.MONTH, -1);
		Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH) + "");

		// 지난달의 마지막 일자를 구한다.
		lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.MONTH, 1);
		Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH) + "");

		if (dayOfMonth == SUNDAY) {
			dayOfMonth += 7;
		}

		lastMonthStartDay -= (dayOfMonth - 1) - 1;

		// 캘린더 타이틀(년월 표시)을 세팅한다.
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");

		DayInfo day;

		Log.e("DayOfMOnth", dayOfMonth + "");

		for (int i = 0; i < dayOfMonth - 1; i++) {
			Calendar today = (Calendar) calendar.clone();// Calendar.getInstance();
			today.add(Calendar.MONTH, -1);

			int date = lastMonthStartDay + i;
			day = new DayInfo();
			day.setDay(Integer.toString(date));
			day.setInMonth(false);
			day.setDate(new GregorianCalendar(today.get(Calendar.YEAR), today
					.get(Calendar.MONTH), date));
			mDayList.add(day);
		}
		for (int i = 1; i <= thisMonthLastDay; i++) {
			Calendar today = (Calendar) calendar.clone();
			day = new DayInfo();
			day.setDay(Integer.toString(i));
			day.setInMonth(true);
			day.setDate(new GregorianCalendar(today.get(Calendar.YEAR), today
					.get(Calendar.MONTH), i));
			mDayList.add(day);
		}
		for (int i = 1; i < 42 - (thisMonthLastDay + dayOfMonth - 1) + 1; i++) {
			Calendar today = (Calendar) calendar.clone();
			today.add(Calendar.MONTH, 1);

			day = new DayInfo();
			day.setDay(Integer.toString(i));
			day.setInMonth(false);
			day.setDate(new GregorianCalendar(today.get(Calendar.YEAR), today
					.get(Calendar.MONTH), i));
			mDayList.add(day);
		}

		initCalendarAdapter();
	}

	/**
	 * 지난달의 Calendar 객체를 반환합니다.
	 * 
	 * @param calendar
	 * @return LastMonthCalendar
	 */
	private Calendar getLastMonth(Calendar calendar) {
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.add(Calendar.MONTH, -1);
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
		return calendar;
	}

	/**
	 * 다음달의 Calendar 객체를 반환합니다.
	 * 
	 * @param calendar
	 * @return NextMonthCalendar
	 */
	private Calendar getNextMonth(Calendar calendar) {
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.add(Calendar.MONTH, +1);
		mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
				+ (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
		return calendar;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position,
			long arg3) {

		TenKApplication applicationData = (TenKApplication) getApplication();
		DayInfo day = (DayInfo) parent.getItemAtPosition(position);

		int selectedMonth = applicationData.getSelectedMonth();
		day.getDate().set(Calendar.MONTH, selectedMonth);

		Intent in = new Intent(this, InputEfforts.class);
//		Intent in = new Intent(this, DailySummary.class);
		// Intent in = new Intent(this, PhotoIntentActivity.class);
		in.putExtra("date", day.getDate());
		startActivityForResult(in, 0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gv_calendar_activity_b_last:
			mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
			((TenKApplication) getApplication())
					.setSelectedMonth(mThisMonthCalendar.getTime().getMonth());
			getCalendar(mThisMonthCalendar);
			break;
		case R.id.gv_calendar_activity_b_next:
			mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
			((TenKApplication) getApplication())
					.setSelectedMonth(mThisMonthCalendar.getTime().getMonth());
			getCalendar(mThisMonthCalendar);
			break;
		}
	}

	private void initCalendarAdapter() {
		mCalendarAdapter = new CalendarAdapter(this, R.layout.day, mDayList);
		//mGvCalendar.setAdapter(new ImageAdapter(this));
		mGvCalendar.setAdapter(mCalendarAdapter);
	}
}