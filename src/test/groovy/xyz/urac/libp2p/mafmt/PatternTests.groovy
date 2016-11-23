package xyz.urac.libp2p.mafmt

class PatternTests extends GroovyTestCase {
  void testOps() {
    final IP = new Pattern('ip4') | new Pattern('ip6')
    final TCP = IP & new Pattern('tcp')
    final UDP = IP & new Pattern('udp')
    final UTP = UDP & new Pattern('utp')
    final Unreliable = UDP
    final Reliable = TCP | UTP
    final IPFS = Reliable & new Pattern('ipfs')
    println "IP: $IP"
    println "TCP: $TCP"
    println "UDP: $UDP"
    println "UTP: $UTP"
    println "Unreliable: $Unreliable"
    println "Reliable: $Reliable"
    println "IPFS: $IPFS"
  }
}
