package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import kotlin.random.Random

class Hilo(p:Lienzo, m:Mosca): Thread() {
    var puntero = p
    var jugando = true
    var alive = true
    var posicionX = m.x
    var posicionY = m.y
    var mosca = m
    var wait = 0
    var limiteX = true
    var limiteY = true

    fun rebotar() {
        if (limiteX == true) {
            posicionX = posicionX+2
            if (posicionX >= 665) {
                limiteX = false
            }
        }else {
            posicionX = posicionX-2
            if (posicionX <= 0f) {
                limiteX = true
            }
        }
        if (limiteY == true) {
            posicionY = posicionY+2
            if (posicionY >= 1100) {
                limiteY = false
            }
        }else {
            posicionY = posicionY-2
            if (posicionY <= 100f) {
                limiteY = true
            }
        }
        mosca.repintar(posicionX.toInt(), posicionY.toInt())
        puntero.invalidate()
    }

    override fun run() {
        super.run()

        while (jugando) {
            if (alive == true) {
                rebotar()
            }else {
                if (wait == 100) {
                    alive = true
                    posicionX = Random.nextInt(0, puntero.width).toFloat()
                    posicionY = Random.nextInt(0, puntero.width).toFloat()
                    mosca.repintar(posicionX.toInt(), posicionX.toInt())
                    wait = 0
                }else {
                    wait++
                    puntero.invalidate()
                }
            }
            sleep(3) //velocidad
        }
    }
}