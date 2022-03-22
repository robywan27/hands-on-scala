// TODO: handle operation against unit (eg 0 * x)

sealed trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Var(s: String) extends Expr
case class Val(v: Int) extends Expr

val exp1 = BinOp(Val(1), "+", Val(1)) // (1 + 1)  ->  2
val exp2 = BinOp(exp1, "*", Var("x")) // ((1 + 1) * x)  ->  (2 * x)
val exp3 = BinOp(
  BinOp(Val(2), "-", Val(1)),
  "*",
  Var("x")
) // ((2 - 1) * x)  ->  (1 * x)   ->  x
val exp4 = BinOp(
  BinOp(
    BinOp(Val(1), "+", Val(1)),
    "*",
    Var("y")
  ),
  "+",
  BinOp(
    BinOp(Val(1), "-", Val(1)),
    "*",
    Var("x")
  )
)   // (((1 + 1) * y) + ((1 - 1) * x))  ->
    // ((2 * y) + (0 * x))  ->
    // (2 * y)

val exp5 = BinOp(
  Val(2),
  "+",
  BinOp(Var("x"), "-", Val(1))
)   // ((2 + (x - 1))


def simplify(exp: Expr): Expr =
  exp match {
    // cases to handle unit wrt operation
//    case BinOp(Var(x), "+", Val(0)) => Var(x)
//    case BinOp(Val(0), "+", Var(y)) => Var(y)
//    case BinOp(Var(x), "-", Val(0)) => Var(x)
//    case BinOp(Var(x), "*", Val(1)) => Var(x)
//    case BinOp(Val(1), "*", Var(y)) => Var(y)
    // cases to handle operations among values
    case BinOp(Val(x), "+", Val(y)) => Val(x + y)
    case BinOp(Val(x), "-", Val(y)) => Val(x - y)
    case BinOp(Val(x), "*", Val(y)) => Val(x * y)
    // cases to handle operations between a value and a variable
    case BinOp(Val(x), op, Var(y)) => BinOp(Val(x), op, Var(y))
    case BinOp(Var(x), op, Val(y)) => BinOp(Var(x), op, Val(y))
    // cases to handle operations between two variables
    case BinOp(Var(x), op, Var(y)) => BinOp(Var(x), op, Var(y))
    case BinOp(Var(x), op, Var(y)) => BinOp(Var(x), op, Var(y))
    // cases to handle operations between an expr and a value
    case BinOp(x, op, Val(y)) => BinOp(simplify(x), op, Val(y))
    case BinOp(Val(x), op, y) => BinOp(Val(x), op, simplify(y))
    // cases to handle operations between an expr and a variable
    case BinOp(x, op, Var(y)) => BinOp(simplify(x), op, Var(y))
    case BinOp(Var(x), op, y) => BinOp(Var(x), op, simplify(y))
    // cases to handle operations between two expressions
    case BinOp(x, op, y) => BinOp(simplify(x), op, simplify(y))
  }

simplify(exp1)
simplify(exp2)
simplify(exp3)
simplify(exp4)
simplify(exp5)