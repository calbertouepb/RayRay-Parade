package com.calbertouepb.rayrayparade;

//View que exibe a tela inicial do jogo (Splash Screen)

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("CustomSplashScreen")
public class SplashScreen  extends View {

    private final Bitmap sprite;
    //private final Bitmap botaoPlay;
    //private final Bitmap botaoPlayAtivado;
    private final Context contextoGame;
    private boolean botaoPlayPressionado;
    private int larguraTela, alturaTela;

    public SplashScreen(Context contexto) {
        super(contexto);
        contextoGame = contexto;
        sprite = BitmapFactory.decodeResource(getResources(), R.drawable.rayray);
        //botaoPlay = BitmapFactory.decodeResource(getResources(),R.drawable.botaoplay);
        //botaoPlayAtivado = BitmapFactory.decodeResource(getResources(), R.drawable.botaoplaypressionado);
    }

    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        larguraTela = w;
        alturaTela = h;
    }

    @Override
    protected void onDraw(Canvas tela) {
        super.onDraw(tela);
        int coordenadaHorizontal = (larguraTela - sprite.getWidth())/2;
        tela.drawBitmap(sprite, coordenadaHorizontal, (int) (alturaTela * 0.1), null);
        //int posicaoBotalPlay = (larguraTela - botaoPlay.getWidth())/2;
        //if (botaoPlayPressionado) {
       //     tela.drawBitmap(botaoPlayAtivado, posicaoBotalPlay, (int) (alturaTela * 0.8), null);
       // } else {
       //     tela.drawBitmap(botaoPlay, posicaoBotalPlay, (int) (alturaTela * 0.8), null);
        //}
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override

    public boolean onTouchEvent(MotionEvent evento) {
        int acao = evento.getAction();

        int coordenadaX = (int) evento.getX();
        int coordenadaY = (int) evento.getY();

        switch(acao) {
            case MotionEvent.ACTION_DOWN:

                //int margemEsquerda = (larguraTela - botaoPlay.getWidth())/2;
                //int margemDireita = margemEsquerda + botaoPlay.getWidth();
                int margemSuperior = (int) (alturaTela * 0.8);
                //int margemInferior = margemSuperior + botaoPlay.getHeight();

                //boolean estaSobreBotaoPlay = coordenadaX > margemEsquerda && coordenadaX < margemDireita &&
                //        coordenadaY > margemSuperior && coordenadaY < margemInferior;

               // if (estaSobreBotaoPlay) {
               //     botaoPlayPressionado = true;
               // }
                break;
            case MotionEvent.ACTION_UP:
                if (botaoPlayPressionado) {
                    Intent gameIntent = new Intent(contextoGame, RayRayParade.class);
                    contextoGame.startActivity(gameIntent);
                }
                botaoPlayPressionado = false;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        invalidate();
        return true;
    }
}
