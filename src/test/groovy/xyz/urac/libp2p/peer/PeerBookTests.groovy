package xyz.urac.libp2p.peer

import org.ipfs.api.Multihash

class PeerBookTests extends GroovyTestCase {
  void testByBase58() {
    // new
    def peerBook = new PeerBook()
    assert peerBook != null
    println peerBook
    // put
    def peerInfo = PeerInfo.create()
    def result = peerBook.put peerInfo
    assert result
    // get
    def id = peerInfo.id.toB58String()
    def pInfo = peerBook.getByB58String id
    assert peerInfo == pInfo
    // remove
    result = peerBook.removeByB58String id
    assert result
  }

  void testByMultihash() {
    // new
    def peerBook = new PeerBook()
    assert peerBook != null
    println peerBook
    // put
    def peerInfo = PeerInfo.create()
    def result = peerBook.put peerInfo
    assert result
    // TODO Test Multihash's get and remove
//    // get
//    def multihash = new Multihash()
//    def pInfo = peerBook.getByMultihash id
//    assert peerInfo == pInfo
//    // remove
//    result = peerBook.removeByMultihash id
//    assert result
  }
}
