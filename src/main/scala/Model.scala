package pong

import org.scalajs.dom.html.Div
import Game._

class Model {
  /**
    * Calculates the new position of the ball according the current position
    * in both direction y and x
    *
    * @param currentPosition
    * @param speed
    * @param direction
    * @return the new position of the ball
    */
  def nextPosition(currentPosition: Int, speed: Int, direction: Int): Int = currentPosition + speed * direction

  /**
    * Gets the new x direction of the ball regarding to its position in the playgroundHTML
    *
    * @param playgroundHTML
    * @return the new directionX of the ball
    */
  def moveBallDirectionX(playgroundHTML: Div): Int = {
    val width = playgroundHTML.offsetWidth
    var directionX = ball.directionX
    var positionX = nextPosition(ball.x, ball.speed, ball.directionX)
    if (positionX > width) directionX = -1
    if (positionX < 0) directionX = 1
    directionX
  }

  /**
    * Gets the new y direction of the ball regarding to its position in the playgroundHTML
    *
    * @param playgroundHTML
    * @return the new directionY of the ball
    */
  def moveBallDirectionY(playgroundHTML: Div): Int = {
    val height = playgroundHTML.offsetHeight
    var directionY = ball.directionY
    val positionY = nextPosition(ball.y, ball.speed, ball.directionY)
    if (positionY > height) directionY = -1
    if (positionY < 0) directionY = 1
    directionY
  }

  /**
    * Gets the position of the ball according its speed and  direction
    *
    * @param direction
    * @return the position of the ball
    */
  def moveBallPosition(direction: Int): Int = ball.speed * direction

  /**
    * Updates the ball coordinate in x and y axis with new values
    *
    * @param dirX
    * @param posX
    * @param dirY
    * @param posY
    */
  def changeBallPosition(dirX: Int, posX: Int, dirY: Int, posY: Int) {
    ball.directionX = dirX
    ball.directionY = dirY
    ball.x += posX
    ball.y += posY
  }

  /**
    * Sets y direction of the ball to -1 if the racket is hit
    *
    * @param hit
    */
  def changeDirectionY(hit: Boolean) = if (hit) ball.directionY = -1

  /**
    * Change the score with new value
    *
    * @param newScore
    */
  def changeScore(newScore: Int) = score = newScore

  /**
    * Change the x position (right & left) of the racket depends on the key pressed
    *
    * @param racketHTML
    * @return the new position of the racket
    */
  def moveRacket(racketHTML: Div): Int = {
    val left: Int = racketHTML.offsetLeft.toInt
    if (pressedKey(Key.Left)) left - 5
    else if (pressedKey(Key.Right)) left + 5
    else left

  }

  /**
    * Gets the racket position y
    *
    * @param racketHTML
    * @param ballHTML
    * @return racket position on y axis
    */
  def racketPositionY(racketHTML: Div, ballHTML: Div): Int = {
    val ballSize = ballHTML.offsetHeight.toInt
    racketHTML.offsetTop.toInt - ballSize / 2 // subtracting size of ball for doesn't pass through racket
  }

}
