package xyz.urac.libp2p.peer

class PeerInfoTests extends GroovyTestCase {
  void testCreate() {
    def peerInfo = PeerInfo.create()
    assert peerInfo != null
    println peerInfo
  }
}
