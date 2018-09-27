package components

 class Paddle ( x:Int,  y:Int,  width:Int,  height:Int) extends Rectangle (x, y, width, height) {

  var motion : Movement = new Movement(0,0)

}
