package com.dev.vpa;

import org.codehaus.jackson.annotate.JsonProperty;

public class HttpResponseData {

	public String acceptLanguage; 
	

	public String host; 
	

	public String userAgent; 
	
	@JsonProperty("Accept")
	public String accept;

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	
}
