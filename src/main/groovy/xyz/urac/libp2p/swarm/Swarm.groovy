package xyz.urac.libp2p.swarm

import xyz.urac.libp2p.peer.PeerInfo

class Swarm {
  PeerInfo pInfo

  Swarm(PeerInfo pInfo) {
    assert id != null : 'missing peer info'
    this.pInfo = pInfo
  }

  def dial() {}

  def handle(protocol, handlerFunc, matchFunc) {}

  def unhandle(protocol) {}

  def hangUp(pInfo) {}

  def close() {}
}
