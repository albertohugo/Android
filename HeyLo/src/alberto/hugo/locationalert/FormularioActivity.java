package alberto.hugo.locationalert;

import alberto.hugo.locationalert.dao.NotificacaoDAO;
import alberto.hugo.locationalert.modelo.Notificacao;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FormularioActivity extends ActionBarActivity {

	private FormularioHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);

		Intent intent = getIntent();
		final Notificacao notificacaoParaSerAlterada = (Notificacao) intent
				.getSerializableExtra("notificacaoSelecionada");

		helper = new FormularioHelper(this);

		Button botao = (Button) findViewById(R.id.botao);

		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Notificacao notificacao = helper.pegaNotificacaoDoFormulario();

				NotificacaoDAO dao = new NotificacaoDAO(FormularioActivity.this);
				if (notificacaoParaSerAlterada == null) {
					dao.salva(notificacao);
				} else {
					notificacao.setId(notificacaoParaSerAlterada
							.getId());
					dao.altera(notificacao);
				}

				dao.close();

				finish();

			}
		});
	}
}
