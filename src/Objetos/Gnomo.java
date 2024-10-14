package Objetos;

import java.awt.Color;

import entorno.Entorno;

public class Gnomo {
    private int x;
    private int y;
    private int altura;
    private int base;
    private int velocidad;

    public Gnomo(int x, int y, int altura, int base, int velocidad) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.base = base;
        this.velocidad = velocidad;
    }

    public void moverIzquierda() {
        this.x = this.x - velocidad;
    }

    public void moverDerecha() {
        this.x = this.x + velocidad;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarTriangulo(this.x, this.y, this.altura, this.base, 90, Color.RED);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAltura() {
        return altura;
    }

    public int getBase() {
        return base;
    }

    public int getVelocidad() {
        return velocidad;
    }

}
