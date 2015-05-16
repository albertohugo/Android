package com.hugo.alberto.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.hugo.alberto.jumper.R;
import com.hugo.alberto.jumper.graphic.Cores;
import com.hugo.alberto.jumper.graphic.Tela;

/**
 * Created by Alberto on 15/05/2015.
 */
public class Cano {

    private Tela tela;

    private int posicao;
    private int alturaDoCanoInferior;
    private static final int TAMANHO_DO_CANO = 350; //default 250
    private static final int LARGURA_DO_CANO = 250; //default 100
    private int alturaDoCanoSuperior;
    private  Bitmap canoInferior;
    private Bitmap canoSuperior;

    private static final Paint VERDE = Cores.getCorDoCano();


    public Cano (Tela tela, int posicao, Context context){
        this.tela = tela;
        this.posicao = posicao;
        alturaDoCanoInferior = tela.getAltura()- TAMANHO_DO_CANO - valorAleatorio();
        alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false);
        canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }

    private int valorAleatorio() {
        return (int) (Math.random()*600);
    }//default 150


    public void desenhaNo(Canvas canvas){
        desenhaCanoSuperior(canvas);
        desenhaCanoInferior(canvas);
    }

    private void desenhaCanoSuperior(Canvas canvas) {
        //canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, VERDE);
        canvas.drawBitmap(canoSuperior,posicao, 0,null);
    }

    private void desenhaCanoInferior(Canvas canvas) {
        //canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE);
        canvas.drawBitmap(canoInferior,posicao, alturaDoCanoInferior,null);
    }

    public void move() {
        this.posicao -=5;
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao - passaro.X < passaro.RAIO;
    }

    public boolean temColisaVerticalCom(Passaro passaro) {
        return passaro.getAltura() - passaro.RAIO  < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }
}
