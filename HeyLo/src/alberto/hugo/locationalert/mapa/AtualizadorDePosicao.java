package alberto.hugo.locationalert.mapa;


import alberto.hugo.locationalert.fragment.MapaFragment;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

public class AtualizadorDePosicao implements LocationListener {

	private LocationManager locationManager;
	private MapaFragment mapa;

	public AtualizadorDePosicao(Activity activity, MapaFragment mapa) {
		this.mapa = mapa;
		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

		String provider = LocationManager.GPS_PROVIDER;
		long tempoMinimo = 20000;// ms
		float distanciaMinima = 20;// m
		
		locationManager.requestLocationUpdates(provider, tempoMinimo,distanciaMinima, this);
	}
	
	public void cancelar() {
		locationManager.removeUpdates(this);
		
	}

	@Override
	public void onLocationChanged(Location novaLocalizacao) {
		double latitude = novaLocalizacao.getLatitude();
		double longitude = novaLocalizacao.getLongitude();
		LatLng local = new LatLng(latitude, longitude);
		mapa.centralizaLocal(local);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// GPS WIFI
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	
}
