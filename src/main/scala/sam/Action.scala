package sam

import components.{Ball, Movement, Player}
import org.scalajs.dom.{KeyboardEvent, document}
import pong.{Game, Settings}

import scala.scalajs.js.timers.{RawTimers, SetIntervalHandle}

object Action {

  var handles :Set[SetIntervalHandle] =  Set()

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
    var startOrPauseGame = (evt: KeyboardEvent) => {
      if (evt.keyCode == Game.Key.Enter) {
        State.startGame()
      }
      if (evt.keyCode == Game.Key.Esc) {
        State.pauseOrContinueGame()
      }
    }
    document.addEventListener("keydown", startOrPauseGame)

  }


  def startTimer(): Unit =
  {
    if (State.isRunning) {

      val executeLoop = () => {
        updateModel()
        updateStateAndView()
      }

      handles += RawTimers.setInterval(executeLoop, Settings.refreshRate)
    }
  }

  def stopTimer(): Unit =
  {
    for (handle <- handles)
      RawTimers.clearInterval(handle)

    //TODO empty handlers set
  }

  def updateModel(): Unit =
  {
    // clear the bounce flags from the last cycle
    Ball.ball.resetBounceFlags()

    // check for collisions with the intended ball position
    State.checkForCollisions(Ball.ball.nextPosition())

    // move ball
    Model.moveBall()

    // move player paddles
    for (player <- Player.players) {
      Model.movePaddle(player.paddle, player.paddle.motion)

    }

  }

  def updateStateAndView(): Unit =
  {
//    // clear the bounce flags from the last cycle
//    Ball.ball.resetBounceFlags()
//
//    // check for collisions with the intended ball position
//    State.checkForCollisions(Ball.ball.nextPosition())

    // now refresh the view
    View.render()
  }

  def bounceBall(x:Boolean, y:Boolean, motion:Movement): Unit =
  {
    // bounce ball off a surface
    Model.bounceBall(x, y, motion)
  }

}
