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

public class AboutDept extends Activity {
	
	TextView v1,v2;
	SharedPreferences sn;	
	
	Spinner sp1;
	ArrayAdapter<String> adapter;
	SharedPreferences sp,sc;
	private String IP, web_url, dept="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutdept);
		v1 = (TextView) findViewById(R.id.textView1);
		v2 = (TextView) findViewById(R.id.textView2);
		sn = getSharedPreferences("user", Context.MODE_PRIVATE);
		//v1.setText("Welcome, "+sn.getString("uname", ""));
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		IP = sp.getString("ip", "");
		web_url ="http://"+IP+"/PhpAndr_ICE/getprelims.php";
		fillData();
	}
	
	public void fillData() {
		
		v2.setText("The Department in the college is started from 1991.  Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.Click any entry in the Index tab to view the topic. To search the index, enter a term in the search field and press Enter. Press Enter multiple times to cycle through all occurrences of the term in the index.");
	}
}