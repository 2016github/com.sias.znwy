package com.sias.znwy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * Adapter »ùÀà
 * 
 * 
 */
public abstract class AbstractBaseAdapter extends BaseAdapter {

	protected List datas;

	protected Context mContext;

	public AbstractBaseAdapter(Context context) {
		this.mContext = context;
		this.datas = new ArrayList();
	}

	public void setDatas(List datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	public void appendDatas(List datas) {
		if (this.datas != null) {
			this.datas.addAll(datas);
		} else {
			this.datas = datas;
		}
		notifyDataSetChanged();
	}

	public void appendData(Object data) {
		if (this.datas != null) {
			this.datas.add(data);
		} else {
			this.datas = new ArrayList();
			this.datas.add(data);
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {

		return this.datas != null && this.datas.size() > 0 ? this.datas.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return this.datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
