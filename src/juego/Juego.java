package juego;

import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;
import Objetos.BolaDeFuego;
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
	private Tortuga[] tortugas;
	// private BolaDeFuego[] bolaDeFuego;
	private ArrayList<BolaDeFuego> bolasDeFuego;

	Random random = new Random();
	int islaActual = 1;
	int anchoPantalla = 1000;
	Boolean juegoRunning = true;
	Boolean logs = true;

	int gnomosSalvados = 0;
	int gnomosPerdidos = 0;
	int tortugasDerrotadas = 0;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", anchoPantalla, 600);

		this.islas = new Isla[16];
		this.gnomos = new Gnomo[4];
		this.tortugas = new Tortuga[6];
		// this.bolaDeFuego = new BolaDeFuego[6];
		this.bolasDeFuego = new ArrayList<>();
		// this.imagenIsla = Herramientas.cargarImagen("img/isla.png");

		int islaNum = 0;
		int gnomoNum = 0;
		int tortugaNum = 0;
		int yInicial = 100;

		// Generar filas para las islas
		for (int fila = 1; fila <= 5; fila++) {
			int espacioEntreIslas = 200;
			int inicioX = (1200 - (fila * espacioEntreIslas)) / 2; // Calcular el inicio para centrar
			// Generar las islas para cada fila
			for (int i = 0; i < fila; i++) {
				int x = inicioX + (i * espacioEntreIslas);
				int y = fila * yInicial;
				this.islas[islaNum] = new Isla(x, y, 100, 30, 2, "img/isla.png");
				islaNum++;
			}
		}
		for (int i = 1; i <= gnomos.length; i++) {
			this.gnomos[gnomoNum] = crearNuevoGnomo();
			gnomoNum++;
		}

		for (int i = 0; i < tortugas.length; i++) {
			this.tortugas[tortugaNum] = crearNuevaTortuga();
			tortugaNum++;

		}

		this.casa = new Casa((anchoPantalla / 2), 60, 60, 60, "img/casa.png");
		this.pep = new Pep(75, 400, 30, 50, 2, true, "img/pepDerecha.png");

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

		// ISLAS
		for (Isla isla : islas) {
			if (isla != null) {
				isla.dibujar(this.entorno);
			}
		}

		// GNOMOS
		for (int i = 0; i < gnomos.length; i++) {
			Gnomo gnomo = gnomos[i];
			boolean sobreUnaIsla = false;

			for (Isla isla : islas) {
				if (isla != null && gnomo.colisionaConIsla(isla)) {
					sobreUnaIsla = true;
					gnomo.cambiarDireccionAleatoria();
					if (!gnomo.getMoverDerecha()) {
						gnomo.setImagen("img/gnomoDerecha.png");
					} else {
						gnomo.setImagen("img/gnomoIzquierda.png");
					}
					break;
				}
			}
			for (Tortuga tortuga : tortugas) {
				if (tortuga != null && gnomo.colisionaConTortuga(tortuga)) {
					gnomosPerdidos++;
					gnomos[i] = crearNuevoGnomo();
					break;
				}
			}
			if (!sobreUnaIsla) {
				gnomo.moverGnomoAbajo();
			} else {
				gnomo.moverGnomo();
			}

			if (gnomo.getY() - gnomo.getAlto() > entorno.alto()) {
				gnomosPerdidos++;
				gnomos[i] = crearNuevoGnomo();
			}
			if (gnomo.colisionaConPep(pep)) {
				gnomosSalvados++;
				gnomos[i] = crearNuevoGnomo();
			}

			gnomo.dibujar(entorno);
		}

		// TOTTUGAS
		for (int i = 0; i < tortugas.length; i++) {
			Tortuga tortuga = tortugas[i];
			boolean sobreUnaIsla = false;

			for (Isla isla : islas) {
				if (isla != null && tortuga.colisionaConIsla(isla)) {
					sobreUnaIsla = true;
					break;
				}
			}
			if (!sobreUnaIsla) {
				tortuga.apareceTortuga();
			} else {
				tortuga.mover();
			}
			if (!sobreUnaIsla && !tortuga.getMoverAbajo()) {
				tortuga.cambiarDireccion();
				if (tortuga.getMoverDerecha()) {
					tortuga.setImagen("img/tortugaIzquierda.png");
				} else {
					tortuga.setImagen("img/tortugaDerecha.png");

				}
				tortuga.mover();
			}
			for (int j = 0; j < bolasDeFuego.size(); j++) {
				if (tortuga.colisionaConBolaDeFuego(bolasDeFuego.get(j))) {
					bolasDeFuego.remove(j);
					tortugasDerrotadas++;
					tortugas[i] = crearNuevaTortuga();
				}

			}
			tortuga.dibujar(entorno);
		}

		// PEP
		moverPep();
		if (entorno.sePresiono('c')) {
			String bolaDeFuego;
			if (pep.getMoverDerecha()) {
				bolaDeFuego = "img/bolaDeFuegoDer.png";
			} else {
				bolaDeFuego = "img/bolaDeFuegoIzq.png";
			}
			bolasDeFuego.add(new BolaDeFuego(pep.getX(), pep.getY(), 30, 30, 3, pep.getMoverDerecha(), bolaDeFuego));
		}
		for (Tortuga tortuga : tortugas) {
			if (pep.colisionaConTortuga(tortuga)) {
				pep.juegoPerdido();
			}

		}
		pep.dibujar(entorno);

		// BOLADEFUEGO
		for (int i = 0; i < bolasDeFuego.size(); i++) {
			BolaDeFuego bola = bolasDeFuego.get(i);
			bola.moverBolaDeFuego();

			if (bola.getX() > anchoPantalla || bola.getX() < 0) {
				bolasDeFuego.remove(i);
				i--;
			}
			bola.dibujar(entorno);
		}

		// TEXTOS
		entorno.escribirTexto("Gnomos perdidos: " + gnomosPerdidos, 100, 100);
		entorno.escribirTexto("Gnomos Salvados: " + gnomosSalvados, 100, 150);
		entorno.escribirTexto("Tortugas Eliminadas: " + tortugasDerrotadas, 100, 200);
	}

	private Gnomo crearNuevoGnomo() {
		boolean moverDerecha = random.nextBoolean();
		String rutaImagen;
		if (!moverDerecha) {
			rutaImagen = "img/gnomoDerecha.png";
		} else {
			rutaImagen = "img/gnomoIzquierda.png";
		}
		return new Gnomo((anchoPantalla / 2), 60, 30, 50, 1, moverDerecha, rutaImagen);
	}

	private Tortuga crearNuevaTortuga() {
		int numRandom;
		boolean moverDerecha = random.nextBoolean();
		String rutaImagen;
		do {
			numRandom = random.nextInt(anchoPantalla);
		} while (!((numRandom > 100 && numRandom <= 400) || (numRandom >= 600 && numRandom < 950)));
		if (!moverDerecha) {
			rutaImagen = "img/tortugaDerecha.png";
		} else {
			rutaImagen = "img/tortugaIzquierda.png";
		}
		return new Tortuga(numRandom, 60, 50, 50, 1, moverDerecha, rutaImagen);
	}

	private void moverPep() {
		pep.actualizarSalto(islas);

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && pep.getX() > pep.getAncho()) {
			pep.setImagen("img/pepIzquierda.png");
			pep.moverIzquierda();
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && pep.getX() < entorno.ancho() - pep.getAncho()) {
			pep.setImagen("img/pepDerecha.png");
			pep.moverDerecha();
		}
		if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
			pep.iniciarSalto();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
