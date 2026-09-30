package com.loan.security;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.loan.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

	private final SecretKey key;

	public JwtUtil() {
		String secret = System.getenv("JWT_SECRET");

		byte[] decodedSecret = Base64.getDecoder().decode(secret); //application decodes it:

		key = new SecretKeySpec(decodedSecret,"HmacSHA256");		// converts those bytes into a cryptographic key:
	}


	public String generateToken(User user) {

		Date now = new Date();

		Date expiraion = new Date(now.getTime()+60*60*1000);

		return Jwts.builder()					// Start building a new JWT
				.subject(user.getUserName())	// Put the username into the JWT as the "sub" claim
				.claim("role", user.getRole())
				.issuedAt(now)					// Put the token creation time into the "iat" claim
				.expiration(expiraion)			// Put the token expiry time into the "exp" claim
				.signWith(key)					// Digitally sign the JWT using the secret key
				.compact();						// Build everything into the final JWT String
	}

	public Claims validateToken(String token) {

		return Jwts.parser()					// Start creating a JWT parser
				.verifyWith(key)				// Tell the parser which key to use to verify the signature
				.build()						// Build/configure the JWT parser
				.parseSignedClaims(token)		// Parse and verify the signed JWT and its claims
				.getPayload();					 // Extract and return the JWT payload (Claims)
	}

}