package com.sias.znwy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sias.znwy.R;
import com.sias.znwy.demo.CQSBBean;

public class CQSHAdapter extends AbstractBaseAdapter {
	private Context context;
	private String[] m_itemType = { "上路巡查", "日常维护", "维护计划" };
	private String[] m_itemState = { "巡查", "旁站", "内业", "病假", "事假" };
	private String[] m_itemParent = { "上行", "下行" };

	public CQSHAdapter(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CQSBBean bean = (CQSBBean) datas.get(position);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapyer_cqshtest, null);
			holder.itemStartTime = (TextView) convertView.findViewById(R.id.itemStartTime);
			holder.itemName = (TextView) convertView.findViewById(R.id.itemName);
			holder.itemParent = (Spinner) convertView.findViewById(R.id.itemParent);
			holder.itemSource = (TextView) convertView.findViewById(R.id.itemSource);
			holder.itemState = (Spinner) convertView.findViewById(R.id.itemState);
			holder.itemEndTime = (TextView) convertView.findViewById(R.id.itemEndTime);
			holder.itemType = (Spinner) convertView.findViewById(R.id.itemType);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.itemStartTime.setText(bean.getCqkssj());
		holder.itemEndTime.setText(bean.getCqjssj());
		holder.itemSource.setText(bean.getCqryhdh());
		holder.itemName.setText(bean.getCqrxm());
		bean.getCqqk();

		// holder.itemState.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// ListView areaRadioListView;
		// RadioOnClick radioOnClick = new RadioOnClick(1, holder.itemState,
		// m_itemState);
		// AlertDialog ad = new
		// AlertDialog.Builder(context).setSingleChoiceItems(m_itemState,
		// radioOnClick.getIndex(), radioOnClick)
		// .create();
		// ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// areaRadioListView = ad.getListView();
		// ad.show();
		//
		// }
		// });
		// holder.itemType.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// ListView areaRadioListView;
		// RadioOnClick radioOnClick = new RadioOnClick(1, holder.itemState,
		// m_itemState);
		// AlertDialog ad = new
		// AlertDialog.Builder(context).setSingleChoiceItems(m_itemType,
		// radioOnClick.getIndex(), radioOnClick)
		// .create();
		// ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// areaRadioListView = ad.getListView();
		// ad.show();
		//
		// }
		// });
		// holder.itemParent.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// ListView areaRadioListView;
		// RadioOnClick radioOnClick = new RadioOnClick(1, holder.itemState,
		// m_itemParent);
		// AlertDialog ad = new
		// AlertDialog.Builder(context).setSingleChoiceItems(m_itemParent,
		// radioOnClick.getIndex(), radioOnClick)
		// .create();
		// ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// areaRadioListView = ad.getListView();
		// ad.show();
		//
		// }
		// });
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, m_itemParent);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemParent.setAdapter(adapter);
		ArrayAdapter<String> itemTypeAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, m_itemType);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		holder.itemType.setAdapter(itemTypeAdapter);
		// List<Map<String, Object>> listems = new ArrayList<Map<String,
		// Object>>();
		// for (int i = 0; i < m_itemType.length; i++) {
		// Map<String, Object> listem = new HashMap<String, Object>();
		// listem.put("name", m_itemState[i]);
		// listems.add(listem);
		// }

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
		TextView itemStartTime;
		TextView itemEndTime;
		Spinner itemState;
		Spinner itemType;
		Spinner itemParent;
		// TextView itemState;
		// TextView itemType;
		// TextView itemParent;
	}

	class RadioOnClick implements DialogInterface.OnClickListener {
		private TextView v;
		private int index;
		private String areas[];

		public RadioOnClick(int index, TextView v, String[] areas) {
			this.index = index;
			this.v = v;
			this.areas = areas;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void onClick(DialogInterface dialog, int whichButton) {
			setIndex(whichButton);
			Toast.makeText(context, "您已经选择了： " + index + ":" + areas[index], Toast.LENGTH_LONG).show();
			dialog.dismiss();
		}
	}
}
