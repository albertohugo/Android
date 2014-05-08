package alberto.hugo.cadastroaluno.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class WebClient {
	private String urlServer;

	public WebClient(String urlServer) {
		this.urlServer = urlServer;
	}
	
	public String post(String dadosJSON) {
		try {
			
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlServer);
			
			post.setEntity(new StringEntity(dadosJSON));
			
			post.setHeader("Content-type", "application/json");
			post.setHeader("Accept", "application/json");
		
			HttpResponse response = client.execute(post);
			//2:41 - Cap 2
			HttpEntity resposta = response.getEntity();
			
			String respostaEmJSON = EntityUtils.toString(resposta);
	
			return respostaEmJSON;
		} catch (Exception e) {
			Log.i("Erro: ","Não conectou com o servidor!");
			throw new RuntimeException(e);
		}
	}
}
