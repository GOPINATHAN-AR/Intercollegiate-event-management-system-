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
import android.widget.Toast;

public class Regn extends Activity implements OnClickListener {

	private EditText t1,t2,t3,t4,t5;
	private Button b1;
	private SharedPreferences sp,sn;
	private String IP,web_url1;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regn);
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);
		t3 = (EditText) findViewById(R.id.editText3);
		t4 = (EditText) findViewById(R.id.editText4);
		t5 = (EditText) findViewById(R.id.editText5);
		b1 = (Button) findViewById(R.id.button1);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url1 = "http://"+IP+"/PhpAndr_ICE/uregn.php";
		b1.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(check()) {
			new RegnTask(this).execute(web_url1, t1.getText().toString().trim(),
					t2.getText().toString().trim(), t3.getText().toString().trim(),
					t4.getText().toString().trim(), t5.getText().toString().trim());
		}
	}
	
	public void showMsg(String s) {
		if(s.equalsIgnoreCase("ok")) {
			Toast.makeText(this, "Registered Successfully...!", Toast.LENGTH_SHORT).show();
			cls();
			finish();
		} else {
			Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean check() {
		if(t1.getText().toString().trim().equals("")) {
			Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(t2.getText().toString().trim().equals("")) {
			Toast.makeText(this, "Enter City", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!t3.getText().toString().trim().matches("[9876]\\d{9}")) {
			Toast.makeText(this, "Invalid Mobile", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!t4.getText().toString().trim().matches("[a-z\\.]+[0-9]{0,}[a-z]{0,}\\@[a-z]+\\.([a-z]{3}|[a-z]{2}\\.[a-z]{2}){1}")) {
			Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(t5.getText().toString().trim().equals("")) {
			Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	private void cls() {
		t1.setText(""); t2.setText(""); t3.setText(""); t4.setText(""); t5.setText("");
	}
}

class RegnTask extends AsyncTask<String, Void, String> {

	private Regn activity;
	ProgressDialog pdialog;
	HttpEntity entity=null;
	
	public RegnTask(Regn activity) {
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
            nameValuePairs.add(new BasicNameValuePair("uname", p[1]));
            nameValuePairs.add(new BasicNameValuePair("city", p[2]));
            nameValuePairs.add(new BasicNameValuePair("mobile", p[3]));
            nameValuePairs.add(new BasicNameValuePair("userid", p[4]));                        
            nameValuePairs.add(new BasicNameValuePair("pwd", p[5]));

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