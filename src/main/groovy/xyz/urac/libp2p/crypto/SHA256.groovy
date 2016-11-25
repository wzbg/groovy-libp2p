package xyz.urac.libp2p.crypto

import java.security.MessageDigest

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:15:12
 */
class SHA256 {
  static digest(byte[] input) {
    def sha256 = MessageDigest.getInstance 'SHA-256'
    sha256.digest input
  }
}
