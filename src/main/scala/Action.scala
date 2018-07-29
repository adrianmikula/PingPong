package pong

import Game._
import org.scalajs.dom.{KeyboardEvent, document}
import org.scalajs.dom.html.Div
import scala.scalajs.js.timers.RawTimers

class Action {
  /**
    * Checks if there is a trigger happened or not,if any key is pressed the game will be started
    * During the game the loop method invoked periodically every 16 millisecond
    *
    * @param playgroundHTML holds the div tag for the play ground
    * @param racketHTML     holds the div tag for the racket
    * @param ballHTML       holds the div tag for the ball
    * @param scoreHTML      holds the div tag for the score
    * @param startHTML      holds the div tag for the start status
    * @param gameOverHTML   holds the div tag for the game over status
    */
  def load(playgroundHTML: Div, racketHTML: Div, ballHTML: Div, scoreHTML: Div, startHTML: Div, gameOverHTML: Div): Unit = {
    val executeLoop = () => {
      loop(playgroundHTML, racketHTML, ballHTML, scoreHTML, gameOverHTML)
    }
    RawTimers.setInterval(executeLoop, 16)
    val markAsPressed = (evt: KeyboardEvent) => pressedKey(evt.keyCode) = true
    document.addEventListener("keydown", markAsPressed)
    val markAsNotPressed = (evt: KeyboardEvent) => pressedKey(evt.keyCode) = false
    document.addEventListener("keyup", markAsNotPressed)
    var startGame = (_: KeyboardEvent) => {
      if (state.isStoped) {
        status = Status.Running
        startHTML.style.display = "none"
      }
    }
    document.addEventListener("keydown", startGame)
  }

  /**
    * Runs periodically while the game is running , where controls :
    * racket position, ball position ,score ,and the status of the game every time
    *
    * @param playgroundHTML holds the div tag for the play ground
    * @param racketHTML     holds the div tag for the racket
    * @param ballHTML       holds the div tag for the ball
    * @param scoreHTML      holds the div tag for the score
    * @param gameOverHTML   holds the div tag for the game over status
    */
  def loop(playgroundHTML: Div, racketHTML: Div, ballHTML: Div, scoreHTML: Div, gameOverHTML: Div) {
    if (state.isRunning) {
      val newDirX = model.moveBallDirectionX(playgroundHTML)
      val newDirY = model.moveBallDirectionY(playgroundHTML)
      val newPosX = model.moveBallPosition(newDirX)
      val newPosY = model.moveBallPosition(newDirY)
      model.changeBallPosition(newDirX, newPosX, newDirY, newPosY)
      view.drawBall(ballHTML)
      val pixelPos = model.moveRacket(racketHTML)
      view.drawRacket(racketHTML, pixelPos)
      val hit = counter(racketHTML, ballHTML)
      val newScore = computeScore(hit, score)
      model.changeDirectionY(hit)
      model.changeScore(newScore)
      view.drawScore(scoreHTML)
      val isOver = state.isGameOver(racketHTML, ballHTML)
      endGame(isOver)
      view.drawEndGame(gameOverHTML, isOver)
    }
  }

  /**
    * Checks if the game is over ,if yes sets the status of game to GameOver
    *
    * @param isOver holds if the game is over or not
    */
  def endGame(isOver: Boolean) = if (isOver) status = Status.GameOver

  /**
    * Invokes isRacketHit to check if the racket is hit
    *
    * @param racketHTML
    * @param ballHTML
    * @return boolean holds if the racket is hit or not
    */
  def counter(racketHTML: Div, ballHTML: Div) = state.isRacketHit(racketHTML, ballHTML)

  /**
    * Checks if the racket is hit , the score will added by 1
    *
    * @param hit   holds boolean represents is hit or not
    * @param score holds the current score
    * @return the new score
    */
  def computeScore(hit: Boolean, score: Int): Int = if (hit) score + 1 else score
}