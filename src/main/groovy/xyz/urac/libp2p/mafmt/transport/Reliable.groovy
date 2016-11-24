package xyz.urac.libp2p.mafmt.transport

import org.ipfs.api.MultiAddress

class Reliable implements Transport {
  @Override
  def dial(addr) {
  }

  @Override
  def createListener(options, handler) {
  }

  @Override
  def filter(MultiAddress... addrs) {
    addrs?.findAll { this.Reliable.matches it }
  }
}
