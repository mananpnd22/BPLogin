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
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	TextView tv;
	
	EditText username;
	
	EditText password;
	
	Button btnLogin;
	
	JSONArray jArray = null;

    String result = null;
    
    static String out_password = null;
    
    static String out_username = null;

    StringBuilder sb = null;

    InputStream is = null;
    
    JSONObject json_data = null;

    int flag = 0;
    
    Context mContext;
    
    String getusername;
    
    String getpassword;
    
    int userid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
        btnLogin = (Button) findViewById(R.id.btnlogin);
		btnLogin.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View view) 
			{
				
				 username = (EditText) findViewById(R.id.txtusername);
				 
				 password = (EditText) findViewById(R.id.txtpassword);
				
				new sendRequest().execute();				
			}
		});
        
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
				
				Log.d("condition", getpassword);
					
				Intent i = new Intent(MainActivity.this,Nextpage.class);
				i.putExtra("username", getusername);
				i.putExtra("userid", userid);
				startActivity(i);
					
			}
			
		}
		@Override
		protected Integer doInBackground(Void... params) {
			 getusername = username.getText().toString();
			 getpassword = password.getText().toString();
			
			 try {
		    	 
			    	Log.d("PF0", "connecting");  // http://File.php
			    	DefaultHttpClient client = new DefaultHttpClient();
			        HttpGet request = new HttpGet("http://10.10.2.217:8888/login.php");
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
			                   String ct_username = json_data.getString("username");//here "Name" is the column name in database
			                   String ct_password = json_data.getString("password");
			                   Log.d("PFName", ct_username);
			                   Log.d("PFPass", ct_password);
			                  
			                   
			                   if(getusername.equals(ct_username) && getpassword.equals(ct_password))
			       			{
			                	   userid = Integer.parseInt(ct_userid);
			       				Log.d("condition", "In Condition");
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
