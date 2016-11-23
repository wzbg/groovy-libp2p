package xyz.urac.libp2p.mafmt

import groovy.transform.AutoClone

import org.ipfs.api.MultiAddress
import org.ipfs.api.Protocol

import xyz.urac.libp2p.util.Operator


/**
 * multiaddr format patterns
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月22日 下午5:23:23
 */
@AutoClone
class Pattern {
  List<Base> bases
  List<Operator> ops = []

  Pattern(String proto) {
    assert proto != null : 'invalid proto provided'
    bases = [new Base(proto)]
  }

  private op(Pattern other, Operator op) {
    def result = this.clone()
    result.with {
      bases += other.bases
      ops << op
      if (other.ops) {
        ops << Operator.leftParentheses
        ops += other.ops
        ops << Operator.rightParentheses
      }
    }
    result
  }

  /**
   * |
   * @param other
   * @return pattern
   */
  def or(other) {
    op other, Operator.or
  }

  /**
   * &
   * @param other
   * @return pattern
   */
  def and(other) {
    op other, Operator.and
  }

  private protos(addr) {
    if (!addr instanceof MultiAddress) {
      addr = new MultiAddress(addr)
    }
    def parts = addr.toString().split '/'
    def protos = []
    parts.eachWithIndex { part, i ->
      if (i % 2) {
        def proto = Protocol.get part
        protos << proto.name()
      }
    }
    protos
  }

  def matches(addr) {
    def protos = protos addr
    if (!protos) return false
    this.clone().with {
      def base = bases.pop()
      while (ops) {
        switch (ops.pop()) {
          case Operator.or:
            if (base.partialMatch(protos)) return true
          case Operator.and:
            if (!base.partialMatch(protos)) return false
          case Operator.leftParentheses:
          case Operator.rightParentheses:
            break
          default:
            throw new IllegalStateException('unrecognized pattern op!')
        }
        base = bases.pop()
      }
    }
    base.partialMatch protos
  }
  
  @Override
  public String toString() {
    String result = bases.head()
    def ops = ops.clone()
    bases.tail()?.each {
      def op1 = ops.head()
      ops = ops.tail()
      def op2 = ops ? ops.first() : null
      switch (op2) {
        case Operator.leftParentheses:
          result += op1.sign + op2.sign + it
          ops = ops.tail()
          break
        case Operator.rightParentheses:
          result += op1.sign + it + op2.sign
          ops = ops.tail()
          break
        default: result += op1.sign + it
      }
    }
    result
  }
}
