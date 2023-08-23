package juego;

import entorno.Entorno;

public class TiempoRasengan {
		private int milisegundos;
		private int segundos;
		

	TiempoRasengan(){
		this.milisegundos=0;
		this.segundos = 0;
	}

	//GETTERS Y SETTERS---------------------------------------------------------------------------------------

	public int getMilisegundos() {
		return this.milisegundos;
	}

	public int getSegundos() {
		return this.segundos;
	}


	//inicia el contador de segundos
	public void iniciar (int x, int y) {
		this.milisegundos = y;
		this.segundos=x;
	}


	//METODOS-------------------------------------------------------------------------------------------------

	//Metodo para ir bajando de a milisegundos
	public void contador () {
		this.milisegundos --;
		if (this.milisegundos<= 00 && this.segundos>00) {
			this.milisegundos =59;
			this.segundos--;
		}
		else if (this.milisegundos<= 00 && this.segundos<=00){
			this.milisegundos=00;
			this.segundos=0;
		}
		}


	public void dibujarse(Entorno entorno, int z, int w) {
		entorno.escribirTexto("Rasengan: " + this.segundos + ":" +this.milisegundos , z, w );
	}


}
