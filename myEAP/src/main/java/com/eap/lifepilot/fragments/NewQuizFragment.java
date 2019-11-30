package com.eap.lifepilot.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.LifePilotQuizResultActivity;
import com.eap.lifepilot.activities.QuizActivity;
import com.eap.lifepilot.activities.QuizResultActivity;
import com.eap.lifepilot.adapter.CustomPagerAdapter;
import com.eap.lifepilot.custom_views.onCheckedChangeListener;
import com.eap.lifepilot.entities.Option;
import com.eap.lifepilot.entities.Quiz;
import com.eap.lifepilot.utils.EAPConstants;

import java.util.ArrayList;

public class NewQuizFragment extends BaseFragment implements onCheckedChangeListener, ViewPager.OnPageChangeListener {

    private View view;
    private QuizActivity quizActivity;
    private TextView txt_quiz_question_counter, tv_answer;
    private ViewPager viewPager;
    private CustomPagerAdapter mCustomPagerAdapter;
    private Quiz selectedQuiz;
    private ArrayList<Option> options;
    private int questionNo;
    private int currentScore;
    private static final float thresholdOffset = 0.5f;
    private boolean scrollStarted, checkDirection;
    private boolean isLAstPosition = false;
    private Boolean isMoving = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz_new, container, false);
        initComponents();
        prepareViews();
        setListeners();

        return view;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void initComponents() {
        quizActivity = (QuizActivity) getActivity();
        try {
            questionNo = getArguments().getInt(EAPConstants.INTENT_KEY_QUESTION_NO);
            currentScore = getArguments().getInt(EAPConstants.INTENT_KEY_SCORE);
        } catch (Exception e) {
            questionNo = 0;
        }
        selectedQuiz = quizActivity.quizData.get(questionNo);
        options = selectedQuiz.getOptions();
        dotsCount = quizActivity.quizData.size();
        tv_answer = view.findViewById(R.id.tv_answer);

        viewPager = view.findViewById(R.id.viewPager);
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity(), quizActivity.quizData, this);
        viewPager.setAdapter(mCustomPagerAdapter);
        viewPager.setOnPageChangeListener(this);

        txt_quiz_question_counter = (TextView) view.findViewById(R.id.txt_quiz_question_counter);

        linearLayout = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawPageSelectionIndicators(0);
                txt_quiz_question_counter.setText(String.format(getString(R.string.question_number_of_total), (0 + 1), quizActivity.quizData.size()));
            }
        }, 1000);


    }

    @Override
    public void onCheckedChangeListener(final int checkedId) {
        if (quizActivity.quizData.get(questionNo).getAnswer()<0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isMoving = true;
                    quizActivity.quizData.get(questionNo).setAnswer(checkedId);
                    conditionsAppliedOnRadioButtonSelectionChanged(checkedId);
                    tv_answer.setVisibility(View.GONE);
                    viewPager.getAdapter().notifyDataSetChanged();
                    if (questionNo < quizActivity.quizData.size() - 1) {
                        conditionsAppliedOnNextButtonPressed(questionNo + 1, checkedId);
                    } else {
                        countScore();
                    }
                }
            },250);

        }
        else {
            isMoving = false;
            quizActivity.quizData.get(questionNo).setAnswer(checkedId);
            conditionsAppliedOnRadioButtonSelectionChanged(checkedId);
            tv_answer.setVisibility(View.GONE);
            viewPager.getAdapter().notifyDataSetChanged();
        }
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void prepareViews() {
        drawPageSelectionIndicators(0);
        txt_quiz_question_counter.setText(String.format(getString(R.string.question_number_of_total), (0 + 1), quizActivity.quizData.size()));
    }

    @Override
    public void setListeners() {

    }

    private int getSelectedAnswerWeight(int index) {
        return options.get(index).getWeight();
    }

    private void finishQuizAndShowResult(int newScore) {
        if (quizActivity.quizType == EAPConstants.QUIZ_LIFE_PILOT) {
            Intent intent = new Intent(getActivity(), LifePilotQuizResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(EAPConstants.INTENT_KEY_SCORE, newScore);
            intent.putExtra(EAPConstants.INTENT_KEY_LIFE_PILOT_ANSWERS, quizActivity.lifePilotAnswers);
            intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, quizActivity.quizType);
//            getActivity().overridePendingTransition(R.anim.slide_in_left,
//                    R.anim.slide_out_left);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), QuizResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(EAPConstants.INTENT_KEY_SCORE, newScore);
            intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, quizActivity.quizType);
//            getActivity().overridePendingTransition(R.anim.slide_in_left,
//                    R.anim.slide_out_left);
            startActivity(intent);
        }


    }

    private void conditionsAppliedOnNextButtonPressed(int nextQuestionNumber, int checkedId) {
        switch (quizActivity.quizType) {
            case EAPConstants.QUIZ_DEPRESSION:

                break;

            case EAPConstants.QUIZ_RELATIONSHIP:
                break;

            case EAPConstants.QUIZ_LIFE_PILOT:


                break;
            case EAPConstants.QUIZ_ALCOHOL:
                if (questionNo == 0 && checkedId == 0) {
                    nextQuestionNumber = 8;
                    break;
                }
                if (questionNo == 1) {
                    if (checkedId == 0) {
                        quizActivity.QUIZ_ALCOHOL_QUESTION_2_SCORE = 1;
                    } else {
                        quizActivity.QUIZ_ALCOHOL_QUESTION_2_SCORE = 0;
                    }
                    break;
                }

                if (questionNo == 2) {
                    if (checkedId == 0) {
                        quizActivity.QUIZ_ALCOHOL_QUESTION_3_SCORE = 1;
                    } else {
                        quizActivity.QUIZ_ALCOHOL_QUESTION_3_SCORE = 0;
                    }
                    if ((quizActivity.QUIZ_ALCOHOL_QUESTION_2_SCORE + quizActivity.QUIZ_ALCOHOL_QUESTION_3_SCORE) == 0) {
                        nextQuestionNumber = 8;
                    }
                }
                break;

            default:

                break;
        }
        questionNo = nextQuestionNumber;
        viewPager.setCurrentItem(nextQuestionNumber);
//		quizActivity.loadQuizFragment(nextQuestionNumber, newScore);
    }

    private void conditionsAppliedOnRadioButtonSelectionChanged(int checkId) {
        switch (quizActivity.quizType) {
            case EAPConstants.QUIZ_DEPRESSION:
                if (questionNo == 8 && checkId != 0) {
                    showDepressionDialog();
                }
                break;

            case EAPConstants.QUIZ_RELATIONSHIP:
                break;

            case EAPConstants.QUIZ_LIFE_PILOT:
                if (checkId == 0) {
                    quizActivity.lifePilotAnswers[questionNo] = "1";
                } else {
                    quizActivity.lifePilotAnswers[questionNo] = "0";
                }
                break;
            case EAPConstants.QUIZ_ALCOHOL:
                break;

            default:
                break;
        }
    }


    private void showDepressionDialog() {
        Bundle bundle = new Bundle();
        bundle.putString(EAPConstants.INTENT_KEY_DIALOG_CONTENT, getString(R.string.depression_dialog));
        DepressionAlertDialogFragment alertDialogFragment = new DepressionAlertDialogFragment();
        alertDialogFragment.setArguments(bundle);
        alertDialogFragment.show(getChildFragmentManager(), "Depression Dialog");
    }
    /////////

    private int dotsCount = 18;    //No of tabs or images
    private ImageView[] dots;
    LinearLayout linearLayout;

    private void drawPageSelectionIndicators(int mPosition) {
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
//		linearLayout=(LinearLayout)findViewById(R.id.viewPagerCountDots);
        dotsCount = dotsCount + 1;
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity().getApplicationContext());
            if (i == mPosition)
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
            else
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            linearLayout.addView(dots[i], params);
        }
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onPageScrolled(int i, float v, int i1) {
        if (v == 0.0 && i == quizActivity.quizData.size() - 1) {
            if (isLAstPosition) {
                if (quizActivity.quizData.get(questionNo).getAnswer() >= 0) {
                    if (isMoving) {
                        countScore();
                    }
                    if (tv_answer.getVisibility() == View.VISIBLE){

                    }else {
                        isMoving =true;
                    }
                } else {
                    tv_answer.setVisibility(View.VISIBLE);
                }
                return;
            } else {
                isLAstPosition = true;
            }
        }
//        Log.e("onPagesCROLLED : ", " pos : " + i + "   i1 : " + i1);
        questionNo = i;
        selectedQuiz = quizActivity.quizData.get(questionNo);
        options = selectedQuiz.getOptions();
        dotsCount = quizActivity.quizData.size();
        drawPageSelectionIndicators(i);
        txt_quiz_question_counter.setText(String.format(getString(R.string.question_number_of_total), (i + 1), quizActivity.quizData.size()));
    }

    private void countScore() {
        int newScore = 0;
        for (int j = 0; j < quizActivity.quizData.size(); j++) {
            Log.e("Answer : ",quizActivity.quizData.get(j).getAnswer()+"");
            if (quizActivity.quizData.get(j).getAnswer() > 0) {
                newScore = newScore + quizActivity.quizData.get(j).getAnswer();
            }
            if (j == quizActivity.quizData.size()-1) {
                finishQuizAndShowResult(newScore);
            }
            Log.e("Size checking : ",j+"  "+quizActivity.quizData.size() +"' ");
        }

    }

    @Override
    public void onPageSelected(int i) {
        if (i < questionNo) {
            // handle swipe LEFT
            isLAstPosition = false;
            tv_answer.setVisibility(View.GONE);
            questionNo = i;
        } else if (i > questionNo) {
            // handle swipe RIGHT
            if (quizActivity.quizData.get(questionNo).getAnswer() < 0) {
                viewPager.setCurrentItem(questionNo);
                tv_answer.setVisibility(View.VISIBLE);
            } else {

            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
