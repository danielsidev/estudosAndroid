package br.com.readingjson.controller;

import br.com.readingjson.R;

import com.androidquery.AQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DetalheShotActivity extends Activity {

	TextView nomeAutor, titulo, shotView;
	String autor, foto, shot, title;
	Integer shotViews;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_shot);
	     Intent intent = getIntent();
	     savedInstanceState = intent.getExtras();
		autor = savedInstanceState.getString("autor");
		foto = savedInstanceState.getString("fotoAutor");
		shot = savedInstanceState.getString("shot");
		title = savedInstanceState.getString("title");
		shotViews = savedInstanceState.getInt("shotViews");
		
		   AQuery aq = new AQuery(this);            
			boolean memCache = true;
			boolean fileCache = true;

			aq.id(R.id.imageShot).image(shot, memCache, fileCache, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);
			aq.id(R.id.imageAutor).image(foto, memCache, fileCache, 0, 0, null, AQuery.FADE_IN_NETWORK);
			
			nomeAutor = (TextView) findViewById(R.id.nomeAutor);
			titulo = (TextView) findViewById(R.id.title);
			shotView = (TextView) findViewById(R.id.shotViews);
			nomeAutor.setText(autor);
			titulo.setText(title);
			shotView.setText(String.valueOf(shotViews));
			Log.d("ShotViews", "Views: "+String.valueOf(shotViews));
			Log.d("Nome Autor", "Nome do Autor: "+autor);
			//Log.d("", "");
			//Log.d("", "");
	        
	}


}
