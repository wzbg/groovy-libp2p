package xyz.urac.libp2p.crypto

import java.security.MessageDigest

class SHA256 {
  static digest(byte[] input) {
    def sha256 = MessageDigest.getInstance 'SHA-256'
    sha256.digest input
  }
}
