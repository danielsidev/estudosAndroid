package br.com.readingjson.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.readingjson.MainActivity;
import br.com.readingjson.R;
import br.com.readingjson.controller.ListViewAdpterShots;
import br.com.readingjson.model.DataShots;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.service.media.MediaBrowserService.Result;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



	public class DownloadJsonAsyncTask extends AsyncTask<String, String, String> {
		ProgressDialog dialog;
		int e = 0;
		String msg="";
	    Context ctx;
		public String exsistingFileName;
		public String url="";
		InputStream instream;
        String result;
        ListView listview;
       
        public ListViewAdpterShots adapterListView;
        
		public DownloadJsonAsyncTask ( Context ctx,ListView listview,  String msg, String url){
			this.url = url;
			this.ctx = ctx;
			this.msg = msg;
			this.listview = listview;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(ctx,msg+"...", "Por favor, aguarde.", false, true);
			dialog.setIcon(R.drawable.ic_launcher);
			dialog.setCancelable(false);
			dialog.show();
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(this.url);
			
			try { 
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) { 
					instream = entity.getContent();
				    BufferedReader bReader = new BufferedReader(new InputStreamReader(instream, "utf-8"), 8);
		            StringBuilder sBuilder = new StringBuilder();

		            String line = null;
		            while ((line = bReader.readLine()) != null) {
		                sBuilder.append(line + "\n");
		            }
		            instream.close();
		            result = sBuilder.toString();
					} 
				} catch (Exception e) { 
					Log.e("FALHA", "Falha ao acessar Web service", e); 
					}

			return null;
		}
	
		@Override
		protected void onPostExecute(String unused) {

				DataShots item;
				
			    try {   
		            
		            JSONObject myJson = new JSONObject(result);
		            Log.d("JSON - Pages: ", String.valueOf(myJson.getInt("pages")));
		            Log.d("JSON - Total", String.valueOf(myJson.getInt("total")));	           
		            
		            
		            JSONArray  listShots = myJson.getJSONArray("shots");
		            int n =listShots.length();
		            
		            for(int i=0; i<n;i++){
		            	JSONObject json_shot = listShots.getJSONObject(i);
		            	  if (json_shot!= null) {
	
		            		  item = new DataShots();
		            		  item.setImageUrl(String.valueOf(json_shot.getString("image_url")));
		            		  item.setTitle(String.valueOf(json_shot.getString("title")));	
		            		  item.setShotViews(json_shot.getInt("views_count"));
		            		  
		            		  JSONObject json_player = json_shot.getJSONObject("player");
		            		  
		            		  item.setPlayerAvatar(String.valueOf(json_player.getString("avatar_url")));
		            		  item.setPlayerName(String.valueOf(json_player.get("name")));
		            		  MainActivity.itens.add(item);
		        
		                   }
		            }
		        	dialog.dismiss();
		        } catch (JSONException e) {
		            Log.e("JSONException", "Error: " + e.toString());
		            dialog.dismiss();
		        } 
				adapterListView = new ListViewAdpterShots(this.ctx, MainActivity.itens);
				  int position = adapterListView.getCount() - 23;
				  this.listview.setAdapter(adapterListView);
				  this.listview.setSelection(position);
				

	       }
   

	}
			




