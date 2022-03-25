def binarySearch(item: Int, orderedSequence: Array[Int]): Option[Int] = {
  var start = 0
  var end = orderedSequence.length - 1
  while (start < end) {
    val mid = (start + end) / 2
    if (orderedSequence(mid) == item) return Some(mid)
    else if (orderedSequence(mid) < item) start = mid + 1
    else end = mid - 1
  }
  if (orderedSequence(start) == item) Some(start) // case when start == end
  else None
}

def genericBinarySearch[T: Ordering](item: T, orderedSequence: Array[T]): Option[Int] = {
  var start = 0
  var end = orderedSequence.length - 1
  while (start < end) {
    val mid = (start + end) / 2
    if (Ordering[T].equiv(orderedSequence(mid), item)) return Some(mid)
    else if (Ordering[T].lt(orderedSequence(mid), item)) start = mid + 1
    else end = mid - 1
  }
  if (orderedSequence(start) == item) Some(start) // case when start == end
  else None
}

binarySearch(8, Array(1, 2, 3, 5, 8, 13, 21))
binarySearch(3, Array(1, 2, 3, 4, 5, 6, 7, 8, 9))
binarySearch(4, Array(1, 3, 5, 7))
binarySearch(788, Array(1, 3, 5, 7, 12, 23, 34, 45, 66, 80, 110, 150, 300, 315, 444, 500, 555, 600, 800, 999, 1000))
binarySearch(66, Array(1, 3, 5, 7, 12, 23, 34, 45, 66, 80, 110, 150, 300, 315, 444, 500, 555, 600, 800, 999, 1000))

genericBinarySearch("home", Array("amorphic", "cold", "hell", "heaven", "snake"))
genericBinarySearch("home", Array("amorphic", "cold", "hell", "heaven", "home", "snake"))