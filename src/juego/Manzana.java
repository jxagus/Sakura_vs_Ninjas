package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Manzana {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	private Image manzana = Herramientas.cargarImagen("Imagenes/manzana.png");
	
	public Manzana(int x, int y, int ancho, int alto) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;		
	}
	
	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.green);
		entorno.dibujarImagen(this.manzana, this.x, this.y, 0,0.25);	
	}
	
	void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.red);
	}
	public int posicionX() {
		return this.x;
	}
	public int posicionY() {
		return this.y;
	}
	public int ancho() {
		return this.ancho;
	}
	public int alto() {
		return this.alto;
	}

}
