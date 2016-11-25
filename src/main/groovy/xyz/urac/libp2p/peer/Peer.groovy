package xyz.urac.libp2p.peer

import xyz.urac.libp2p.mafmt.Transport
import xyz.urac.libp2p.swarm.Swarm

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 上午11:51:32
 */
class Peer {
  PeerInfo pInfo
  PeerBook pBook
  Swarm swarm
  boolean online

  Peer(PeerInfo pInfo, PeerBook pBook = new PeerBook()) {
    assert pInfo != null : 'missing peer info'
    this.pInfo = pInfo
    this.pBook = pBook
    // Swarm
    swarm = new Swarm(pInfo)
    //    swarm.connection.addStreamMuxer(spdy)
    //    swarm.connection.reuse()
    //    swarm.connection.crypto(secio.tag, secio.encrypt)
    //    swarm.on('peer-mux-established', pInfo -> {
    //      pBook.put peerInfo
    //    })
    //    swarm.on('peer-mux-closed', pInfo -> {
    //      pBook.removeByB58String pInfo.id.toB58String()
    //    })
  }

  void start() {
    def multiAddrs = pInfo.multiAddrs
    assert !multiAddrs.empty : 'empty addrs'
    Transport.supports().each { name, pattern ->
      def addrs = pattern.filter multiAddrs
      if (addrs) {
        swarm.transports.put name, addrs
      }
    }
    swarm.listen()
    online = true
  }

  def stop() {}

  def dialById(id, protocol) {}

  def dialByMultiaddr(maddr, protocol) {}

  def dialByPeerInfo(pInfo, protocol) {}

  def hangUpById(id) {}

  def hangUpByMultiaddr(maddr) {}

  def hangUpByPeerInfo(pInfo) {}

  def handle(protocol, handlerFunc, matchFunc) {}

  def unhandle(protocol) {}
}
