package components

class Vector(val x:Int, val y:Int) {


  // add the vectors together
  def +(another:Vector):Vector=
    {
      new Vector(this.x + another.x, this.y+another.y)
    }

  // subtract the vectors from each other
  def -(another:Vector):Vector=
  {
    new Vector(this.x - another.x, this.y-another.y)
  }

  // vector dot product
  // https://www.mathsisfun.com/algebra/vectors-dot-product.html
  def *(another:Vector):Int=
  {
    (this.x * another.x + this.y * another.y)
  }

  // vector cross product
  // refer to https://www.mathsisfun.com/algebra/vectors-cross-product.html
  def x(another:Vector):Vector=
  {
    new Vector(this.y - another.y, this.x - another.x)
  }


  // vector cross product
  // refer to https://www.mathsisfun.com/algebra/vectors-cross-product.html
  def rotateLeft():Vector=
  {
    new Vector(-this.y, this.x)
  }


  // vector cross product
  // refer to https://www.mathsisfun.com/algebra/vectors-cross-product.html
  def rotateRight():Vector=
  {
    new Vector(this.y, -this.x)
  }
}
