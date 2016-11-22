package xyz.urac.libp2p.mafmt

class PatternTests extends GroovyTestCase {
  void testOps() {
    final IP = new Pattern('ip4') | new Pattern('ip6')
    println "IP: $IP"
    final TCP = IP & new Pattern('tcp')
    println "TCP: $TCP"
    final UDP = IP & new Pattern('udp')
    println "UDP: $UDP"
    final UTP = UDP & new Pattern('utp')
    println "UTP: $UTP"
    final Unreliable = UDP
    println "Unreliable: $Unreliable"
    final Reliable = TCP | UTP
    println "Reliable: $Reliable"
    final IPFS = Reliable & new Pattern('ipfs')
    println "IPFS: $IPFS"
  }
}
