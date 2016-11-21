package xyz.urac.libp2p.peer

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 上午11:51:32
 */
class Peer {
  PeerInfo pInfo
  PeerBook pBook
  boolean isOnline

  Peer(PeerInfo pInfo, PeerBook pBook = new PeerBook()) {
    assert id != null : 'missing peer info'
    this.pInfo = pInfo
    this.pBook = pBook
  }

  def start() {}

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
