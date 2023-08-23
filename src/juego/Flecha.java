package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Flecha {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	private Image flecha = Herramientas.cargarImagen("imagenes/flecha.png");
	
	public Flecha(int x, int y, int ancho, int alto) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.yellow);
		entorno.dibujarImagen(this.flecha, this.x, this.y, 0,0.2);}
	
	public int posicionX()
	{
		return this.x;
	}
	
	public int posicionY()
	{
		return this.y;
	}
	
	public int ancho()
	{
		return this.ancho;
	}
	
	public int alto()
	{
		return this.alto;
	}

}
