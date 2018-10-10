package components

import pong.Game.Bounds
import pong.Settings

//class Ball (var x : Int, var y : Int) {
//class Ball (var position:Vector= new Vector(Bounds.right/2,Bounds.bottom/2),
class Ball ( position:Vector, radius:Int ) extends Circle (position, radius) with AnimateObject {


    var speed = Settings.ballSpeed
    var hasBounced = Array(false, false)



  // TODO type bounding
  def getAt(gameTime:Int):Circle={
    new Circle(getPositionAt(gameTime), radius)
  }


  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  // TODO type bounding
  def distanceTo(line:Paddle, gameTime:Int): Int =
  {
    val circlePos = getPositionAt(gameTime)
    val linePos =
    val lineStart = line.getPositionAt(gameTime)

    // find out starting distance
    var d1 = circleStart.distanceTo(lineStart)
  }


  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  // TODO type bounding
  def willTouch(target:AnimateObject, startTime:Int, endTime:Int): Boolean =
  {

    // one object is a line, and the other is a circle
    if (true)
    {
      // find out which objects are the line and circle
      val circle = this
      val line = target

      // find out start & end positions of the objects
      val circleStart = circle.getPrecisePosition(startTime)
      val circleEnd = circle.getPrecisePosition(endTime)
      val lineStart = line.getPrecisePosition(startTime)
      val lineStart = line.getPrecisePosition(endTime)

      // find out starting distance
      var d1 = circleStart.distanceTo(lineStart)


    }
    // both objects are lines
    else if (true)
    {

    }


    // find out ending distance
    var d1 = ball.distanceTo(this)

    // find out the x and y distances seperately
    var xdiff = ball.x - this.x
    var ydiff = ball.y - this.y

    // convert the results into positive numbers
    if (xdiff < 0) xdiff *= -1
    if (ydiff < 0) ydiff *= -1

    // use an approximation for now
    // TODO implement a precise collision calculation

    var intersectsX = (xdiff <= width/2 + ball.radius)
    var intersectsY = (ydiff <= height/2 + ball.radius)

    (intersectsX && intersectsY)

  }



  //
//  def touchesCircle(circle:Circle): Boolean =
//  {
//    // always compare to the singleton ball
//    val ball:Ball = Ball.ball
//
//    // find out the x and y distances seperately
//    var xdiff = ball.x - this.x
//    var ydiff = ball.y - this.y
//
//    // convert the results into positive numbers
//    if (xdiff < 0) xdiff *= -1
//    if (ydiff < 0) ydiff *= -1
//
//    // use an approximation for now
//    // TODO implement a precise collision calculation
//
//    var intersectsX = (xdiff <= width/2 + ball.radius)
//    var intersectsY = (ydiff <= height/2 + ball.radius)
//
//    (intersectsX && intersectsY)
//
//    //    var touchesX = intersectsX && intersectsY && xdiff < width/2
//    //    var touchesY = intersectsX && intersectsY && ydiff < height/2
//    //    (intersectsX, intersectsY)
//  }
//
//
  def bounceX(cause:AnimateObject): Unit = {
        if (!hasBounced(0))
        {
            this.motion.x *= -1
            this.motion.y += (cause.y * Settings.ballSpin).toInt
            hasBounced(0) = true
        }
    }

    def bounceY(cause:AnimateObject): Unit = {
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


  def nextPosition() : Ball =
  {
    (new Ball((this.x + this.motion.x).toInt, (this.y + this.motion.y).toInt, this.motion))
  }

}

object Ball
{
    // TODO add multi-ball support
    //var balls : collection.mutable.Set[Ball] = collection.mutable.Set()

    var ball = new Ball()
}
