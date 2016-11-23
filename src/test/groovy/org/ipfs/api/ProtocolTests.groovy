package org.ipfs.api

class ProtocolTests extends GroovyTestCase {
  final ADDR = '/ip4/127.0.0.1/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234'

  void testReadVarint() {
    MultiAddress addr = new MultiAddress(ADDR)
    def input = new ByteArrayInputStream(addr.raw)
    int code = Protocol.readVarint input
    def proto = Protocol.get code
    assert proto.name() == 'ip4'
  }

  void testGetProtocols() {
    def parts = ADDR.split '/'
    def protos = []
    parts.eachWithIndex { part, i ->
      if (i % 2) {
        def proto = Protocol.get part
        protos << proto.name()
      }
    }
    assert protos == ['ip4', 'ipfs', 'tcp']
  }
}
