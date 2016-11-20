package xyz.urac.libp2p.crypto

class Crypto extends RSA {
  static hash(byte[] input) {
    if (input) SHA256.digest input
  }
}
