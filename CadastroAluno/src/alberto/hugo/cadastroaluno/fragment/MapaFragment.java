package alberto.hugo.cadastroaluno.fragment;

import java.util.List;

import alberto.hugo.cadastroaluno.dao.AlunoDAO;
import alberto.hugo.cadastroaluno.mapa.Localizador;
import alberto.hugo.cadastroaluno.modelo.Aluno;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {
	@Override
	public void onResume() {
		super.onResume();
	
		//LatLng local = new LatLng(-3.717, -38.54);//Zoom no mapa de Fortaleza
		FragmentActivity context = getActivity();
		//LatLng local = new Localizador(context).getCoordenada("Jovita Feitosa 2305 Parquelandia"); //Definicao de local programaticamente
		//centralizaLocal(local);
		
		AlunoDAO dao = new AlunoDAO(context);
		List<Aluno> alunos = dao.getLista();	
		
		for(Aluno aluno: alunos){
			
			GoogleMap map = getMap();
			LatLng localAluno = new Localizador(context).getCoordenada(aluno.getEndereco());
			MarkerOptions options = new MarkerOptions().title(aluno.getNome()).position(localAluno);
		
			map.addMarker(options);
		}
		dao.close();
	}

	public void centralizaLocal(LatLng local) {
		GoogleMap map = getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local,15);
		Log.e("teste", "Teste");
		map.animateCamera(update);
	}


}
