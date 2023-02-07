package com.ch04.sec03;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class KeyGeneratorTest {
	public static void main(String[] args) {
		StringKeyGenerator stringKeyGenerator = KeyGenerators.string();
		String salt = stringKeyGenerator.generateKey();
		
		BytesKeyGenerator byteKeyGenerator = KeyGenerators.secureRandom(); // 키 길이, 기본 8
		byte[] key = byteKeyGenerator.generateKey();
		int keyLength = byteKeyGenerator.getKeyLength();
		
		// 같은 생성기를 호출하면 같은 키를 반환하도록
		BytesKeyGenerator sharedKeyGenerator = KeyGenerators.shared(16);
		byte[] key1 = sharedKeyGenerator.generateKey();
		byte[] key2 = sharedKeyGenerator.generateKey();
	}
}
