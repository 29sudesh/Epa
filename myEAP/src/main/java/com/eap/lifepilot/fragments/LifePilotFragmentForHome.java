package com.eap.lifepilot.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.QuizActivity;
import com.eap.lifepilot.utils.EAPConstants;

public class LifePilotFragmentForHome extends BaseFragment {

	private TextView txt_quiz_intro_expanded_direction;
	private TextView txt_quiz_intro_direction;
	private TextView txt_quiz_intro_instruction;

	private Button btn_quiz_intro_take_quiz;

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home_life_pilot, container, false);

		initComponents();
		prepareViews();
		setListeners();

		return view;
	}

	@Override
	public void initComponents() {

		txt_quiz_intro_direction = (TextView) view.findViewById(R.id.txt_quiz_intro_direction);
		txt_quiz_intro_instruction = (TextView) view.findViewById(R.id.txt_quiz_intro_instruction);
		txt_quiz_intro_expanded_direction = (TextView) view.findViewById(R.id.txt_quiz_intro_expanded_direction);

		btn_quiz_intro_take_quiz = (Button) view.findViewById(R.id.btn_quiz_intro_take_quiz);

	}

	@Override
	public void prepareViews() {
		txt_quiz_intro_direction.setText(Html.fromHtml(getString(R.string.life_pilot_directions)));
		txt_quiz_intro_instruction.setText(Html.fromHtml(getString(R.string.life_pilot_instruction)));
		txt_quiz_intro_expanded_direction.setText(Html.fromHtml(getString(R.string.life_pilot_expanded_directions)));

	}

	@Override
	public void setListeners() {
		btn_quiz_intro_take_quiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), QuizActivity.class);
				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.life_pilot));
				startActivity(intent);
			}
		});
	}

}
