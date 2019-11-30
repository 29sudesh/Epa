package com.eap.lifepilot.entities;

import java.util.ArrayList;

public class Resource {

	private String title;
	private int listImage;
	
	
	public int getListImage() {
		return listImage;
	}
	public void setListImage(int listImage) {
		this.listImage = listImage;
	}
	private ArrayList<Component> components;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<Component> getComponents() {
		return components;
	}
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
}
