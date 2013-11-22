package com.patsud.bwsvplan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;


class NetworkRequest extends AsyncTask<String, Integer, String> {
	
	private String url;
	private List<NameValuePair> param;
	private String ret;
	private ProgressDialog dlgLoading;
	
	private HttpClient httpClient;
	private HttpResponse response;
	private HttpPost httpPost;
	private HttpEntity entity;
	private HttpContext httpContext;
	public CookieStore cStore;
	
	private NetworkCallbackInterface callbackInterface;
	
	
	
	public CookieStore getcStore() {
		return cStore;
	}

	public NetworkRequest(String Url, List<NameValuePair> Param, ProgressDialog ProgDlgLoading, CookieStore cookieStore, NetworkCallbackInterface NetCallback) {
		this.url = Url;
		this.param = Param;
		this.dlgLoading = ProgDlgLoading;
		this.callbackInterface = NetCallback;
		
		cStore = cookieStore;
		
		httpClient = new DefaultHttpClient();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cStore);
	}
	
	public NetworkRequest(String Url, List<NameValuePair> Param, ProgressDialog ProgDlgLoading, NetworkCallbackInterface NetCallback) {
		this.url = Url;
		this.param = Param;
		this.dlgLoading = ProgDlgLoading;
		this.callbackInterface = NetCallback;
		
		cStore = new BasicCookieStore();
		
		httpClient = new DefaultHttpClient();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cStore);
	}
	
	public NetworkRequest(String Url, List<NameValuePair> Param, CookieStore cookieStore, NetworkCallbackInterface NetCallback) {
		this.url = Url;
		this.param = Param;
		this.dlgLoading = null;
		this.callbackInterface = NetCallback;
		
		cStore = cookieStore;
		
		httpClient = new DefaultHttpClient();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cStore);
	}
	
	public NetworkRequest(String Url, List<NameValuePair> Param, NetworkCallbackInterface NetCallback) {
		this.url = Url;
		this.param = Param;
		this.dlgLoading = null;
		this.callbackInterface = NetCallback;
		
		cStore = new BasicCookieStore();
		
		httpClient = new DefaultHttpClient();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cStore);
	}
	
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
		if (dlgLoading != null) {
			dlgLoading.show();
		}
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
    }
	
//    /**
//     * Convert an InputStream to a StringBuiler
//     * 
//     * @param is Stream to convert
//     * @return StringBuilder
//     * @throws IOException
//     */
//	private StringBuilder InputStreamToString(InputStream is) throws IOException {
//	    String line = "";
//	    StringBuilder total = new StringBuilder();
//	    
//	    // Wrap a BufferedReader around the InputStream
//	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//
//	    // Read response until the end
//	    while ((line = rd.readLine()) != null) { 
//	        total.append(line); 
//	    }
//	    
//	    // Return full string
//	    return total;
//	}

	@Override
	protected String doInBackground(String... arg0) {
		httpPost = new HttpPost(url);
		
		try {
			if (param != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(param));
			}

			////log.i("nRequest", url);
			
			response = httpClient.execute(httpPost, httpContext);
			entity = response.getEntity();
			
			if (entity != null) {
//				entity.consumeContent();
			}			

		/*	BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
          */
//
            // Pega o status da solicitação
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) { // Ok
            	Log.i("Status if", "Status if");
                // Pega o retorno
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                // Lê o buffer e coloca na variável
                String line = "";
                while (( line = rd.readLine()) != null) {
                    ret += line;
                }
            } else {
            	Log.i("Status else", "Status else");
            	new Exception("wrong state");
            }
//            in.close();Stri
////            ret = sb.toString();
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            
//			ret = InputStreamToString(response.getEntity().getContent()).toString();
			//log.i("nRequest", ret);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	@Override
	protected void onPostExecute(String Result) {
		if (dlgLoading != null) {
			dlgLoading.dismiss();
		}
		
		callbackInterface.OnNetworkRequestFinished(cStore, Result);
	}
	
	
	/**
	 * Check current connection type<br/>
	 * Requires permission ACCESS_NETWORK_STATE
	 * 
	 * @param context
	 * 
	 * @return If a mobile connection is established return true else false
	 */
	public boolean usingMobileConnection(Context context) {
		boolean conMobile = false;
		
		ConnectivityManager conMng = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		int conType = conMng.getActiveNetworkInfo().getType();
		switch (conType) {
			case ConnectivityManager.TYPE_MOBILE:
			case ConnectivityManager.TYPE_MOBILE_DUN:
			case ConnectivityManager.TYPE_MOBILE_HIPRI:
			case ConnectivityManager.TYPE_MOBILE_MMS:
			case ConnectivityManager.TYPE_MOBILE_SUPL:
				conMobile = true;
				break;
			default:
				conMobile = false;
		}
		
		return conMobile;
	}
}
