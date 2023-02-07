package com.ch04.sec03;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class Encryptor {
	public static void main(String[] args) {
		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		String valueToEncrypt = "HELLO";

		BytesEncryptor e = Encryptors.standard(password, salt);
		byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
		byte[] decrypted = e.decrypt(encrypted);
		
		// 테스트할 때 쓸 수 있다.
		TextEncryptor e1 = Encryptors.noOpText();
		
		// encrypt() 를 반복 호출해도 다른 출력이 반환된다. 임의의 초기화 벡터 생성
		Encryptors.text(password, salt);
		Encryptors.delux(password, salt);
		
		// queryableText(): 메서드를 반복호출 해도 같은 값 출력, OAuth API 키 등으로 이용 가능
		Encryptors.queryableText(password, salt);
	}
}
