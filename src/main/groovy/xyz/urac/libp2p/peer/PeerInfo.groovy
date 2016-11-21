package xyz.urac.libp2p.peer

import groovy.transform.Canonical

/**
 * Peer represents a peer on the IPFS network
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 上午10:00:00
 */
@Canonical
class PeerInfo {
  PeerId id

  PeerInfo(peerId) {
    assert peerId != null : 'Missing peerId. Use PeerId.create([bits]) to create one'
    id = peerId
  }
  
  /**
   * generation
   */
  static create(id) {
    if (!id) {
      id = PeerId.create()
    }
    new PeerInfo(id)
  }
}
