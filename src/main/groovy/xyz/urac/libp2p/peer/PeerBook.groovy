package xyz.urac.libp2p.peer

import groovy.transform.Canonical

import org.ipfs.api.Multihash

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 上午9:58:58
 */
@Canonical
class PeerBook {
  Map<String, PeerInfo> peers = [:]

  def put(PeerInfo peerInfo, boolean replace = true) {
    def id = peerInfo.id.toB58String()
    if (peers.containsKey(id) && !replace) {
      return false
    }
    peers[id] = peerInfo
    true
  }

  def getAll() {
    peers
  }

  def getByB58String(String id) {
    peers.get id
  }

  def getByMultihash(Multihash multihash) {
    getByB58String multihash.toBase58()
  }

  def removeByB58String(String id) {
    peers.remove id
  }

  def removeByMultihash(Multihash multihash) {
    removeByB58String multihash.toBase58()
  }
}
