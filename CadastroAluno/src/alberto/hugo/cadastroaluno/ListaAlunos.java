package alberto.hugo.cadastroaluno;

import java.util.List;

import alberto.hugo.cadastroaluno.adapter.ListaAlunosAdapter;
import alberto.hugo.cadastroaluno.dao.AlunoDAO;
import alberto.hugo.cadastroaluno.modelo.Aluno;
import alberto.hugo.cadastroaluno.task.EnviaAlunosTask;
import alberto.hugo.cadastroaluno.util.AlunoConverter;
import alberto.hugo.cadastroaluno.util.WebClient;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunos extends ActionBarActivity {
	private ListView lista;
	private Aluno aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();

		// int layout = android.R.layout.simple_list_item_1;//list default
		// ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout,
		// alunos);
		// int layout = R.layout.linha_listagem;
		ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);

		lista = (ListView) findViewById(R.id.lista);
		// Ativa a lista para o menu de longo clique
		registerForContextMenu(lista);
		lista.setAdapter(adapter);

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(posicao);
				Intent irPararFormulario = new Intent(ListaAlunos.this,
						Formulario.class);
				irPararFormulario.putExtra("alunoSelecionado", alunoClicado);
				startActivity(irPararFormulario);
			}
		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				aluno = (Aluno) adapter.getItemAtPosition(posicao);

				return false;
			}

		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuItem ligar = menu.add("Ligar");
		ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaTelaDeDiscagem = new Intent(Intent.ACTION_CALL);
				Uri discarPara = Uri.parse("tel:" + aluno.getTelefone());
				irParaTelaDeDiscagem.setData(discarPara);
				startActivity(irParaTelaDeDiscagem);
				return false;
			}
		});

		menu.add("Enviar SMS");
		MenuItem site = menu.add("Navegar no site");
		site.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaOSite = new Intent(Intent.ACTION_VIEW);
				Uri localSite = Uri.parse("http://" + aluno.getSite());
				irParaOSite.setData(localSite);
				startActivity(irParaOSite);
				return false;
			}
		});

		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
				dao.deletar(aluno);
				carregaLista();
				dao.close();
				return false;
			}
		});
		menu.add("Ver no Mapa");
		menu.add("Enviar Email");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alunos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemClicado = item.getItemId();
		switch (itemClicado) {
		case R.id.novo:
			Intent irParaFormulario = new Intent(this, Formulario.class);
			startActivity(irParaFormulario);
			break;
			
		case R.id.mapa:
			Intent irParaMapa = new Intent(this, MostraAlunosProximos.class);
			startActivity(irParaMapa);
			break;
/*		case R.id.enviar_alunos:
			
			http://jsonlint.com/
			CODE JSON
			"{"list":[{"aluno":[{"nome":"Mara","nota":9},{"nome":"Maria","nota":7}]}]}"
			
			
			EnviaAlunosTask task = new EnviaAlunosTask(this);
			task.execute();
			break;*/
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
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();
		ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);

		lista.setAdapter(adapter);
	}
}
