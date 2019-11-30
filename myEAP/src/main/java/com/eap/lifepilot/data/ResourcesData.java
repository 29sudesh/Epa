package com.eap.lifepilot.data;

import java.util.ArrayList;

import android.content.Context;

import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.Component;
import com.eap.lifepilot.entities.Resource;

public class ResourcesData {

	private ArrayList<Resource> resources;
	
	public ResourcesData(Context context) {
		resources = new ArrayList<Resource>();
		
		Resource stressResource = new Resource();
		stressResource.setTitle(context.getString(R.string.stress));
		stressResource.setListImage(R.drawable.resource_stress);
		stressResource.setComponents(getStressComponents(context));
		
		Resource depressionResource = new Resource();
		depressionResource.setTitle(context.getString(R.string.depression));
		depressionResource.setListImage(R.drawable.resource_depression);
		depressionResource.setComponents(getDepressionComponents(context));
		
		Resource relationshipResource = new Resource();
		relationshipResource.setTitle(context.getString(R.string.relationship));
		relationshipResource.setListImage(R.drawable.resource_relationship);
		relationshipResource.setComponents(getRelationshipComponents(context));
		
		Resource financeResource = new Resource();
		financeResource.setTitle(context.getString(R.string.financial));
		financeResource.setListImage(R.drawable.resource_financial);
		financeResource.setComponents(getFinanceComponents(context));
		
		Resource healthResource = new Resource();
		healthResource.setTitle(context.getString(R.string.health));
		healthResource.setListImage(R.drawable.resource_health);
		healthResource.setComponents(getHealthComponents(context));
		
		Resource workResource = new Resource();
		workResource.setTitle(context.getString(R.string.work));
		workResource.setListImage(R.drawable.resource_work);
		workResource.setComponents(getWorkComponents(context));
		
		resources.add(stressResource);
		resources.add(depressionResource);
		resources.add(relationshipResource);
		resources.add(financeResource);
		resources.add(healthResource);
		resources.add(workResource);
	}
	
	public ArrayList<Resource> getResources() {
		return resources;
	}
	
	private ArrayList<Component> getStressComponents(Context context) {
		
		ArrayList<Component> stressComponents = new ArrayList<Component>();
		
		Component component1 = new Component();
		component1.setHeading(context.getString(R.string.stress_component_1));
		component1.setFileName("file:///android_asset/stress/1_breathing_techniques.html");
		
		Component component2 = new Component();
		component2.setHeading(context.getString(R.string.stress_component_2));
		component2.setFileName("file:///android_asset/stress/2_beat_serious_stress.html");
		
		Component component3 = new Component();
		component3.setHeading(context.getString(R.string.stress_component_3));
		component3.setFileName("file:///android_asset/stress/3_managing_emotional_reactions.html");
		
		Component component4 = new Component();
		component4.setHeading(context.getString(R.string.stress_component_4));
		component4.setFileName("file:///android_asset/stress/4_understanding_stress_health.html");
		
		
		stressComponents.add(component1);
		stressComponents.add(component2);
		stressComponents.add(component3);
		stressComponents.add(component4);
		
		return stressComponents;
	}
	
	private ArrayList<Component> getDepressionComponents(Context context) {
		
		ArrayList<Component> depressionComponents = new ArrayList<Component>();
		
		Component component1 = new Component();
		component1.setHeading(context.getString(R.string.depression_component_1));
		component1.setFileName("file:///android_asset/depression_anxiety/1_understanding_depression.html");
		
		Component component2 = new Component();
		component2.setHeading(context.getString(R.string.depression_component_2));
		component2.setFileName("file:///android_asset/depression_anxiety/2_depression_and_health.html");
		
		Component component3 = new Component();
		component3.setHeading(context.getString(R.string.depression_component_3));
		component3.setFileName("file:///android_asset/depression_anxiety/3_how_to_feel_good.html");
		
		Component component4 = new Component();
		component4.setHeading(context.getString(R.string.depression_component_4));
		component4.setFileName("file:///android_asset/depression_anxiety/4_how_much_control.html");
		
		Component component5 = new Component();
		component5.setHeading(context.getString(R.string.depression_component_5));
		component5.setFileName("file:///android_asset/depression_anxiety/5_how_do_i_cope.html");
		
		
		depressionComponents.add(component1);
		depressionComponents.add(component2);
		depressionComponents.add(component3);
		depressionComponents.add(component4);
		depressionComponents.add(component5);
		
		return depressionComponents;
	}
	
	
	private ArrayList<Component> getRelationshipComponents(Context context) {
		
		ArrayList<Component> relationshipComponents = new ArrayList<Component>();
		
		Component component1 = new Component();
		component1.setHeading(context.getString(R.string.relationship_component_1));
		component1.setFileName("file:///android_asset/relationship/1_making_time_for_yourself.html");
		
		Component component2 = new Component();
		component2.setHeading(context.getString(R.string.relationship_component_2));
		component2.setFileName("file:///android_asset/relationship/2_listening_tips_for_difficult_situations.html");
		
		Component component3 = new Component();
		component3.setHeading(context.getString(R.string.relationship_component_3));
		component3.setFileName("file:///android_asset/relationship/3_opening_lines_of_communications.html");
		
		Component component4 = new Component();
		component4.setHeading(context.getString(R.string.relationship_component_4));
		component4.setFileName("file:///android_asset/relationship/4_want_to_make_marriage_great.html");
		
		Component component5 = new Component();
		component5.setHeading(context.getString(R.string.relationship_component_5));
		component5.setFileName("file:///android_asset/relationship/5_military_the_emonational.html");
		
		
		relationshipComponents.add(component1);
		relationshipComponents.add(component2);
		relationshipComponents.add(component3);
		relationshipComponents.add(component4);
		relationshipComponents.add(component5);
		
		return relationshipComponents;
	}
	
