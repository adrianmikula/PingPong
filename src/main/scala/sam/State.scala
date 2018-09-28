package sam

import components.{Ball, Player}
import pong.Game.{Bounds, Status, status}

object State {

  var score:Array[Int] = Array[Int](0,0)
  var pressedKeys:Map[Int,Boolean] = Map()

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


  def startGame(): Unit =
  {
     if (isStoped) {
        status = Status.Running
        View.view.hideWelcome()
        Action.startTimer()
       View.debug("started game")
      }

  }


  def pauseOrContinueGame(): Unit =
  {
    if (isPaused) {
      status = Status.Running
      Action.startTimer()
      View.debug("resumed game")
    }
    else
    {
      status = Status.Paused
      Action.stopTimer()
      View.debug("paused game")
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
    View.view.drawEndGame(endMessage)
    View.debug("ended game")
    Action.stopTimer()
  }

  def checkForCollisions(): Unit =
  {

    for (player <- Player.players)
      {
        // check for bounces on the paddles
        if (player.paddle.touchesBall())
          {
            if (player.side == Left || player.side == Right)
              {
                Action.bounceBall(true, false)
              }
            else
              {
                Action.bounceBall(false, true)
              }
          }
          // check for the ball hitting the player's goal
        else if (player.goal.touchesBall())
          {
            endGame(player.id)
          }
      }

    // check for the ball hitting a wall
      if (Ball.x - Ball.radius <= Bounds.left || Ball.x + Ball.radius >= Bounds.right)
        Action.bounceBall(true, false)

      if (Ball.y - Ball.radius <= Bounds.top || Ball.y + Ball.radius >= Bounds.bottom)
        Action.bounceBall(false, true)

  }


}
