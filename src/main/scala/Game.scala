package pong

import org.scalajs.dom.html.Div

import scala.collection.mutable.Map
import scala.scalajs.js.annotation.JSExport
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
    var Stopped, Running, GameOver = Value
  }

  object Side extends Enumeration {
    var Left, Right, Top, Bottom = Value
  }

  object Bounds {
    val top = 0
    val left = 0
    val right = 400
    val bottom = 300
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
  def startPong(playgroundHTML: Div, rackets: Array[Div], ballHTML: Div, scoreHTML: Div, startHTML: Div, gameOverHTML: Div): Unit = {
    val arrowKeys = collection.Map(Key.Left -> new Movement(-1, 0), Key.Right -> new Movement(1, 0))
    Player.players += new Player("Player 1", arrowKeys, Side.Top)

    val wasdKeys = collection.Map(Key.A -> new Movement(-1, 0), Key.D -> new Movement(1, 0))
    Player.players += new Player("Player 2", wasdKeys, Side.Bottom)

    Ball.x = (Bounds.left + Bounds.right)/2
    Ball.y = (Bounds.top + Bounds.bottom)/2

//    for (player <- Player.players)
//    {
//      for (key <- player.keys)
//      {
//        State.pressedKeys(key) = false
//      }
//    }

    new View(playgroundHTML, rackets, ballHTML, scoreHTML, startHTML, gameOverHTML)
    Action.listenToKeys()
  }

  def main(args: Array[String]): Unit = {}
}

