package com.eap.lifepilot.activities;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.adapter.MyIDCardListAdapter;
import com.eap.lifepilot.database.IDCardTable;
import com.eap.lifepilot.entities.IDCard;

public class MyIDCardListActivity extends BaseActivity{

	private TextView txt_header;
	
	private View headerView;
	private View footerView;
	
	private Button btn_my_id_card;
	
	private ListView lst_my_id_card;
	
	private MyIDCardListAdapter myIDCardListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_id_card);
		
		initComponents();
		prepareViews();
		setListeners();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		new GetIDCardAsync().execute();
	}
	
	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		btn_my_id_card = (Button) findViewById(R.id.btn_my_id_card);
		
		lst_my_id_card = (ListView) findViewById(R.id.lst_my_id_card);
	}

	@Override
	public void prepareViews() {
		txt_header.setText(R.string.my_id_card);
		
		setFooter(this, footerView);
	}

	@Override
	public void setListeners() {
		btn_my_id_card.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyIDCardListActivity.this, AddNewIDActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private class GetIDCardAsync extends AsyncTask<Void, Void, Void>  {

		private ProgressDialog progressDialog;
		private ArrayList<IDCard> idCards;
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(MyIDCardListActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			IDCardTable idCardTable = new IDCardTable(MyIDCardListActivity.this);
			idCardTable.open();
			idCards = idCardTable.getIDCards();
			idCardTable.close();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			try {
				progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			myIDCardListAdapter = new MyIDCardListAdapter(MyIDCardListActivity.this, idCards);
			lst_my_id_card.setAdapter(myIDCardListAdapter);
		}
		
	}

}
