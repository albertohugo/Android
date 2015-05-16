package com.hugo.alberto.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.service.carrier.CarrierMessagingService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.hugo.alberto.jumper.R;
import com.hugo.alberto.jumper.elements.Cano;
import com.hugo.alberto.jumper.elements.Canos;
import com.hugo.alberto.jumper.elements.GameOver;
import com.hugo.alberto.jumper.elements.Passaro;
import com.hugo.alberto.jumper.elements.Pontuacao;
import com.hugo.alberto.jumper.graphic.Tela;


public class Game  extends SurfaceView implements Runnable, OnTouchListener {

    private Som som;
    private boolean isRunning= true;
    private SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;
    //private Cano cano;
    private Canos canos;
    private Pontuacao pontuacao;
    private Context context;

    public Game(Context context) {
        super(context);
        this.context=context;
        tela = new Tela(context);
        som = new Som(context);
        inicializaElementos();
        setOnTouchListener(this);
    }

    private void inicializaElementos() {
        
        passaro = new Passaro(tela, context, som);
        pontuacao = new Pontuacao(som);
        canos = new Canos(tela, pontuacao, context);       //cano = new Cano(tela,400);//um cano
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(back, back.getWidth(),tela.getAltura(),false);
    }

    @Override
    public void run() {
        while(isRunning){
            if(!holder.getSurface().isValid()) continue;
            Canvas canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);
            passaro.desenhaNo(canvas);
            passaro.cai();

            canos.desenhaNo(canvas);
            canos.move();

            pontuacao.desenhaNo(canvas);


            if(new VerificadorDeColisao(passaro, canos).temColisao()){
                new GameOver(tela).desenhaNo(canvas);
                som.toca(Som.COLISAO);
                isRunning = false;
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void inicia(){
        isRunning = true;

    }

    public void pausa(){
        isRunning = false;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        passaro.pula();
        return false;
    }
}
