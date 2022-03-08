import kotlin.math.cos
import kotlin.math.sin

class Cube(
    var size:Int,
    var center:Point,
) {

//Puntos:
    var p1 =  Point(center.x - size/2f, center.y + size/2f, center.z + size/2f)  //esquina superior izquierda
    var p2 = Point(center.x + size/2f, center.y + size/2f, center.z + size/2f) //esquina superior derecha
    var p3 = Point(center.x - size/2f, center.y + size/2f, center.z - size/2f) //esquina inferior izquierda
    var p4 = Point(center.x + size/2f, center.y + size/2f, center.z - size/2f) //esquina inferior derecha
    var p5 =  Point(center.x - size/2f, center.y - size/2f, center.z + size/2f)  //esquina superior izquierda
    var p6 = Point(center.x + size/2f, center.y - size/2f, center.z + size/2f) //esquina superior derecha
    var p7 = Point(center.x - size/2f, center.y - size/2f, center.z - size/2f) //esquina inferior izquierda
    var p8 = Point(center.x + size/2f, center.y - size/2f, center.z - size/2f) //esquina inferior derecha
//Vertices:
    var vertices = listOf(p1,p2,p3,p4,p5,p6,p7,p8)
//Aristas:
    val edges = mapOf(
        p1 to listOf(p2,p3,p5),
        p2 to listOf(p1,p4,p6),
        p3 to listOf(p1,p4,p7),
        p4 to listOf(p2,p3,p8),
        p5 to listOf(p1,p6,p7),
        p6 to listOf(p2,p5,p8),
        p7 to listOf(p3,p5,p8),
        p8 to listOf(p4,p7,p6),
    )

    fun rotateY(deg:Float) {
        for (v in vertices) {
            var newX = (v.x-center.x)*cos(deg) - (v.z-center.z)*sin(deg) + center.x
            var newZ = (v.x-center.x)*sin(deg) + (v.z-center.z)*cos(deg) + center.z
            v.x = newX
            v.z = newZ
        }
    }

    fun rotateX(deg:Float) {
        for (v in vertices) {
            var newY = (v.y-center.y)*cos(deg) - (v.z-center.z)*sin(deg) + center.y
            var newZ = (v.y-center.y)*sin(deg) + (v.z-center.z)*cos(deg) + center.z
            v.y = newY
            v.z = newZ
        }
    }

    fun rotateZ(deg:Float) {
        for (v in vertices) {
            var newX = (v.x-center.x)*cos(deg) - (v.y-center.y)*sin(deg) + center.x
            var newY = (v.x-center.x)*sin(deg) + (v.y-center.y)*cos(deg) + center.y
            v.x = newX
            v.y = newY
        }
    }

    fun scaleY(units:Int) {
        for (v in vertices) {
            if (v.y > center.y) {v.y -= units}
            if (v.y < center.y) {v.y += units}
        }
    }
}