package components

import pong.Game.Bounds

abstract class Rectangle (var x:Int, var y:Int, val width:Int, val height:Int)
{
//  var x :Int
//  var y : Int
//  val width:Int
//  val height: Int

//  def touchesBall(ball:Ball): (Boolean) =
//  {
//    var (x, y) = touchesBallXY(ball)
//    (x || y)
//  }

  def touchesBall(ball:Ball): Boolean =
  {
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

//    var touchesX = intersectsX && intersectsY && xdiff < width/2
//    var touchesY = intersectsX && intersectsY && ydiff < height/2
//    (intersectsX, intersectsY)
  }

  def isWithinBounds(motion: Movement):Boolean=
  {
    (isWithinLeftEdge(motion) && isWithinRightEdge(motion) && isWithinTopEdge(motion) && isWithinBottomEdge(motion))
  }

  def isWithinLeftEdge(motion:Movement):Boolean=
  {
    (this.x - this.width/2 + motion.x > Bounds.left)
  }

  def isWithinRightEdge(motion:Movement):Boolean=
  {
    (this.x + this.width/2 + motion.x < Bounds.right)
  }

  def isWithinTopEdge(motion:Movement):Boolean= {
    (this.y - this.height/2 + motion.y > Bounds.top)
  }

  def isWithinBottomEdge(motion:Movement):Boolean= {
    (this.y + this.height/2 + motion.y < Bounds.bottom)
  }
}
