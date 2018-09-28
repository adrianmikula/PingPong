package components

import pong.Game.Bounds

abstract class Rectangle (var x:Int, var y:Int, var width:Int, var height:Int)
{
//  var x :Int
//  var y : Int
//  val width:Int
//  val height: Int



  def touchesBall(): Boolean =
  {
    // find out the x and y distances seperately
    var xdiff = Ball.x - this.x
    var ydiff = Ball.y - this.y

    // convert the results into positive numbers
    if (xdiff < 0) xdiff *= -1
    if (ydiff < 0) ydiff *= -1

    // use an approximation for now
    // TODO implement a precise collision calculation

    var intersectsX = (xdiff <= width/2 + Ball.radius)
    var intersectsY = (ydiff <= height/2 + Ball.radius)

    (intersectsX && intersectsY)
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
