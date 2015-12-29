package com.sias.znwy.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sias.znwy.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CQSHAdapter extends AbstractBaseAdapter {
	private Context context;
	private String[] m_itemType = { "��·Ѳ��", "�ճ�ά��", "ά���ƻ�" };
	private String[] m_itemState = { "Ѳ��", "��վ", "��ҵ", "����", "�¼�" };
	private String[] m_itemParent = { "����", "����" };

	public CQSHAdapter(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapyer_cqsh, null);
			holder.itemDate = (TextView) convertView.findViewById(R.id.itemDate);
			holder.itemName = (TextView) convertView.findViewById(R.id.itemName);
			holder.itemParent = (Spinner) convertView.findViewById(R.id.itemParent);
			holder.itemSource = (TextView) convertView.findViewById(R.id.itemSource);
			holder.itemState = (Spinner) convertView.findViewById(R.id.itemState);
			holder.itemTime = (TextView) convertView.findViewById(R.id.itemTime);
			holder.itemType = (Spinner) convertView.findViewById(R.id.itemType);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, m_itemParent);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemParent.setAdapter(adapter);
		ArrayAdapter<String> itemTypeAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, m_itemType);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemType.setAdapter(itemTypeAdapter);
		List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < m_itemType.length; i++) {
			Map<String, Object> listem = new HashMap<String, Object>();
			listem.put("name", m_itemState[i]);
			listems.add(listem);
		}

		ArrayAdapter<String> itemStateAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, m_itemState);
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
