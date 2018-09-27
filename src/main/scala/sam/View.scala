package sam

import components.{Ball, Player}
import org.scalajs.dom.html.Div
import pong.Game

class View (playgroundHTML: Div, racketHTML: Array[Div], ballHTML: Div, scoreHTML: Div, startHTML: Div, gameOverHTML: Div)
  {

  View.view = this


  /**
    * Sets the left and top attributes of the ballHtml div
    * @param ballHTML
    */
  def drawBall(ballHTML: Div) {

    ballHTML.style.left = Ball.x + "px"
    ballHTML.style.top = Ball.y + "px"
  }




  /**
    * Sets the left attribute for the racket html div
    * @param racketHTML
    * @param pixelPos
    */
  def drawRackets() {
    for (player <- Player.players) {
      racketHTML(player.id).style.left = player.paddle.x + "px"
      racketHTML(player.id).style.top = player.paddle.y + "px"
    }

  }

  /**
    * Sets the  scoreHtml div with the new score value
    * @param scoreHTML
    */
  def drawScore() = {
    scoreHTML.innerHTML = ""
    for (player <- Player.players) {
      scoreHTML.innerHTML += "Player " + player.id + ": " + State.score(player.id).toString + "<br/>"
    }
  }

  /**
    * Checks if the game over or not then displays the gameOver html div block
    * @param gameOverHTML
    * @param isOver
    */
  def drawEndGame(gameOverHTML: Div, isOver: Boolean) = if (isOver) gameOverHTML.style.display = "block"
}


object View {
  var view:View = null
}

