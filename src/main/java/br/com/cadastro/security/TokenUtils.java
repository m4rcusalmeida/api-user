package br.com.cadastro.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

	private final static String ACCESS_TOKEN_SECRET = "nTgrjAaRFSKyC76vvRdSVIPYdhmifuOF";

	private final static Long ACCESS_TOKEN_VALIDY_SECONDS = 2_592_000L;

	public static String createToken(String nome, String email) {
		long expirationTime = ACCESS_TOKEN_VALIDY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		Map<String, Object> extra = new HashMap<>();
		extra.put("nome", nome);
		return Jwts.builder().setSubject(email).setExpiration(expirationDate).addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).compact();
	}

	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build()
					.parseClaimsJws(token).getBody();
			String email = claims.getSubject();

			return new UsernamePasswordAuthenticationToken(email, null, java.util.Collections.emptyList());

		} catch (JwtException e) {
			return null;
		}
	}
}
