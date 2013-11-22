package com.patsud.bwsvplan;


import org.apache.http.client.CookieStore;

public interface NetworkCallbackInterface {
	public void OnNetworkRequestFinished(CookieStore CStore, String Result);
	public void OnException(Exception e);
}
