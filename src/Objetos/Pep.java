package Objetos;

import java.awt.Color;
import entorno.Entorno;

public class Pep {
    private int x, y, ancho, alto, velocidad;
    private int velocidadVertical;
    private boolean saltando;

    public Pep(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.velocidadVertical = 0;
        this.saltando = false;
    }

    public void iniciarSalto() {
        if (!saltando) {
            saltando = true;
            velocidadVertical = -15; // Fuerza inicial hacia arriba
        }
    }

    public void actualizarSalto(Isla[] islas) {
        boolean sobreUnaIsla = false;

        for (Isla isla : islas) {
            if (isla != null && colisionaConIsla(isla)) {
                System.out.println(sobreUnaIsla);

                sobreUnaIsla = true;
                // Detener la caída y ajustar posición encima de la isla
                y = isla.getY() - this.alto + 8;
                saltando = false;
                velocidadVertical = 0;
                break;
            }
        }
        // Si no está sobre una isla, aplicar gravedad
        if (!sobreUnaIsla) {

            y += velocidadVertical;
            velocidadVertical += 1; // Gravedad

            // Limitar caída al suelo base
            if (y >= 600) {
                juegoPerdido();
            }
        }
    }

    public void moverIzquierda() {
        x -= velocidad;
    }

    public void moverDerecha() {
        x += velocidad;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
    }

    public boolean colisionaConIsla(Isla isla) {
        int limiteIzquierdoPep = this.x - this.ancho / 2;
        int limiteDerechoPep = this.x + this.ancho / 2;
        int limiteInferiorPep = this.y + this.alto / 2;

        int limiteIzquierdoIsla = isla.getX() - isla.getAncho() / 2;
        int limiteDerechoIsla = isla.getX() + isla.getAncho() / 2;
        int limiteSuperiorIsla = isla.getY() - isla.getAlto() / 2;

        return (limiteDerechoPep > limiteIzquierdoIsla && limiteIzquierdoPep < limiteDerechoIsla)
                && (limiteInferiorPep >= limiteSuperiorIsla && limiteInferiorPep <= isla.getY());
    }

    public boolean colisionaConTortuga(Tortuga tortuga) {
        int limiteIzquierdoPep = this.x - this.ancho / 2;
        int limiteDerechoPep = this.x + this.ancho / 2;
        int limiteSuperiorPep = this.y - this.alto / 2;
        int limiteInferiorPep = this.y + this.alto / 2;

        int limiteIzquierdoTortuga = tortuga.getX() - tortuga.getAncho() / 2;
        int limiteDerechoTortuga = tortuga.getX() + tortuga.getAncho() / 2;
        int limiteSuperiorTortuga = tortuga.getY() - tortuga.getAlto() / 2;
        int limiteInferiorTortuga = tortuga.getY() + tortuga.getAlto() / 2;

        return (limiteDerechoPep > limiteIzquierdoTortuga && limiteIzquierdoPep < limiteDerechoTortuga)
                && (limiteInferiorPep > limiteSuperiorTortuga && limiteSuperiorPep < limiteInferiorTortuga);
    }

    public void juegoPerdido() {
        System.out.println("Pep ha salido de la pantalla. Fin del juego.");
        // Aquí puedes manejar el reinicio o la finalización del juego
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
}
