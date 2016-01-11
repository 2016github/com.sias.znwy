package com.sias.znwy.adapter;

import com.sias.znwy.R;
import com.sias.znwy.adapter.CQSHAdapter.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TextAdapter extends BaseAdapter {
	private Context context;
	private String[] m_itemType = { "上路巡查", "日常维护", "维护计划" };
	private String[] m_itemState = { "巡查", "旁站", "内业", "病假", "事假" };
	private String[] m_itemParent = { "上行", "下行" };

	public TextAdapter(Context context) {

		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.adapyer_cqsh, null);
			holder.itemDate = (TextView) convertView.findViewById(R.id.itemStartTime);
			holder.itemName = (TextView) convertView.findViewById(R.id.itemName);
			holder.itemParent = (Spinner) convertView.findViewById(R.id.itemParent);
			holder.itemSource = (TextView) convertView.findViewById(R.id.itemSource);
			holder.itemState = (Spinner) convertView.findViewById(R.id.itemState);
			holder.itemTime = (TextView) convertView.findViewById(R.id.itemEndTime);
			holder.itemType = (Spinner) convertView.findViewById(R.id.itemType);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, m_itemParent);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemParent.setAdapter(adapter);
		ArrayAdapter<String> itemTypeAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, m_itemType);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemType.setAdapter(itemTypeAdapter);
		ArrayAdapter<String> itemStateAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, m_itemState);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemState.setAdapter(itemStateAdapter);

		holder.itemParent.setSelection(1, true);
		holder.itemType.setSelection(1, true);
		holder.itemState.setSelection(1, true);
		holder.itemType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				parent.setVisibility(View.VISIBLE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		holder.itemState.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				parent.setVisibility(View.VISIBLE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		holder.itemParent.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				parent.setVisibility(View.VISIBLE);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView itemSource;
		TextView itemName;
		TextView itemDate;
		TextView itemTime;
		Spinner itemState;
		Spinner itemType;
		Spinner itemParent;
	}
}
