/**
 * Параметризованные типы
 * Как многие другие главы, эту надо просто читать.
 */
//T1 и Т2 - параметризуемые типы
class Pair[T1,T2](p1:T1,p2:T2)
//ограничение на тип: должен совпадать.
class GPair[T](p1:T,p2:T)
//Установка верхней границы: T должен быть подтипом Comparable[T]
class GCPair[T <: Comparable[T]](p1:T,p2:T){
  //R - дожен быть супертипом T.
  //def replaceFirst[R >: T](other:R) = new GCPair(other,p2)
  //Это избыточно, но как мне кажется, более очевидно.
  def replaceFirst[R >: T <: Comparable[R]](other:R) = new GCPair[R](other,p2)
}