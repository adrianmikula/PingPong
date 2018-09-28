package sam

import components.{Ball, Movement, Paddle, Player}

object Model {


  def startPaddle(player:Player, motion:Movement): Unit =
  {
    player.paddle.motion = motion
  }

  def stopPaddle(player:Player): Unit =
  {
    player.paddle.motion = new Movement(0,0)
  }


  def movePaddle (paddle:Paddle, motion:Movement): Unit =
  {
    if (paddle.isWithinBounds(motion))
    {
      paddle.x += paddle.motion.x
      paddle.y += paddle.motion.y
    }
  }

//  def movePaddles (): Unit =
//  {
//    for (player <- Player.players) {
//      player.paddle.x += player.paddle.motion.x
//      player.paddle.y += player.paddle.motion.y
//    }
//  }

  def bounceBall (x:Boolean, y:Boolean, motion:Movement): Unit =
  {
    if (x) {
      Ball.ball.bounceX(motion)
    }
    if (y)
    {
      Ball.ball.bounceY(motion)
    }
  }

  def moveBall (): Unit =
  {
    Ball.ball.x += Ball.ball.motion.x
    Ball.ball.y += Ball.ball.motion.y
  }


}
