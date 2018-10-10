package components

import pong.Game
import pong.Game.Bounds

class Paddle (start:Vector, end:Vector) extends Line (start, end) with AnimateObject {

// val centreX = x
// val centreY = y

//  var motion : AnimateObject = new AnimateObject(new Vector(0,0), Game.gameTime, new Vector(0,0))


 def getAt(gameTime:Int):Line={
  new Line(getPositionAt(gameTime))
 }



 // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
 def willTouchBall(startTime:Int, endTime:Int): Boolean =
 {
  // always compare to the singleton ball
  val ball:Ball = Ball.ball

  // find out starting distance
  var d1 = ball.distanceTo(super.)

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



// def willTouchBall(startTime:Int, endTime:Int): Boolean =
// {
//  // always compare to the singleton ball
//  val ball:Ball = Ball.ball
//
//  // find out the x and y distances seperately
//  var xdiff = ball.x - this.x
//  var ydiff = ball.y - this.y
//
//  // convert the results into positive numbers
//  if (xdiff < 0) xdiff *= -1
//  if (ydiff < 0) ydiff *= -1
//
//  // use an approximation for now
//  // TODO implement a precise collision calculation
//
//  var intersectsX = (xdiff <= width/2 + ball.radius)
//  var intersectsY = (ydiff <= height/2 + ball.radius)
//
//  (intersectsX && intersectsY)
//
//  //    var touchesX = intersectsX && intersectsY && xdiff < width/2
//  //    var touchesY = intersectsX && intersectsY && ydiff < height/2
//  //    (intersectsX, intersectsY)
// }


}
