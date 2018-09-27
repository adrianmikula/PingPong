package sam

import components.{Ball, Movement, Player}

object Model {


  def startPaddle(player:Player, motion:Movement): Unit =
  {
    player.paddle.motion = motion
  }

  def stopPaddle(player:Player): Unit =
  {
    player.paddle.motion = new Movement(0,0)
  }

  def movePaddles (): Unit =
  {
    for (player <- Player.players) {
      player.paddle.x += player.paddle.motion.x
      player.paddle.y += player.paddle.motion.y
    }
  }

  def bounceBall (x:Boolean, y:Boolean): Unit =
  {
    if (x) {
      Ball.motion.x *= -1
    }
    if (y)
    {
      Ball.motion.y *= -1
    }
  }

  def moveBall (): Unit =
  {
    Ball.x += Ball.motion.x
    Ball.y += Ball.motion.y
  }


}
