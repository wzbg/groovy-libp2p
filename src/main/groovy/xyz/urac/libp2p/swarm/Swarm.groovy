package xyz.urac.libp2p.swarm

import xyz.urac.libp2p.peer.PeerInfo

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月21日 下午2:46:15
 */
class Swarm {
  PeerInfo pInfo
  Map transports = [:] // { key: transport }; e.g { tcp: <tcp> }
  Map conns = [:] // { peerIdB58: { conn: <conn> } }
  /**
   * {
   *   peerIdB58: {
   *     muxer: <muxer>,
   *     conn: <transport socket> // to extract info required for the Identify Protocol
   *   }
   * }
   */
  Map muxedConns = [:]
  Map muxers = [:] // { muxerCodec: <muxer> } e.g { '/spdy/0.3.1': spdy }
  Map crypto = [:] // Crypto details
  Map protocols = [:] // { protocol: handler }
  boolean identify // is the Identify protocol enabled?

  Swarm(PeerInfo pInfo) {
    assert pInfo != null : 'missing peer info'
    this.pInfo = pInfo
  }

  def dial() {}

  def listen() {
    transports.each { name, addrs ->
    }
  }

  def handle(protocol, handlerFunc, matchFunc) {}

  def unhandle(protocol) {}

  def hangUp(pInfo) {}

  def close() {}
}
