package xyz.urac.libp2p.mafmt

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:17:36
 */
class Transport {
  static final IP = new Pattern('ip4') | new Pattern('ip6') // ~'ip4|ip6'
  static final TCP = IP & new Pattern('tcp') // ~'(ip4|ip6)/tcp'
  static final UDP = IP & new Pattern('udp') // ~'(ip4|ip6)/udp'
  static final UTP = UDP & new Pattern('utp') // ~'(ip4|ip6)/udp/utp'
  static final Unreliable = UDP // ~'(ip4|ip6)/udp'
  static final Reliable = TCP | UTP // ~'((ip4|ip6)/tcp)|((ip4|ip6)/udp/utp)'
  static final IPFS = Reliable & new Pattern('ipfs') // ~'(((ip4|ip6)/tcp)|((ip4|ip6)/udp/utp))/ipfs'

  static Map<String, Pattern> supports() {
    [
      tcp: TCP,
      udp: UDP,
      utp: UTP,
      ipfs: IPFS
    ]
  }
}
