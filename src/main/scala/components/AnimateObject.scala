package components

import java.util.{Calendar, Date}
import java.time.LocalDate
import java.time.Duration
import pong.Game

// speed is in pixels per second
// direction is in degrees
trait AnimateObject
{
  val startPos:Vector
  val startTime:Int
  val speed:Vector
  type S


  def getPositionAt(initialPos :Vector, atGameTime:Int):Vector=
  {
    val timeSpan :Int = atGameTime - startTime
    val newX :Int = initialPos.x + speed.x * timeSpan
    val newY :Int = initialPos.y + speed.y * timeSpan
    new Vector(newX, newY)
  }

  def getPositionAt(atGameTime:Int):Vector=
  {
    getPositionAt(startPos, atGameTime)
  }

  // return an instance of the object at the requested moment in game time
  // TODO type bounding
  def getAt(atGameTime:Int):AnimateObject


  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  // TODO type bounding
  def distanceTo(target : AnimateObject, gameTime:Int): Int


  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  // TODO type bounding
  def willTouch(target:AnimateObject, startTime:Int, endTime:Int): Boolean =
  {


  // refer to https://seb.ly/2010/01/predicting-circle-line-collisions/
  def willTouch(line:Line, startTime:Int, endTime:Int): Boolean =
  {

    // one object is a line, and the other is a circle
    //if (line isInstanceOf[S])
    //{
      // find out which objects are the line and circle
      val circle = this
      val line = line

      // find out start & end positions of the objects
      val circleStart = circle.getPrecisePosition(startTime)
      val circleEnd = circle.getPrecisePosition(endTime)
      val lineStart = line.getPrecisePosition(startTime)
      val lineStart = line.getPrecisePosition(endTime)

      // find out starting distance
      var d1 = circleStart.distanceTo(lineStart)


    }


  }

}
