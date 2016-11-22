package xyz.urac.libp2p.mafmt

import groovy.transform.AutoClone
import groovy.transform.Canonical

import org.ipfs.api.MultiAddress

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
  def bases
  def ops = []

  Pattern(String proto) {
    assert proto != null : 'invalid proto provided'
    bases = [new Base(proto)]
  }

  def op(Pattern other, Operator op) {
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

  def or(other) {
    op other, Operator.or
  }

  def and(other) {
    op other, Operator.and
  }

  def matches(addr) {
    if (!addr instanceof MultiAddress) {
      addr = new MultiAddress(addr)
    }
  }

  def partialMatch(protos) {
  }
}
