package com.eap.lifepilot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.eap.lifepilot.activities.HomeActivity;
import com.eap.lifepilot.fragments.ContactUsFragment;
import com.eap.lifepilot.utils.EAPConstants;

/**
 * BaseActivity is the base activity for all activities to provide
 * better architecture
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 * 
 */
public abstract class BaseActivity extends FragmentActivity {

	/**
	 * A method to initialize view, variables etc. in all activities
	 */
	public abstract void initComponents();

	/**
	 * A method to preset values in views, variables etc. in all activities
	 */
	public abstract void prepareViews();

	/**
	 * A method that should contain all types of listeners in the activity
	 */
	public abstract void setListeners();
	
	/**
	 * Assigns common functions to the activities with same footer
	 * @param activity context required for various operations
	 * @param view Footer view created in an activity 
	 */
	static FragmentTransaction fragmentTransaction;
	public static void setFooter(final Activity activity, View view) {

		ImageView img_footer_home = (ImageView) view.findViewById(R.id.img_footer_home);
		ImageView img_footer_contact_us = (ImageView) view.findViewById(R.id.img_footer_contact_us);
		ImageView img_footer_send_message = (ImageView) view.findViewById(R.id.img_footer_send_message);
		ImageView img_footer_call_us = (ImageView) view.findViewById(R.id.img_footer_call_us);

		img_footer_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activity instanceof HomeActivity) {
					HomeActivity homeActivity = (HomeActivity) activity;
					homeActivity.loadSelectedFragment(525);
					return;
				}
				Intent intent = new Intent(activity, HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(intent);
			}
		});

		img_footer_contact_us.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto",activity.getString(R.string.contact_us_prefilled_email), null));
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "");
				activity.startActivity(Intent.createChooser(emailIntent, "Send email..."));
//				Intent intent = new Intent(activity,ContactUsFragment.class);
//				activity.startActivity(intent);
//				if (activity instanceof HomeActivity) {
//					ContactUsFragment contactUsFragment = new ContactUsFragment();
//					fragmentTransaction = ((HomeActivity) activity).getSupportFragmentManager().beginTransaction();
//					fragmentTransaction.replace(R.id.lnr_activity_base, contactUsFragment);
//					fragmentTransaction.addToBackStack(null);
//					fragmentTransaction.commit();
//					return;
//				}


//				if (activity instanceof HomeActivity) {
//					HomeActivity homeActivity = (HomeActivity) activity;
//					homeActivity.loadSelectedFragment(R.id.rdb_menu_contact_us);
//					return;
//				}
//
//				Intent intent = new Intent(activity, HomeActivity.class);
//				intent.putExtra(EAPConstants.INTENT_KEY_SELECTED_FRAGMENT, R.id.rdb_menu_contact_us);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//				activity.startActivity(intent);
			}
		});

		img_footer_send_message.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + activity.getString(R.string.eap_contact_sms)));
				Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
				smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
				smsIntent.setType("vnd.android-dir/mms-sms");
				smsIntent.setData(Uri.parse("sms:" + activity.getString(R.string.sms_number)));
				activity.startActivity(smsIntent);
			}
		});

		img_footer_call_us.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + activity.getString(R.string.eap_contact_no)));
				activity.startActivity(intent);
			}
		});
	}

	public static Bitmap decodeFile(File f) {
		try {

			ExifInterface exif = null;
			try {
				exif = new ExifInterface(f.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			int angle = 0;

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				angle = 90;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
				angle = 180;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				angle = 270;
			}

			Matrix mat = new Matrix();
			mat.postRotate(angle);

			// Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 800;

			// Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
				scale *= 2;

			// Decode with inSampleSize
			// int scale = 1;
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			// o2.inSampleSize = 1;

			Bitmap bmp1 = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
			Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, 0, bmp1.getWidth(), bmp1.getHeight(), mat, true);

			return bmp2;
		} catch (FileNotFoundException e) {
			Log.e("", "" + e);
		}
		return null;
	}
	
	public static void showValidationDialog(Activity activity, String message) {
	
		if(message.equals("")) {
			return;
		}
		Toast.makeText(activity,message,Toast.LENGTH_LONG).show();

//		AlertDialog.Builder alertDialogVar = new AlertDialog.Builder(activity);
//		alertDialogVar.setIcon(android.R.drawable.ic_dialog_alert).setCancelable(false)
//				.setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		AlertDialog alertDialog = alertDialogVar.create();
//		alertDialog.show();
//		AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.TextAppearance_Theme_Dialog);
//		builder.setIcon(android.R.drawable.ic_dialog_alert).setCancelable(false)
//				.setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		builder.show();
	}
	
	public static String getExceptionMessageFromCode(Activity activity, String exceptionCode) {
		String message = "";
		
		if(exceptionCode.equals("0")) {
			message = activity.getString(R.string.exception_0);
		} 
		else if(exceptionCode.equals("1")) {
			message = activity.getString(R.string.exception_1);
		}
		else if(exceptionCode.equals("2")) {
			message = activity.getString(R.string.exception_2);
		}
		else if(exceptionCode.equals("3")) {
			message = activity.getString(R.string.exception_3);
		}
		else if(exceptionCode.equals("4")) {
			message = activity.getString(R.string.exception_4);
		}
		else if(exceptionCode.equals("5")) {
			message = activity.getString(R.string.exception_5);
		}
		else if(exceptionCode.equals("6")) {
			message = activity.getString(R.string.exception_6);
		}
		else if(exceptionCode.equals("7")) {
			message = activity.getString(R.string.exception_7);
		} 
		else if(exceptionCode.equals("8")) {
			message = activity.getString(R.string.exception_8);
		} 
		else if(exceptionCode.equals("11")) {
			message = activity.getString(R.string.exception_11);
		}
		
		return message;
	}

}
