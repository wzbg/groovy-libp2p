package xyz.urac.libp2p.peer

import org.ipfs.api.MultiAddress

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:39:11
 */
class PeerInfoTests extends GroovyTestCase {
  void test() {
    PeerInfo peerInfo = PeerInfo.create()
    assert peerInfo != null
    println peerInfo
    MultiAddress addr = new MultiAddress('/ip4/127.0.0.1/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234')
    def result = peerInfo.addMultiAddr addr
    assert result
    result = peerInfo.removeMultiAddr addr
    assert result
    peerInfo.replaceMultiAddrs(addr, addr)
    peerInfo.replaceMultiAddrs([addr], addr)
    peerInfo.replaceMultiAddrs(addr, [addr])
    peerInfo.replaceMultiAddrs([addr], [addr])
  }
}
