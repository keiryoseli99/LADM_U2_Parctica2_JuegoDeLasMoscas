package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Mosca(punteroLienzo: Lienzo, posx: Float, posy: Float, nombreImagen: Int) {
    var x = posx
    var y = posy
    var imagenOriginal = BitmapFactory.decodeResource(punteroLienzo.resources, nombreImagen)
    var imagen = Bitmap.createScaledBitmap(imagenOriginal,150, 150, false)

    fun pintar(c: Canvas) {
        c.drawBitmap(imagen, x, y, Paint())
    }
    fun estaEnArea(toqueX:Float, toqueY:Float): Boolean {
        var x2 = x + imagen.width + 50
        var y2 = y + imagen.height + 50

        if(toqueX >= x && toqueX <= x2){
            if (toqueY >= y && toqueY <= y2){
                return true
            }
        }
        return false
    }
    fun repintar(ancho: Int, alto: Int) {
        x = ancho.toFloat()
        y = alto.toFloat()
    }
}