package xyz.urac.libp2p.peer

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import java.security.PrivateKey
import java.security.PublicKey

import org.bouncycastle.util.encoders.Hex
import org.ipfs.api.Base58

import xyz.urac.libp2p.crypto.Crypto

/**
 * Id is an object representation of a peer Id. a peer Id is a multihash
 * 
 * @author zyc ￠幻冰
 * @date 2016年11月19日 下午1:37:05
 */
class PeerId {
  byte[] id
  PrivateKey privKey
  PublicKey pubKey

  PeerId(byte[] id) {
    this(id, null, null)
  }

  PeerId(byte[] id, PrivateKey privKey) {
    this(id, privKey, null)
  }

  PeerId(byte[] id, PublicKey pubKey) {
    this(id, null, pubKey)
  }

  PeerId(PrivateKey privKey) {
    this(null, privKey, null)
  }

  PeerId(PublicKey pubKey) {
    this(null, null, pubKey)
  }

  PeerId(PrivateKey privKey, PublicKey pubKey) {
    this(null, privKey, pubKey)
  }

  PeerId(byte[] id, PrivateKey privKey, PublicKey pubKey) {
    if (!pubKey) pubKey = Crypto.unmarshalPublicKey privKey
    if (!id) id = Crypto.hash pubKey?.encoded
    assert id != null : 'invalid id provided'
    if (pubKey) {
      assert id == Crypto.hash(pubKey.encoded) : 'inconsistent arguments(id, pubKey)'
      if (privKey) {
        assert pubKey == Crypto.unmarshalPublicKey(privKey) : 'inconsistent arguments(privKey, pubKey)'
      }
    }
    this.id = id
    this.privKey = privKey
    this.pubKey = pubKey
  }

  /**
   * @return the protobuf version of the  key, matching go ipfs formatting
   */
  def marshalPrivKey() {
    privKey?.encoded
  }

  /**
   * @return the protobuf version of the  key, matching go ipfs formatting
   */
  def marshalPubKey() {
    pubKey?.encoded
  }

  /**
   * pretty print
   */
  def toPrint() {
    toJSON().toPrettyString()
  }

  @Override
  String toString() {
    toJSON()
  }

  /**
   * @return the jsonified version of the key, matching the formatting of go-ipfs for its config file
   */
  def toJSON() {
    new JsonBuilder([
      id: toB58String(),
      privKey: toB64PrivKey(),
      pubKey: toB64PubKey()
    ])
  }

  /**
   * encode/decode functions
   */
  def toBytes() {
    id
  }

  def toHexString() {
    Hex.toHexString id
  }

  def toB58String() {
    Base58.encode id
  }

  def toB64PrivKey() {
    toB64Opt marshalPrivKey()
  }

  def toB64PubKey() {
    toB64Opt marshalPubKey()
  }

  /**
   * generation
   */
  static create(int bits = 2048) {
    def kp = Crypto.genKeyPair bits
    new PeerId(kp.private, kp.public)
  }

  static createFromBytes(byte[] bytes) {
    new PeerId(bytes)
  }

  static createFromHexString(String data) {
    def bytes = Hex.decode data
    createFromBytes(bytes)
  }

  static createFromB58String(String str) {
    def bytes = Base58.decode str
    createFromBytes(bytes)
  }

  /**
   * Private key input will be a bytes or string
   */
  static createFromPrivKey(privKey) {
    if (privKey instanceof String) privKey = Base64.decoder.decode privKey
    assert privKey instanceof byte[] : 'Private key input must be a bytes or string'
    privKey = Crypto.unmarshalPrivateKey privKey
    new PeerId(privKey)
  }

  /**
   * Public Key input will be a bytes or string
   */
  static createFromPubKey(pubKey) {
    if (pubKey instanceof String) pubKey = Base64.decoder.decode pubKey
    assert pubKey instanceof byte[] : 'Public Key input must be a bytes or string'
    pubKey = Crypto.unmarshalPublicKey pubKey
    new PeerId(pubKey)
  }

  static createFromJSON(json) {
    if (!json instanceof String && !json instanceof JsonBuilder) {
      json = new JsonBuilder(json)
    }
    def map = new JsonSlurper().parseText json.toString()
    def id = map.id
    def privKey = map.privKey
    def pubKey = map.pubKey
    if (id) id = Base58.decode map.id
    if (privKey) privKey = Crypto.unmarshalPrivateKey Base64.decoder.decode(privKey)
    if (pubKey) pubKey = Crypto.unmarshalPublicKey Base64.decoder.decode(pubKey)
    new PeerId(id, privKey, pubKey)
  }

  def toB64Opt(src) {
    if (src) Base64.encoder.encodeToString src
  }
}
