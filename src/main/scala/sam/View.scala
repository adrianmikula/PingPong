package sam

import components.{Ball, Player}
import org.scalajs.dom.html.Div
import pong.Game.Bounds


class View (val playgroundHTML: Div, var racketHTML: scala.scalajs.js.Array[Div], val ballHTML: Div, val scoreHTML: Div, val startHTML: Div,val pauseHTML: Div, val gameOverHTML: Div, val debugHTML: Div)
  {

  View.view = this

    def resize(): Unit =
    {
      // resize the rackets
      for (player <- Player.players) {
        View.view.racketHTML(player.id).style.width = player.paddle.width + "px"
        View.view.racketHTML(player.id).style.height = player.paddle.height + "px"
      }

      // resize the ball
      View.view.ballHTML.style.width = Ball.radius*2 + "px"
      View.view.ballHTML.style.height = Ball.radius*2 + "px"
      View.view.ballHTML.style.borderRadius = Ball.radius + "px"
    }


  /**
    * Sets the left and top attributes of the ballHtml div
    * @param ballHTML
    */
  def drawBall() {

    ballHTML.style.left = Ball.x - Ball.radius + "px"
    ballHTML.style.top = Ball.y - Ball.radius  + "px"
  }




  /**
    * Sets the left attribute for the racket html div
    * @param racketHTML
    * @param pixelPos
    */
  def drawRackets() {
    for (player <- Player.players) {
      racketHTML(player.id).style.left = player.paddle.x - player.paddle.width/2 + "px"
      racketHTML(player.id).style.top = player.paddle.y - player.paddle.height/2 + "px"
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
  def drawEndGame(endMessage:String) = {
    gameOverHTML.innerHTML = endMessage
    gameOverHTML.style.display = "block"
  }


    def hideWelcome(): Unit =
    {
      startHTML.style.display = "none"
    }


}


object View {
  var view:View = null

  def render(): Unit =
  {
    View.view.drawBall()
    View.view.drawRackets()
    View.view.drawScore()

    // TODO handle end game
    //View.view.drawEndGame(false)
  }

  def debug(message: String): Unit =
  {
    view.debugHTML.innerHTML = message
  }
}

