package org.ipfs.api

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:34:37
 */
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
    def names = Protocol.byName.keySet()
    def protos = parts.findAll { names.contains it }
    assert protos == ['ip4', 'ipfs', 'tcp']
  }
}
