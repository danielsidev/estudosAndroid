package br.com.readingjson;



import java.util.ArrayList;

import com.androidquery.AQuery;
import br.com.readingjson.controller.ListViewAdpterShots;
import br.com.readingjson.controller.DownloadJsonAsyncTask;
import br.com.readingjson.model.DataShots;
import br.com.readingjson.model.EndlessScrollListener;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	Integer page = 1;
	ArrayList<DataShots>  listaItens;
	ListView listView;
	public static Integer contador = 0;
	public static ArrayList<DataShots> itens =  new ArrayList<DataShots>();
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listConteudo); 
		 
		MainActivity.contador =0;
		new DownloadJsonAsyncTask(MainActivity.this,listView, "Carregando", "http://api.dribbble.com/shots/popular?page=1").execute();
		
		listView.setOnScrollListener(new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	        	String pag = String.valueOf(page);
	        	
	        	new DownloadJsonAsyncTask(MainActivity.this,listView, "Carregando", "http://api.dribbble.com/shots/popular?page="+pag).execute();
	        	Log.d("PAGE", "Page: "+String.valueOf(page));
	        
	        	
	        }
	        });
		
		}


	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
