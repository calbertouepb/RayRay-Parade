package com.calbertouepb.rayrayparade;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements RayListener{

    private final String TAG = getClass().getName();
    private ImageButton startOver, resetTable, howToPlay, easy, medium, hard;
    private TextView movimentos;
    private ImageView view;
    private ArrayList<ImageView> views = new ArrayList<ImageView>();
    private ArrayList<RayRay> rayrays = new ArrayList<RayRay>();
    private HashMap<Integer, ArrayList<String>> niveis = new HashMap<Integer, ArrayList<String>>();
    ArrayList<String> subNiveis = new ArrayList<String>();
    private int totalDeRays = 25;
    private int dificuldade = 1;
    private int subNivelAtual = 1;
    private String stringSubNivel;
    private InputStream inputStream;
    private int pontuacao;
    private boolean resolveuNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ray_ray);
        criaBotoes();
        configuraViews();
        criaRays();
        carregaNiveis();
        criaNivel(dificuldade);
    }
    private void criaBotoes() {
        //Associa as respectivas views e atribui a função de cada um
        startOver = findViewById(R.id.startOver);
        startOver.setOnClickListener(view -> reiniciaJogo());
        resetTable = findViewById(R.id.resetTable);
        resetTable.setOnClickListener(view -> reiniciaNivel());
        howToPlay = findViewById(R.id.howToPlay);
        howToPlay.setOnClickListener(view -> mostraInstrucoes());
        easy = findViewById(R.id.easyButton);
        easy.setOnClickListener(view -> setNivelFacil());
        medium = findViewById(R.id.mediumButton);
        medium.setOnClickListener(view -> setNivelMedio());
        hard = findViewById(R.id.hardButton);
        hard.setOnClickListener(view -> setNivelDificil());
        movimentos = (TextView) findViewById(R.id.score_moves);

    }
    private void configuraViews() {
        view = (ImageView) findViewById(R.id.ray1);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray2);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray3);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray4);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray5);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray6);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray7);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray8);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray9);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray10);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray11);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray12);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray13);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray14);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray15);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray16);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray17);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray18);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray19);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray20);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray21);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray22);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray23);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray24);
        views.add(view);
        view = (ImageView) findViewById(R.id.ray25);
        views.add(view);

    }
    private void criaRays() {
        for (int i = 0; i < totalDeRays; i++) {
            RayRay oRay = new RayRay(this, i, views.get(i));
            rayrays.add(oRay);
        }
        criaAdjacentes();
    }
    private void criaAdjacentes() {
        for (int i = 0; i < rayrays.size(); i++) {
            switch(i) {
                case 0:
                rayrays.get(0).setAdjacentes(rayrays.get(1));
                rayrays.get(0).setAdjacentes(rayrays.get(2));
                break;
                case 1:
                    rayrays.get(1).setAdjacentes(rayrays.get(0));
                    rayrays.get(1).setAdjacentes(rayrays.get(3));
                    rayrays.get(1).setAdjacentes(rayrays.get(4));
                    break;
                case 2:
                    rayrays.get(2).setAdjacentes(rayrays.get(0));
                    rayrays.get(2).setAdjacentes(rayrays.get(4));
                    rayrays.get(2).setAdjacentes(rayrays.get(5));
                    break;
                case 3:
                    rayrays.get(3).setAdjacentes(rayrays.get(1));
                    rayrays.get(3).setAdjacentes(rayrays.get(6));
                    rayrays.get(3).setAdjacentes(rayrays.get(7));
                    break;
                case 4:
                    rayrays.get(4).setAdjacentes(rayrays.get(1));
                    rayrays.get(4).setAdjacentes(rayrays.get(2));
                    rayrays.get(4).setAdjacentes(rayrays.get(7));
                    rayrays.get(4).setAdjacentes(rayrays.get(8));
                    break;
                case 5:
                    rayrays.get(5).setAdjacentes(rayrays.get(2));
                    rayrays.get(5).setAdjacentes(rayrays.get(8));
                    rayrays.get(5).setAdjacentes(rayrays.get(9));
                    break;
                case 6:
                    rayrays.get(6).setAdjacentes(rayrays.get(3));
                    rayrays.get(6).setAdjacentes(rayrays.get(10));
                    rayrays.get(6).setAdjacentes(rayrays.get(11));
                    break;
                case 7:
                    rayrays.get(7).setAdjacentes(rayrays.get(3));
                    rayrays.get(7).setAdjacentes(rayrays.get(4));
                    rayrays.get(7).setAdjacentes(rayrays.get(11));
                    rayrays.get(7).setAdjacentes(rayrays.get(12));
                    break;
                case 8:
                    rayrays.get(8).setAdjacentes(rayrays.get(4));
                    rayrays.get(8).setAdjacentes(rayrays.get(5));
                    rayrays.get(8).setAdjacentes(rayrays.get(12));
                    rayrays.get(8).setAdjacentes(rayrays.get(13));
                    break;
                case 9:
                    rayrays.get(9).setAdjacentes(rayrays.get(5));
                    rayrays.get(9).setAdjacentes(rayrays.get(13));
                    rayrays.get(9).setAdjacentes(rayrays.get(14));
                    break;
                case 10:
                    rayrays.get(10).setAdjacentes(rayrays.get(6));
                    rayrays.get(10).setAdjacentes(rayrays.get(15));
                    break;
                case 11:
                    rayrays.get(11).setAdjacentes(rayrays.get(6));
                    rayrays.get(11).setAdjacentes(rayrays.get(7));
                    rayrays.get(11).setAdjacentes(rayrays.get(15));
                    rayrays.get(11).setAdjacentes(rayrays.get(16));
                    break;
                case 12:
                    rayrays.get(12).setAdjacentes(rayrays.get(7));
                    rayrays.get(12).setAdjacentes(rayrays.get(8));
                    rayrays.get(12).setAdjacentes(rayrays.get(16));
                    rayrays.get(12).setAdjacentes(rayrays.get(17));
                    break;
                case 13:
                    rayrays.get(13).setAdjacentes(rayrays.get(8));
                    rayrays.get(13).setAdjacentes(rayrays.get(9));
                    rayrays.get(13).setAdjacentes(rayrays.get(17));
                    rayrays.get(13).setAdjacentes(rayrays.get(18));
                    break;
                case 14:
                    rayrays.get(14).setAdjacentes(rayrays.get(9));
                    rayrays.get(14).setAdjacentes(rayrays.get(18));
                    break;
                case 15:
                    rayrays.get(15).setAdjacentes(rayrays.get(10));
                    rayrays.get(15).setAdjacentes(rayrays.get(11));
                    rayrays.get(15).setAdjacentes(rayrays.get(19));
                    break;
                case 16:
                    rayrays.get(16).setAdjacentes(rayrays.get(11));
                    rayrays.get(16).setAdjacentes(rayrays.get(12));
                    rayrays.get(16).setAdjacentes(rayrays.get(19));
                    rayrays.get(16).setAdjacentes(rayrays.get(21));
                    break;
                case 17:
                    rayrays.get(17).setAdjacentes(rayrays.get(12));
                    rayrays.get(17).setAdjacentes(rayrays.get(13));
                    rayrays.get(17).setAdjacentes(rayrays.get(20));
                    rayrays.get(17).setAdjacentes(rayrays.get(21));
                    break;
                case 18:
                    rayrays.get(18).setAdjacentes(rayrays.get(13));
                    rayrays.get(18).setAdjacentes(rayrays.get(14));
                    rayrays.get(18).setAdjacentes(rayrays.get(21));
                    break;
                case 19:
                    rayrays.get(19).setAdjacentes(rayrays.get(15));
                    rayrays.get(19).setAdjacentes(rayrays.get(16));
                    rayrays.get(19).setAdjacentes(rayrays.get(22));
                    break;
                case 20:
                    rayrays.get(20).setAdjacentes(rayrays.get(16));
                    rayrays.get(20).setAdjacentes(rayrays.get(17));
                    rayrays.get(20).setAdjacentes(rayrays.get(22));
                    rayrays.get(20).setAdjacentes(rayrays.get(23));
                    break;
                case 21:
                    rayrays.get(21).setAdjacentes(rayrays.get(17));
                    rayrays.get(21).setAdjacentes(rayrays.get(18));
                    rayrays.get(21).setAdjacentes(rayrays.get(23));
                    break;
                case 22:
                    rayrays.get(22).setAdjacentes(rayrays.get(19));
                    rayrays.get(22).setAdjacentes(rayrays.get(20));
                    rayrays.get(22).setAdjacentes(rayrays.get(24));
                    break;
                case 23:
                    rayrays.get(23).setAdjacentes(rayrays.get(20));
                    rayrays.get(23).setAdjacentes(rayrays.get(21));
                    rayrays.get(23).setAdjacentes(rayrays.get(24));
                    break;
                case 24:
                    rayrays.get(24).setAdjacentes(rayrays.get(22));
                    rayrays.get(24).setAdjacentes(rayrays.get(23));
                    break;
            }
        }
    }
    private void carregaNiveis() {
        try {
            inputStream = getApplicationContext().getAssets().open("levels.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            int indiceMapa = 0;
            String line;
            ArrayList<String> linhas = new ArrayList<String>();
            while((line = br.readLine( )) != null) {
                if (line.length( ) == 0) // blank line
                    continue;
                if (line.startsWith("//")) // comment
                    continue;
                if (line.startsWith("Nivel")) {
                    niveis.put(indiceMapa, linhas);
                    indiceMapa++;
                    continue;
                }
                linhas.add(line);
                Log.d(TAG, "Carregou Niveis");
                }
            br.close( );
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    } // fim de carregaNiveis()
    private void criaNivel(int nivel) {
        subNiveis = niveis.get(nivel);
        Log.d(TAG, "Pegou niveis");
        criaSubNivel((String) subNiveis.get(subNivelAtual-1));
    }
    private void criaSubNivel(String subNivel) {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).clearAnimation();
            rayrays.get(i).setEstaAtivo(false);
        }
        stringSubNivel = subNivel;
        Scanner scanner = new Scanner(subNivel);
        while (scanner.hasNext()) {
            // o índice precisa ser rebaixado porque o arquivo trabalha com indexação 1 ao invés de indexação 0
            int indice = (int) scanner.nextInt() - 1;
            ImageView imagem = views.get(indice);
            imagem.setBackgroundResource(R.drawable.animacao_ray_abaixado);
            imagem.setOnClickListener(view -> rayrays.get(indice).toca(imagem));
            rayrays.get(indice).setEstaAtivo(true);
        }
    }
    private void reiniciaJogo() {
        dificuldade = 1;
        subNivelAtual = 1;
        pontuacao = 0;
        movimentos.setText("000");
        criaNivel(dificuldade);
    }
    private void reiniciaNivel() {
        criaSubNivel(stringSubNivel);
    }
    private void mostraInstrucoes() {
    }
    private void setNivelFacil() {
        pontuacao = 0;
        dificuldade = 1;
        subNivelAtual = 1;
        movimentos.setText("000");
        criaNivel(dificuldade);
    }
    private void setNivelMedio() {
        pontuacao = 0;
        dificuldade = 2;
        subNivelAtual = 1;
        movimentos.setText("000");
        criaNivel(dificuldade);
    }
    private void setNivelDificil() {
        pontuacao = 0;
        dificuldade = 3;
        subNivelAtual = 1;
        movimentos.setText("000");
        criaNivel(dificuldade);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setToFullScreen();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
    private void setToFullScreen() {
        //Habilita o modo imersivo normal.
        //Para o modo "lean back" remover SYSTEM_UI_FLAG_IMMERSIVE.
        //Ou para o modo imersivo fixo substituir por SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View tela = getWindow().getDecorView();
        tela.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                //Set the content to appear under the system bar so that the
                //content doesn't resize when the system bars hide and show.
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    @Override
    public void tocaRay(RayRay oRay, ImageView imagem) {
            pontuacao++;
            movimentos.setText(String.valueOf(pontuacao));
            Log.d(TAG, "Ray " + String.valueOf(oRay.getId()));
            if (oRay.estaEmPe()) {
                oRay.abaixaRay(imagem);
                Log.d(TAG, "Ray está em pé? " + String.valueOf(oRay.estaEmPe()));
                Iterator<RayRay> adjacentes = oRay.getAdjacentes();
                while (adjacentes.hasNext()) {
                    RayRay ray = adjacentes.next();
                    if (ray.estaAtivo()) {
                        Log.d(TAG, "Ray adjacente " + String.valueOf(ray.getId()));
                        ray.abaixaRay(ray.getImagem());
                        Log.d(TAG, "Ray está em pé? " + String.valueOf(oRay.estaEmPe()));
                    }
                }
                testaSolucao();
            } else {
                oRay.levantaRay(imagem);
                Log.d(TAG, "Ray está em pé? " + String.valueOf(oRay.estaEmPe()));
                Iterator<RayRay> adjacentes = oRay.getAdjacentes();
                while (adjacentes.hasNext()) {
                    RayRay ray = adjacentes.next();
                    if (ray.estaAtivo()) {
                        Log.d(TAG, "Ray adjacente " + String.valueOf(ray.getId()));
                        ray.levantaRay(ray.getImagem());
                        Log.d(TAG, "Ray adjacente está em pé? " + String.valueOf(oRay.estaEmPe()));
                    }
                }testaSolucao();
            }
        if (resolveuNivel) {
            finalizaNivel();
        }
    }

    private void testaSolucao() {
        Log.d(TAG, "Testa solucao");
        Log.d(TAG, "Solucao antes: " + String.valueOf(resolveuNivel));
        boolean resolveu = true;
        for (int i = 0; i <rayrays.size(); i++) {
            if(rayrays.get(i).estaAtivo()) {
                Log.d(TAG, "Ray " + String.valueOf(rayrays.get(i)) + " está em pé? " + String.valueOf(rayrays.get(i).estaEmPe()));
                resolveu = resolveu && rayrays.get(i).estaEmPe();
            }
        }
        resolveuNivel = resolveu;
        Log.d(TAG, "Solucao depois: " + String.valueOf(resolveuNivel));
    }

    private void finalizaNivel() {
        Log.d(TAG, "Resolveu");
        /*Iterator iterador = subNiveis.iterator();
        while (iterador.hasNext()) {
            subNivelAtual++;
            for (int i = 0; i < rayrays.size(); i++) {
                rayrays.get(i).setEstaAtivo(false);
                rayrays.get(i).getImagem().setImageDrawable(null);
            }
        }*/
        while (subNiveis.iterator().hasNext()){
            subNivelAtual++;
        stringSubNivel = subNiveis.iterator().next();
        criaSubNivel(stringSubNivel);
    }
        dificuldade++;
        criaNivel(dificuldade);
    }

    /*class LoopNivel extends Thread {

        private final int nivel;
        private int raysCriados;

        public LoopNivel(int nivel) {
            this.nivel = nivel;
        }

        public void run() {
            RayRay ray;
            switch (nivel) {
                case 1:
                    Log.d(TAG, "criando rays do nivel");
                    rayrays[2][1].setImagem(findViewById(R.id.ray8));
                    rayrays[2][1].getImagem().setBackgroundResource(R.drawable.animacao_ray_abaixado);
                    rayrays[2][1].setAdjacente(rayrays[2][2]);
                    rayrays[2][1].setAdjacente(rayrays[3][1]);
                    rayrays[2][2].setImagem(findViewById(R.id.ray13));
                    rayrays[2][2].getImagem().setBackgroundResource(R.drawable.animacao_ray_abaixado);
                    rayrays[2][2].setAdjacente(rayrays[2][1]);
                    rayrays[2][2].setAdjacente(rayrays[2][3]);
                    rayrays[2][2].setAdjacente(rayrays[3][3]);

            }
            while(true) {
                runOnUiThread(new Thread() {
                    public void run() {

                        //TODO
                    }
                });

            }
        }
    }*/
}