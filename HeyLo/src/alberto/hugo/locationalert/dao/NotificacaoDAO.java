package alberto.hugo.locationalert.dao;

import java.util.ArrayList;
import java.util.List;

import alberto.hugo.locationalert.modelo.Notificacao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotificacaoDAO extends SQLiteOpenHelper{

	private static final String DATABASE = "HeyLo";
	private static final int VERSAO = 1;
	
	public NotificacaoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}
	public void salva(Notificacao notificacao) {
		ContentValues values = new ContentValues();

		values.put("descricao", notificacao.getDescricao());
		values.put("endereco", notificacao.getEndereco());
	
		getWritableDatabase().insert("Notificacoes", null, values);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE Notificacoes (id INTEGER PRIMARY KEY, descricao TEXT UNIQUE NOT NULL, endereco TEXT);";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String ddl = "DROP TABLE IF EXISTS Notificacoes";
		db.execSQL(ddl);
		onCreate(db);
	}
	
	public List<Notificacao> getLista() {
		String[] colunas = { "id", "descricao", "endereco"};
		Cursor cursor = getWritableDatabase().query("Notificacoes", colunas, null,
				null, null, null, null);

		ArrayList<Notificacao> notificacoes = new ArrayList<Notificacao>();
		while (cursor.moveToNext()) {
			Notificacao notificacao = new Notificacao();

			notificacao.setId(cursor.getLong(0));
			notificacao.setDescricao(cursor.getString(1));
			notificacao.setEndereco(cursor.getString(2));
		
			notificacoes.add(notificacao);
		}

		return notificacoes;
	}

	public void deletar(Notificacao notificacao) {
		String[] args = { notificacao.getId().toString() };
		getWritableDatabase().delete("Notificacoes", "id=?", args);
	}

	public void altera(Notificacao notificacao) {
		ContentValues values = new ContentValues();

		values.put("descricao", notificacao.getDescricao());
		values.put("endereco", notificacao.getEndereco());

		String[] args= {notificacao.getId().toString()};
		getWritableDatabase().update("Notificacoes", values, "id=?", args);		
	}
}
