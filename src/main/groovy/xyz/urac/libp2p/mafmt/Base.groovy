package xyz.urac.libp2p.mafmt

import org.ipfs.api.MultiAddress
import org.ipfs.api.Protocol

/**
 * multiaddr format base
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月22日 下午4:22:44
 */
class Base {
  String name

  Base(String name) {
    assert name != null : 'invalid name provided'
    this.name = name.toLowerCase()
  }

  def matches(addr) {
    if (!addr instanceof MultiAddress) {
      addr = new MultiAddress(addr)
    }
    def input = new ByteArrayInputStream(addr.raw)
    int code = Protocol.readVarint input
    def proto = Protocol.get code
    assert proto.name() == name
  }

  def partialMatch(protos) {
    protos?.each {
      if (it == name) {
        return true
      }
    }
  }

  @Override
  public String toString() {
    name
  }
}
