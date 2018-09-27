package components

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
    if (xdiff < 0) xdiff *= -1
    if (ydiff < 0) ydiff *= -1

    // use an approximation for now
    // TODO implement a precise collision calculation

    ((xdiff <= width/2 + Ball.radius)
          && (ydiff <= height/2 + Ball.radius))
  }

}
