package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

import Objetos.Casa;
import Objetos.Gnomo;
import Objetos.Isla;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Variables y métodos propios de cada grupo
	private Isla[] islas;
	private Casa casa;
	private Gnomo gnomo;

	private boolean moviendoDerecha;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);

		this.islas = new Isla[16];

		int islaNum = 0;
		int yInicial = 100;

        for (int fila = 1; fila <= 5; fila++) {
            int espacioEntreIslas = 200;
            int inicioX = (1000 - (fila * espacioEntreIslas)) / 2; // Calcular el inicio para centrar
			System.out.println("inicioX: " + inicioX);

            // Generar las islas para cada fila
            for (int i = 0; i < fila; i++) {
				System.out.println("fila: " + fila);

                int x = inicioX + (i * espacioEntreIslas);
				System.out.println("x: " + x);
                int y = fila * yInicial;
				System.out.println("y: " +y);

                this.islas[islaNum] = new Isla(x, y, 100, 25, 2);
                islaNum++;
            }
        }



		this.casa = new Casa(400, 60, 50, 50);
		this.gnomo = new Gnomo(400, 60, 50, 50, 2);

		this.moviendoDerecha = true;

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y
	 * por lo tanto es el método más importante de esta clase. Aquí se debe
	 * actualizar el estado interno del juego para simular el paso del tiempo
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {

		// Mover el gnomo automáticamente
		if (this.moviendoDerecha) {
			gnomo.moverDerecha();
			// Cambiar dirección si el gnomo alcanza el borde derecho
			if (gnomo.getX() > this.entorno.ancho() - gnomo.getBase()) {
				this.moviendoDerecha = false; 
			}
		} else {
			gnomo.moverIzquierda();
			// Cambiar dirección si el gnomo alcanza el borde izquierdo
			if (gnomo.getX() < 0) {
				this.moviendoDerecha = true;
			}
		}

		// Dibujar todas las islas
		for (int i = 0; i < this.islas.length; i++) {
			Isla isla = this.islas[i];
			if (isla != null) {
				isla.dibujar(this.entorno);
			}
		}
		casa.dibujar(entorno);
		gnomo.dibujar(entorno);
		// Procesamiento de un instante de tiempo
		// ...

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
