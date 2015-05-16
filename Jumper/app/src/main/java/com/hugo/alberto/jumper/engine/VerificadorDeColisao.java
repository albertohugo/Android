package com.hugo.alberto.jumper.engine;

import com.hugo.alberto.jumper.elements.Canos;
import com.hugo.alberto.jumper.elements.Passaro;


/**
 * Created by Alberto on 16/05/2015.
 */
public class VerificadorDeColisao {
    private Canos canos;
    private Passaro passaro;


    public VerificadorDeColisao(Passaro passaro, Canos canos){
         this.passaro = passaro;
         this.canos = canos;

     }

    public boolean temColisao() {
        return canos.temColisaoCom(passaro);
    }
}
