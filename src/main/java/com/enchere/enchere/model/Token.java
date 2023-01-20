/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enchere.enchere.model;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Claims;

/**
 *
 * @author tsotsoa
 */
public class Token {
    public static final long DateEXP = 100000000;
    public static final String keyToken = "Token22";

    int id;
    String token;
    Date dateexpiration;
    int utilisateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String CreerToken(int utilisateurid) {
        long now = System.currentTimeMillis();
        Date dt = new Date(now + Token.DateEXP);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Token.keyToken)
                .setIssuedAt(new Date(now))
                .setExpiration(dt)
                .claim("idutilisateur", utilisateurid)
                .compact();
        return token;
    }

    public Token ReturnToken(int idutilisateur) {
        String token = new Token().CreerToken(idutilisateur);
        Claims cl = Jwts.parser().setSigningKey(Token.keyToken)
                .parseClaimsJws(token).getBody();
        Token tok = new Token();
        tok.setToken(token);
        tok.setDateexpiration(cl.getExpiration());
        tok.setUtilisateur(idutilisateur);
        return tok;

    }

    public Date getDateEXP(String token) {
        Claims cl = Jwts.parser().setSigningKey(Token.keyToken)
                .parseClaimsJws(token).getBody();
        return cl.getExpiration();
    }

    public Token ToToken(String tok) {
        Token token = new Token();
        Claims cl = Jwts.parser().setSigningKey(Token.keyToken)
                .parseClaimsJws(tok).getBody();
        int idutilisateur = Integer.parseInt(cl.get("idutilisateur").toString());
        token.setUtilisateur(idutilisateur);
        token.setToken(tok);
        return token;
    }

}
