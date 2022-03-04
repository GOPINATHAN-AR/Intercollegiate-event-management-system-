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

public class AddParticipant2 extends Activity implements OnClickListener {
	
	TextView v1;
	SharedPreferences sn;
	
	Button b1;
	Spinner sp1;
	EditText t1,t2;
	ArrayAdapter<String> adapter;
	SharedPreferences sp,sc;
	private String IP, web_url,web_url1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addparticipant2);
		v1 = (TextView) findViewById(R.id.textView1);
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);
		b1 = (Button) findViewById(R.id.button1);
		sp1 = (Spinner) findViewById(R.id.spinner1);		
		sn = getSharedPreferences("user", Context.MODE_PRIVATE);
		v1.setText("Welcome, "+sn.getString("uname", ""));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url ="http://"+IP+"/PhpAndr_ICE/getevents.php";
		web_url1 ="http://"+IP+"/PhpAndr_ICE/apply.php";
		new FetchEventTask(this).execute(web_url);
		Intent i2 = getIntent();
		String s1 = i2.getStringExtra("dept");
		t1.setText(s1);
		b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(check()) {
			new ApplyTask(this).execute(web_url1, sn.getString("userid", ""), t1.getText().toString().trim(), t2.getText().toString().trim(), sp1.getSelectedItem().toString().trim());
		}
	}
	
	public void fillData(String s) {
		String s1[] = s.split("~");
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s1);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter);
	}
	
	public void showMsg(String s) {
		if(s.equalsIgnoreCase("ok")) {
			cls();
			Toast.makeText(this, "Applied Successfully...!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean check() {
		if(t1.getText().toString().trim().equalsIgnoreCase("")) {
			Toast.makeText(this, "Enter Dept", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(t2.getText().toString().trim().equalsIgnoreCase("")) {
			Toast.makeText(this, "Enter Student Name", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	private void cls() {
		t2.setText("");
		sp1.setSelection(0);
		t2.requestFocus();
	}
}

class FetchEventTask extends AsyncTask<String, Void, String> {

	private AddParticipant2 activity;
	ProgressDialog pdialog;
	HttpEntity entity=null;
	
	public FetchEventTask(AddParticipant2 activity) {
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
            //nameValuePairs.add(new BasicNameValuePair("uname", p[1]));

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(p[0]);
                //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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

class ApplyTask extends AsyncTask<String, Void, String> {

	private AddParticipant2 activity;
	ProgressDialog pdialog;
	HttpEntity entity=null;
	
	public ApplyTask(AddParticipant2 activity) {
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
            nameValuePairs.add(new BasicNameValuePair("dept", p[2]));
            nameValuePairs.add(new BasicNameValuePair("studname", p[3]));
            nameValuePairs.add(new BasicNameValuePair("event", p[4]));

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
		activity.showMsg(result);
	}
}