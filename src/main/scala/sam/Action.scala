package sam

import components.{Ball, Movement, Player}
import org.scalajs.dom.{KeyboardEvent, document}

import scala.scalajs.js.timers.RawTimers

object Action {

  def listenToKeys(): Unit =
  {

    // add event listeners for player's keys
    for (player <- Player.players) {

      val markAsPressed = (evt: KeyboardEvent) => {
        if (player.keys.contains(evt.keyCode)) {
          player.paddle.motion = player.keys(evt.keyCode)
        }
      }
      document.addEventListener("keydown", markAsPressed)

      val markAsNotPressed = (evt: KeyboardEvent) => {
        if (player.keys.contains(evt.keyCode)) {
          player.paddle.motion = new Movement(0,0)
        }
      }
      document.addEventListener("keyup", markAsNotPressed)

    }

    // add event listener for starting game
    var startGame = (_: KeyboardEvent) => {
      State.startGame()
    }
    document.addEventListener("keydown", startGame)
  }

  def timerLoop(): Unit =
  {
    val executeLoop = () => {
      moveObjects()

    }
    RawTimers.setInterval(executeLoop, 16)

  }

  def moveObjects(): Unit =
  {
    // move player's paddles
    Model.movePaddles()

    // move ball
    Model.moveBall()

    // now update the state
    State.checkForCollisions()
  }

  def bounceBall(x:Boolean, y:Boolean): Unit =
  {
    // bounce ball off a surface
    Model.bounceBall(x, y)
  }

}
