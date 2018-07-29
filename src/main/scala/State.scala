package pong
import pong.Game._
import org.scalajs.dom.html.Div

class State {
  /**
    * Checks if the game is stopped or not
    * @return
    */
  def isStoped: Boolean = status == Status.Stopped

  /**
    * Checks if the game is running or not
    * @return
    */
  def isRunning: Boolean = status == Status.Running

  /**
    * Checks if there is racket hit or not
    * @param racketHTML
    * @param ballHTML
    * @return
    */
  def isRacketHit(racketHTML: Div, ballHTML: Div): Boolean = {
    val racketBorderLeft = racketHTML.offsetLeft.toInt
    val racketBorderRight = racketBorderLeft + racketHTML.offsetWidth.toInt
    val posX = model.nextPosition(ball.x, ball.speed, ball.directionX)
    val posY = model.nextPosition(ball.y, ball.speed, ball.directionY)
    val racketPosY = model.racketPositionY(racketHTML, ballHTML)
    posX >= racketBorderLeft && posX <= racketBorderRight && posY >= racketPosY
  }

  /**
    * Check if the game is over or not
    * @param racketHTML
    * @param ballHTML
    * @return
    */
  def isGameOver(racketHTML: Div, ballHTML: Div): Boolean = {
    val bottomPos = racketHTML.offsetHeight.toInt
    val posY = model.nextPosition(ball.y, ball.speed, ball.directionY) - bottomPos
    val racketPosY = model.racketPositionY(racketHTML, ballHTML)
    posY > racketPosY;
  }
}
