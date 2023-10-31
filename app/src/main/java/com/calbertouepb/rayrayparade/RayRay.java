package com.calbertouepb.rayrayparade;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

public class RayRay {
    private final int idRay;
    private boolean estaAtivo;
    private boolean estaEmPe;
    private final ImageView imagem;
    private final ArrayList<RayRay> adjacentes = new ArrayList<>();
    private final RayListener mainActivity;
    public RayRay (Context contexto, int id, ImageView imagem) {
        mainActivity = (RayListener) contexto;
        this.idRay = id;
        this.estaAtivo = false;
        this.estaEmPe = false;
        this.imagem = imagem;
    }
    public void toca(ImageView imagem) {
        mainActivity.tocaRay(this, imagem);
    }
    public void levantaRay(ImageView imagem) {
        imagem.setBackgroundResource(R.drawable.animacao_ray_levantando);
        imagem.setBackgroundResource(R.drawable.animacao_ray_em_pe);
        this.estaEmPe = true;
    }
    public void abaixaRay(ImageView imagem) {
        imagem.setBackgroundResource(R.drawable.animacao_ray_abaixando);
        imagem.setBackgroundResource(R.drawable.animacao_ray_abaixado);
        this.estaEmPe = false;
    }
    public int getId() {
        return idRay;
    }
    public ImageView getImagem() {
        return imagem;
    }
    public Iterator<RayRay> getAdjacentes() {
        return adjacentes.iterator();
    }
    public void setAdjacentes(RayRay ray) {
        adjacentes.add(ray);
    }
    public boolean estaEmPe() {
        return this.estaEmPe;
    }
    public boolean estaAtivo() {
        return this.estaAtivo;
    }
    public void setEstaAtivo(boolean estado) {
        this.estaAtivo = estado;
    }
}
