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
    var startGame = (_: KeyboardEvent) => {
      State.startGame()
    }
    document.addEventListener("keydown", startGame)



    // add event listener for pausing game
    var pauseGame = (evt: KeyboardEvent) => {
      if (evt.keyCode == Game.Key.Esc) {
        State.pauseOrContinueGame()
      }
    }
    document.addEventListener("keydown", pauseGame)
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
    // move player's paddles
    //Model.movePaddles()

    for (player <- Player.players) {
      {
        Model.movePaddle(player.paddle, player.paddle.motion)
      }
    }

    // move ball
    Model.moveBall()
  }

  def updateStateAndView(): Unit =
  {
    // clear the bounce flags from the last cycle
    Ball.resetBounceFlags()

    // now update the state
    State.checkForCollisions()

    // now refresh the view
    View.render()
  }

  def bounceBall(x:Boolean, y:Boolean): Unit =
  {
    // bounce ball off a surface
    Model.bounceBall(x, y)
  }

}
