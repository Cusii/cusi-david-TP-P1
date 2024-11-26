package Objetos;

import java.awt.Color;

import entorno.Entorno;

public class Tortuga {
    private int x, y, ancho, alto, velocidad;
    private boolean moviendoseDerecha;
    private boolean moverAbajo;

    public Tortuga(int x, int y, int ancho, int alto, int velocidad, Boolean moviendoseDerecha) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.moviendoseDerecha = moviendoseDerecha;
        this.moverAbajo = true;

    }

    public void cambiarDireccion() {
        this.moviendoseDerecha = !this.moviendoseDerecha;
    }

    public void apareceTortuga() {
        if (moverAbajo) {
            this.y += velocidad;
        }
    }

    public void mover() {
        this.moverAbajo = false;
        if (moviendoseDerecha) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
    }

    public boolean colisionaConIsla(Isla isla) {
        int limiteIzquierdoTortuga = this.x - this.ancho / 2;
        int limiteDerechoTortuga = this.x + this.ancho / 2;
        int limiteSuperiorTortuga = this.y - this.alto / 2;
        int limiteInferiorTortuga = this.y + this.alto / 2;

        int limiteIzquierdoIsla = isla.getX() - isla.getAncho() / 2;
        int limiteDerechoIsla = isla.getX() + isla.getAncho() / 2;
        int limiteSuperiorIsla = isla.getY() - isla.getAlto() / 2;
        int limiteInferiorIsla = isla.getY() + isla.getAlto() / 2;

        return (limiteDerechoTortuga > limiteIzquierdoIsla && limiteIzquierdoTortuga < limiteDerechoIsla)
                && (limiteInferiorTortuga > limiteSuperiorIsla && limiteSuperiorTortuga < limiteInferiorIsla);
    }

    public boolean colisionaConBolaDeFuego(BolaDeFuego bolaDeFuego) {
        int limiteIzquierdoTortuga = this.x - this.ancho / 2;
        int limiteDerechoTortuga = this.x + this.ancho / 2;
        int limiteSuperiorTortuga = this.y - this.alto / 2;
        int limiteInferiorTortuga = this.y + this.alto / 2;

        int limiteIzquierdoBolaDeFuego = bolaDeFuego.getX() - bolaDeFuego.getAncho() / 2;
        int limiteDerechoBolaDeFuego = bolaDeFuego.getX() + bolaDeFuego.getAncho() / 2;
        int limiteSuperiorBolaDeFuego = bolaDeFuego.getY() - bolaDeFuego.getAlto() / 2;
        int limiteInferiorBolaDeFuego = bolaDeFuego.getY() + bolaDeFuego.getAlto() / 2;

        return (limiteDerechoTortuga > limiteIzquierdoBolaDeFuego && limiteIzquierdoTortuga < limiteDerechoBolaDeFuego)
                && (limiteInferiorTortuga > limiteSuperiorBolaDeFuego
                        && limiteSuperiorTortuga < limiteInferiorBolaDeFuego);
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

    public boolean getMoverAbajo() {
        return moverAbajo;
    }

}
