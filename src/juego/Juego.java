package juego;

import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

import Objetos.Casa;
import Objetos.Gnomo;
import Objetos.Isla;
import Objetos.Pep;
import Objetos.Tortuga;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Variables y métodos propios de cada grupo
	private Isla[] islas;
	private Casa casa;
	private Pep pep;
	private Gnomo[] gnomos;
	Random random = new Random();
	int islaActual = 1;
	int anchoPantalla = 1000;
	Boolean juegoRunning = true;
	Boolean logs = true;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", anchoPantalla, 600);

		this.islas = new Isla[16];
		this.gnomos = new Gnomo[2];

		int islaNum = 0;
		int gnomoNum = 0;
		int yInicial = 100;

		// Generar filas para las islas
		for (int fila = 1; fila <= 5; fila++) {
			int espacioEntreIslas = 200;
			int inicioX = (1200 - (fila * espacioEntreIslas)) / 2; // Calcular el inicio para centrar
			// Generar las islas para cada fila
			for (int i = 0; i < fila; i++) {
				int x = inicioX + (i * espacioEntreIslas);
				int y = fila * yInicial;
				this.islas[islaNum] = new Isla(x, y, 100, 30, 2);
				islaNum++;
			}
		}
		for (int i = 1; i <= 2; i++) {
			this.gnomos[gnomoNum] = new Gnomo((anchoPantalla / 2), 60, 30, 50, 1, random.nextBoolean());
			gnomoNum++;
		}

		this.casa = new Casa((anchoPantalla / 2), 60, 50, 50);
		this.pep = new Pep(75, 460, 30, 50, 2);

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

		// CASA
		casa.dibujar(entorno);

		// GNOMOS

		// Mover el gnomo automáticamente
		for (int i = 0; i < this.gnomos.length; i++) {
			Gnomo gnomo = gnomos[i];
			// Isla isla = this.islas[1];
			Isla isla = nextIsla(islaActual);
			// System.out.println(islaActual);

			if (isla != null) {
				if (gnomo.estaMoviendoseAbajo()) {
					gnomo.moverAbajo();
					if (gnomo.getY() > this.islas[islaActual].getY() - 40) {
						gnomo.detenerMovimientoAbajo();
						islaActual++;
					}
				} else {
					if (gnomo.getMovimiento()) {
						gnomo.moverDerecha();
						// Cambiar dirección si el gnomo alcanza el borde derecho
						if (gnomo.getX() > (isla.getX() + 185)) {
							gnomo.cambiarDireccion();
							gnomo.iniciarMovimientoAbajo();
						}
					} else {
						gnomo.moverIzquierda();
						// Cambiar dirección si el gnomo alcanza el borde izquierdo
						if (gnomo.getX() < (isla.getX() + 15)) {
							gnomo.cambiarDireccion();
							gnomo.iniciarMovimientoAbajo();
						}
					}
				}
			}
			gnomo.dibujar(entorno);

		}
		// ISLAS
		for (int i = 0; i < this.islas.length; i++) {
			Isla isla = this.islas[i];
			if (isla != null) {
				isla.dibujar(this.entorno);
			}
		}

		// PEP
		if (juegoRunning) {
			// Isla isla = this.islas[10];
			// System.out.println("pep: " + pep.getX());
			// System.out.println("pep: " + pep.getAncho());
			// System.out.println("isla: " + (isla.getX() + isla.getAncho()));
			// System.out.println("isla test : " + pep.getY());
			// System.out.println("isla test : " + (isla.getY() - isla.getAlto()));
			//
			// if ((pep.getX() >= isla.getX() - (pep.getX() + pep.getAncho()) && pep.getX()
			// <= 170)
			// && pep.getY() <= isla.getY() - isla.getAlto()) {
			// controlIslasPep(10);
			// } else if ((pep.getX() >= isla.getX() - (pep.getX() + pep.getAncho()) &&
			// pep.getX() <= 170)
			// && pep.getY() <= isla.getY() - isla.getAlto()) {
			// controlIslasPep(11);
			// }

			for (int isl = 1; isl < this.islas.length; isl++) {
				Isla isla = this.islas[isl];
				if (isla != null) {

				if ((pep.getX() >= isla.getX() - (pep.getX() + pep.getAncho()) && pep.getX() <= 170)
						&& pep.getY() <= isla.getY() - isla.getAlto()) {
					controlIslasPep(isl);
				} else if ((pep.getX() >= isla.getX() - (pep.getX() + pep.getAncho()) && pep.getX() <= 170)
						&& pep.getY() <= isla.getY() - isla.getAlto()) {
					controlIslasPep(isl);
				}
				}

			}

		} else if (logs) {
			System.out.println("Fin del juego: pepe cayo del mundo");
			logs = false;
		}
		entorno.escribirTexto(".test.", 400, 500);
		entorno.escribirTexto("_480", 100, 480);

		pep.dibujar(entorno);

	}

	private Isla nextIsla(int numIslaActual) {
		Isla isla = this.islas[numIslaActual];
		if (isla != null) {
			return isla;
		} else {
			return null;
		}

	}

	private void moverPep() {
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			pep.iniciarSalto();
		}
		pep.actualizarSalto();
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && pep.getX() < entorno.ancho() - pep.getAncho()) {
			pep.moverDerecha();
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && pep.getX() > 0 + pep.getAncho()) {
			pep.moverIzquierda();
		}
	}

	private void controlIslasPep(int isla) {
		Isla islaActual = this.islas[isla];
		int limiteIzquierdoIsla = islaActual.getX() - (islaActual.getAncho());
		int limiteDerechoIsla = islaActual.getX() + (islaActual.getAncho());
		int limiteIzquierdoPep = pep.getX() - (pep.getAncho());
		int limiteDerechoPep = pep.getX() + (pep.getAncho());

		// add control when pep is outside screen cant move

		if ((limiteIzquierdoPep >= limiteIzquierdoIsla && limiteDerechoPep <= limiteDerechoIsla)) {
			moverPep();
		} else {
			pep.moverAbajo();
			if (pep.getY() <= 500) {
				moverPep();
			}
			if (pep.getY() - pep.getAlto() >= this.entorno.alto()) {
				juegoRunning = false;
			}

		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
