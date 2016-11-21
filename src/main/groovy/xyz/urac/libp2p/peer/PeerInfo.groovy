package xyz.urac.libp2p.peer

import groovy.transform.Canonical

import org.ipfs.api.MultiAddress

/**
 * Peer represents a peer on the IPFS network
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 上午10:00:00
 */
@Canonical
class PeerInfo {
  PeerId id
  List<MultiAddress> multiAddrs = []

  PeerInfo(PeerId peerId) {
    assert peerId != null : 'Missing peerId. Use PeerId.create([bits]) to create one'
    id = peerId
  }

  def addMultiAddr(addr) {
    if (!addr instanceof MultiAddress) {
      addr = new MultiAddress(addr)
    }
    if (multiAddrs.contains(addr)) {
      return true
    }
    multiAddrs.add addr
  }

  def removeMultiAddr(addr) {
    if (!addr instanceof MultiAddress) {
      addr = new MultiAddress(addr)
    }
    multiAddrs.remove addr
  }

  void replaceMultiAddrs(existing, fresh) {
    if (existing instanceof MultiAddress) {
      existing = [existing]
    }
    if (fresh instanceof MultiAddress) {
      fresh = [fresh]
    }
    existing.forEach { removeMultiAddr it }
    fresh.forEach { removeMultiAddr it }
  }

  /**
   * generation
   */
  static create(PeerId id) {
    if (!id) {
      id = PeerId.create()
    }
    new PeerInfo(id)
  }
}
