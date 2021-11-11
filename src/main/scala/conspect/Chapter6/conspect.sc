/**
 * Глава 6:
 */

//----------------------------------------------------объекты-----------------------------------------------------------
object Single{ //объявление объекта сингл-тона. Обладает всеми свойствами класса
}
class Account{                //некий класс
  var id = Account.generateId //обращение к функции объекта-компаньона
  def apply() = {         // будет вызван если к объекту класса дописать ()
    id
  }
}
object Account{                 // объект компаньон
  var ids = 0                   // его поля доступны из класса, а поля класса доступны из него
  def generateId = {       // верно и для методов. Их можно воспринимать как статические методы класса и его статические поля.
    ids += 1
    ids
  }
  def apply() = { //будет вызван если написать Account()
    ()
  }
}
var acc0 = new Account
var acc1 = new Account
acc0.id
acc1.id
acc0()      //вызываем apply из acc0
Account()   //вызываем apply из Account

class Application extends App{ //тело главного конструктора такого класса будет восприниматься как main
  print("Hello ")
  args.foreach(str=>print(str+" ")) //из args доступны аргументы командной строки
}
object MyColor extends Enumeration { //это enum
  type Colors = Value               //Что такое Value на самом деле - понять не удалось.
  val Red,Green,Blue = Value        //можно обойтись без строчки выше, но теперь при использовании import  иожно писать так:
}
//(тут можно опустить MyColor, но я не могу сделать это в worksheet)
def ColorDependAct(color: MyColor.Colors) = {
    if(color == MyColor.Red)
      "stop"
    else
      "continue"
  }
//числовое значение вызвается через .id строковое - через .toString
MyColor.values//все элементы
