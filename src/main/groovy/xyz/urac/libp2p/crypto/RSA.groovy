package xyz.urac.libp2p.crypto

import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateCrtKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec

class RSA {
  static final ALGORITHM = 'RSA'

  static genKeyPair(int bits = 2048) {
    def rsa = KeyPairGenerator.getInstance ALGORITHM
    rsa.initialize bits
    rsa.genKeyPair()
  }

  static unmarshalPrivateKey(byte[] bytes) {
    def keySpec = new PKCS8EncodedKeySpec(bytes)
    def keyFactory = KeyFactory.getInstance ALGORITHM
    keyFactory.generatePrivate keySpec
  }

  static unmarshalPublicKey(RSAPrivateCrtKey privKey) {
    if (!privKey) return
    def keySpec = new RSAPublicKeySpec(privKey.modulus, privKey.publicExponent)
    def keyFactory = KeyFactory.getInstance ALGORITHM
    keyFactory.generatePublic keySpec
  }

  static unmarshalPublicKey(byte[] bytes) {
    def keySpec = new X509EncodedKeySpec(bytes)
    def keyFactory = KeyFactory.getInstance ALGORITHM
    keyFactory.generatePublic keySpec
  }
}
