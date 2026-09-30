	package com.loan.helper;

	import java.util.Base64;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

	public class GenerateSecretKey {

		public static void main(String[] args) {

			SecretKey key = Jwts.SIG.HS256.key().build();

			String secret = Base64.getEncoder().encodeToString(key.getEncoded());

			System.out.println(secret);
		}
	}
