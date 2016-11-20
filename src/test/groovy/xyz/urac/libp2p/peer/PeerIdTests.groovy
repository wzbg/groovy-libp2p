package xyz.urac.libp2p.peer

import org.bouncycastle.util.encoders.Hex

class PeerIdTests extends GroovyTestCase {
  void testCreate() {
    // create
    def peerId = PeerId.create()
    assert peerId != null
    println peerId.toPrint()
    println peerId.toHexString()
    println Hex.toHexString(peerId.marshalPrivKey())
    println Hex.toHexString(peerId.marshalPubKey())
    // create by id, privKey, pubKey
    def peerId1 = new PeerId(peerId.id, peerId.privKey, peerId.pubKey)
    assert peerId1?.id == peerId.id
    assert peerId1.privKey == peerId.privKey
    assert peerId1.pubKey == peerId.pubKey
    // create by id, privKey
    def peerId2 = new PeerId(peerId.id, peerId.privKey)
    assert peerId2?.id == peerId.id
    assert peerId2.privKey == peerId.privKey
    assert peerId2.pubKey == peerId.pubKey
    // create by id, pubKey
    def peerId3 = new PeerId(peerId.id, peerId.pubKey)
    assert peerId3?.id == peerId.id
    assert peerId3.privKey == null
    assert peerId3.pubKey == peerId.pubKey
    // create by id
    def peerId4 = new PeerId(peerId.id)
    assert peerId4?.id == peerId.id
    assert peerId4.privKey == null
    assert peerId4.pubKey == null
    // create by privKey
    def peerId5 = new PeerId(peerId.privKey)
    assert peerId5?.id == peerId.id
    assert peerId5.privKey == peerId.privKey
    assert peerId5.pubKey == peerId.pubKey
    // create by pubKey
    def peerId6 = new PeerId(peerId.pubKey)
    assert peerId6?.id == peerId.id
    assert peerId6.privKey == null
    assert peerId6.pubKey == peerId.pubKey
    // create by privKey, pubKey
    def peerId7 = new PeerId(peerId.privKey, peerId.pubKey)
    assert peerId7?.id == peerId.id
    assert peerId7.privKey == peerId.privKey
    assert peerId7.pubKey == peerId.pubKey
    // create from id's bytes
    def peerId8 = PeerId.createFromBytes peerId.id
    assert peerId8?.id == peerId.id
    assert peerId8.privKey == null
    assert peerId8.pubKey == null
    // create from id's hex string
    def peerId9 = PeerId.createFromHexString peerId.toHexString()
    assert peerId9?.id == peerId.id
    assert peerId9.privKey == null
    assert peerId9.pubKey == null
    // create from id's base58 string
    def peerId10 = PeerId.createFromB58String peerId.toB58String()
    assert peerId10?.id == peerId.id
    assert peerId10.privKey == null
    assert peerId10.pubKey == null
    // create from privKey
    def peerId11 = PeerId.createFromPrivKey peerId.toB64PrivKey()
    assert peerId11?.id == peerId.id
    assert peerId11.privKey == peerId.privKey
    assert peerId11.pubKey == peerId.pubKey
    // create from pubKey
    def peerId12 = PeerId.createFromPubKey peerId.toB64PubKey()
    assert peerId12?.id == peerId.id
    assert peerId12.privKey == null
    assert peerId12.pubKey == peerId.pubKey
    // create from json
    def peerId13 = PeerId.createFromJSON peerId.toString()
    assert peerId13?.id == peerId.id
    assert peerId13.privKey == peerId.privKey
    assert peerId13.pubKey == peerId.pubKey
  }
}
