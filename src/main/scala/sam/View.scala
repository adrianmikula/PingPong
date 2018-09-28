package sam

import components.{Ball, Player}
import org.scalajs.dom.html.Div
import pong.Game.Bounds
import pong.Settings


class View (val playgroundHTML: Div, var racketHTML: scala.scalajs.js.Array[Div], val ballHTML: Div, val scoreHTML: Div, val startHTML: Div,val pauseHTML: Div, val gameOverHTML: Div, val countdownHTML: Div)
  {

  View.view = this

    def resizeObjects(): Unit =
    {
      // resize the rackets
      for (player <- Player.players) {
        View.view.racketHTML(player.id).style.width = player.paddle.width + "px"
        View.view.racketHTML(player.id).style.height = player.paddle.height + "px"
      }

      // resize the ball
      View.view.ballHTML.style.width = Ball.ball.radius*2 + "px"
      View.view.ballHTML.style.height = Ball.ball.radius*2 + "px"
      View.view.ballHTML.style.borderRadius = Ball.ball.radius + "px"
    }

    def resizeBounds(): Unit =
    {
      View.view.playgroundHTML.style.width = Bounds.right + "px"
      View.view.playgroundHTML.style.height = Bounds.bottom + "px"
    }


  /**
    * Sets the left and top attributes of the ballHtml div
    * @param ballHTML
    */
  def drawBall() {

    ballHTML.style.left = Ball.ball.x - Ball.ball.radius + "px"
    ballHTML.style.top = Ball.ball.y - Ball.ball.radius  + "px"
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
  def showResult(endMessage:String) = {
    gameOverHTML.innerHTML = endMessage
    gameOverHTML.style.display = "block"
  }

    def hideResult(): Unit =
    {
      gameOverHTML.style.display = "none"
    }

    def hideWelcome(): Unit =
    {
      startHTML.style.display = "none"
    }

    def showPause() = {
      pauseHTML.style.display = "block"
    }


    def hidePause() = {
      pauseHTML.style.display = "none"
    }

    def showCountDown() = {
      countdownHTML.innerHTML = ""
      countdownHTML.style.display = "block"
    }

    def hideCountDown(): Unit =
    {
      countdownHTML.style.display = "none"
    }

    def refreshCountDown(counter:Int): Unit =
    {
      countdownHTML.innerHTML = "Starting in " + counter
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

}

