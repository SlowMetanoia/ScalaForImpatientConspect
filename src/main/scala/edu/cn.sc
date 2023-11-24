
//------------------------------------------------------ class ---------------------------------------------------------
case class ComplexNumber(re:Double, im:Double = 0) extends Numeric[ComplexNumber]{
  import math._

  def isReal:Boolean = im == 0
  def length:Double = sqrt(re*re+im*im)



  override def plus(x: ComplexNumber, y: ComplexNumber) = ComplexNumber(x.re+y.re,x.im+y.im)

  override def minus(x: ComplexNumber, y: ComplexNumber) = ComplexNumber(x.re-y.re,x.im-y.im)

  override def times(x: ComplexNumber, y: ComplexNumber) =
    ComplexNumber(
      x.re*y.re - x.im*y.im,
      x.re*y.im + y.re*x.im
    )

  override def negate(x: ComplexNumber) = ComplexNumber(-x.re,-x.im)

  def unary_-():ComplexNumber = negate(this)

  override def fromInt(x: Int) = ComplexNumber(x)

  override def parseString(str: String) = ???

  override def toInt(x: ComplexNumber) = if(x.im==0) re.toInt else throw new IllegalStateException()

  override def toLong(x: ComplexNumber) = if(x.im==0) re.toLong else throw new IllegalStateException()

  override def toFloat(x: ComplexNumber) = if(x.im==0) re.toFloat else throw new IllegalStateException()

  override def toDouble(x: ComplexNumber) = if(x.im==0) re else throw new IllegalStateException()

  override def compare(x: ComplexNumber, y: ComplexNumber):Int = x.length.compare(y.length)

  override def toString: String = {
    s"$re + ${im}i"
  }
}
object ComplexNumber{

}

val cm0 = ComplexNumber(-4,7.82)

-cm0
