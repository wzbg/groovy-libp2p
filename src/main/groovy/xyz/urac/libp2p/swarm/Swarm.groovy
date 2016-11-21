package xyz.urac.libp2p.swarm

import xyz.urac.libp2p.peer.PeerInfo

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 下午2:46:15
 */
class Swarm {
  PeerInfo pInfo

  Swarm(PeerInfo pInfo) {
    assert pInfo != null : 'missing peer info'
    this.pInfo = pInfo
  }

  def dial() {}

  def listen() {}

  def handle(protocol, handlerFunc, matchFunc) {}

  def unhandle(protocol) {}

  def hangUp(pInfo) {}

  def close() {}
}
