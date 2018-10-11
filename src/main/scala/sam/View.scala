package sam

import components.{Ball, Player}
import org.scalajs.dom.html.Div
import pong.Game.Bounds
import pong.Settings


class View (){
  var playgroundHTML = None:Option[Div]
  var  racketHTML = None : Option[scala.scalajs.js.Array[Div]]
  var  ballHTML= None:Option[Div]
  var  scoreHTML= None:Option[Div]
  var  startHTML= None:Option[Div]
  var pauseHTML= None:Option[Div]
  var  gameOverHTML= None:Option[Div]
  var  countdownHTML= None:Option[Div]



  // provide a no-arg constructor for the singleton to use
//  def this() {
//    this(null, null, null, null, null, null, null, null)
//  }
//
  View.view = this

    def resizeObjects(): Unit =
    {
      // resize the rackets
      for (player <- Player.players) {
        racketHTML.get(player.id).style.width = player.paddle.width + "px"
        racketHTML.get(player.id).style.height = player.paddle.height + "px"
      }

      // resize the ball
      ballHTML.get.style.width = Ball.ball.radius*2 + "px"
      ballHTML.get.style.height = Ball.ball.radius*2 + "px"
      ballHTML.get.style.borderRadius = Ball.ball.radius + "px"
    }

    def resizeBounds(): Unit =
    {
      playgroundHTML.get.style.width = Bounds.right + "px"
      playgroundHTML.get.style.height = Bounds.bottom + "px"
    }


  /**
    * Sets the left and top attributes of the ballHtml div
    * @param ballHTML
    */
  def drawBall() {

    ballHTML.get.style.left = Ball.ball.x - Ball.ball.radius + "px"
    ballHTML.get.style.top = Ball.ball.y - Ball.ball.radius  + "px"
  }




  /**
    * Sets the left attribute for the racket html div
    * @param racketHTML
    * @param pixelPos
    */
  def drawRackets() {
    for (player <- Player.players) {
      racketHTML.get(player.id).style.left = player.paddle.x - player.paddle.width/2 + "px"
      racketHTML.get(player.id).style.top = player.paddle.y - player.paddle.height/2 + "px"
    }

  }

  /**
    * Sets the  scoreHtml div with the new score value
    * @param scoreHTML
    */
  def drawScore() = {
    scoreHTML.get.innerHTML = ""
    for (player <- Player.players) {
      scoreHTML.get.innerHTML += "Player " + player.id + ": " + State.score(player.id).toString + "<br/>"
    }
  }

  /**
    * Checks if the game over or not then displays the gameOver html div block
    * @param gameOverHTML
    * @param isOver
    */
  def showResult(endMessage:String) = {
    gameOverHTML.get.innerHTML = endMessage
    gameOverHTML.get.style.display = "block"
  }

    def hideResult(): Unit =
    {
      gameOverHTML.get.style.display = "none"
    }

    def hideWelcome(): Unit =
    {
      startHTML.get.style.display = "none"
    }

    def showPause() = {
      pauseHTML.get.style.display = "block"
    }


    def hidePause() = {
      pauseHTML.get.style.display = "none"
    }

    def showCountDown() = {
      countdownHTML.get.innerHTML = ""
      countdownHTML.get.style.display = "block"
    }

    def hideCountDown(): Unit =
    {
      countdownHTML.get.style.display = "none"
    }

    def refreshCountDown(counter:Int): Unit =
    {
      countdownHTML.get.innerHTML = "Starting in " + counter
    }

  def render(): Unit =
  {
    drawBall()
    drawRackets()
    drawScore()

    // TODO handle end game
    //View.view.drawEndGame(false)
  }

}


// retain a singleton instance
object View {
   var view = new View()

}

