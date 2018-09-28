package pong

import org.scalajs.dom.html.Div

import scala.collection.mutable.Map
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import components._
import sam._

import scala.collection

@JSExport
object Game {

  /** *
    * Singleton object status to holds the status of the to be enum of the
    * following values :Stopped , Running and Game over
    */
  object Status extends Enumeration {
    var Stopped, Running, Paused = Value
  }

  object Side extends Enumeration {
    var Left, Right, Top, Bottom = Value
  }

  object Bounds {
    val top = 0
    val left = 0
    val right = 300
    val bottom = 400
  }

  /**
    * Singleton object key to holds movement quantity in x axis as
    * two variable right and left direction
    */
  object Key  {
    val Left = 37
    val Right = 39
    val A = 65
    val D = 68
    val Esc = 27

    //TODO use official key constants
    val Enter = org.scalajs.dom.ext.KeyCode.Enter
  }

  import Status._
//
//  /**
//    * Instance of Model
//    */
//  var model: Model = new Model()
//  /**
//    * Instance of State
//    */
//  var state: State = new State()
//  /**
//    * Instance of View
//    */
//  var view: View = new View()
//  /**
//    * Instance of Action
//    */
//  var action: Action = new Action()

//  /**
//    * Holds pressed key value as a map while :
//    * the key of map reflects the amount of movement
//    * in x direction and the value reflects if player
//    * pressed the right or left key
//    */
//  var pressedKey: Map[Int, Boolean] = Map(Key.Left -> false, Key.Right -> false)

  /**
    * Holds status for the game
    */
  var status = Stopped
  /**
    * Holds score
    */
  var score = 0

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
  def startPong(playgroundHTML: Div, rackets: scala.scalajs.js.Array[Div], ballHTML: Div, scoreHTML: Div, startHTML: Div, pauseHTML: Div, gameOverHTML: Div, debugHTML: Div ): Unit =
  {
    //TODO initialise View in a safer way
    new View(playgroundHTML, rackets, ballHTML, scoreHTML, startHTML, pauseHTML, gameOverHTML, debugHTML)

    var moveLeft = new Movement(-1 * Settings.paddleSpeed, 0)
    var moveRight = new Movement(1 * Settings.paddleSpeed, 0)

    val arrowKeys = collection.Map(Key.Left -> moveLeft, Key.Right -> moveRight)
    Player.players += new Player("Player 1", arrowKeys, Side.Top)

    val wasdKeys = collection.Map(Key.A -> moveLeft, Key.D -> moveRight)
    Player.players += new Player("Player 2", wasdKeys, Side.Bottom)

    // update the size of the HTML elements
    View.view.resize()

    // register keyboard event listeners
    Action.listenToKeys()
  }

  def main(args: Array[String]): Unit = {
  }
}

