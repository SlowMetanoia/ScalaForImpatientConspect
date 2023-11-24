import java.time.LocalDate
import java.util.Date
import scala.annotation.unused
import scala.util.{Random, Try}

/**
 * -> lambda
 * -> Контейнеры и обёртки
 * -> структуры данных
 * -> классы, case-class
 */
//
//------------------------------------------------------ lambda --------------------------------------------------------

//Что такое функция?
//Что такое лямбда?

//функция
def f0(n:Int):Int = n+2
//lambda, одно и то-же разными словами
val f1 = (n:Int)=> n+2
val f3:Int=>Int = n => n+2
val f2:(Int,Int)=>Int = _ + _
val intSeq = Seq(1,3,7,9,4,8,2)
intSeq
  .filter(_ % 2==1)
  .map(_ * 2)
//lambda - функция без имени, в смысле, что лямбда может быть использована как значение.


/**
 * Написать функцию, переставляющую смежные элементы Seq
 */
//

Random.nextInt()
val xs = (1 to 5)
val intSeq3 = Seq(1,2,3,4,5,6,7,8)
Seq(2,1,4,3,6,5,8,7)

def swapPairs(seq:Seq[Int]):Seq[Int] = {
  seq
    .grouped(2)
    .flatMap {
      _.reverse
    }
    .toSeq
}

swapPairs(intSeq3)
Seq(
  Seq(1,2),
  Seq(3,4)
).flatten

//------------------------------------------------ class и case class --------------------------------------------------

//Что такое класс?
//Что такое объект?
//Что такое singleton?

//Просто  object - по сути своей - singleton
object config{
  var db: String = "postgre"
  var logger:String = "def"
  private var lastId = 0;
  def getNewId() = {
    lastId+=1
    lastId
  }
}


//class - аналог класса из java/c++
class Person0(
               //Вот это параметры конструктора,
               //они автоматически становятся атрибутами класса
               //по умолчанию val/var = <public> val/val
               //<без обозначений> = private[this] val
               //(считаем, что просто private, но если интересно - расскажу)
               val name: String,
               val surname: String,
               private val birthday: Date,
               private var age: Int
             ){
  //А вот здесь у нас - главный конструктор.
  //Но все переменные, которые здесь объявлены - атрибуты класса,
  private val id = config.getNewId()
  //а все функции - методы
  def getId:Int = id
}

//Как быть, если нам нужен метод или значение, относящееся не к конкретному объекту, а к классу целиком?
//А чего такого могут статически методы, чего не могут внешние функции?

//Объект-компаньон.
object Person0{
  //метод для сборки объекта, фабричный метод.
  def apply(name: String, surname: String, birthday: Date, age: Int): Person0 =
    new Person0(name, surname, birthday, age)

  //метод для разборки объекта
  def unapply(arg: Person0): Option[(String, String, Date, Int, Int)] =
    Some((arg.name,arg.surname,arg.birthday, arg.age, arg.id))
}

//Почему атрибуты класса в языках по-умолчанию - private?

case class Person1(
                    //а вот тут всё <public> val
                    name: String,
                    surname: String,
                    birthday: Int,
                    age: Int
                  )
Random.nextInt(8)
/**
 * Функция, перетасовывающая элементы в случайном порядке Seq[Int]=>Seq[Int]
 */
  //
  def randomizedOrder(seq: Seq[Int]):Seq[Int] = seq.sorted((x:Int,y:Int)=>Random.nextInt(2)-1)
randomizedOrder(intSeq3)

//------------------------------------------------ Контейнеры и обёртки  -----------------------------------------------
// Что такое коллекция?
// Что такое обёртка?
// Что такое контейнер?


val intSeq:Seq[Int] = Seq(1,2,3,5,-4,-4,-8,4,1,-9,21,-11,6,8)

val opt0:Option[Int] = Some(10)
opt0.map(_*2).map(_+1)
val opt1:Option[Int] = None

val either0:Either[Int,String] = Left(10)
val either1:Either[Int,String] = Right("kjhgdfjg")

val try0:Try[Int] = Try{
  10
}

val try1:Try[Int] = Try{
  throw new Exception("Something went wrong exception")
}

val optSeq = Seq[Option[Int]](
  Some(10),
  None,
  Some(4),
  Some(7),
  Some(-1),
  None,
  None
)
optSeq.flatten

val person = Person1("John","Snow",4,14)

val sum = person match {
  case Person1(name,surname,_,n) => s"$name $surname, $n y.o."
}
optSeq.collect{
    case Some(value) => value*2
  }

Seq(1,2,3) ++ Seq(4,5,6)

def reOrder(seq:Seq[Int]):Seq[Int] = seq.groupBy(_<=0).values.flatten.toSeq

