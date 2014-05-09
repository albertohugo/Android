package alberto.hugo.locationalert;

import java.util.List;

import alberto.hugo.locationalert.adapter.ListaNotificacoesAdapter;
import alberto.hugo.locationalert.dao.NotificacaoDAO;
import alberto.hugo.locationalert.modelo.Notificacao;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

}
