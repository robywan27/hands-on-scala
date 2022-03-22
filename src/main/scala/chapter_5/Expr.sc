sealed trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Var(s: String) extends Expr
case class Val(v: Int) extends Expr


val exp1: Expr = BinOp(Val(1), "+", Val(1))  // (1 + 1)
val exp2 = BinOp(exp1, "*", Var("x"))  // ((1 + 1) * x)
val exp3 = BinOp(
  BinOp(Var("y"), "+", Val(2)),
  "*",
  Val(2)
) // ((y + 2) * 2)


def parseExpr(exp: Expr): String =
  exp match {
    case BinOp(l, o, r) => s"(${parseExpr(l)} $o ${parseExpr(r)})"
    case Var(s) => s
    case Val(v) => v.toString
  }

parseExpr(exp1)
parseExpr(exp2)
parseExpr(exp3)



def evaluate(exp: Expr, varValues: Map[String, Int]): Int =
  exp match {
    case BinOp(l, "+", r) => evaluate(l, varValues) + evaluate(r, varValues)
    case BinOp(l, "-", r) => evaluate(l, varValues) - evaluate(r, varValues)
    case BinOp(l, "*", r) => evaluate(l, varValues) * evaluate(r, varValues)
    case Var(s) => varValues(s)
    case Val(v) => v
  }

var varValues = Map("x" -> 1, "y" -> 3)
evaluate(exp1, varValues)
evaluate(exp2, varValues)
evaluate(exp3, varValues)
varValues += ("z" -> 5)

