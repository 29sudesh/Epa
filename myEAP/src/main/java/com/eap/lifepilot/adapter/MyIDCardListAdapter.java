package com.eap.lifepilot.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.IDCardImageActivity;
import com.eap.lifepilot.database.IDCardTable;
import com.eap.lifepilot.entities.IDCard;
import com.eap.lifepilot.utils.EAPConstants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class MyIDCardListAdapter extends BaseAdapter {

	private Activity mActivity;
	private ArrayList<IDCard> mIDCards;
	private ImageLoader imgLoader;
	private DisplayImageOptions options;

	public MyIDCardListAdapter(Activity activity, ArrayList<IDCard> idCards) {
		mIDCards = idCards;
		mActivity = activity;

		imgLoader = ImageLoader.getInstance();

		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher).showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(20)).considerExifParams(true).build();
		
	}

	@Override
	public int getCount() {
		return mIDCards.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final IDCard selectedIDCard = mIDCards.get(position);

		LayoutInflater inflater = LayoutInflater.from(mActivity);
		convertView = inflater.inflate(R.layout.list_item_id_card, parent, false);

		ViewHolder viewHolder = new ViewHolder();
		viewHolder.rlt_list_item_id_card = (RelativeLayout) convertView.findViewById(R.id.rlt_list_item_id_card);
		viewHolder.txt_list_item_id_card_name = (TextView) convertView.findViewById(R.id.txt_list_item_id_card_name);
		viewHolder.txt_list_item_id_card_desc = (TextView) convertView.findViewById(R.id.txt_list_item_id_card_desc);
		viewHolder.img_list_item_id_card = (ImageView) convertView.findViewById(R.id.img_list_item_id_card);
		viewHolder.img_list_item_id_card_delete = (ImageView) convertView.findViewById(R.id.img_list_item_id_card_delete);

		viewHolder.txt_list_item_id_card_name.setText(selectedIDCard.getName());
		viewHolder.txt_list_item_id_card_desc.setText(selectedIDCard.getDescription());
		// viewHolder.img_list_item_id_card.setImageBitmap(selectedIDCard.getImage());

		imgLoader.displayImage("file:///" + selectedIDCard.getImageLocation(), viewHolder.img_list_item_id_card, options);
		
		viewHolder.rlt_list_item_id_card.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity, IDCardImageActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, selectedIDCard.getName());
				intent.putExtra(EAPConstants.INTENT_KEY_FILE_URL, selectedIDCard.getImageLocation());
				mActivity.startActivity(intent);
			}
		});
		
		viewHolder.img_list_item_id_card_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IDCardTable idCardTable = new IDCardTable(mActivity);
				idCardTable.open();
				idCardTable.delete(selectedIDCard);
				idCardTable.close();
				
				mIDCards.remove(position);
				notifyDataSetChanged();
			}
		});

		convertView.setTag(viewHolder);

		return convertView;
	}

	private class ViewHolder {
		RelativeLayout rlt_list_item_id_card;
		ImageView img_list_item_id_card;
		ImageView img_list_item_id_card_delete;
		TextView txt_list_item_id_card_name;
		TextView txt_list_item_id_card_desc;
	}
}
