package alberto.hugo.locationalert;

import alberto.hugo.locationalert.modelo.Notificacao;
import android.widget.EditText;

public class FormularioHelper {
	private EditText editDescricao;
	private EditText editEndereco;
	
	private Notificacao notificacao;

	public FormularioHelper(FormularioActivity formulario) {
			
		editDescricao = (EditText) formulario.findViewById(R.id.descricao);
		editEndereco = (EditText) formulario.findViewById(R.id.endereco);
		
		notificacao = new Notificacao();
	}

	public Notificacao pegaNotificacaoDoFormulario() {

		notificacao.setDescricao(editDescricao.getText().toString());
		notificacao.setEndereco(editEndereco.getText().toString());
		return notificacao;

	}

	public void colocaNotificacaoNoFormulario(Notificacao notificacaoParaSerAlterado) {
		notificacao = notificacaoParaSerAlterado;
		editDescricao.setText(notificacaoParaSerAlterado.getDescricao());
		editEndereco.setText(notificacaoParaSerAlterado.getEndereco());
	}



}
