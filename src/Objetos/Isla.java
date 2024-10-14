package Objetos;

import java.awt.Color;
import entorno.Entorno;

public class Isla {

    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    public Isla(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }

    public void moverIzquierda() {
        this.x = this.x - velocidad;
    }

    public void moverDerecha() {
        this.x = this.x + velocidad;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.YELLOW);
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
