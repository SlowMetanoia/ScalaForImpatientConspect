object symbolConstructions {
  val fullSize = 120

  def constructTheme(str: String) =
    if (str.length > fullSize - 4) throw new IllegalArgumentException("given string is too big")
    else s"//${"-" * ((fullSize - str.length - 4) / 2)} $str ${"-" * ((fullSize - str.length - 4) / 2)}"
}