package xyz.urac.libp2p.enums

import xyz.urac.libp2p.mafmt.Pattern

enum Transport {
  IP (new Pattern('ip4') | new Pattern('ip6')), // ~'ip4|ip6'
  TCP (IP.pattern & new Pattern('tcp')), // ~'(ip4|ip6)/tcp'
  UDP (IP.pattern & new Pattern('udp')), // ~'(ip4|ip6)/udp'
  UTP (UDP.pattern & new Pattern('utp')), // ~'(ip4|ip6)/udp/utp'
  Unreliable (UDP.pattern), // ~'(ip4|ip6)/udp'
  Reliable (TCP.pattern | UTP.pattern), // ~'((ip4|ip6)/tcp)|((ip4|ip6)/udp/utp)'
  IPFS (Reliable.pattern & new Pattern('ipfs')) // ~'(((ip4|ip6)/tcp)|((ip4|ip6)/udp/utp))/ipfs'

  final pattern

  Transport(pattern) {
    this.pattern = pattern
  }
}
