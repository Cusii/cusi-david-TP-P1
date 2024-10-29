package Objetos;

import java.awt.Color;

import entorno.Entorno;

public class Pep {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private int velocidadVertical;
    private boolean saltando;
    private boolean cayendo;

    public Pep(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.velocidadVertical = 0;
        this.saltando = false;
        this.cayendo = false;

    }

    public void iniciarSalto() {
        if (!saltando) {
            saltando = true;
            velocidadVertical = -15;
        }
    }

    public void actualizarSalto() {
        if (saltando) {
            y += velocidadVertical;
            velocidadVertical += 1; // Incrementa para simular gravedad

            // Verifica si ha llegado al suelo (o la isla en este caso)
            if (y >= 460) { // Suponiendo que 460 es la posición base
                y = 460;
                saltando = false;
                velocidadVertical = 0;
            }
        }
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

    public void cayendo() {
        this.cayendo = true;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
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
    public boolean getCayendo() {
        return cayendo;
    }

    public void setY(int y) {
        this.y = y;
    }

}
