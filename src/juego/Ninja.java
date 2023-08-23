package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ninja {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	private Image ninjas = Herramientas.cargarImagen("Imagenes/ninja.gif");
	
	public Ninja(int x, int y, int ancho, int alto) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void bajar()
	{
		this.y +=1;
	}
	
	public void subir()
	{
		this.y -=1;
	}
	
	public void derecha()
	{
		this.x +=1;
	}
	
	public void izquierda()
	{
		this.x -=1;
	}

	public void dibujar(Entorno entorno) {
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLACK);
		entorno.dibujarImagen(this.ninjas, this.x, this.y, 0,0.35);
		
	}
	public int posicionX()
	{
		return this.x;
	}
	
	public int posicionY()
	{
		return this.y;
	}
	public int posicionX0()
	{
		return this.x;
	}
	
	public int posicionY0()
	{
		return this.y;
	}
	public int posicionX1()
	{
		return this.x;
	}
	
	public int posicionY1()
	{
		return this.y;
	}
	public int posicionX2()
	{
		return this.x;
	}
	
	public int posicionY2()
	{
		return this.y;
	}
	public int posicionX3()
	{
		return this.x;
	}
	
	public int posicionY3()
	{
		return this.y;
	}
	public int posicionX4()
	{
		return this.x;
	}
	
	public int posicionY4()
	{
		return this.y;
	}
	public int posicionX5()
	{
		return this.x;
	}
	
	public int posicionY5()
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
	public void iniciaArriba() {
		this.y=70;
	}
	public void iniciaAbajo() {
		this.y=600;
	}
	public void iniciaIzquierda() {
		this.x=0 ;
	}
	public void iniciaDerecha() {
		this.x=800;
	}

}
