package xyz.urac.libp2p.mafmt

import org.ipfs.api.MultiAddress

/**
 * multiaddr format patterns
 * 
 * @author huanbing ￠幻冰
 * @date 2016年11月22日 下午5:23:23
 */
class Pattern {
  
  
  def matches(addr) {
    if (!addr instanceof MultiAddress) {
      addr = new MultiAddress(addr)
    }
    partialMatch addr
  }

  def partialMatch(protos) {
    
  }
}
