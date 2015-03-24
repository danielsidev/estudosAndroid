package br.com.readingjson.controller;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.androidquery.AQuery;

import br.com.readingjson.R;
import br.com.readingjson.model.DataShots;

@SuppressLint("ShowToast")
public class ListViewAdpterShots  extends BaseAdapter{
	  private LayoutInflater mInflater;
    private ArrayList<DataShots> itens;
    ArrayList<Integer> idconta = new ArrayList<Integer>();
    Context context;
    FragmentManager  getFragmentManager;
    
	public ListViewAdpterShots(Context context, ArrayList<DataShots> itens) {
    	this.context = context;
        //Itens que preencheram o listview
        this.itens = itens;

        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
	
	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public DataShots getItem(int position) {
		   return itens.get(position);
	}

	@Override
	  public long getItemId(int position) {
        return position;
    }

	@Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporteCat itemHolder;
        final int pos = position; 
        //se a view estiver nula (nunca criada), inflamos o layout nela.
        if (view == null) {
            //infla o layout para podermos pegar as views
            view = mInflater.inflate(R.layout.shots, null);
 
            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            itemHolder = new ItemSuporteCat();
            itemHolder.txtTitle = ((TextView) view.findViewById(R.id.title));
            itemHolder.foto = ((ImageView) view.findViewById(R.id.imageShot));
            itemHolder.linhaShots = (RelativeLayout)view.findViewById(R.id.linhaShot);
            itemHolder.txtViews = ((TextView) view.findViewById(R.id.shotViews));
            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view já existe pega os itens.
            itemHolder = (ItemSuporteCat) view.getTag();
        }
 
        //pega os dados da lista
        //e define os valores nos itens.
        DataShots item = itens.get(position);
        itemHolder.txtTitle.setText(String.valueOf(item.getTitle()));
        itemHolder.txtViews.setText(String.valueOf(item.getShotViews()));
        
        AQuery aq = new AQuery(this.context);            
		boolean memCache = true;
		boolean fileCache = true;

		aq.id(itemHolder.foto).progress(R.id.progress).image(item.getImageUrl(), memCache, fileCache, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);
        
		itemHolder.linhaShots.setOnClickListener(new OnClickListener() {
			
        	@Override
    		public void onClick(View arg0) {

        		Intent i = new Intent(ListViewAdpterShots.this.context,DetalheShotActivity.class);
        		String autor=itens.get(pos).getPlayerName();
    			String foto =itens.get(pos).getPlayerAvatar();
    			String shot =itens.get(pos).getImageUrl();
    			String title =itens.get(pos).getTitle();
    			Integer shotViews =itens.get(pos).getShotViews();
    			i.putExtra("shot", shot);
    			i.putExtra("title", title);
    			i.putExtra("shotViews", shotViews);
    			i.putExtra("autor", autor);
        		i.putExtra("fotoAutor", foto);
        		ListViewAdpterShots.this.context.startActivity(i);
    		
        		Log.d("DEBUG","Autor: "+autor);
        		Log.d("DEBUG","Foto: "+foto);
    		}
		});

        //retorna a view com as informações
        return view;
    }
 

    private class ItemSuporteCat {     
        TextView txtTitle, txtViews, txtDescricao;
        RelativeLayout linhaShots;
        ImageView foto;
      
    }
	
	

}
