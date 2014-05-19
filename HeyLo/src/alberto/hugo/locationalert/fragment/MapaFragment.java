package alberto.hugo.locationalert.fragment;


import java.util.List;

import alberto.hugo.locationalert.dao.NotificacaoDAO;
import alberto.hugo.locationalert.mapa.Localizador;
import alberto.hugo.locationalert.modelo.Notificacao;
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
		
		
		 NotificacaoDAO dao = new NotificacaoDAO(context);
		List<Notificacao> notificacoes = dao.getLista();	
		
		for(Notificacao notificacao: notificacoes){
			
			GoogleMap map = getMap();
			LatLng localNotificacao = new Localizador(context).getCoordenada(notificacao.getEndereco());
			MarkerOptions options = new MarkerOptions().title(notificacao.getDescricao()).position(localNotificacao);
		
			map.addMarker(options);
		}
		dao.close();
	}

	public void centralizaLocal(LatLng local) {
		GoogleMap map = getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local,15);
		map.animateCamera(update);
	}


}
