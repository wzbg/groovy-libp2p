package xyz.urac.libp2p.crypto

import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.RSAPrivateCrtKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec

/**
 * @author huanbing ￠幻冰
 * @date 2016年11月25日 上午11:16:05
 */
class RSA {
  static final ALGORITHM = 'RSA'

  static KeyPair genKeyPair(int bits) {
    def rsa = KeyPairGenerator.getInstance ALGORITHM
    rsa.initialize bits
    rsa.genKeyPair()
  }

  static PrivateKey unmarshalPrivateKey(byte[] bytes) {
    def keySpec = new PKCS8EncodedKeySpec(bytes)
    def keyFactory = KeyFactory.getInstance ALGORITHM
    keyFactory.generatePrivate keySpec
  }

  static PublicKey unmarshalPublicKey(RSAPrivateCrtKey privKey) {
    if (!privKey) return
      def keySpec = new RSAPublicKeySpec(privKey.modulus, privKey.publicExponent)
    def keyFactory = KeyFactory.getInstance ALGORITHM
    keyFactory.generatePublic keySpec
  }

  static PublicKey unmarshalPublicKey(byte[] bytes) {
    def keySpec = new X509EncodedKeySpec(bytes)
    def keyFactory = KeyFactory.getInstance ALGORITHM
    keyFactory.generatePublic keySpec
  }
}
