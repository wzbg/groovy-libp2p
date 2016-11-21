package xyz.urac.libp2p.peer

import org.ipfs.api.MultiAddress

class PeerInfoTests extends GroovyTestCase {
  void test() {
    def peerInfo = PeerInfo.create()
    assert peerInfo != null
    println peerInfo
    MultiAddress addr = new MultiAddress('')
    def result = peerInfo.addMultiAddr addr
    assert result
    result = peerInfo.removeMultiAddr addr
    assert result
    peerInfo.replaceMultiAddrs(addr, addr)
    peerInfo.replaceMultiAddrs([addr], addr)
    peerInfo.replaceMultiAddrs(addr, [addr])
    peerInfo.replaceMultiAddrs([addr], [addr])
  }
}
