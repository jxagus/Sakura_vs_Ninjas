package juego;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Sakura sakura;
	private boolean muerta=false;
	private Casa[] casas;
	private Ninja[] ninjas;
	private Manzana[] manzanas;
	private Rasengan rasengan;
	private boolean derecha;
	private boolean izquierda;
	private boolean arriba;
	private boolean abajo;
	private boolean permite;
	private TiempoRasengan tiempo;
	private int ubicacionPuntajesY;
    private int ubicacionPuntajesX;
    private Casa casaElegida;
    private Flecha[] flecha;
    //
    private int ninjasElim;	
    private int casaElig;

	Juego()
	{
		
		// INICIALIZA EL OBJETO ENTORNO----------------------------------------------------------------------------------------------------------------------------
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 12 - v1", 800, 600);
		
		this.abajo=false;
		this.arriba=false;
		this.derecha=false;
		this.izquierda=false;
		this.permite = true;
		this.tiempo=new TiempoRasengan();
		this.ubicacionPuntajesX=180;
		this.ubicacionPuntajesY=20;
		this.ninjasElim=0;
		this.casaElig=-5;
		
		// Inicializar lo que haga falta para el juego
		
		
		//POSICION Y VELOCIDAD DE SAKURA----------------------------------------------------------------------------------------------------------------------------
		int velocidad=3;
		this.sakura= new Sakura(this.entorno.ancho()/2,310,velocidad);
		//----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		this.flecha= new Flecha[1]; //(this.casas[i].posicionX(), this.casas[i].posicionY()-this.casas[i].alto()/2 - 5, 15, 20);}
		
		
		//POSICION Y TAMAÑO DE CADA NINJA---------------------------------------------------------------------------------------------------------------------------
		this.ninjas = new Ninja [6];
		this.ninjas[0] = new Ninja(140,150,10,30);
		this.ninjas[1] = new Ninja(660,450,10,30);
		this.ninjas[2] = new Ninja(140,465,10,30);
		this.ninjas[3] = new Ninja(660,150,10,30);
		this.ninjas[4] = new Ninja(315,550,10,30);
		this.ninjas[5] = new Ninja(490,80,10,30);
		//----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//POSICION Y TAMAÑO DE CADA MANZANA-------------------------------------------------------------------------------------------------------------------------
		this.manzanas = new Manzana [20];
		
		this.manzanas[0] = new Manzana(50 , 80,  100, 80);
		this.manzanas[1] = new Manzana(50 , 235, 100, 80);
		this.manzanas[2] = new Manzana(50 , 385, 100, 80);
		this.manzanas[3] = new Manzana(50 , 545, 100, 80);
		//x= 225
		this.manzanas[4] = new Manzana(225 , 80, 100, 80);
		this.manzanas[5] = new Manzana(225 , 235, 100, 80);
		this.manzanas[6] = new Manzana(225 , 385, 100, 80);
		this.manzanas[7] = new Manzana(225 , 545, 100, 80);
		//x= 400
		this.manzanas[8] = new Manzana(400 , 80, 100, 80);
		this.manzanas[9] = new Manzana(400 , 235, 100, 80);
		this.manzanas[10] = new Manzana(400, 385, 100, 80);
		this.manzanas[11] = new Manzana(400, 545, 100, 80);
		//X= 575
		this.manzanas[12] = new Manzana(575 , 80, 100, 80);
		this.manzanas[13] = new Manzana(575 , 235, 100, 80);
		this.manzanas[14] = new Manzana(575 , 385, 100, 80);
		this.manzanas[15] = new Manzana(575 , 545, 100, 80);
		//x= 750
		this.manzanas[16] = new Manzana(750 , 80, 100, 80);
		this.manzanas[17] = new Manzana(750 , 235, 100, 80);
		this.manzanas[18] = new Manzana(750 , 385, 100, 80);
		this.manzanas[19] = new Manzana(750 , 545, 100, 80);
		//------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		//POSICION Y TAMAÑO DE CADA CASA------------------------------------------------------------------------------------------------------------------------
			
		this.casas = new Casa [46];
		crearCasas();
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------------

		// INICIA EL JUEGO
		this.entorno.iniciar();
		
	}
	//TERMINA METODO JUEGO--------TERMINA METODO JUEGO--------TERMINA METODO JUEGO--------TERMINA METODO JUEGO--------TERMINA METODO JUEGO--------TERMINA METODO JUEGO

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle)
	 */
	
	
	public void tick()
	{
		//DIBUJA Y MUESTRA LO QUE HAGA FALTA--------------------------------------------------------------------------------------------------------------------------
		entorno.dibujarRectangulo(400 , 300, 800, 600, 0, Color.GRAY);
		dibujarManzanas();
		dibujarNinjas();
		moverNinjas();
		dibujarCasas();
		
		this.sakura.dibujar(entorno);
		colisionRasenganNinja();
		muestraTiempo();
		entorno.escribirTexto("Ninjas eliminados:"+ninjasElim,5,20);
		entorno.escribirTexto("Puntos:"+casaElig,720,20);
		if (tocaNinjaSakura()) {
			this.sakura.reinicia(400,310);
			
		}
		if(muerta) {
			entorno.escribirTexto("GAME OVER",430, 310);
		}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------
				
		
		// Procesamiento de un instante de tiempo
		
		//MOVIMIENTO DE SAKURA-----------------------------------------------------------------------------------------------------------------------------------------
		if (!muerta&&this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && this.sakura.posicionX()+this.sakura.ancho()/2<this.entorno.ancho() && !tocaManzanaSakura()){
			this.sakura.moverDerecha();
			if(this.permite==true) {
				this.abajo=false;
				this.arriba=false;
				this.derecha=true;
				this.izquierda=false;	
			}
			if(tocaManzanaSakura()) {
				this.sakura.moverIzquierda();		
			}
		}
		
		if (!muerta&&this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) && this.sakura.posicionX()-this.sakura.ancho()/2>0){
			this.sakura.moverIzquierda();
			if(this.permite==true) {
				this.abajo=false;
				this.arriba=false;
				this.derecha=false;
				this.izquierda=true;	
			}
			if(tocaManzanaSakura()) {
				this.sakura.moverDerecha();		
			}			
		}
		
		if (!muerta&&this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) && this.sakura.posicionY()-this.sakura.alto()/2>0){
			this.sakura.moverArriba();
			if(this.permite==true) {
				this.abajo=false;
				this.arriba=true;
				this.derecha=false;
				this.izquierda=false;	
			}
			if(tocaManzanaSakura()) {
				this.sakura.moverAbajo();		
			}
		}
		
		if (!muerta&&this.entorno.estaPresionada(this.entorno.TECLA_ABAJO)&& this.sakura.posicionY()+this.sakura.alto()/2<this.entorno.alto()){
			this.sakura.moverAbajo();
			if(this.permite==true) {
				this.abajo=true;
				this.arriba=false;
				this.derecha=false;
				this.izquierda=false;	
			}
			if(tocaManzanaSakura()) {
				this.sakura.moverArriba();		
			}
		}
		//-----------------------------------------------------------------------------------------------------------------------------------------------------
			
		
		//REAPARICION DE LOS NINJAS CUANDO SE SALEN DE LA PANTALLA---------------------------------------------------------------------------------------------
		if (this.ninjas[0].posicionY0()>this.entorno.alto()) {
			this.ninjas[0].iniciaArriba();
			}
		if (this.ninjas[1].posicionY1()<70) {
			this.ninjas[1].iniciaAbajo();
			}
		if (this.ninjas[2].posicionX2()>this.entorno.ancho()) {
			this.ninjas[2].iniciaIzquierda();
			}
		if (this.ninjas[3].posicionX3()>this.entorno.ancho()) {
			this.ninjas[3].iniciaIzquierda();
			}
		if (this.ninjas[4].posicionY4()<70) {
			this.ninjas[4].iniciaAbajo();
			}
		if (this.ninjas[5].posicionY5()>this.entorno.alto()) {
			this.ninjas[5].iniciaArriba();
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
	
		//LANZAMIENTO DE RASENGAN POR TECLADO--------------------------------------------------------------------------------------------------------------------
		if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO) && this.permite==true){
			this.permite=false;
			this.rasengan=this.sakura.rasengan
					 (this.sakura.posicionX(),this.sakura.posicionY());
			this.tiempo.iniciar(3, 0);
		}
		//--------------------------------------------------------------------------------------------------------------------------------------------------------


		//DISPARO DE RASENGAN EN DIREFENTES DIRECCIONES-----------------------------------------------------------------------------------------------------------
		if (this.rasengan!=null&&this.permite==false) {
			if(this.derecha==true) {
				this.rasengan.dibujar(this.entorno);
				this.rasengan.derecha();
				if(this.rasengan.posicionX()>this.entorno.ancho()) {
					this.rasengan=null;
				}
			}
		}
		
		if (this.rasengan!=null&& this.izquierda==true) {
			this.rasengan.dibujar(this.entorno);
			this.rasengan.izquierda();			
			if (this.rasengan.posicionX()<0) {
				this.rasengan=null;
			}
		}
		if (this.rasengan!=null&& this.arriba==true) {
			this.rasengan.dibujar(this.entorno);
			this.rasengan.subir();
			if (this.rasengan.posicionY()<0) {
				this.rasengan=null;
			}
		}
		if (this.rasengan!=null && this.abajo==true) {
			this.rasengan.dibujar(this.entorno);
			this.rasengan.bajar();
			if(this.rasengan.posicionY()>this.entorno.alto()) {
				this.rasengan=null;
			}
		}
		
		if (this.tiempo.getMilisegundos() !=0 || this.tiempo.getSegundos() != 0) {
			this.tiempo.contador();
		}
		if (this.tiempo.getMilisegundos() ==0 && this.tiempo.getSegundos() == 0) {
			this.permite=true;
		}	
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
		//ELIGE LA CASA Y CHEQUEA QUE ENTREGUE EL RAMO EN LA CASA ELEGIDA
		
				if (casaElegida==null)
					casaElegida=elegirCasa();
				for (int i=0; i<casas.length; i++) {
					if (casaElegida == casas[i]) {
						this.flecha[0] = new Flecha(this.casas[i].posicionX(), this.casas[i].posicionY()-this.casas[i].alto()/2 - 7, 20, 40);
						this.flecha[0].dibujar(entorno);
				if (colisionCasaSakura(this.casaElegida,this.sakura)) {
						this.casaElegida=null;
						this.flecha[0]=null;}
				
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		}}	
	}//TERMINA METODO TICK--------TERMINA METODO TICK--------TERMINA METODO TICK--------TERMINA METODO TICK--------TERMINA METODO TICK-------TERMINA METODO TICK--------TERMINA METODO TICK

     //CHEQUEA COLISION ENTRE RASENGAN Y NINJAS--------------------------------------------------------------------------------------------------------------------------------------------
	public boolean colisionRasenganNinja(Ninja n, Rasengan r) {
		  boolean cumpleX0 = r.posicionX()<n.posicionX0() - n.ancho()/2 && r.posicionX()>n.posicionX0() + n.ancho()/2;
	      boolean cumpleY0 = r.posicionY()>n.posicionY0() + n.alto()/2 && r.posicionY()<n.posicionY0() - n.alto()/2;
	      
	      boolean cumpleX1 = r.posicionX()<n.posicionX1() - n.ancho()/2 && r.posicionX()>n.posicionX1() + n.ancho()/2;
	      boolean cumpleY1 = r.posicionY()>n.posicionY1() + n.alto()/2 && r.posicionY()<n.posicionY1() - n.alto()/2;
	      
	      boolean cumpleX2 = r.posicionX()<n.posicionX2() - n.ancho()/2 && r.posicionX()>n.posicionX2() + n.ancho()/2;
	      boolean cumpleY2 = r.posicionY()>n.posicionY2() + n.alto()/2 && r.posicionY()<n.posicionY2() - n.alto()/2;
	      
	      boolean cumpleX3 = r.posicionX()<n.posicionX3() - n.ancho()/2 && r.posicionX()>n.posicionX3() + n.ancho()/2;
	      boolean cumpleY3 = r.posicionY()>n.posicionY3() + n.alto()/2 && r.posicionY()<n.posicionY3() - n.alto()/2;
	      
	      boolean cumpleX4 = r.posicionX()<n.posicionX4() - n.ancho()/2 && r.posicionX()>n.posicionX4() + n.ancho()/2;
	      boolean cumpleY4 = r.posicionY()>n.posicionY4() + n.alto()/2 && r.posicionY()<n.posicionY4() - n.alto()/2;
	      
	      boolean cumpleX5 = r.posicionX()<n.posicionX5() - n.ancho()/2 && r.posicionX()>n.posicionX5() + n.ancho()/2;
	      boolean cumpleY5 = r.posicionY()>n.posicionY5() + n.alto()/2 && r.posicionY()<n.posicionY5() - n.alto()/2;
			     
			      return cumpleX0 && cumpleY0 || cumpleX1 && cumpleY1 || cumpleX2 && cumpleY2 || cumpleX3 && cumpleY3 || cumpleX4 && cumpleY4 || cumpleX5 && cumpleY5;
	}
    public void colisionRasenganNinja() {
    	for(int i =0 ; i<this.ninjas.length;i++){
			if (ninjas[0]!=null&&rasengan!=null) {
			if (this.rasengan.posicionX() <=  this.ninjas[0].posicionX0()+30 && this.rasengan.posicionX() >=  this.ninjas[0].posicionX0()-30) 
			{
			     if ( this.rasengan.posicionY() <= this.ninjas[0].posicionY0()+30 && this.rasengan.posicionY() >= this.ninjas[0].posicionY0()-30)
			     {
			    	
			        this.ninjas[0].iniciaArriba();
			        this.rasengan=null;
			      //Incrementamos el contador de ninjas eliminados +1
			        this.ninjasElim=this.ninjasElim+1;
			     }
			 }
			}
			if (ninjas[1]!=null&&rasengan!=null) {
			if (this.rasengan.posicionX() <=  this.ninjas[1].posicionX1()+30 && this.rasengan.posicionX() >=  this.ninjas[1].posicionX1()-30) 
		    {
		        if ( this.rasengan.posicionY() <= this.ninjas[1].posicionY1()+30 && this.rasengan.posicionY() >= this.ninjas[1].posicionY1()-30)
		        {
		           this.ninjas[1].iniciaAbajo();
		           this.rasengan=null;
		           //System.out.println("borrar rasengan");
		         //Incrementamos el contador de ninjas eliminados +1
			       this.ninjasElim=this.ninjasElim+1;
		        }
		    }
			}
			if (ninjas[2]!=null&&rasengan!=null) {
			if (this.rasengan.posicionX() <=  this.ninjas[2].posicionX2()+30 && this.rasengan.posicionX() >=  this.ninjas[2].posicionX2()-30) 
		    {
		        if ( this.rasengan.posicionY() <= this.ninjas[2].posicionY2()+30 && this.rasengan.posicionY() >= this.ninjas[2].posicionY2()-30)
		        {
		           this.ninjas[2].iniciaIzquierda();
		           this.rasengan=null;
		        	//System.out.println("borrar rasengan");
		         //Incrementamos el contador de ninjas eliminados +1
			       this.ninjasElim=this.ninjasElim+1;
		        }
		    }
			}
			if (ninjas[3]!=null&&rasengan!=null) {
			if (this.rasengan.posicionX() <=  this.ninjas[3].posicionX3()+30 && this.rasengan.posicionX() >=  this.ninjas[3].posicionX3()-30) 
		    {
		        if ( this.rasengan.posicionY() <= this.ninjas[3].posicionY3()+30 && this.rasengan.posicionY() >= this.ninjas[3].posicionY3()-30)
		        {
		           this.ninjas[3].iniciaDerecha();
		           this.rasengan=null;
		        	//System.out.println("borrar rasengan");
		         //Incrementamos el contador de ninjas eliminados +1
			       this.ninjasElim=this.ninjasElim+1;
		        }
		    }
			}
			if (ninjas[4]!=null&&rasengan!=null) {
			if (this.rasengan.posicionX() <=  this.ninjas[4].posicionX4()+30 && this.rasengan.posicionX() >=  this.ninjas[4].posicionX4()-30) 
		    {
		        if ( this.rasengan.posicionY() <= this.ninjas[4].posicionY4()+30 && this.rasengan.posicionY() >= this.ninjas[4].posicionY4()-30)
		        {
		           this.ninjas[4].iniciaAbajo();
		           this.rasengan=null;
		        	//System.out.println("borrar rasengan");
		         //Incrementamos el contador de ninjas eliminados +1
			       this.ninjasElim=this.ninjasElim+1;
		        }
		    }
			}
			if (ninjas[5]!=null&&rasengan!=null) {
			if (this.rasengan.posicionX() <=  this.ninjas[5].posicionX5()+30 && this.rasengan.posicionX() >=  this.ninjas[5].posicionX5()-30) 
		    {
		        if ( this.rasengan.posicionY() <= this.ninjas[5].posicionY5()+30 && this.rasengan.posicionY() >= this.ninjas[5].posicionY5()-30)
		        {
		           this.ninjas[5].iniciaArriba();
		           this.rasengan=null;
		        	//System.out.println("borrar rasengan");
		         //Incrementamos el contador de ninjas eliminados +1
			       this.ninjasElim=this.ninjasElim+1;
		        }
		    }
		}
    	}
		}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		
	//CHEQUEA COLISION DE NINJAS Y SAKURA--------------------------------------------------------------------------------------------------------------------------------------------------
	private boolean colisionNinjaSakura(Ninja n, Sakura s) {
		boolean chequeoY = (n.posicionY() > s.posicionY() - 30 && n.posicionY() < s.posicionY() + 30);           
		boolean chequeoX = (n.posicionX() > s.posicionX() - 30 && n.posicionX() < s.posicionX() + 30);
		
		return chequeoY && chequeoX/* || chequeoY1 && chequeoX1 || chequeoY2 && chequeoX2 || chequeoY3 && chequeoX3 || chequeoY4 && chequeoX4 || chequeoY5 && chequeoX5*/;
	}
	public boolean tocaNinjaSakura() {
		for(int i =0 ; i<this.ninjas.length;i++){
			if (colisionNinjaSakura(this.ninjas[i],this.sakura)) {
				this.muerta=true;
				return true;
				
			}
		}
		return false;
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
	//CHEQUEA COLISION DE MANZANAS Y SAKURA------------------------------------------------------------------------------------------------------------------------------------------------
	public boolean colisionManzanaSakura(Manzana m,Sakura s ) {
	     boolean cumpleX = (s.posicionX() - s.ancho()/2 < m.posicionX() + m.ancho() / 2 && s.posicionX() + s.ancho()/2>m.posicionX() - m.ancho() / 2);
	     boolean cumpleY = (s.posicionY() - s.alto()/2 < m.posicionY()+ m.alto() / 2 && s.posicionY()+s.alto()/2 > m.posicionY() - m.alto() / 2);
	    
	      return cumpleX && cumpleY;
	     }
	
	public boolean tocaManzanaSakura() {
		for(int i =0 ; i<this.manzanas.length;i++){
			if (colisionManzanaSakura(this.manzanas[i],this.sakura)) {
				return true;
			}			
		}
		return false;
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
	
	
	
	//DIBUJA LAS MANZANAS------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void dibujarManzanas() { 
		for (int i=0; i < manzanas.length; i++)
			this.manzanas[i].dibujar(entorno);			
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//DIBUJA LAS CASAS------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public void dibujarCasas() { 
			for (int i=0; i < casas.length; i++)
				this.casas[i].dibujar(entorno);			
		}
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
	
	
	
	
	//MOVIMIENTO DE CADA NINJA---------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void dibujarNinjas() { 
		for (int i=0; i < ninjas.length; i++)
			this.ninjas[i].dibujar(entorno);			
	}
	
	public void moverNinjas() {	
		this.ninjas[0].bajar();	
		this.ninjas[1].subir();
		this.ninjas[2].derecha();
		this.ninjas[3].derecha();
		this.ninjas[4].subir();	
		this.ninjas[5].bajar();
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//METODO MOSTRAR TIEMPO------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void muestraTiempo() {
		int xTimer=this.ubicacionPuntajesX;
		int yTimer=this.ubicacionPuntajesY;
		if (this.permite==false) {
			this.entorno.cambiarFont("Timer",12, Color.BLUE);
			this.tiempo.dibujarse(this.entorno, xTimer, yTimer);
		}
		else {
			this.entorno.cambiarFont("Timer",12, Color.YELLOW);
			this.tiempo.dibujarse(this.entorno, xTimer, yTimer);
		}		
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	
	//CREA CADA CASA---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void crearCasas() { 
		
		//creo casas en cada posición del arreglo casas
		this.casas[0]= new Casa(91, 70, 25, 25);       
		this.casas[1] = new Casa(185, 70, 25, 25);
		this.casas[2] = new Casa(266, 70, 25, 25);
		this.casas[3] = new Casa(360, 70, 25, 25);
		this.casas[4] = new Casa(441, 70, 25, 25);
		this.casas[5] = new Casa(533, 70, 25, 25);
		this.casas[6] = new Casa(616, 70, 25, 25);
		this.casas[7] = new Casa(710, 70, 25, 25);
		this.casas[8] = new Casa(50, 111, 25, 25);
		this.casas[9] = new Casa(225, 111, 25, 25);
		this.casas[10] = new Casa(400, 111, 25, 25);
		this.casas[11] = new Casa(575, 111, 25, 25);
		this.casas[12] = new Casa(750, 111, 25, 25);
		this.casas[13] = new Casa(50, 203, 25, 25);
		this.casas[14] = new Casa(225, 203, 25, 25);
		this.casas[15] = new Casa(400, 203, 25, 25);
		this.casas[16] = new Casa(575, 203, 25, 25);
		this.casas[17] = new Casa(750, 203, 25, 25);
		this.casas[18] = new Casa(50, 266, 25, 25);
		this.casas[19] = new Casa(225, 266, 25, 25);
		this.casas[20] = new Casa(400, 266, 25, 25);
		this.casas[21] = new Casa(575, 266, 25, 25);
		this.casas[22] = new Casa(750, 266, 25, 25);
		this.casas[23] = new Casa(50, 353, 25, 25);
		this.casas[24] = new Casa(225, 353, 25, 25);
		this.casas[25] = new Casa(400, 353, 25, 25);
		this.casas[26] = new Casa(575, 353, 25, 25);
		this.casas[27] = new Casa(750, 353, 25, 25);
		this.casas[28] = new Casa(50, 416, 25, 25);
		this.casas[29] = new Casa(225, 416, 25, 25);
		this.casas[30] = new Casa(400, 416, 25, 25);
		this.casas[31] = new Casa(575, 416, 25, 25);
		this.casas[32] = new Casa(750, 416, 25, 25);
		this.casas[33] = new Casa(50, 513, 25, 25);
		this.casas[34] = new Casa(225, 513, 25, 25);
		this.casas[35] = new Casa(400, 513, 25, 25);
		this.casas[36] = new Casa(575, 513, 25, 25);
		this.casas[37] = new Casa(750, 513, 25, 25);
		this.casas[38] = new Casa(91, 554, 25, 25);  
		this.casas[39] = new Casa(184, 554, 25, 25);
		this.casas[40] = new Casa(266, 554, 25, 25);
		this.casas[41] = new Casa(360, 554, 25, 25);
		this.casas[42] = new Casa(441, 554, 25, 25);
		this.casas[43] = new Casa(533, 554, 25, 25);
		this.casas[44] = new Casa(614, 554, 25, 25);
		this.casas[45] = new Casa(710, 554, 25, 25);
		
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//SELECCIONA CASA PARA DEJAR EL RAMO
		public Casa elegirCasa() {
			Random numObjetivo = new Random();
			int aleatorio = numObjetivo .nextInt(46);
			System.out.println(aleatorio);
		    
		    this.casaElig = this.casaElig + 5; 
			return casas[aleatorio];
			}
	//CHEQUEA COLISION DE SAKURA Y CASAS
		public boolean colisionCasaSakura(Casa c,Sakura s ) {
		     boolean cumpleX = (s.posicionX() - s.ancho()/2 < c.posicionX() + c.ancho() / 2 && s.posicionX() + s.ancho()/2>c.posicionX() - c.ancho() / 2);
		     boolean cumpleY = (s.posicionY() - s.alto()/2 < c.posicionY()+ c.alto() / 2 && s.posicionY()+s.alto()/2 > c.posicionY() - c.alto() / 2);

		     return cumpleX && cumpleY; 
		      
		     }
				
}
		
		
		
		
					
				



	
		



	
	