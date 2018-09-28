package components

import pong.Game.Bounds
import pong.Settings

//class Ball (var x : Int, var y : Int) {
class Ball (var x:Int=Bounds.right/2, var y:Int=Bounds.bottom/2, val motion:Movement = new Movement()) {

    var speed = Settings.ballSpeed
    val radius:Int = Settings.ballRadius
    var hasBounced = Array(false, false)

    def bounceX(cause:Movement): Unit = {
        if (!hasBounced(0))
        {
            this.motion.x *= -1
            this.motion.y += (cause.y * Settings.ballSpin).toInt
            hasBounced(0) = true
        }
    }

    def bounceY(cause:Movement): Unit = {
        if (!hasBounced(1))
        {
            this.motion.y *= -1
            this.motion.x += (cause.x * Settings.ballSpin).toInt
            hasBounced(1) = true
        }
    }

    def resetBounceFlags(): Unit = {
        hasBounced(0) = false
        hasBounced(1) = false
    }

    def nextPosition() : Ball =
    {
        (new Ball((this.x + this.motion.x).toInt, (this.y + this.motion.y).toInt, this.motion))
    }

    def randomiseMotion(): Unit =
    {
        this.motion.x = (scala.util.Random.nextInt(2) * 2 - 1) * speed
        this.motion.y = (scala.util.Random.nextInt(2) * 2 - 1) * speed
    }

}

object Ball
{
    // TODO add multi-ball support
    //var balls : collection.mutable.Set[Ball] = collection.mutable.Set()

    var ball = new Ball()
}
