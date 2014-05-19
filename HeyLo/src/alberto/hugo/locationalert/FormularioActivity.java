package alberto.hugo.locationalert;

import alberto.hugo.locationalert.dao.NotificacaoDAO;
import alberto.hugo.locationalert.modelo.Notificacao;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class FormularioActivity extends ActionBarActivity implements
		OnItemSelectedListener {
	private Spinner spinner;
	private FormularioHelper helper;
	private Notificacao notificacaoParaSerAlterada;
	String []mydata1={"American Cuisine","French Cuisine"};
	Integer[]images={R.drawable.ic_delete,R.drawable.ic_list};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);
		
		 Spinner mySpinner = (Spinner)findViewById(R.id.spinner);
	        mySpinner.setAdapter(new MyAdapter(this, R.layout.linha_spinner, mydata1));
		
		Intent intent = getIntent();
		notificacaoParaSerAlterada = (Notificacao) intent
				.getSerializableExtra("notificacaoSelecionada");

		helper = new FormularioHelper(this);

		if (notificacaoParaSerAlterada != null) {
			helper.colocaNotificacaoNoFormulario(notificacaoParaSerAlterada);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.formulario, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemClicado = item.getItemId();
		switch (itemClicado) {
		case R.id.salva:
			Notificacao notificacao = helper.pegaNotificacaoDoFormulario();

			NotificacaoDAO dao = new NotificacaoDAO(FormularioActivity.this);
			if (notificacaoParaSerAlterada == null) {
				dao.salva(notificacao);
			} else {
				notificacao.setId(notificacaoParaSerAlterada.getId());
				dao.altera(notificacao);
			}

			dao.close();

			finish();
			break;
			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	 public class MyAdapter extends ArrayAdapter<String>
	    {
	 
	            public MyAdapter(Context context, int textViewResourceId, String[] objects) 
	            {
	                  super(context, textViewResourceId, objects);
	            }
	             
	             
	            @Override
	            public View getDropDownView(int position, View convertView,ViewGroup parent)
	            {
	            return getCustomView(position, convertView, parent);
	            }
	 
	            @Override
	            public View getView(int position, View convertView, ViewGroup parent) 
	            {
	            return getCustomView(position, convertView, parent);
	            }
	 
	        public View getCustomView(int position, View convertView, ViewGroup parent) 
	        {
	 
	            LayoutInflater inflater=getLayoutInflater();
	            View row=inflater.inflate(R.layout.linha_spinner, parent, false);
	         
	            ImageView iconSpinner=(ImageView)row.findViewById(R.id.img_Spinner);
	            iconSpinner.setImageResource(images[position]);
	 
	            return row;
	            }
	        
	        }
	   
}
