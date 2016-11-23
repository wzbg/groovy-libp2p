package xyz.urac.libp2p.util

enum Operator {
  plus('+'), // a + b
  minus('-'), // a – b
  multiply('*'), // a * b
  power('**'), // a ** b
  div('/'), // a / b
  mod('%'), // a % b
  or('|'), // a | b
  and('&'), // a & b
  xor('^'), // a ^ b
  next('++'), // a++ or ++a
  previous('--'), // a–- or -–a
  getAt('[]'), // a[b]
  putAt('[]='), // a[b] = c
  leftShift('<<'), // a << b
  rightShift('>>'), // a >> b
  isCase('case'), // switch(a) { case(b) : }
  bitwiseNegate('~'), // ~a
  negative('-?'), // -a
  positive('+?'), // +a
  // a == b : a.equals(b) or a.compareTo(b) == 0
  // a != b : !a.equals(b)
  equals('='),
  // a <=> b : a.compareTo(b)
  // a > b : a.compareTo(b) > 0
  // a >= b : a.compareTo(b) >= 0
  // a < b : a.compareTo(b) < 0
  // a <= b : a.compareTo(b) <= 0
  compareTo('<=>'),
  ////////////////////
  leftBraces('{'), // {
  rightBraces('}'), // }
  leftBrackets('['), // [
  rightBrackets(']'), // ]
  leftParentheses('('), // (
  rightParentheses(')'), // )
  dollar('$'), // $
  hash('#'), // #
  at('@') // @

  final sign

  Operator(sign) {
    this.sign = sign
  }
  
  @Override
  public String toString() {
    sign
  }
}
