import scala.collection.mutable.ArrayBuffer

/**
 * Глава 8: Наследование
 */
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

/**
 * Тут стоит внимательно взглянуть на схему наследования в scala.(Например в папке проекта)
 * Итак, классы в scala:
 * Any - определяет методы isInstanceOf, asInstanceOf, методы вычисления хеш-кода и методы сравнения
 * AnyVal и AnyRef наследуются от Any.
 * AnyVal не добавляет методов, он служит маркером всех типоа-значений.
 * AnyRef добавляет методы мониторинга из такие-же как в Java-Object: wait, notify, notifyAll
 * и synchronized с параметром-функцией
 * (Хотя, лучше этих вещей не использовать без необходимости,
 * заменяя их более высокоуровневыми конструкциями).
 * Все scala-классы реализуют интерфейс-маркер ScalaObject(у него нет методов).
 * тип Null - явдяется подтипом чего угодно, что наследуется от AnyRef и имеет еодиственное значение - null
 * тип Nothing наследуется от всего и не имеет значений.
 * Nothing - не тоже самое, что void в Java и С++.
 * void в scala представлен типом Unit имеющим единственное значение - ()
 */
def Show(x:Any) = println(s"${x.getClass} $x")
/**
 * Немного странного: Если метод имеет параметр типа Any или AnyRef,
 * то при передаче нескольких параметров они будут преобразованы в кортеж:
 */
Show(3)
Show(3,4,5)
//А если без аргуметов, то какой-то странный Unit...
Show()
//---------------------------------------------------равество объектов--------------------------------------------------
class Something(name:String, amount:Int)
val inst1 = new Something("nothing",0)
val inst2 = new Something("nothing",0)
inst1.eq(inst2)
inst1.eq(inst1)
inst1.equals(inst2)
//eq проверяет, что объекты лежат по одной ссылке то есть проверяется не равенство, а тождественность.
//equals по умолчанию использует eq но часто нужна будет более гибкая его реализация и тогда:
class Smthg(val name:String, val amount:Int){
  //equals должен принимать именно Any!
  final override def equals(obj: Any): Boolean = {
    //я использую pattern matching, потому что это читабельнее и короче. Иначе можно, но не нужно.
    obj match {
      case that:Smthg => name==that.name && amount == that.amount
      case _ => false
    }
  }// метод объявляется final, чтобы для любых подклассов было верно, что a.equals(b)==b.equals(a)
  /**
   * ВАЖНО: существует соглашение между equals и hashСode, поэтому переопределяя equals нужно обязательно переопределять
   * и хеш. Причём новый хеш должен вычисляться на основе только тех полей, которые сравниваются в equals.
   * Кстати, если уж зашла речь об этом, соглашегие звучит так: "Если у объектов одинаковый хеш, из этого не следует,
   * что они равны; если хеш разный,из этого следует, что объекты неравны"(равны и неравны с точки зрения equals)
   * то есть, хеш на самом деле делит все возможные объекты на группы, так что в рамках одной группы у объектов равный
   * хеш.
   *
   * Всё это нужно, для совместимости с объектами, которые работают с хешом, например, HashMap
   */
  final override def hashCode(): Int = (name,amount).##

  /**
   * Есть ещё метод ==, он проверяет, что оба операнда != null и вызывает equals => его переопределять НЕ НУЖНО.
   * А в подавляющем числе случаев в программе можно обойтись == не прибегая к eq и equals
   */
}
//-------------------------------------------------value classes--------------------------------------------------------
/**
 * Предыстроия такова: есть довольно много классов, имеющих единственное поле. Например, обёртки для протых типов(Int)
 * в scala для создания такого класса нужно наследоваться от AnyVal и в качестве единственного параметра конструктора
 * иметь val-параметр. Такой класс не должен иметь других полей и конструкторов. Тогда единственное поле этого класса
 * можно будет использовать непосредственно.
 */
class NotInt private(val value:Int) extends AnyVal{
  def procent = value/100
}
object NotInt {
  //можно и без этого, но так чуть удобнее, а сам код выглядит более читаемым в месте использования
  def apply(value:Int) = new NotInt(value)
}
val inst3 = NotInt(249)
//inst3+1 //- выдаст ошибку =) потому что наш класс - не инт, а значит мы не можем использовать интовые методы.
/**
 * Создавать собственные value-классы можно по разным причинам, например, чтобы специфицировать значение.
 * Условно говоря, так:
 */
class Book(val author:String, val title:String)
//при таком подходе, в общем-то всё будет работать. Но можно переписать так:
class Author(val value:String) extends AnyVal
class Title(val value:String) extends AnyVal
class Book2(val author: Author, title: Title)
//при таком подходе перепутать author и title не выйдет при всём желинии.