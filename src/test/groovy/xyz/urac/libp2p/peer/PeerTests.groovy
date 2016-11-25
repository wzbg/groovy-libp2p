package xyz.urac.libp2p.peer

import xyz.urac.libp2p.mafmt.Transport

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:39:55
 */
class PeerTests extends GroovyTestCase {
  Peer peer

  @Override
  void setUp() {
    def peerInfo = PeerInfo.create()
    assert peerInfo != null
    peer = new Peer(peerInfo)
    assert peer != null
    assert peer.pInfo != null
    assert peer.pBook != null
    assert peer.swarm != null
    assert !peer.online
  }

  void testStart() {
    peer.pInfo.addMultiAddr '/ip4/127.0.0.1/tcp/4001'
    peer.pInfo.addMultiAddr '/ip6/::1/tcp/4001'
    peer.pInfo.addMultiAddr '/ip4/127.0.0.1/udp/4002'
    peer.pInfo.addMultiAddr '/ip6/::1/udp/4002'
    peer.pInfo.addMultiAddr '/ip4/127.0.0.1/udp/4003/utp'
    peer.pInfo.addMultiAddr '/ip6/::1/udp/4003/utp'
    peer.pInfo.addMultiAddr '/ip4/127.0.0.1/tcp/4011/ipfs/QmQnQ1krfw9FjGe9fan8N5LQkJTLfLcWAtidnfXXdAx9hZ'
    peer.pInfo.addMultiAddr '/ip6/::1/tcp/4011/ipfs/QmQnQ1krfw9FjGe9fan8N5LQkJTLfLcWAtidnfXXdAx9hZ'
    peer.pInfo.addMultiAddr '/ip4/127.0.0.1/udp/4012/ipfs/QmQnQ1krfw9FjGe9fan8N5LQkJTLfLcWAtidnfXXdAx9hZ'
    peer.pInfo.addMultiAddr '/ip6/::1/udp/4012/ipfs/QmQnQ1krfw9FjGe9fan8N5LQkJTLfLcWAtidnfXXdAx9hZ'
    peer.pInfo.addMultiAddr '/ip4/127.0.0.1/udp/4013/utp/ipfs/QmQnQ1krfw9FjGe9fan8N5LQkJTLfLcWAtidnfXXdAx9hZ'
    peer.pInfo.addMultiAddr '/ip4/ip6/::1/udp/4013/utp/ipfs/QmQnQ1krfw9FjGe9fan8N5LQkJTLfLcWAtidnfXXdAx9hZ'
    peer.start()
    assert peer.swarm.transports.keySet() == Transport.supports().keySet()
    assert peer.online
  }
}
