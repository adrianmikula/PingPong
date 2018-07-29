package pong
import org.scalajs.dom.html.Div
import pong.Game.ball
class View {
  /**
    * Sets the left and top attributes of the ballHtml div
    * @param ballHTML
    */
  def drawBall(ballHTML: Div) {
    ballHTML.style.left = ball.x + "px"
    ballHTML.style.top = ball.y + "px"
  }

  /**
    * Sets the left attribute for the racket html div
    * @param racketHTML
    * @param pixelPos
    */
  def drawRacket(racketHTML: Div, pixelPos: Int) {
    racketHTML.style.left = pixelPos + "px"
  }

  /**
    * Sets the  scoreHtml div with the new score value
    * @param scoreHTML
    */
  def drawScore(scoreHTML: Div) = scoreHTML.innerHTML = Game.score.toString

  /**
    * Checks if the game over or not then displays the gameOver html div block
    * @param gameOverHTML
    * @param isOver
    */
  def drawEndGame(gameOverHTML: Div, isOver: Boolean) = if (isOver) gameOverHTML.style.display = "block"
}