def distinct(seq: Seq[Int]):Seq[Int] = {
  val seq0 = seq.sorted
  var last = seq.head
  seq0.flatMap { x => {
    if (x == last) None else {
      last = x; Some(x)}}}
}



reOrder(Seq(1,-4,-6,7,8,10,-5))

/**
 * Seq[Int]=>Seq[Int]
 * сначала положительные, потом неположительные.
 * порядок положительных и неположительных - должен сохраниться
 */


/**
 * 1. pattern-matching
 * 2. class и case-class ~> pattern-matching
 * 3. lambda
 * 4. Контейнеры и обёртки
 * 5. Применение pattern
 */
//
val str = "//------------------------------------------------- pattern-matching ---------------------------------------------------"
str.length

object symbolConstructions {
  val fullSize = 120

  def constructTheme(str: String) =
    if (str.length > fullSize - 4) throw new IllegalArgumentException("given string is too big")
    else print(
      s"//${"-" * ((fullSize - str.length - 4) / 2 + str.length%2)} $str ${"-" * ((fullSize - str.length - 4) / 2)}")
}

//------------------------------------------------- pattern-matching ---------------------------------------------------
//Знаете, что это?

abstract class Person(val name:String)
class Child(override val name:String, val age:Int) extends Person(name)
class Adult(override val name:String, val occupation:String) extends Person(name)
// Предположите, как написать функцию, Person => Boolean, является ли объект Child, или нет?


















def isChild(person:Person):Boolean = person.isInstanceOf[Child]

//Так, окей, а теперь нам хочется возвращать строку, содержащую имя класса.














//так?
def displayPerson(person: Person):String = {
  if (person.isInstanceOf[Adult]) return "Adult"
  if (person.isInstanceOf[Child]) return "Child"
  return "Person"
}



//И в этот момент в голову закрадывается идея:
//Вот хочется иметь такой switch, только вместо попытки сравнения значений, проверять принадлежность к классу.
//И вот pattern-matching, в сути своей - это оно и есть - сопоставление класса значения с классом-образцом.
//перепишем displayPerson() c использованием pattern-matching
def displayPerson0(person: Person):String = {
  person match {
    case adult: Adult => "Adult"
    case child: Child => "Child"
    case _ => "Person"
  }
}


//Хорошо, теперь давайте попробуем выводить, например ещё и содержащиеся параметры.
def displayPerson1(person:Person):String = {
  person match {
    case adult: Adult => s"Adult(${adult.name},${adult.occupation})"
    case child: Child => s"Child(${child.name},${child.age})"
    case _ => s"Person(${person.name})"
  }
}

displayPerson1(new Adult("Bob", "student") )

// Вообще, уже неплохо.
// Но отметим, что действие которое мы делаем в каждом конкретном случае -
// по сути, является разборкой объекта на составляющие его атрибуты.
// Для этого нам надо сделать шаг в сторону и поговорить про объекты-компаньоны.
//-------------------------------------------------- companion-object --------------------------------------------------
//Как уже было сказано, scala умеет в singleton из коробки.
object SomeService{
  def sayHello = print("Hello!")
}

SomeService.sayHello

//А вот объект-компаньон - это статическая часть класса. Такой аналог статических членов класса из.
//Оформляется это так: пишется object с именем как у класса.
//Для объекта-компаньона доступны все члены класса, кроме private[this]. Верно и обратное.

object Person{
  //Для нас здесь будет важен один конкретный метод - unapply.
  //Это такой метод, который "разбирает" объект и возвращает кортеж, завёрнутый в Option.
  def unapply(arg: Person): Option[String] = Some(arg.name)
}

object Child{
  def unapply(arg: Child): Option[(String, Int)] = Some((arg.name, arg.age))
}

object Adult{
  def unapply(arg: Adult): Option[(String, String)] = Option((arg.name,arg.occupation))
}
//Так, для чуть большей очевидности происходящего используем этот метод просто так.
val adult = new Adult(name = "Eden", occupation = "worker")
val child = new Child(name = "Sarah", age = 17)
//get в данном случае просто вытаскивает кортеж из обёртки Option
Adult.unapply(adult).get
//а ещё scala умеет в соответствующее присваивание
val (name, occupation) = Adult.unapply(adult).get
// И вот теперь мы можем сможем использовать pattern-matching вполне.
def displayPerson2(person:Person):String = {
  person match {
    case Adult(name, occupation) => s"Adult($name,$occupation)"
    case Child(name, age) => s"Child($name,$age)"
    case Person(name) => s"Person($name)"
  }
}
displayPerson2(adult)
displayPerson2(child)
//ну, не круто ли?
//--------------------------------------- class и case-class ~> pattern-matching ---------------------------------------
//case-class - полный аналог data-class из kotlin. Он автоматически генерирует:
// toString - перевод в строчку для вывода в консоль.
// apply - фабричный метод, "сборка" объекта
// unapply - "разборка" объекта
// ну и да, по-умолчания все поля case-class - "public" val
// а ещё от case-class нельзя наследоваться
case class OtherPerson(name:String, surname:String, age:Int)
val otherPerson = OtherPerson("Alice", "Livsi", 31)
otherPerson match {
  case OtherPerson(name, surname, age) => s"Name is $name $surname, and I am $age y.o."
}

