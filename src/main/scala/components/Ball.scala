package components

import pong.Settings

//class Ball (var x : Int, var y : Int) {
object Ball {

    val speed = Settings.ballSpeed
    var x : Int = 0
    var y : Int = 0
    val radius:Int = Settings.ballRadius
    val startX = (scala.util.Random.nextInt(2) * 2 - 1) * speed
    val startY = (scala.util.Random.nextInt(2) * 2 - 1) * speed
    val motion = new Movement(startX, startY)
    var hasBounced = Array(false, false)

    def bounceX(): Unit = {
        if (!hasBounced(0))
        {
            Ball.motion.x *= -1
            hasBounced(0) = true
        }
    }

    def bounceY(): Unit = {
        if (!hasBounced(1))
        {
            Ball.motion.y *= -1
            hasBounced(1) = true
        }
    }

    def resetBounceFlags(): Unit = {
        hasBounced(0) = false
        hasBounced(1) = false
    }

}

//object Ball
//{
    //var balls : collection.mutable.Set[Ball] = collection.mutable.Set()
//}
