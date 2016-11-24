package xyz.urac.libp2p.mafmt

import groovy.transform.AutoClone
import xyz.urac.libp2p.enums.Operator;

import org.ipfs.api.MultiAddress
import org.ipfs.api.Protocol


/**
 * multiaddr format patterns
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月22日 下午5:23:23
 */
@AutoClone
class Pattern {
  String regex
  List<Base> bases

  Pattern(String proto) {
    assert proto != null : 'invalid proto provided'
    bases = [new Base(regex = proto)]
  }

  private op(Pattern other, Operator op) {
    def result = this.clone()
    result.with {
      def regex1 = regex
      def regex2 = other.regex
      if (bases.size() > 1) {
        regex1 = "($regex1)"
      }
      if (other.bases.size() > 1 && op == Operator.or) {
        regex2 = "($regex2)"
      }
      regex = "$regex1$op$regex2"
      bases += other.bases
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
    def names = Protocol.byName.keySet()
    def protos = parts.findAll { names.contains it }
    protos
  }

  def matches(addr) {
    def protos = protos addr
    if (!protos) return false
    protos.join(Operator.and.sign) ==~ ~regex
  }

  def filter(addrs) {
    addrs?.findAll { matches it }
  }

  @Override
  public String toString() {
    regex
  }
}
