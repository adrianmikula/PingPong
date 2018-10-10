package components

import pong.Game.Bounds

 class Line(var start:Vector, val end:Vector)
{
  val normal = start x end
  val fromCentre = normal

  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  def distanceTo(circle:Circle): Int =
  {
    //val normal = start x end
    var startDistance = start - circle.position
    (startDistance * normal)
  }

  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  def touches(circle:Circle): Boolean =
  {
    (distanceTo(circle) <= circle.radius)
  }

  def getPositionAt(gameTime:Int): Vector =
  {
    (getPositionAt(start, gameTime), getPositionAt(end, gameTime))
  }


//  def isWithinBounds(motion: Movement):Boolean=
//  {
//    (isWithinLeftEdge(motion) && isWithinRightEdge(motion) && isWithinTopEdge(motion) && isWithinBottomEdge(motion))
//  }
//
//  def isWithinLeftEdge(motion:Movement):Boolean=
//  {
//    (this.x - this.width/2 + motion.x > Bounds.left)
//  }
//
//  def isWithinRightEdge(motion:Movement):Boolean=
//  {
//    (this.x + this.width/2 + motion.x < Bounds.right)
//  }
//
//  def isWithinTopEdge(motion:Movement):Boolean= {
//    (this.y - this.height/2 + motion.y > Bounds.top)
//  }
//
//  def isWithinBottomEdge(motion:Movement):Boolean= {
//    (this.y + this.height/2 + motion.y < Bounds.bottom)
//  }
}
