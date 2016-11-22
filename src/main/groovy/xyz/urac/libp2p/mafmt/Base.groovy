package xyz.urac.libp2p.mafmt

import groovy.transform.Canonical

import org.ipfs.api.MultiAddress
import org.ipfs.api.Protocol

/**
 * multiaddr format base
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月22日 下午4:22:44
 */
@Canonical
class Base {
  String name

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
    if (protos && protos[0] == name) {
      protos.drop 1
    }
  }
}
