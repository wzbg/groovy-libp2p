package xyz.urac.libp2p.mafmt

class PatternTests extends GroovyTestCase {
  static IP = Transport.IP
  static TCP = Transport.TCP
  static UDP = Transport.UDP
  static UTP = Transport.UTP
  static Unreliable = Transport.Unreliable
  static Reliable = Transport.Reliable
  static IPFS = Transport.IPFS

  def assertMatches(p, args, b = true) {
    args?.each {
      def matched = p.matches it
      assert matched == b
    }
  }

  def assertMismatches(p, args) {
    assertMatches p, args, false
  }

  void testMatchesIP() {
    assertMatches IP, GOOD_IP
    assertMismatches IP, BAD_IP + GOOD_TCP
  }

  void testMatchesTCP() {
    assertMatches TCP, GOOD_TCP
    assertMismatches TCP, BAD_TCP + GOOD_IP
  }

  void testMatchesUDP() {
    assertMatches UDP, GOOD_UDP
    assertMismatches UDP, BAD_UDP + GOOD_IP + GOOD_TCP + GOOD_UTP + GOOD_IPFS
  }

  void testMatchesUTP() {
    assertMatches UTP, GOOD_UTP
    assertMismatches UTP, BAD_UTP + GOOD_IP + GOOD_TCP + GOOD_UDP + GOOD_IPFS
  }

  void testMatchesUnreliable() {
    assertMatches Unreliable, GOOD_UDP
    assertMismatches Unreliable, GOOD_IP + GOOD_TCP + GOOD_UTP + GOOD_IPFS
  }

  void testMatchesReliable() {
    assertMatches Reliable, GOOD_TCP + GOOD_UTP
    assertMismatches Reliable, GOOD_IP + GOOD_UDP + GOOD_IPFS
  }

  void testMatchesIPFS() {
    assertMatches IPFS, GOOD_IPFS
    assertMismatches IPFS, BAD_IPFS + GOOD_IP + GOOD_TCP + GOOD_UTP + GOOD_UDP
  }

  static GOOD_IP = [
    '/ip4/0.0.0.0',
    '/ip6/fc00::'
  ]
  static BAD_IP = [
    '/ip4/0.0.0.0/tcp/555',
    '/udp/789/ip6/fc00::'
  ]
  static GOOD_TCP = [
    '/ip4/0.0.7.6/tcp/1234',
    '/ip6/::/tcp/0'
  ]
  static BAD_TCP = [
    '/tcp/12345',
    '/ip6/fc00::/udp/5523/tcp/9543'
  ]
  static GOOD_UDP = [
    '/ip4/0.0.7.6/udp/1234',
    '/ip6/::/udp/0'
  ]
  static BAD_UDP = [
    '/udp/12345',
    '/ip6/fc00::/tcp/5523/udp/9543'
  ]
  static GOOD_UTP = [
    '/ip4/1.2.3.4/udp/3456/utp',
    '/ip6/::/udp/0/utp'
  ]
  static BAD_UTP = [
    '/ip4/0.0.0.0/tcp/12345/utp',
    '/ip6/1.2.3.4/ip4/0.0.0.0/udp/1234/utp',
    '/utp'
  ]
  static GOOD_IPFS = [
    '/ip4/1.2.3.4/tcp/1234/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ip6/::/tcp/1234/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ip6/::/udp/1234/utp/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ip4/0.0.0.0/udp/1234/utp/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ'
  ]
  static BAD_IPFS = [
    '/ip4/1.2.3.4/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ip6/::/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/tcp/123/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ip6/::/udp/1234/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ip6/::/utp/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ',
    '/ipfs/QmaCpDMGvV2BGHeYERUEnRQAwe3N8SzbUtfsmvsqQLuvuJ'
  ]

  static {
    println "IP: $IP"
    println "TCP: $TCP"
    println "UDP: $UDP"
    println "UTP: $UTP"
    println "Unreliable: $Unreliable"
    println "Reliable: $Reliable"
    println "IPFS: $IPFS"
  }
}
