package com.motionapps.GSYSocial.dao.vo;

import javax.xml.bind.annotation.XmlRootElement;


	@XmlRootElement
	public class Notification
	{
		private String title;
		private String text;
		private String icon;
		
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}