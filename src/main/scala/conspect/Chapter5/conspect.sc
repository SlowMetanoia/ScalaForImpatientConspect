import java.beans.BeanProperty

//------------------------------------------------class-----------------------------------------------------------------
class Counter{                      //создание класса
  private var value = 0             //поля ДОЛЖНЫ быть инициализиолванн.
  def increment() = value+=1  //по умолчанию элементы - public
  def current() = value
  def current0 = value         //так можно обязать пользователей класса обращаться без скобок
}

var counter = new Counter           //создание объекта класса
counter.current()                   //вызовы методов
counter.increment()
counter.current                     //если передавать переменные не надо, то () можно не писать
//хорошим стилем считается использование () при вызове мутатора и без () при вызове аксессора
counter current                     //... и без точки...
class person {
  private var age = 0               //поля лучше делать private с getter и setter
  def GetAge = age
  def SetAge(x:Int) = age = x //как-то так было бы в java
}

class MyInt{                        //
  var value = 0                     //Для чтения и записи были созданы методы value и value_ соответственно(права доступа как у value)
}
var t0 = new MyInt()
println(t0.value)                   //будет вызван метод t0.value
t0.value = 1                        //будет вызван метод t0.value_
                                    //переопределить эти методы можно при желании:
class MyInt1{
  private[this] var value = 0       //тогда доступ будет будет ТОЛЬКО из методов этого-же объекта
  //private[className]              //тогда доступ будет только из класс className
  //def value = value               //видимо, тут так нельзя, но вообще должно работать
  //def value_(newVal:Int) = value = newVal          // тоже
  }
//------------------------------------------------конструкторы----------------------------------------------------------
class tClass0{                //если необъявлен главный конструктор, класс получает пустой конструктор по-умолчанию
  var name:String = new String
  var value:Int = new Int
  def this(name:String) {     //дополнительные конструкторы - это методы объявленные как this
    this()                    //каждый конструктор в начале должен вызывать другой конструктор объявленный выше, либо главный констркктор
    this.name = name
  }
  def this(name:String,value:Int) {
    this(name)
    this.value = value
    }
}                             //3 конструктора: 1 главный, 2 дополнительных

class tClass1(name:String,age:Int){                       //поля name и age созданы, к ним созданы публичные методы доступа.
  println(f"$name is $age years old has been generated")  //всё, что описано в теле класса выполнится при вызове главного конструктруктора
  //val age = 0 - кинет ошибку, такое поле уже есть.
}
class tClass2(private val name:String, var age:Int, counter:Int) {
  //иодфикаторы доступа можно указывать в главном конструкторе при его вызове будет создан класс со следующими поляи и модфикаторами:
  //private       name
  //public        age
  //private[this] counter
  //если объявлен в конструкторе без val/var и не используется в методах, он не буедт преобразован в поле
}
class tClass3 private() { //главный конструктор - приватный
  @BeanProperty var name:String = new String // это модфикатор, создающий геттеры и сетторы для чтобы можно было использовать JavaBeans
}
class tClass4 {
  class innerClass{ //вот так можно!
    innerClass.this //это обращение к внешнему классу!
  }
}