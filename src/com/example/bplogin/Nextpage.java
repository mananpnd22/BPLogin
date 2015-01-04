package com.example.bplogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Nextpage extends Activity {

	String username;
	int userid;
	
	JSONArray jArray = null;
    String result = null;    
    StringBuilder sb = null;
    InputStream is = null;    
    JSONObject json_data = null;
	
    TextView tv_high;
    TextView tv_low;
    TextView tv_time;
    
    String bp_high;
    String bp_low;
    String time;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nextpage);
		Bundle extra = getIntent().getExtras();
		if(extra != null)
		{
			 username = extra.getString("username");
			 userid = extra.getInt("userid");
			TextView tv = (TextView) findViewById(R.id.textview1);
			tv.setText("Hi, " + username.toString());
		}
		
			
		
		new sendRequest().execute();
		
	}

	
	
	
	
	 public class sendRequest extends AsyncTask<Void, Void, Integer>
		{
			@Override
			protected void onPreExecute() 
			{
				
			};
			protected void onPostExecute(Integer result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				Log.d("result", result.toString());
				
				if(result == 1)
				{
					 tv_high = (TextView) findViewById(R.id.txthigh);
	           			tv_low = (TextView) findViewById(R.id.txtlow);
	           			tv_time = (TextView) findViewById(R.id.txttime);
	                	   tv_high.setText(bp_high);
	                	   tv_low.setText(bp_low);
	                	   tv_time.setText(time);
					
						
				
				}
				
			}
			@Override
			protected Integer doInBackground(Void... params) {
				
				
				 try {
			    	 
				    	Log.d("PF0", "connecting");  // http://File.php
				    	DefaultHttpClient client = new DefaultHttpClient();
				        HttpGet request = new HttpGet("http://192.168.1.113/get_bp_data.php");
				        HttpResponse response = client.execute(request);
				        
				        HttpEntity entity = response.getEntity();
				        is = entity.getContent();
				        
				      //  long entity_length = response.getEntity().getContentLength();
				    if(entity.getContentLength() == 0)
				        {
				    	Log.d("cot","Entity Length 0");
				        }
				    else
				    {
				    	Log.d("cot","Entity Length Found");
				    }
				       
				        
				      //convert response to string
				        try{
				              BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				               sb = new StringBuilder();
				               sb.append(reader.readLine() + "\n");

				               String line="0";
				               while ((line = reader.readLine()) != null) {
				                              sb.append(line + "\n");
				                }
				                is.close();
				                result=sb.toString();
				                Log.d("String", result);
				                }catch(Exception e){
				                      Log.e("log_tag", "Error converting result "+e.toString());
				                }
				        
				       // String name;
				        try{
				        	
				            jArray = new JSONArray(result);
				            JSONObject json_data=null;
				            for(int i=0;i<jArray.length();i++){
				                   json_data = jArray.getJSONObject(i);
				                   String ct_userid = json_data.getString("userid");
				                   String ct_bp_high = json_data.getString("bp_high");//here "Name" is the column name in database
				                   String ct_bp_low = json_data.getString("bp_low");
				                   String ct_time = json_data.getString("time");
				                   Log.d("PFName", ct_bp_high);
				                   Log.d("PFPass", ct_bp_low);
				                   Log.d("PFPass", ct_userid);
				                   if(userid == Integer.parseInt(ct_userid))
				                   {
				                	  
				                	   bp_high = ct_bp_high;
				                	   bp_low = ct_bp_low;
				                	   time = ct_time;
				                	   Log.d("getdata", "sadas");
				                	   return 1;
				                   }
				                   
				                  
				                   
				                   
				                 //Toast.makeText(getBaseContext(), out, Toast.LENGTH_LONG).show();
				                   
				            }
				              }
				              catch(JSONException e1){
				            	  Log.d("PF2", "ERROR HERE");   
				              } catch (ParseException e1) {
				           e1.printStackTrace();
				         }
				        
				        
				        
				    } catch (ClientProtocolException e) {
				        Log.d("HTTPCLIENT", "Not Connected");
				        //e.getLocalizedMessage()
				    } catch (IOException e) {
				        Log.d("HTTPCLIENT",e.getLocalizedMessage());
				    }
				
				
				return 0;
			}
				
			}

	
	
	
}
