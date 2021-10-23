package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.graphics.Canvas
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class Lienzo(p: MainActivity): View(p) {

    //Posiciones de las moscas
    var posicion = Random
    var px0 = posicion.nextInt(720).toFloat()
    var py0 = posicion.nextInt(1100).toFloat()
    var px1 = posicion.nextInt(720).toFloat()
    var py1 = posicion.nextInt(1100).toFloat()
    var px2 = posicion.nextInt(720).toFloat()
    var py2 = posicion.nextInt(1100).toFloat()
    var px3 = posicion.nextInt(720).toFloat()
    var py3 = posicion.nextInt(1100).toFloat()
    var px4 = posicion.nextInt(720).toFloat()
    var py4 = posicion.nextInt(1100).toFloat()
    var px5 = posicion.nextInt(720).toFloat()
    var py5 = posicion.nextInt(1100).toFloat()
    var px6 = posicion.nextInt(720).toFloat()
    var py6 = posicion.nextInt(1100).toFloat()
    var px7 = posicion.nextInt(720).toFloat()
    var py7 = posicion.nextInt(1100).toFloat()
    var px8 = posicion.nextInt(720).toFloat()
    var py8 = posicion.nextInt(1100).toFloat()
    var px9 = posicion.nextInt(720).toFloat()
    var py9 = posicion.nextInt(1100).toFloat()
    //Moscas
    var mosca0 = Mosca(this, px0, py0, R.drawable.mosca)
    var mosca1 = Mosca(this, px1, py1, R.drawable.mosca)
    var mosca2 = Mosca(this, px2, py2, R.drawable.mosca)
    var mosca3 = Mosca(this, px3, py3, R.drawable.mosca)
    var mosca4 = Mosca(this, px4, py4, R.drawable.mosca)
    var mosca5 = Mosca(this, px5, py5, R.drawable.mosca)
    var mosca6 = Mosca(this, px6, py6, R.drawable.mosca)
    var mosca7 = Mosca(this, px7, py7, R.drawable.mosca)
    var mosca8 = Mosca(this, px8, py8, R.drawable.mosca)
    var mosca9 = Mosca(this, px9, py9, R.drawable.mosca)
    //Equis de eliminacion
    var equis0 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis1 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis2 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis3 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis4 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis5 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis6 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis7 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis8 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    var equis9 = Mosca(this, -1000f, -1000f, R.drawable.equis)
    //Hilos
    var hiloMosca0 = Hilo(this, mosca0)
    var hiloMosca1 = Hilo(this, mosca1)
    var hiloMosca2 = Hilo(this, mosca2)
    var hiloMosca3 = Hilo(this, mosca3)
    var hiloMosca4 = Hilo(this, mosca4)
    var hiloMosca5 = Hilo(this, mosca5)
    var hiloMosca6 = Hilo(this, mosca6)
    var hiloMosca7 = Hilo(this, mosca7)
    var hiloMosca8 = Hilo(this, mosca8)
    var hiloMosca9 = Hilo(this, mosca9)
    //Otros
    val p = Paint()
    var start = false
    var btnIniciar = Mosca(this, 280f, 500f, R.drawable.start) //posición del boton
    var mEliminadas = "Moscas Eliminadas: "
    var puntos = 1
    var tiempoRestante: Int = 0
    var moscas = Random.nextInt(80, 100) //numero random de moscas generadas, entre 80 y 100
    //Timer
    var contadorTiempo = 0
    val lapso = 1000
    val tiempoTotal = 9999999
    var timer = object: CountDownTimer(tiempoTotal.toLong(), lapso.toLong()){
        override fun onTick(p0: Long) {
            contadorTiempo++
        }
        override fun onFinish() {
            start()
        }
    }
    //Dibujar
    override fun onDraw(c: Canvas) {
        try {
            super.onDraw(c)

            c.drawARGB(250, 255, 204, 0)
            btnIniciar.pintar(c)
            p.textSize = 40f
            c.drawText(mEliminadas, 150f, 1300f, p)

            //Moscas
            mosca0.pintar(c)
            mosca1.pintar(c)
            mosca2.pintar(c)
            mosca3.pintar(c)
            mosca4.pintar(c)
            mosca5.pintar(c)
            mosca6.pintar(c)
            mosca7.pintar(c)
            mosca8.pintar(c)
            mosca9.pintar(c)

            //Marca de eliminacion de las moscas
            quitar()
            equis0.pintar(c)
            equis1.pintar(c)
            equis2.pintar(c)
            equis3.pintar(c)
            equis4.pintar(c)
            equis5.pintar(c)
            equis6.pintar(c)
            equis7.pintar(c)
            equis8.pintar(c)
            equis9.pintar(c)

            //Unir hilos
            unir(1)

            //Timer
            timer.onTick(1) //indica que debe esperar
            tiempoRestante = 60-contadorTiempo/50 //los 60seg - el tiempo trancurrido del timer
            c.drawText("Segundos restantes: "+tiempoRestante, 150f, 50f, p)
            if (tiempoRestante <= 0) {
                finalizar()
                c.drawText("Segundos restantes: "+tiempoRestante, 150f, 50f, p)
                if (puntos >= moscas) {
                    c.drawText("!!! WIN !!!", 280f, 500f, p)
                }else {
                    c.drawText("!!! LOSER !!!", 280f, 500f, p)
                }
            }

            //Start
            if (start == true) {
                try {
                    hiloMosca0.start()
                    hiloMosca1.start()
                    hiloMosca2.start()
                    hiloMosca3.start()
                    hiloMosca4.start()
                    hiloMosca5.start()
                    hiloMosca6.start()
                    hiloMosca7.start()
                    hiloMosca8.start()
                    hiloMosca9.start()
                }catch (e: Exception) {
                    try {
                        unir(2)
                    }catch (e: InterruptedException) {}
                }
                start = false //Fin de la partida
            }
        }catch (e: InterruptedException) {
            unir(1)
        }
    }

    //Quitar la x
    fun quitar() {
        if (hiloMosca0.wait >= 99) {
            equis0.repintar(-1000, -1000)
        }
        if (hiloMosca1.wait >= 99) {
            equis1.repintar(-1000, -1000)
        }
        if (hiloMosca2.wait >= 99) {
            equis2.repintar(-1000, -1000)
        }
        if (hiloMosca3.wait >= 99) {
            equis3.repintar(-1000, -1000)
        }
        if (hiloMosca4.wait >= 99) {
            equis4.repintar(-1000, -1000)
        }
        if (hiloMosca5.wait >= 99) {
            equis5.repintar(-1000, -1000)
        }
        if (hiloMosca6.wait >= 99) {
            equis6.repintar(-1000, -1000)
        }
        if (hiloMosca7.wait >= 99) {
            equis7.repintar(-1000, -1000)
        }
        if (hiloMosca8.wait >= 99) {
            equis8.repintar(-1000, -1000)
        }
        if (hiloMosca9.wait >= 99) {
            equis9.repintar(-1000, -1000)
        }
    }

    //Unir hilos
    fun unir(tiempo:Int){
        hiloMosca0.join(tiempo.toLong())
        hiloMosca1.join(tiempo.toLong())
        hiloMosca2.join(tiempo.toLong())
        hiloMosca3.join(tiempo.toLong())
        hiloMosca4.join(tiempo.toLong())
        hiloMosca5.join(tiempo.toLong())
        hiloMosca6.join(tiempo.toLong())
        hiloMosca7.join(tiempo.toLong())
        hiloMosca8.join(tiempo.toLong())
        hiloMosca9.join(tiempo.toLong())
    }

    //Finalizar hilos y el juego
    fun finalizar() {
        hiloMosca0.jugando=false
        hiloMosca1.jugando=false
        hiloMosca2.jugando=false
        hiloMosca3.jugando=false
        hiloMosca4.jugando=false
        hiloMosca5.jugando=false
        hiloMosca6.jugando=false
        hiloMosca7.jugando=false
        hiloMosca8.jugando=false
        hiloMosca9.jugando=false
        tiempoRestante = 0
        start = false
    }

    //Acciones
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        when(event.action){
            //Presionar
            MotionEvent.ACTION_DOWN -> {
                //Boton play para comenzar a jugar
                if (btnIniciar.estaEnArea(event.x, event.y)==true) {
                    btnIniciar.repintar(-130,-100)
                    start = true
                    tiempoRestante = 60-contadorTiempo/50
                }
                //Moscas
                if (mosca0.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis0.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca0.alive = false
                        mosca0.repintar(2000, 2000)
                    }
                }
                if (mosca1.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis1.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca1.alive = false
                        mosca1.repintar(2000, 2000)
                    }
                }
                if (mosca2.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis2.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca2.alive = false
                        mosca2.repintar(2000, 2000)
                    }
                }
                if (mosca3.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis3.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca3.alive = false
                        mosca3.repintar(2000, 2000)
                    }
                }
                if (mosca4.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis4.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca4.alive = false
                        mosca4.repintar(2000, 2000)
                    }
                }
                if (mosca5.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis5.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca5.alive = false
                        mosca5.repintar(2000, 2000)
                    }
                }
                if (mosca6.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis6.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca6.alive = false
                        mosca6.repintar(2000, 2000)
                    }
                }
                if (mosca7.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis7.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca7.alive = false
                        mosca7.repintar(2000, 2000)
                    }
                }
                if (mosca8.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis8.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca8.alive = false
                        mosca8.repintar(2000, 2000)
                    }
                }
                if (mosca9.estaEnArea(event.x, event.y)==true) {
                    if (tiempoRestante >= 0) {
                        mEliminadas = "Moscas eliminadas: "+puntos++
                        equis9.repintar(event.x.toInt(), event.y.toInt())
                        hiloMosca9.alive = false
                        mosca9.repintar(2000, 2000)
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        invalidate()
        return true
    }
}