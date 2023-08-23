package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	
	
	private Image sakura = Herramientas.cargarImagen("Imagenes/Sakura.png");

	Sakura(int x, int y, int velocidad){
		this.x=x;
		this.y=y;
		this.alto=50; 
		this.ancho=25;
		this.velocidad=velocidad;
	}

	public void moverDerecha() {
		this.x=this.x+this.velocidad;
	}

	public void moverIzquierda() {
		this.x=this.x-this.velocidad;
		
	}

	public void moverArriba() {
		this.y=this.y-this.velocidad;
		
	}

	public void moverAbajo() {
		this.y=this.y+this.velocidad;
		
	}

	public void dibujar(Entorno entorno) {
		
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.gray);
		entorno.dibujarImagen(this.sakura, this.x, this.y, 0,0.15);
	}
	
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

	public Rasengan rasengan(int x, int y){
    	Rasengan rasengan= new Rasengan(x,y);
        return rasengan;
    }
	
	public void reinicia(int x, int y) {
		this.x = x;
		this.y = y;	
	}

}

