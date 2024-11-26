package Objetos;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class BolaDeFuego {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private Image imagen;
    private boolean ladoDerecho;

    public BolaDeFuego(int x, int y, int ancho, int alto, int velocidad, boolean ladoDerecho) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.ladoDerecho = ladoDerecho;
    }

    public void moverBolaDeFuego() {
        if (ladoDerecho) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
    }

    public void dibujar(Entorno entorno) {
        if (imagen != null) {
            entorno.dibujarImagen(imagen, this.x, this.y, 0, 1.0);
        } else {
            entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, java.awt.Color.ORANGE);
        }
    }

    public boolean fueraDePantalla(int anchoPantalla) {
        return this.x < 0 || this.x > anchoPantalla;
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
}
