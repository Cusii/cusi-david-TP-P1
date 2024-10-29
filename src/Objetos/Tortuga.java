package Objetos;

import java.awt.Color;

import entorno.Entorno;

public class Tortuga {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private boolean moverDerecha;
    private boolean moverAbajo;

    public Tortuga(int x, int y, int ancho, int alto, int velocidad, Boolean moverDerecha) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.moverDerecha = moverDerecha;
        this.moverAbajo = true; // Empieza sin moverse hacia abajo

    }

    public void cambiarDireccion() {
        this.moverDerecha = !this.moverDerecha;
    }

    public void iniciarMovimientoAbajo() {
        this.moverAbajo = true;
    }

    public void detenerMovimientoAbajo() {
        this.moverAbajo = false;
    }

    public void moverIzquierda() {
        this.x -= velocidad;
    }

    public void moverDerecha() {
        this.x += velocidad;
    }

    public void moverAbajo() {
        this.y += velocidad;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean getMovimiento() {
        return moverDerecha;
    }

    public boolean estaMoviendoseAbajo() {
        return moverAbajo;
    }
}
