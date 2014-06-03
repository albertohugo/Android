package alberto.hugo.locationalert.mapa;


import alberto.hugo.locationalert.R;
import alberto.hugo.locationalert.fragment.MapaFragment;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AtualizadorDePosicao implements LocationListener {

	public LocationManager locationManager;
	private MapaFragment mapa;
	private Marker currentLocation =null;

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
		if( currentLocation==null){
		 currentLocation = mapa.getMap().addMarker(new MarkerOptions().position(local).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_current_location)).title("Current Location").alpha(0.7f));
		}else{
			currentLocation.remove();
			currentLocation = mapa.getMap().addMarker(new MarkerOptions().position(local).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_current_location)).title("Current Location").alpha(0.7f));
			}
		 
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
	public static LatLng fromLocationToLatLng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
        
  }
	
	
}
