package alberto.hugo.locationalert;

import alberto.hugo.locationalert.adapter.GalleryImageAdapter;
import alberto.hugo.locationalert.modelo.Notificacao;
import android.util.Log;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;

public class FormularioHelper {

	private static final String TAG = null;
	private EditText editDescricao;
	private EditText editEndereco;
	private Gallery gallery;
	int position = 0;

	ImageView selectedImage;
	private Integer[] mImageIds = { R.drawable.ic_home, R.drawable.ic_friends,
			R.drawable.ic_love, R.drawable.ic_ok, R.drawable.ic_nok,
			R.drawable.ic_mark, R.drawable.ic_plane, R.drawable.ic_beach,
			R.drawable.ic_stadium };

	private Notificacao notificacao;

	@SuppressWarnings("deprecation")
	public FormularioHelper(FormularioActivity formulario) {

		gallery = (Gallery) formulario.findViewById(R.id.galleryImages);
		gallery.setSpacing(1);
		gallery.setAdapter(new GalleryImageAdapter(formulario));

		editDescricao = (EditText) formulario.findViewById(R.id.descricao);
	    editEndereco = (EditText) formulario.findViewById(R.id.endereco);

		
		notificacao = new Notificacao();
	}

	public Notificacao pegaNotificacaoDoFormulario() {

		notificacao.setImage((int) gallery.getSelectedItemId());
		Log.d(TAG, "teste: " + gallery.getSelectedItemId());

		notificacao.setDescricao(editDescricao.getText().toString());
		notificacao.setEndereco(editEndereco.getText().toString());

		if(isValidDescription(notificacao.getDescricao().toString())==false){
			editDescricao.setError("Invalid Email - 1 character minimum");
	    }		
		
		if(isValidAddress(notificacao.getEndereco().toString())==false){
			editEndereco.setError("Invalid Email - 3 characters minimum");
	    }
		
		return notificacao;

	}

	public void colocaNotificacaoNoFormulario(
			Notificacao notificacaoParaSerAlterado) {
		notificacao = notificacaoParaSerAlterado;
		gallery.setSelection(notificacaoParaSerAlterado.getImage());
		editDescricao.setText(notificacaoParaSerAlterado.getDescricao());
		editEndereco.setText(notificacaoParaSerAlterado.getEndereco());
	}

	public Gallery getImage() {

		return gallery;
	}
	
	public boolean isValidAddress(String string) {
		if (string != null && string.length() > 2) {
			return true;
		}
		return false;
	}
	
	public boolean isValidDescription(String string) {
		if (string != null && string.length() > 0) {
			return true;
		}
		return false;
	}

	
}
