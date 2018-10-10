package sam

import components.{Ball, AnimateObject, Paddle, Player}
import pong.Game.Bounds
import pong.Settings

object Model {


  def startPaddle(player:Player, motion:AnimateObject): Unit =
  {
    player.paddle.motion = motion
  }

  def stopPaddle(player:Player): Unit =
  {
    player.paddle.motion = new AnimateObject(0,0)
  }


  def movePaddle (paddle:Paddle, motion:AnimateObject): Unit =
  {
    if (paddle.isWithinBounds(motion))
    {
      paddle.x = (paddle.x + paddle.motion.x).toInt
      paddle.y = (paddle.y + paddle.motion.y).toInt
    }
  }

//  def movePaddles (): Unit =
//  {
//    for (player <- Player.players) {
//      player.paddle.x += player.paddle.motion.x
//      player.paddle.y += player.paddle.motion.y
//    }
//  }

  def bounceBall (x:Boolean, y:Boolean, motion:AnimateObject): Unit =
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
    Ball.ball.x = (Ball.ball.x + Ball.ball.motion.x).toInt
    Ball.ball.y = (Ball.ball.y + Ball.ball.motion.y).toInt
  }


  def randomiseGame(): Unit = {

    // randomise the size of the playing field
    Bounds.right = scala.util.Random.nextInt(200) + 300
    Bounds.bottom = scala.util.Random.nextInt(200) + 300

    // shift the ball and paddles back to the centre
    Ball.ball = new Ball()
    for (player <- Player.players) {
      player.resetPaddle()
    }

    // launch the ball in a random direction at the start
    Ball.ball.randomiseMotion()

  }

  def increaseDifficulty(): Unit = {
    Settings.ballSpeed += 0.3
    //Settings.paddleSpeed += 0.5
    Settings.ballSpin += 0.05

    // update the paddle speeds
    for (player <- Player.players) {
      for ((key, motion) <- player.keys)
      {
        motion.x *= 1.05
        motion.y *= 1.05
      }
    }
  }


}
