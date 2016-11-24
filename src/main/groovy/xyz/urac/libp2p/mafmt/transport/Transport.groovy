package xyz.urac.libp2p.mafmt.transport

import org.ipfs.api.MultiAddress

import xyz.urac.libp2p.mafmt.Pattern

interface Transport {
  static IP = new Pattern('ip4') | new Pattern('ip6') // ~'ip4|ip6'
  static TCP = IP & new Pattern('tcp') // ~'(ip4|ip6)/tcp'
  static UDP = IP & new Pattern('udp') // ~'(ip4|ip6)/udp'
  static UTP = UDP & new Pattern('utp') // ~'(ip4|ip6)/udp/utp'
  static Unreliable = UDP // ~'(ip4|ip6)/udp'
  static Reliable = TCP | UTP // ~'((ip4|ip6)/tcp)|((ip4|ip6)/udp/utp)'
  static IPFS = Reliable & new Pattern('ipfs') // ~'(((ip4|ip6)/tcp)|((ip4|ip6)/udp/utp))/ipfs'

  def dial(addr)

  def createListener(options, handler)

  def filter(MultiAddress... addrs)
}
