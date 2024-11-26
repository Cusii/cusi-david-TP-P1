package Objetos;

import java.awt.Color;
import entorno.Entorno;

public class Casa {

    private int x, y, ancho, alto;

    public Casa(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
    }
}
