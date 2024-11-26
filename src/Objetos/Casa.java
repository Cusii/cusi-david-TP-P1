package Objetos;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {

    private int x, y, ancho, alto;
    private Image imagen;

    public Casa(int x, int y, int ancho, int alto, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = Herramientas.cargarImagen(rutaImagen);
    }

    public void dibujar(Entorno entorno) {
        if (imagen != null) {
            entorno.dibujarImagen(imagen, this.x, this.y, 0, 1.0);
        } else {
            entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
        }
    }
}
