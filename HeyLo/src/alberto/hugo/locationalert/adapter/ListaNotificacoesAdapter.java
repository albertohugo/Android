package alberto.hugo.locationalert.adapter;

import java.util.List;

import alberto.hugo.locationalert.R;
import alberto.hugo.locationalert.modelo.Notificacao;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaNotificacoesAdapter extends BaseAdapter {

	private static final String TAG = null;
	private List<Notificacao> notificacoes;
	private Activity activity;
	 private Integer[] mImageIds = {
             R.drawable.ic_delete,
             R.drawable.ic_delete,
             R.drawable.ic_delete,
             R.drawable.ic_list
     };

	public ListaNotificacoesAdapter(List<Notificacao> notificacoes, Activity activity) {
		this.notificacoes = notificacoes;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notificacoes.size();
	}

	@Override
	public Object getItem(int position) {
		return notificacoes.get(position);
	}

	@Override
	public long getItemId(int position) {
		Notificacao notificacao= notificacoes.get(position);
		return notificacao.getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Notificacao notificacao= notificacoes.get(position);
		LayoutInflater inflater = activity.getLayoutInflater();
		View linha = inflater.inflate(R.layout.linha_listagem, null);

		ImageView foto = (ImageView) linha.findViewById(R.id.foto);
		
		foto.setImageResource(mImageIds[notificacao.getImage()]);
		 Log.d(TAG,"Foto: "+ notificacao.getImage());
		
		TextView descricao = (TextView) linha.findViewById(R.id.descricao);
		descricao.setText(notificacao.getDescricao());
		
		TextView endereco= (TextView) linha.findViewById(R.id.endereco);
		endereco.setText(notificacao.getEndereco());
		
		  if(position % 2 ==0){
			  linha.setBackgroundResource(R.drawable.gradient_bg);
		    }else if(position % 3 ==0){
		    	 linha.setBackgroundResource(R.drawable.gradient_bg);
		    	//linha.setBackgroundResource(R.drawable.gradient_bg_hover);
		    }else{
		    	 linha.setBackgroundResource(R.drawable.gradient_bg);
		    	//linha.setBackgroundResource(R.drawable.gradient_bg_hover);
		    }

//add more informations
		
		return linha;
	}

}
