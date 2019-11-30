package com.eap.lifepilot.activities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.database.IDCardTable;
import com.eap.lifepilot.entities.IDCard;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Activity that handles operation to add new ID card
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class AddNewIDActivity extends BaseActivity {

	private TextView txt_header;
	private View headerView;
	
	static final int REQUEST_IMAGE_CAPTURE = 0;

	private ImageView img_new_id_photo;

	private File mCurrentPhotoFile;

	private EditText edt_new_id_name;
	private EditText edt_new_id_description;
	
	private Button btn_new_id_save;
	private Button btn_new_id_cancel;
	
	private ImageLoader imgLoader;
	private DisplayImageOptions options;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_id_card);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		img_new_id_photo = (ImageView) findViewById(R.id.img_new_id_photo);
		
		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		edt_new_id_name = (EditText) findViewById(R.id.edt_new_id_name);
		edt_new_id_description = (EditText) findViewById(R.id.edt_new_id_description);
		
		btn_new_id_save = (Button) findViewById(R.id.btn_new_id_save);
		btn_new_id_cancel = (Button) findViewById(R.id.btn_new_id_cancel);
		
		imgLoader = ImageLoader.getInstance();

		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher).showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true).build();
	}

	@Override
	public void prepareViews() {
		txt_header.setText(R.string.add_id_card);
		dispatchTakePictureIntent();
	}

	@Override
	public void setListeners() {
		btn_new_id_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentPhotoFile.delete();
				finish();
			}
		});
		
		btn_new_id_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IDCard idCard = new IDCard();
				idCard.setName(edt_new_id_name.getText().toString());
				idCard.setDescription(edt_new_id_description.getText().toString());
				idCard.setImageLocation(mCurrentPhotoFile.getAbsolutePath());
				
				IDCardTable idCardTable = new IDCardTable(AddNewIDActivity.this);
				idCardTable.open();
				idCardTable.insert(idCard);
				idCardTable.close();
				
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_IMAGE_CAPTURE) {
			if (resultCode == RESULT_OK) {
				imgLoader.displayImage("file:///" + mCurrentPhotoFile.getAbsolutePath(), img_new_id_photo, options);
			} else {
				mCurrentPhotoFile.delete();
				finish();
			}
		}
	}

	/**
	 * open camera with provided file path where the result image will be provided.
	 */
	private void dispatchTakePictureIntent() {
		try {
			mCurrentPhotoFile = createImageFile();

			if (mCurrentPhotoFile != null) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
				takePictureIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 
	 * @return File a file where the larger size image (photo) for ID card is created
	 * @throws IOException
	 */
	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
		String imageFileName = "PNG_" + timeStamp + "_";
		File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File storageDir = new File(pictureFolder, getString(R.string.app_name));
		storageDir.mkdir();

		File image = File.createTempFile(imageFileName, ".png", storageDir);

		return image;
	}


}
