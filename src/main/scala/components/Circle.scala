package components

import pong.Game.Bounds
import pong.Settings

//class Ball (var x : Int, var y : Int) {
 class Circle (var position:Vector, val radius:Int) {

    //val radius:Int = Settings.ballRadius


  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  def distanceTo(face :Line): Int =
  {
    //val normal = face.start x face.end
    var startDistance = face.start - this.position
    (startDistance * face.normal)
  }

  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  def touches(face :Line): Boolean =
  {
    (distanceTo(face) <= radius)
  }







}


