package com.eap.lifepilot.entities;

public class MyEAPService {

	private String fileName = "";
	private String heading = "";
	private String description = "";
	private String detail = "";
	
	private int imageResource;
	private int detailImageResource;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getImageResource() {
		return imageResource;
	}

	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getDetailImageResource() {
		return detailImageResource;
	}

	public void setDetailImageResource(int detailImageResource) {
		this.detailImageResource = detailImageResource;
	}
	
}