//меняет местами смежные элементы
def swapNeighbours(seq:Seq[Int]):Seq[Int] = {
  val out = for(i<-seq.indices if i%2==0 ) yield {
    if(i+1<seq.length-1) Seq(seq(i+1),seq(i))
    else Seq(seq(i))
  }
  out.flatten
}
def swapNeighbours0(seq:Seq[Int]):Seq[Int] = {
  val left = seq.indices.filter(_%2==0)
  val right = seq.indices.filter(_%2==1)
  val out = right.zip(left)
    .flatMap(pair => Seq(pair._1, pair._2))
    .map(i=>seq(i))

  if(seq.length%2 == 1) out.appended(seq.last) else out
}
def swapNeighbours1(seq:Seq[Int]):Seq[Int] = {
  seq.grouped(2).flatMap{
    case Seq(l,r) => Seq(r,l)
    case Seq(l) => Seq(l)
  }.toSeq
}
val seq = Seq.empty
seq.filter(_==1)
seq.filter(x=>x==1)
swapNeighbours0(Seq(1,2,3,4,5))
def positiveFirst(seq:Seq[Int]) = {
  val vals = seq.groupBy(_>0)
  vals(true) ++ vals(false)
}
// Seq[Int]=>Seq[Int] в выходной последовательности сначала > 0, затем <= 0









//------------------------------------------------------- lambda -------------------------------------------------------
//------------------------------------------------------ lambda --------------------------------------------------------

//Что такое функция?
//Что такое лямбда?

//функция
def f0(n:Int):Int = n+2
//lambda, одно и то-же разными словами
val f1 = (n:Int)=> n+2
val f3:Int=>Int = n => n+2
val f2:(Int,Int)=>Int = _ + _
val intSeq = Seq(1,3,7,9,4,8,2)
intSeq
  .filter(_ % 2==1)
  .map(_ * 2)
//lambda - функция без имени, в смысле, что лямбда может быть использована как значение.


/**
 * Написать функцию, переставляющую смежные элементы Seq
 */
//

Random.nextInt()
val xs = (1 to 5)
val intSeq3 = Seq(1,2,3,4,5,6,7,8)
Seq(2,1,4,3,6,5,8,7)

def swapPairs(seq:Seq[Int]):Seq[Int] = {
  seq
    .grouped(2)
    .flatMap {
      _.reverse
    }
    .toSeq
}

swapPairs(intSeq3)
Seq(
  Seq(1,2),
  Seq(3,4)
).flatten

//String=>String:

def reverseWords(string: String):String =
  string
    .split(" ")
    .reverse
    .filter(_ != "")
    .map(_ + " ")
    .reduce(_ ++ _)
    .init


def reverseWords0(string: String):String = string.replaceAll(" +"," ")
val str = "bad  as     heck   "
reverseWords(str)




















//------------------------------------------------ Контейнеры и обёртки  -----------------------------------------------
// Что такое коллекция?
// Что такое обёртка?
// Что такое контейнер?


val intSeq:Seq[Int] = Seq(1,2,3,5,-4,-4,-8,4,1,-9,21,-11,6,8)

val opt0:Option[Int] = Some(10)
opt0.map(_*2).map(_+1)
val opt1:Option[Int] = None

val either0:Either[Int,String] = Left(10)
val either1:Either[Int,String] = Right("kjhgdfjg")

val try0:Try[Int] = Try{
  10
}

val try1:Try[Int] = Try{
  throw new Exception("Something went wrong exception")
}

val optSeq = Seq[Option[Int]](
  Some(10),
  None,
  Some(4),
  Some(7),
  Some(-1),
  None,
  None
)
optSeq.flatten

val person = Person1("John","Snow",4,14)

val sum = person match {
  case Person1(name,surname,_,n) => s"$name $surname, $n y.o."
}
optSeq.collect{
  case Some(value) => value*2
}

Seq(1,2,3) ++ Seq(4,5,6)

def reOrder(seq:Seq[Int]):Seq[Int] = seq.groupBy(_<=0).values.flatten.toSeq

def distinct(seq: Seq[Int]):Seq[Int] = {
  val seq0 = seq.sorted
  var last = seq.head
  seq0.flatMap { x => {
    if (x == last) None else {
      last = x; Some(x)}}}
}



reOrder(Seq(1,-4,-6,7,8,10,-5))

/**
 * Seq[Int]=>Seq[Int]
 * сначала положительные, потом неположительные.
 * порядок положительных и неположительных - должен сохраниться
 */