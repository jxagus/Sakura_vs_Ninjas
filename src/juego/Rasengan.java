package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {
	
	private int x;
	private double y;
	private int velocidad;
	private int diametro;
	
	private Image rasengan = Herramientas.cargarImagen("Imagenes/rasengan.gif");
	
	Rasengan (int x, double y){ 
		this.x = x;
		this.y = y;
		this.velocidad=4;
		this.diametro=20;
		}
	
	public void dibujar(Entorno entorno) {	
		entorno.dibujarCirculo(this.x, this.y, this.diametro, Color.gray);
		entorno.dibujarImagen(this.rasengan, this.x, this.y, 0,0.15);
	}
	
	public void bajar()
	{
		this.y=this.y+this.velocidad;
	}
	
	public void subir()
	{
		this.y=this.y-this.velocidad;
	}
	
	public void derecha() {
		
		this.x=this.x+this.velocidad;
	}
	
	public void izquierda()
	{
		this.x=this.x-this.velocidad;
	}
	
	public int posicionX()
	{
		return this.x;
	}
	
	public double posicionY()
	{
		return this.y;
	}
	
	public int Diametro() {
		return this.diametro;
	}
	
		
}
