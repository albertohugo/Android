package com.hugo.alberto.jumper.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.hugo.alberto.jumper.graphic.Cores;
import com.hugo.alberto.jumper.graphic.Tela;

/**
 * Created by Alberto on 16/05/2015.
 */
public class GameOver {
    private Rect limiteDoTexto;
    private Tela tela;
    private static final Paint VERMELHO = Cores.getCorDoGameOver();
    private int centroHorizontal = 0;

    public GameOver(Tela tela) {
        this.tela = tela;

    }
    public void desenhaNo(Canvas canvas) {
        String gameOver = "Game Over";
         centroHorizontal = centralizaTexto(gameOver);
        canvas.drawText(gameOver, centroHorizontal, tela.getAltura()/ 2, VERMELHO);
    }

    private int centralizaTexto(String gameOver) {
        Rect limiteDoTexto = new Rect();
        VERMELHO.getTextBounds(gameOver, 0, gameOver.length(), limiteDoTexto);
        int centroHorizontal = tela.getLargura()/2 - (limiteDoTexto.right - limiteDoTexto.left)/2;
        return centroHorizontal;
    }


}
