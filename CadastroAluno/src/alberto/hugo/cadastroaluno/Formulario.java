package alberto.hugo.cadastroaluno;

import java.io.File;

import alberto.hugo.cadastroaluno.dao.AlunoDAO;
import alberto.hugo.cadastroaluno.modelo.Aluno;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Formulario extends Activity {

	protected static final String ALBERTOLOG = null;
	private static final String Alberto = null;
	protected static final int CAMERA_RESULT = 0;
	private FormularioHelper helper;
	private String caminhoArquivo;
	static final int REQUEST_IMAGE_CAPTURE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Intent intent = getIntent();
		 final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoSelecionado");
		
		helper = new FormularioHelper(this);
		
		
		Button botao = (Button) findViewById(R.id.botao);
		
		if(alunoParaSerAlterado !=null){
			botao.setText("Alterar");
			helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
			
		}
		
		botao.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
				
				AlunoDAO dao = new AlunoDAO(Formulario.this);
				if(alunoParaSerAlterado==null){
					dao.salva(aluno);
				}else {
					aluno.setId(alunoParaSerAlterado.getId());
					dao.altera(aluno);
				}
			
				dao.close();
				
				finish();
				
			}
		});
		
		ImageView foto = helper.getFoto();
		//SetSize Photo
		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(60,60);
		foto.setLayoutParams(parms);
		
		foto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

					Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					
					caminhoArquivo = Environment.getExternalStorageDirectory().toString()+"/"+System.currentTimeMillis()+".jpg";
					File arquivo = new File (caminhoArquivo);
					Uri localImagem= Uri.fromFile(arquivo);
					irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);
					startActivityForResult(irParaCamera, REQUEST_IMAGE_CAPTURE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
				helper.carregaImagem(caminhoArquivo);
				Log.d(Alberto, "Camera Ok");
			} else {
				caminhoArquivo = null;
				Log.d(Alberto, "Camera NOk");
			}
		
	}
}
