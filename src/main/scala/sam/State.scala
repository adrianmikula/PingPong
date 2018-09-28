package sam

import com.sun.javafx.image.impl.IntArgb.ToIntArgbPreConv
import components.{Ball, Movement, Player}
import pong.Game.{Bounds, Side, Status, status}
import pong.Settings
import sam.Action.startTimer

object State {

  var score:Array[Int] = Array[Int](0,0)
  var pressedKeys:scala.collection.mutable.Map[Int,Boolean] = scala.collection.mutable.Map()

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

  def isPaused: Boolean = status == Status.Paused

  def isCountDown: Boolean = status == Status.CountDown

  def pressKey(key:Int): Unit =
  {
    pressedKeys(key) = true
  }

  def releaseKey(key:Int): Unit =
  {
    pressedKeys(key) = false
  }

  def updatePaddleMotion(pressed:Int): Unit =
  {
    // find the players who use this key
    for (player <- Player.players)
    {
      if (player.keys.contains(pressed))
      {
        // sum the motion for all pressed keys
        player.paddle.motion = new Movement(0,0)
        for ((key, motion) <- player.keys)
        {
          if (State.isPressed(key)) {
            player.paddle.motion.x += motion.x
            player.paddle.motion.y += motion.y
          }
        }
      }
    }
  }

  def isPressed(key:Int): Boolean =
  {
     pressedKeys.contains(key) && pressedKeys(key)
  }

  def startGame(): Unit =
  {
    status = Status.Running
    View.view.hideCountDown()
    Action.startTimer()
  }

  def prepareGame(): Unit =
  {
     if (isStoped) {

       if (Settings.increaseDifficulty)
         {
           Model.increaseDifficulty()
         }

       Model.randomiseGame()

       // update status and view elements
       View.view.hideWelcome()
       View.view.hideResult()
       View.view.resizeBounds()
       View.view.drawBall()
       View.view.drawRackets()
       status = Status.CountDown

       // start timer
       View.view.showCountDown()
       Action.countDown(Settings.countDownSeconds)
      }

  }


  def pauseOrContinueGame(): Unit =
  {
    if (isPaused) {
      status = Status.Running
      Action.startTimer()
      View.view.hidePause()
    }
    else
    {
      status = Status.Paused
      Action.stopTimer()
      View.view.showPause()
    }
  }


  def endGame(loser:Int): Unit =
  {
    var endMessage = "The game was won by "
    for (player <- Player.players)
      {
        // everyone except the loser gets a point
        if (player.id != loser)
        {
          score(player.id) += 1
          endMessage += player.name
        }
      }
    endMessage += "<br/><br/>Press Enter to start a new game"
    View.view.showResult(endMessage)
    Action.stopTimer()
    status = Status.Stopped
  }

  def checkForCollisions(ball:Ball): Unit =
  {

    for (player <- Player.players)
      {
        // check for bounces on the paddles
        if (player.paddle.touchesBall(ball))
          {
            if (player.side == Side.Left && ball.motion.x < 0)
              {
                Action.bounceBall(true, false, player.paddle.motion)
              }
            else if (player.side == Side.Right && ball.motion.x > 0)
              {
                Action.bounceBall(true, false, player.paddle.motion)
              }
            else if (player.side == Side.Top && ball.motion.y < 0)
            {
              Action.bounceBall(false, true, player.paddle.motion)
            }
            else if (player.side == Side.Bottom && ball.motion.y > 0)
            {
              Action.bounceBall(false, true, player.paddle.motion)
            }
          }
      }

    var wasHit :scala.collection.mutable.Map[Side.Value, Boolean] = scala.collection.mutable.Map()

    // check for the ball hitting a vertical wall
    wasHit(Side.Left) = ball.x - ball.radius <= Bounds.left
    wasHit(Side.Right) = ball.x + ball.radius >= Bounds.right
    wasHit(Side.Top) = ball.y - ball.radius <= Bounds.top
    wasHit(Side.Bottom) = ball.y + ball.radius >= Bounds.bottom


    // check if the wall we hit belonged to a player,
    // in which case the game is over!
    for ((side, hit) <- wasHit) {
      if (hit && Player.sides.contains(side)) {
        State.endGame(Player.sides(side).id)
      }
    }

    // check for the ball hitting a vertical wall
      if (wasHit(Side.Left) || wasHit(Side.Right)) {
        Action.bounceBall(true, false, new Movement())
      }

    // check for the ball hitting a horizontal wall
      if (wasHit(Side.Top) || wasHit(Side.Bottom)) {
        Action.bounceBall(false, true, new Movement())
      }

  }


}