	private ArrayList<Component> getFinanceComponents(Context context) {
		
		ArrayList<Component> financeComponents = new ArrayList<Component>();
		
		Component component1 = new Component();
		component1.setHeading(context.getString(R.string.finance_component_1));
		component1.setFileName("file:///android_asset/financial/1_budgeting_rules.html");
		
		Component component2 = new Component();
		component2.setHeading(context.getString(R.string.finance_component_2));
		component2.setFileName("file:///android_asset/financial/2_start_a_new.html");
		
		Component component3 = new Component();
		component3.setHeading(context.getString(R.string.finance_component_3));
		component3.setFileName("file:///android_asset/financial/3_budgetting_to_meet_goals.html");
	
		Component component4 = new Component();
		component4.setHeading(context.getString(R.string.finance_component_4));
		component4.setFileName("file:///android_asset/financial/4_money_saving_tips.html");
		
		Component component5 = new Component();
		component5.setHeading(context.getString(R.string.finance_component_5));
		component5.setFileName("file:///android_asset/financial/5_preparing_a_will.html");
		
		Component component6 = new Component();
		component6.setHeading(context.getString(R.string.finance_component_6));
		component6.setFileName("file:///android_asset/financial/6_family_law.html");
		
		
		financeComponents.add(component1);
		financeComponents.add(component2);
		financeComponents.add(component3);
		financeComponents.add(component4);
		financeComponents.add(component5);
		financeComponents.add(component6);
		
		return financeComponents;
	}
	
	private ArrayList<Component> getHealthComponents(Context context) {
		
		ArrayList<Component> healthComponents = new ArrayList<Component>();
		
		Component component1 = new Component();
		component1.setHeading(context.getString(R.string.health_component_1));
		component1.setFileName("file:///android_asset/health/1_habits_to_help_you.html");
		
		Component component2 = new Component();
		component2.setHeading(context.getString(R.string.health_component_2));
		component2.setFileName("file:///android_asset/health/2_living_a_life_in_balance.html");
		
		Component component3 = new Component();
		component3.setHeading(context.getString(R.string.health_component_3));
		component3.setFileName("file:///android_asset/health/3_a_real_8_a_minute.html");
	
		
		healthComponents.add(component1);
		healthComponents.add(component2);
		healthComponents.add(component3);
		
		return healthComponents;
	}
	
	
	private ArrayList<Component> getWorkComponents(Context context) {
		
		ArrayList<Component> workComponents = new ArrayList<Component>();
		
		Component component1 = new Component();
		component1.setHeading(context.getString(R.string.work_component_1));
		component1.setFileName("file:///android_asset/work/1_reduce_workplace_stress.html");
		
		Component component2 = new Component();
		component2.setHeading(context.getString(R.string.work_component_2));
		component2.setFileName("file:///android_asset/work/2_seven_ways.html");
		
		Component component3 = new Component();
		component3.setHeading(context.getString(R.string.work_component_3));
		component3.setFileName("file:///android_asset/work/3_clarifying_your_work.html");
	
		
		workComponents.add(component1);
		workComponents.add(component2);
		workComponents.add(component3);
		
		return workComponents;
	}
}
