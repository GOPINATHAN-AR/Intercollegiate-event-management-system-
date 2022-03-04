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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends Activity implements OnClickListener {
	
	TextView v1;
	EditText t1;
	Button b1;
	SharedPreferences sn;	
	
	SharedPreferences sp,sc;
	private String IP, web_url, dept="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		v1 = (TextView) findViewById(R.id.textView1);
		t1 = (EditText) findViewById(R.id.editText1);
		b1 = (Button) findViewById(R.id.button1);
		sn = getSharedPreferences("user", Context.MODE_PRIVATE);
		v1.setText("Welcome, "+sn.getString("uname", ""));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url ="http://"+IP+"/PhpAndr_ICE/feedback.php";
		b1.setOnClickListener(this);		
	}
	
	public void fillData(String s) {
		if(s.equalsIgnoreCase("ok")) {
			Toast.makeText(this, "Feedback Send...!", Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View arg0) {
		new FeedbackTask(this).execute(web_url,sn.getString("userid", ""),t1.getText().toString().trim());		
	}
}

class FeedbackTask extends AsyncTask<String, Void, String> {

	private Feedback activity;
	ProgressDialog pdialog;
	HttpEntity entity=null;
	
	public FeedbackTask(Feedback activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pdialog = ProgressDialog.show(activity,"Please Wait","Uploading is in Process...!",false,false);
	}

	@Override
	protected String doInBackground(String... p) {		
		String s="";		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("userid", p[1]));
            nameValuePairs.add(new BasicNameValuePair("feedback", p[2]));
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