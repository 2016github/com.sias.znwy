package com.sias.znwy.adapter;

import com.sias.znwy.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

public class ChooseDialogAdapter extends BaseAdapter {
	private Context context;
	private String[] mDatas;

	public ChooseDialogAdapter(Context context, String data[]) {
		this.context = context;
		this.mDatas = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = View.inflate(context, R.layout.spinner_item, null);
		RadioButton item = (RadioButton) convertView.findViewById(R.id.spinnerItem);
		item.setText(mDatas[position]);
		return convertView;
	}
}
