package com.eap.lifepilot.data;

import java.util.ArrayList;

import android.content.Context;

import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.MyEAPService;

public class MyEAPServicesData {

	private ArrayList<MyEAPService> myEAPServices;
	
	public MyEAPServicesData(Context context) {
		myEAPServices = new ArrayList<MyEAPService>();
		
		MyEAPService myEAPService1 = new MyEAPService();
		myEAPService1.setHeading(context.getString(R.string.counseling_session));
		myEAPService1.setFileName("file:///android_asset/MyEapServices/Counseling.html");
		myEAPService1.setDescription(context.getString(R.string.counseling_desc));
		myEAPService1.setImageResource(R.drawable.service_counselling);
		myEAPService1.setDetailImageResource(R.drawable.service_detail_counselling);
		myEAPService1.setDetail(context.getString(R.string.service_detail_counseling));
		
		MyEAPService myEAPService2 = new MyEAPService();
		myEAPService2.setHeading(context.getString(R.string.crisis_support));
		myEAPService2.setFileName("file:///android_asset/MyEapServices/Financial.html");
		myEAPService2.setDescription(context.getString(R.string.crisis_desc));
		myEAPService2.setImageResource(R.drawable.service_support);
		myEAPService2.setDetailImageResource(R.drawable.service_detail_crisis_support);
		myEAPService2.setDetail(context.getString(R.string.service_detail_crisis_support));
		
		MyEAPService myEAPService3 = new MyEAPService();
		myEAPService3.setHeading(context.getString(R.string.legal_consultations));
		myEAPService3.setFileName("file:///android_asset/MyEapServices/Legal.html");
		myEAPService3.setDescription(context.getString(R.string.legal_desc));
		myEAPService3.setImageResource(R.drawable.service_legal);
		myEAPService3.setDetailImageResource(R.drawable.service_detail_legal);
		myEAPService3.setDetail(context.getString(R.string.service_detail_legal));
		
		MyEAPService myEAPService4 = new MyEAPService();
		myEAPService4.setHeading(context.getString(R.string.financial_coaching));
		myEAPService4.setFileName("file:///android_asset/MyEapServices/Financial.html");
		myEAPService4.setDescription(context.getString(R.string.financial_desc));
		myEAPService4.setImageResource(R.drawable.service_financial);
		myEAPService4.setDetailImageResource(R.drawable.service_detail_financial);
		myEAPService4.setDetail(context.getString(R.string.service_detail_financial));
		
		MyEAPService myEAPService5 = new MyEAPService();
		myEAPService5.setHeading(context.getString(R.string.child_Care));
		myEAPService5.setFileName("file:///android_asset/MyEapServices/ChildCare.html");
		myEAPService5.setDescription(context.getString(R.string.childcare_desc));
		myEAPService5.setImageResource(R.drawable.service_childcare);
		myEAPService5.setDetailImageResource(R.drawable.service_detail_child_care);
		myEAPService5.setDetail(context.getString(R.string.service_detail_child_care));
		
		MyEAPService myEAPService6 = new MyEAPService();
		myEAPService6.setHeading(context.getString(R.string.elder_Care));
		myEAPService6.setFileName("file:///android_asset/MyEapServices/ElderCare.html");
		myEAPService6.setDescription(context.getString(R.string.elder_desc));
		myEAPService6.setImageResource(R.drawable.service_eldercare);
		myEAPService6.setDetailImageResource(R.drawable.service_detail_elder_care);
		myEAPService6.setDetail(context.getString(R.string.service_detail_elder_care));
		
		MyEAPService myEAPService7 = new MyEAPService();
		myEAPService7.setHeading(context.getString(R.string.identity_theft_services));
		myEAPService7.setFileName("file:///android_asset/MyEapServices/ResourceRetrieval.html");
		myEAPService7.setDescription(context.getString(R.string.identity_desc));
		myEAPService7.setImageResource(R.drawable.service_identity_theft);
		myEAPService7.setDetailImageResource(R.drawable.service_detail_identity_theft);
		myEAPService7.setDetail(context.getString(R.string.service_detail_identity_theft));
		
		MyEAPService myEAPService8 = new MyEAPService();
		myEAPService8.setHeading(context.getString(R.string.resource_retrieval));
		myEAPService8.setFileName("file:///android_asset/MyEapServices/ResourceRetrieval.html");
		myEAPService8.setDescription(context.getString(R.string.resource_desc));
		myEAPService8.setImageResource(R.drawable.service_resource_retrieval);
		myEAPService8.setDetailImageResource(R.drawable.service_detail_resource_retrieval);
		myEAPService8.setDetail(context.getString(R.string.service_detail_resource_retrieval));
		
		MyEAPService myEAPService9 = new MyEAPService();
		myEAPService9.setHeading(context.getString(R.string.homeownership_program));
		myEAPService9.setFileName("file:///android_asset/MyEapServices/ResourceRetrieval.html");
		myEAPService9.setDescription(context.getString(R.string.home_desc));
		myEAPService9.setImageResource(R.drawable.service_home_ownership);
		myEAPService9.setDetailImageResource(R.drawable.service_detail_homw_ownership);
		myEAPService9.setDetail(context.getString(R.string.service_detail_home_ownership));
		
		MyEAPService myEAPService10 = new MyEAPService();
		myEAPService10.setHeading(context.getString(R.string.personal_wellness));
		myEAPService10.setFileName("file:///android_asset/MyEapServices/ResourceRetrieval.html");
		myEAPService10.setDescription(context.getString(R.string.wellness_desc));
		myEAPService10.setImageResource(R.drawable.service_personal_wellness);
		myEAPService10.setDetailImageResource(R.drawable.service_detail_personal_wellness);
		myEAPService10.setDetail(context.getString(R.string.service_detail_personal_wellness));
		
		myEAPServices.add(myEAPService1);
		myEAPServices.add(myEAPService2);
		myEAPServices.add(myEAPService3);
		myEAPServices.add(myEAPService4);
		myEAPServices.add(myEAPService5);
		myEAPServices.add(myEAPService6);
		myEAPServices.add(myEAPService7);
		myEAPServices.add(myEAPService8);
		myEAPServices.add(myEAPService9);
		myEAPServices.add(myEAPService10);
	}
	
	public ArrayList<MyEAPService> getMyEAPServices() {
		return myEAPServices;
	}
	
}
