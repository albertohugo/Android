package alberto.hugo.cadastroaluno.task;

import java.util.List;

import alberto.hugo.cadastroaluno.dao.AlunoDAO;
import alberto.hugo.cadastroaluno.modelo.Aluno;
import alberto.hugo.cadastroaluno.util.AlunoConverter;
import alberto.hugo.cadastroaluno.util.WebClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class EnviaAlunosTask extends AsyncTask<Integer, Double, String>{

	private Activity context;
	private ProgressDialog progress;
	public EnviaAlunosTask(Activity context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde...", "Enviando dados para web...", true, true);
	}
	@Override
	protected String doInBackground(Integer... params) {
		String urlServer = "http://www.caelum.com.br/mobile";
		
		AlunoDAO dao = new AlunoDAO(context);
		List<Aluno> alunos = dao.getLista();
		dao.close();
		
		String dadosJSON = new AlunoConverter().toJSON(alunos);//TODO
		
		
		WebClient client = new WebClient(urlServer);
		//client.post(dadosJSON);
		
		final String respostaJSON = client.post(dadosJSON);
		
		return respostaJSON;
	}
	@Override
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

}
