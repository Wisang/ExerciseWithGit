package com.eom.tenthousandhour;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * BaseAdapter�� ��ӹ޾� ������ CalendarAdapter
 * 
 * @author croute
 * @since 2011.03.08
 */
public class CalendarAdapter extends BaseAdapter
{
	private ArrayList<DayInfo> mDayList;
	private Context mContext;
	private int mResource;
	private LayoutInflater mLiInflater;
	private LinearLayout cell;
	private int gridviewHeight;
	
	LinearLayout layout;

	/**
	 * Adpater ������
	 * 
	 * @param context
	 *            ���ؽ�Ʈ
	 * @param textResource
	 *            ���̾ƿ� ���ҽ�
	 * @param dayList
	 *            ��¥������ ����ִ� ����Ʈ
	 */
	public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList)
	{
		this.mContext = context;
		this.mDayList = dayList;
		this.mResource = textResource;
		this.mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mDayList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		// update position with the month value
		
//		Calendar dateOfTheView = Calendar.getInstance();
//		
//		FitnessApplication applicationObject = (FitnessApplication)mContext.getApplicationContext();
//		
//		dateOfTheView.set(Calendar.MONTH, applicationObject.getSelectedMonth());
		
		return mDayList.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	
	public LinearLayout getCell()
	{
		return cell;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		DayInfo day = mDayList.get(position);
		
		DayViewHolde dayViewHolder;
		
		TenKApplication applicationObject = (TenKApplication)mContext.getApplicationContext();
		
		Map<Calendar, DailyEffort> mapData = applicationObject.getRecords();
		
		if(convertView == null || convertView.getHeight() == 0)
		{
			int width;
			int height;

			gridviewHeight = parent.getHeight();
			convertView = mLiInflater.inflate(mResource, null);

			if(position % 7 == 6)
				width = getCellWidthDP()+getRestCellWidthDP();
			else
				width = getCellWidthDP();
			
			if(position / 7 == 6)
				height = getCellHeightDP()+getRestCellHeightDP();
			else
				height = getCellHeightDP();

			convertView.setLayoutParams(new GridView.LayoutParams(width, height));	
			
			dayViewHolder = new DayViewHolde();

			dayViewHolder.llBackground = (FrameLayout)convertView.findViewById(R.id.day_cell_ll_background);
			dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.day_cell_tv_day);
			dayViewHolder.iv = (ImageView)convertView.findViewById(R.id.daily_picture);
			
			convertView.setTag(dayViewHolder);
			
//			dayViewHolder.iv.setImageResource(R.drawable.small); //bench press image
			dayViewHolder.tvDay.setText(day.getDay());
		}
		else
		{
			dayViewHolder = (DayViewHolde) convertView.getTag();
		}

		if(day != null)
		{
			if(day.isInMonth())
			{		
				if(position % 7 == 0)
				{
					dayViewHolder.tvDay.setTextColor(Color.RED);
					
				}
				else if(position % 7 == 6)
				{
					dayViewHolder.tvDay.setTextColor(Color.BLUE);
				}
				else
				{
					dayViewHolder.tvDay.setTextColor(Color.BLACK);
				}
				
				if(mapData.containsKey(day.getDate()))
				{
					dayViewHolder.tvDay.setBackgroundColor(Color.GREEN);
				}
			}
			else
			{
				dayViewHolder.tvDay.setTextColor(Color.GRAY);
			}
		}
		
		return convertView;
	}

	public class DayViewHolde
	{
		public FrameLayout llBackground;
		public TextView tvDay;
		
		public ImageView iv;
		
	}

	private int getCellWidthDP()
	{
		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellWidth = width/7;
		
		return cellWidth;
	}
	
	private int getRestCellWidthDP()
	{
		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		int cellWidth = width%7;
		
		return cellWidth;
	}
	
	private int getCellHeightDP()
	{
		int cellHeight = gridviewHeight / 6;

		return cellHeight;
	}
	
	private int getRestCellHeightDP()
	{
		int cellHeight = gridviewHeight % 6;

		return cellHeight;
	}
}