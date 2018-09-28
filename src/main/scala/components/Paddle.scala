package components

import pong.Game.Bounds

class Paddle ( x:Int,  y:Int,   width:Int,  height:Int) extends Rectangle (x, y, width, height) {

 val centreX = x
 val centreY = y

  var motion : Movement = new Movement(0,0)

}
