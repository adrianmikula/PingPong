package pong

import org.scalajs.dom.html.Div
import scala.collection.mutable.Map
import scala.scalajs.js.annotation.JSExport

@JSExport
object Game {

  /** *
    * Singleton object status to holds the status of the to be enum of the
    * following values :Stopped , Running and Game over
    */
  object Status extends Enumeration {
    var Stopped, Running, GameOver = Value
  }

  /**
    * Singleton object key to holds movement quantity in x axis as
    * two variable right and left direction
    */
  object Key extends Enumeration {
    val Left = 37
    val Right = 39
  }

  import Status._

  /**
    * Instance of Model
    */
  var model: Model = new Model()
  /**
    * Instance of State
    */
  var state: State = new State()
  /**
    * Instance of View
    */
  var view: View = new View()
  /**
    * Instance of Action
    */
  var action: Action = new Action()
  /**
    * Holds pressed key value as a map while :
    * the key of map reflects the amount of movement
    * in x direction and the value reflects if player
    * pressed the right or left key
    */
  var pressedKey: Map[Int, Boolean] = Map(Key.Left -> false, Key.Right -> false)

  /**
    * Holds status for the game
    */
  var status = Stopped
  /**
    * Holds score
    */
  var score = 0

  /**
    * Singleton object ball to holds the ball coordinates and speed as 5 variables :
    * speed , x position , y position , x direction , and y direction
    */
  object ball {

    val speed = 5
    var x = 135
    var y = 100
    var directionX = -1
    var directionY = -1
  }

  /**
    * The first method invoked from the html page , call the load method from action
    * to start the game
    *
    * @param playgroundHTML
    * @param racketHTML
    * @param ballHTML
    * @param scoreHTML
    * @param startHTML
    * @param gameOverHTML
    */
  @JSExport
  def startPong(playgroundHTML: Div, racketHTML: Div, ballHTML: Div, scoreHTML: Div, startHTML: Div, gameOverHTML: Div): Unit = {
    action.load(playgroundHTML, racketHTML, ballHTML, scoreHTML, startHTML, gameOverHTML)
  }

  def main(args: Array[String]): Unit = {}
}

