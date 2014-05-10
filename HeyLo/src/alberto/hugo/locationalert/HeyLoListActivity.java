package alberto.hugo.locationalert;

import java.util.List;

import alberto.hugo.locationalert.adapter.ListaNotificacoesAdapter;
import alberto.hugo.locationalert.dao.NotificacaoDAO;
import alberto.hugo.locationalert.modelo.Notificacao;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class HeyLoListActivity extends ActionBarActivity {
	private ListView lista;
	private Notificacao notificacao;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hey_lo);

        NotificacaoDAO dao = new NotificacaoDAO(this);
		List<Notificacao> notificacoes = dao.getLista();
		dao.close();
      
		ListaNotificacoesAdapter adapter = new ListaNotificacoesAdapter(notificacoes, this);

		lista = (ListView) findViewById(R.id.lista);
		// Ativa a lista para o menu de longo clique
		registerForContextMenu(lista);
		lista.setAdapter(adapter);

    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.hey_lo, menu);
		return super.onCreateOptionsMenu(menu);
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemClicado = item.getItemId();
		switch (itemClicado) {
		case R.id.novo:
			Intent irParaFormulario = new Intent(this, FormularioActivity.class);
			startActivity(irParaFormulario);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    @Override
	protected void onResume() {
		super.onResume();

		carregaLista();

	}

	private void carregaLista() {
		NotificacaoDAO dao = new NotificacaoDAO(this);
		List<Notificacao> notificacao = dao.getLista();
		dao.close();
		ListaNotificacoesAdapter adapter = new ListaNotificacoesAdapter(notificacao, this);

		lista.setAdapter(adapter);
	}

}
