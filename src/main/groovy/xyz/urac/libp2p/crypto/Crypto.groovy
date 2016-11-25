package xyz.urac.libp2p.crypto

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:16:21
 */
class Crypto extends RSA {
  static hash(byte[] input) {
    if (input) SHA256.digest input
  }
}
