# Arrays
* `Array.newBuilder[T]`
  * lets you dynamically construct immutable arrays of unknown length
  * to get the array, call `result()` method
* Factory methods
  * `Array.fill(n)(=> e)`
  * `Array.tabulate(n)(f)`
* Queries
  * `Array(...).find(f)`
  * `Array(...).exists(f)`
* Stringify
  * `Array(...).mkString(sep)`
  * `Array(...).mkString(start, sep, end)`
* Converters
  * conversions among different types of collections
  * e.g. `Array(...).to(Set)` returns a Set (removing duplicates)
* Views
  * when you have long chains of collection transformation as bottleneck
  * `array.view.map(...)....to(Array)`
  * use `view` before transformations to defer traversal and creation of a new collection
  * use `to` after transformations to convert to a concrete collection type

# Immutable Collections
* when performance is critical, should consider using Mutable collections
## Immutable Vectors
* fixed-size linear sequences
* provide O(log n) performance for most operations (copy and update)
* thanks to tree-structure implementation
## Immutable Sets
* unordered collections without duplicates
* provide O(log n) for `contains` method and most operations
* underlying implementation is tree
## Immutable Maps
* unordered collections of keys and values
* provide efficient lookup by key, O(log n) for most operations
* underlying implementation is tree
## Immutable Lists
* provide O(1) for `head` and `tail` methods and `::` operator
* slow O(n) for indexed lookup and appending/removing elements on the right-hand side. Can use Vectors in this case
* implemented as singly-linked list data structure
* use case: collections that have identical elements on one side as right-hand side can be shared (eg file paths)

# Mutable Collections
## Mutable ArrayDeques
* linear collections
* provide O(1) for indexed lookups, updates and insertions and removals at both ends
* implemented as a circular buffer with pointers to start and end
  * tries to re-use underlying Array as much as possible
  * only re-allocated when capacity isn't sufficient
* use cases
  * an array with mutable size (and more flexible than Array.newBuilder)
  * faster alternative to Vectors
  * FIFO queue (`append`, `removeHead`)
  * FILO stack (`append`, `removeLast`)
## Mutable Sets
* use `add` and `remove` methods instead of `+` and `-` operations
## Mutable Maps
* use `add` and `remove` methods instead of `+` and `-` operations
* `getOrElseUpdate` method allows to use mutable map as cache
* provide O(1) for lookups and updates
* implemented as hash table
## In-Place Operations
* all mutable collections including Array have corresponding in-place operations
* perform operations without creating a transformed copy
