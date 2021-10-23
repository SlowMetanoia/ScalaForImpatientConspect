import scala.collection.mutable.ArrayBuffer

//--------------------------------------------------------наследование--------------------------------------------------
class Person{  //это просто класс
  var name = ""
  var age = 0
}
class Employer extends Person{  //наследуемся от класса Person
  var stage = 0
}

final class Employee extends Person{      // наследуемся от Person и запрещаем наследоваться от Employee
  final override def toString: String = s"${getClass.getName}[name = $name, age = $age]"
                                          // переопределяем метод и запрещаем его переопределять дальше
  def oldToString:String = super.toString // вызов метода суперкласса
  protected var owo = 0                   // protected как в Java
  protected[this] var uwu = 0             // protected[this] по аналогии с private[this]
}

var p0 = new Person
var e0r = new Employer
var e0e = new Employee

e0r.isInstanceOf[Person]  //проверка на соответствие типов если в e0r лежит null, то isInstanceOf вернёт false
e0r.asInstanceOf[Person]  //приведение типов если в e0r лежит null, то asInstanceOf вернёт null

e0r match{            //сопоставление с образцом
  case s:Person => {} //обработка как Person
  case _=>{}          //обработка, если ни одно выше не верно.
}

class Joker(name:String,age:Int)
class Clown(name:String,age:Int,stage:Int) extends Joker(name,age) //наследование с конструктором

class Man(val name:String,val age:Int) {
  override def toString: String = s"${getClass.getName}[name = $name, age = $age]"
}
class Secret(secret:String,age:Int) extends Man(secret,age) { //переопределение val и def
  override val name = "secret"          //затрёт name
  override val toString = "secret"      //затрёт метод toString
}

val a = new Secret("name",30)
a.toString

/**
 * ограничения:
 * def переопределяет только def
 * val пепреопределяет val и def
 * var переопределяет только val из абстрактного класса.
 */
val alien = new Man("Fred",30){ //объявдение анонимного подкласса
  def greeting = "SAY HELLO TO MY LITTLE FRIEND"
}                                            //такая штука будет иметь тип Man{def greeting:String}, этот тип можно передавать.
def SayHello(man:Man{def greeting:String}) = println(s"${man.name} say: ${man.greeting}")
SayHello(alien)
class Node(id:Int){
  val children = new ArrayBuffer[Node]
}
val nodeP = new Node(10){                 //Node{val node:Node}
  val node = new Node(9)
}
abstract class AnotherNode {                  //абстрактный класс
  val id: Int                                 //абстрактное поле
}
