package alberto.hugo.locationalert.adapter;

import java.util.List;

import alberto.hugo.locationalert.modelo.Notificacao;
import alberto.hugo.locationalert.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaNotificacoesAdapter extends BaseAdapter {

	private List<Notificacao> notificacoes;
	private Activity activity;

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

		TextView descricao = (TextView) linha.findViewById(R.id.descricao);
		descricao.setText(notificacao.getDescricao());

//add more informations
		
		return linha;
	}

}
