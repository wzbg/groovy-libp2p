package xyz.urac.libp2p.mafmt.transport

import org.ipfs.api.MultiAddress

class UDP implements Transport {
  @Override
  def dial(addr) {
  }

  @Override
  def createListener(options, handler) {
  }

  @Override
  def filter(MultiAddress... addrs) {
    addrs?.findAll { this.UDP.matches it }
  }
}
