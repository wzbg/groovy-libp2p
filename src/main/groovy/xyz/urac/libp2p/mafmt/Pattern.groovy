package xyz.urac.libp2p.mafmt

import groovy.transform.AutoClone
import groovy.transform.Canonical

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
@Canonical
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
    def base = bases.pop()
    while (ops) {
      switch (ops.pop()) {
        case or:
          if (base.partialMatch(protos)) return true
        case and:
          if (!base.partialMatch(protos)) return false
        case leftParentheses:
        case rightParentheses:
        default:
          throw new IllegalStateException('unrecognized pattern op!')
      }
      base = bases.pop()
    }
    base.partialMatch protos
  }
}
