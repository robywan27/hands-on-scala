/*
Generic MergeSort to also handle other types other than Int
 */
def mergeSort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
  /*
  DIVIDE
   */
  // base case of recursion is if array has one element. In case of invalid array;
  // in case of invalid array (with no elements or with erroneous negative length) return
  if (items.length <= 1)
    items
  else {
    // int division, in case of odd number of elements result is rounded to the (N/2 - 1)th element
    // e.g. 3 / 2 = 1.5     .5 is truncated so result is 1
    val (left, right) = items.splitAt(items.length / 2)
    val (leftItems, rightItems) = (mergeSort(left), mergeSort(right))

    /*
    IMPERA
     */
    var (leftIdx, rightIdx) = (0, 0)
    val sortedItems = IndexedSeq.newBuilder[T]
    while (leftIdx < leftItems.length || rightIdx < rightItems.length) {
      val takeLeft =
        (leftIdx < leftItems.length, rightIdx < rightItems.length) match {
          case (true, false) => true
          case (false, true) => false
          case (true, true) => Ordering[T].lt(leftItems(leftIdx), rightItems(rightIdx))
        }
      if (takeLeft) {
        sortedItems += leftItems(leftIdx)
        leftIdx += 1
      } else {
        sortedItems += rightItems(rightIdx)
        rightIdx += 1
      }
    }
    sortedItems.result()
  }
}


mergeSort(Array(5, 3, 4, 2, 1))   // a Vector is returned because that's the default implementation of IndexedSeq
mergeSort(Vector(5, 3, 4, 2, 1))
mergeSort(Vector("banana", "strawberry", "apple", "peach", "orange"))
