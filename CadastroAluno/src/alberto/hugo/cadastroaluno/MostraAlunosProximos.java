package alberto.hugo.cadastroaluno;

import alberto.hugo.cadastroaluno.fragment.MapaFragment;
import alberto.hugo.cadastroaluno.mapa.AtualizadorDePosicao;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MostraAlunosProximos extends FragmentActivity {

	private AtualizadorDePosicao atualizador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		MapaFragment mapaFragment = new MapaFragment();
		transaction.replace(R.id.mapa,mapaFragment);
		transaction.commit();
		Log.e("teste", "Teste2");
		atualizador = new AtualizadorDePosicao(this, mapaFragment );
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		atualizador.cancelar();
	}
}
