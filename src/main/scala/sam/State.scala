package sam

import components.Player

object State {

  var score:Array[Int] = Array[Int](0,0)
  var pressedKeys:Map[Int,Boolean] = Map()

  def startGame(): Unit =
  {
    Action.timerLoop()
  }


  def pauseGame(): Unit =
  {

  }


  def endGame(loser:Int): Unit =
  {
    //score(winner) += 1
  }

  def checkForCollisions(): Unit =
  {
    for (player <- Player.players)
      {
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
        else if (player.goal.touchesBall())
          {
            endGame(player.id)
          }
      }
  }


}
