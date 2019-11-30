package com.eap.lifepilot.adapter;

/**
 * Created by Sudesh Bishnoi on 01-Oct-19.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.eap.lifepilot.R;
import com.eap.lifepilot.custom_views.onCheckedChangeListener;
import com.eap.lifepilot.entities.Quiz;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Quiz> mQuizList;
    private onCheckedChangeListener mCheckedListener;

    public CustomPagerAdapter(Context context,ArrayList<Quiz> quizList,onCheckedChangeListener checkedListener) {
        mContext = context;
        mQuizList  = quizList;
        mCheckedListener = checkedListener;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        Quiz modelObject = mQuizList.get(position);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_quiz, null, false);
        collection.addView(view);
        TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
        TextView tv_questions = (TextView) view.findViewById(R.id.tv_questions);
        LinearLayout ll_options = view.findViewById(R.id.ll_options);
        tv_number.setText(""+(position+1));
        tv_questions.setText(modelObject.getQuestion());

        addRadioButton(modelObject,ll_options);
        view.setTag(position);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addRadioButton(Quiz modelObject, LinearLayout ll_options) {
        int disableColor = mContext.getResources().getColor(R.color.grey_light);
        int enableColor = mContext.getResources().getColor(R.color.light_blue);

        final RadioButton[] rb = new RadioButton[modelObject.getOptions().size()];
        RadioGroup rg = new RadioGroup(mContext); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<modelObject.getOptions().size(); i++){
            rb[i]  = new RadioButton(mContext);
            rb[i].setText(" " + modelObject.getOptions().get(i).getDescription().toString());
            rb[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            rb[i].setId(i);
            rb[i].setTextColor(enableColor);
            rb[i].setTextSize(20);
            if (modelObject.getAnswer() == i){
                rb[i].setChecked(true);
            }
            rg.addView(rb[i]);
        }
        ll_options.addView(rg);//you add the whole RadioGroup to the layout

        //set listener to radio button group
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                mCheckedListener.onCheckedChangeListener(checkedRadioButtonId);
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount() {
        return mQuizList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void updateList(ArrayList<Quiz> updatedList){
        mQuizList = updatedList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Quiz customPagerEnum = mQuizList.get(position);
        return customPagerEnum.getQuestion();
    }
}
