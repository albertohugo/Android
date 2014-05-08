package alberto.hugo.cadastroaluno.util;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import alberto.hugo.cadastroaluno.modelo.Aluno;
import android.util.Log;

public class AlunoConverter {

	public String toJSON(List<Aluno> alunos) {
		// "{"list":[{"aluno":[{"nome":"Mara","nota":9},{"nome":"Maria","nota":7}]}]}"
		try {
			JSONStringer js = new JSONStringer();
			js.object().key("list").array();
			js.object().key("aluno").array();
			for (Aluno aluno : alunos) {
				js.object();
				js.key("nome").value(aluno.getNome());
				js.key("nota").value(aluno.getNota());
				js.endObject();
			}
			js.endArray().endObject();
			js.endArray().endObject();
			
			return js.toString();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		
	}

}
