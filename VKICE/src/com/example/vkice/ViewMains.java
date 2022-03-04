package com.example.vkice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewMains extends Activity {
	
	TextView v1,v2;
	SharedPreferences sn;	
	
	Spinner sp1;
	ArrayAdapter<String> adapter;
	SharedPreferences sp,sc;
	private String IP, web_url, dept="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewmains);
		v1 = (TextView) findViewById(R.id.textView1);
		v2 = (TextView) findViewById(R.id.textView2);
		sn = getSharedPreferences("user", Context.MODE_PRIVATE);
		v1.setText("Welcome, "+sn.getString("uname", ""));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url ="http://"+IP+"/PhpAndr_ICE/getmains.php";
		new FetchMainsTask(this).execute(web_url,sn.getString("userid", ""));
	}
	
	public void fillData(String s) {
		String s1[] = s.split("~");
		String s2="";
		for(int i=0; i<s1.length; i++) {
			s2+=s1[i]+"\n";
		}
		v2.setText(s2);
	}
}

class FetchMainsTask extends AsyncTask<String, Void, String> {

	private ViewMains activity;
	ProgressDialog pdialog;
	HttpEntity entity=null;
	
	public FetchMainsTask(ViewMains activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pdialog = ProgressDialog.show(activity,"Please Wait","Downloading is in Process...!",false,false);
	}

	@Override
	protected String doInBackground(String... p) {		
		String s="";		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("userid", p[1]));
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(p[0]);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);

                entity = response.getEntity();               
      
                s = EntityUtils.toString(entity,"UTF-8");                
            } catch (ClientProtocolException e) {		                	
            	Log.e("ClientProtocol","Log_tag");
				e.printStackTrace();
            } catch (IOException e1) {		                	
            	Log.e("Log_tag", "IOException");
				 e1.printStackTrace();
            }

		} catch (Exception e) {
			Log.e("RegnTask", e.getLocalizedMessage());
		} finally {
			try {				
			} catch (Exception e) {
			}
		}
		return s.trim();
	}
	
	@Override
	protected void onPostExecute(String result) {		
		super.onPostExecute(result);
		pdialog.dismiss();
		activity.fillData(result);
	}
}