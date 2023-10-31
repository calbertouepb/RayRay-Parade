package com.calbertouepb.rayrayparade;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class RayRayParade extends AppCompatActivity {//implements RayListener{

    private View gameView;
    private ViewGroup conteudoView;
    private int larguraTela, alturaTela, nivel;
    private TextView displayNivel;
    private TextView displayScore;
    private int pontuacaoUsuario;

    AnimationDrawable splashAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //gameView = new RayRayView(this);
        //gameView.setKeepScreenOn(true);
        //setContentView(gameView);
        setContentView(R.layout.activity_ray_ray);
        ImageView splashImage = (ImageView) findViewById(R.id.ray1);
        splashImage.setBackgroundResource(R.drawable.animacao_ray_correndo);
        splashAnimation = (AnimationDrawable) splashImage.getBackground();
        splashImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splashAnimation.start();
            }
        });
        /*conteudoView = (ViewGroup) findViewById(R.id.content_view);
        displayNivel = (TextView) findViewById(R.id.level_display);
        displayScore = (TextView) findViewById(R.id.score_display);
        conteudoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent evento) {
                RayRay ray = new RayRay(RayRays.this, 100, 1);
                ray.setY(alturaTela);
                ray.setX(evento.getX());
                conteudoView.addView(ray);
                ray.release(alturaTela, 2000);
                if (evento.getAction() == MotionEvent.ACTION_DOWN) {
                    setToFullScreen();
                }
                return false;
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();

        /*ViewTreeObserver viewTreeObserver = conteudoView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    conteudoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    larguraTela = conteudoView.getWidth();
                    alturaTela = conteudoView.getHeight();
                }
            });
        }*/
    }

    private void setToFullScreen() {
        gameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /*private void iniciarNivel() {
        nivel++;
        new LoopNivel(nivel).start();
        displayNivel.setText(String.format("%s", (int) nivel));
    }

    public void lancaRay(int posicaoX) {
        RayRay rayTemporario = new RayRay(RayRayParade.this, 100, 1);
        rayTemporario.setY(larguraTela);
        rayTemporario.setX(posicaoX);

        conteudoView.addView(rayTemporario);
        rayTemporario.release(larguraTela, 2000);
    }*/

    /*@Override
    public void touchRay(RayRay theRay, boolean foiTocado) {
        conteudoView.removeView(theRay);
        if (foiTocado) {
            pontuacaoUsuario++;
            displayScore.setText(String.format("%d", pontuacaoUsuario));
        }
    }*/

    /*class LoopNivel extends Thread {
        private int raysLancados = 0;
        private int nivelLoop, atrasoMaximo, atrasoMinimo, atraso;
        private int atrasoCurto = 500;
        private int atrasoLongo = 1_500;

        public LoopNivel(int argumento) {
            nivelLoop = argumento;
        }


        public void run() {
            while (raysLancados <=25) {
                raysLancados++;
                Random random = new Random();
                final int posicaoX = random.nextInt(larguraTela - 200);
                atrasoMaximo = Math.max(atrasoMinimo, (atrasoLongo - ((nivelLoop-1)) * 500));
                atrasoMinimo = atrasoMaximo/2;
                atraso = random.nextInt(atrasoMinimo) + atrasoMinimo;

                Log.i(TAG, String.format("Thread Delay = %d", atraso));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }

                runOnUiThread(new Thread() {
                    public void run() {
                        lancaRay(posicaoX);
                    }
                });
            }
        }
}*/
}