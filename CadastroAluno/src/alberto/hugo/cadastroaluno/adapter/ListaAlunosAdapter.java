package alberto.hugo.cadastroaluno.adapter;

import java.util.List;

import alberto.hugo.cadastroaluno.R;
import alberto.hugo.cadastroaluno.modelo.Aluno;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListaAlunosAdapter extends BaseAdapter {
	private List<Aluno> alunos;
	private Activity activity;

	public ListaAlunosAdapter(List<Aluno> alunos, Activity activity) {
		this.alunos = alunos;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {
		Aluno aluno = alunos.get(position);
		return aluno.getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Aluno aluno = alunos.get(position);
		LayoutInflater inflater = activity.getLayoutInflater();
		View linha = inflater.inflate(R.layout.linha_listagem, null);

		TextView nome = (TextView) linha.findViewById(R.id.nome);
		nome.setText(aluno.getNome());

		ImageView foto = (ImageView) linha.findViewById(R.id.foto);
		
		if(aluno.getFoto() !=null){
			Bitmap fotoAluno = BitmapFactory.decodeFile(aluno.getFoto());
			Bitmap fotoReduzida = Bitmap.createScaledBitmap(fotoAluno, 40, 40, true);
			foto.setImageBitmap(fotoReduzida);
		}else {
			Drawable semFoto = activity.getResources().getDrawable(R.drawable.ic_photo);
			//SetSize Photo
			LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(40,40);
			foto.setLayoutParams(parms);
			foto.setImageDrawable(semFoto);
		}
		TextView telefone = (TextView) linha.findViewById(R.id.telefone);
		if(telefone!=null){
		telefone.setText(aluno.getTelefone());
		}
		TextView site = (TextView) linha.findViewById(R.id.site);
		if(site!=null){
		site.setText(aluno.getSite());
		}
		return linha;
	}

}
