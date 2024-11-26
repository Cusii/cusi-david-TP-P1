package Objetos;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Gnomo {
    private int x, y, ancho, alto, velocidad;
    private boolean moviendoseDerecha;
    private boolean aterrizado;
    private Image imagen;
    private Random random;

    public Gnomo(int x, int y, int ancho, int alto, int velocidad, boolean moviendoseDerecha, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.moviendoseDerecha = moviendoseDerecha;
        this.aterrizado = false;
        this.random = new Random();
        this.imagen = Herramientas.cargarImagen(rutaImagen);

    }

    public void moverGnomo() {
        if (moviendoseDerecha) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
    }

    public void moverGnomoAbajo() {
        this.y += velocidad;
        this.aterrizado = false;
    }

    public void cambiarDireccionAleatoria() {
        if (!aterrizado) {
            this.moviendoseDerecha = random.nextBoolean();
            this.aterrizado = true;
        }
    }

    public void dibujar(Entorno entorno) {
        if (imagen != null) {
            entorno.dibujarImagen(imagen, this.x, this.y, 0, 1.0);
        } else {
            entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, java.awt.Color.RED);
        }
    }

    public boolean colisionaConIsla(Isla isla) {
        int limiteIzquierdoGnomo = this.x - this.ancho / 2;
        int limiteDerechoGnomo = this.x + this.ancho / 2;
        int limiteSuperiorGnomo = this.y - this.alto / 2;
        int limiteInferiorGnomo = this.y + this.alto / 2;

        int limiteIzquierdoIsla = isla.getX() - isla.getAncho() / 2;
        int limiteDerechoIsla = isla.getX() + isla.getAncho() / 2;
        int limiteSuperiorIsla = isla.getY() - isla.getAlto() / 2;
        int limiteInferiorIsla = isla.getY() + isla.getAlto() / 2;

        return (limiteDerechoGnomo > limiteIzquierdoIsla && limiteIzquierdoGnomo < limiteDerechoIsla)
                && (limiteInferiorGnomo > limiteSuperiorIsla && limiteSuperiorGnomo < limiteInferiorIsla);
    }

    public boolean colisionaConTortuga(Tortuga tortuga) {
        int limiteIzquierdoGnomo = this.x - this.ancho / 2;
        int limiteDerechoGnomo = this.x + this.ancho / 2;
        int limiteSuperiorGnomo = this.y - this.alto / 2;
        int limiteInferiorGnomo = this.y + this.alto / 2;

        int limiteIzquierdoTortuga = tortuga.getX() - tortuga.getAncho() / 2;
        int limiteDerechoTortuga = tortuga.getX() + tortuga.getAncho() / 2;
        int limiteSuperiorTortuga = tortuga.getY() - tortuga.getAlto() / 2;
        int limiteInferiorTortuga = tortuga.getY() + tortuga.getAlto() / 2;

        return (limiteDerechoGnomo > limiteIzquierdoTortuga && limiteIzquierdoGnomo < limiteDerechoTortuga)
                && (limiteInferiorGnomo > limiteSuperiorTortuga && limiteSuperiorGnomo < limiteInferiorTortuga);
    }

    public boolean colisionaConPep(Pep pep) {
        int limiteIzquierdoGnomo = this.x - this.ancho / 2;
        int limiteDerechoGnomo = this.x + this.ancho / 2;
        int limiteSuperiorGnomo = this.y - this.alto / 2;
        int limiteInferiorGnomo = this.y + this.alto / 2;

        int limiteIzquierdoPep = pep.getX() - pep.getAncho() / 2;
        int limiteDerechoPep = pep.getX() + pep.getAncho() / 2;
        int limiteSuperiorPep = pep.getY() - pep.getAlto() / 2;
        int limiteInferiorPep = pep.getY() + pep.getAlto() / 2;

        return (limiteDerechoGnomo > limiteIzquierdoPep && limiteIzquierdoGnomo < limiteDerechoPep)
                && (limiteInferiorGnomo > limiteSuperiorPep && limiteSuperiorGnomo < limiteInferiorPep);
    }

    public int getY() {
        return y;
    }

    public int getAlto() {
        return alto;
    }

    public boolean getMoverDerecha(){
        return moviendoseDerecha;
    }

    public void setImagen(String link) {
        this.imagen = Herramientas.cargarImagen(link);
    }
}
