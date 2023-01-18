/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enchere.enchere.PROJET;

import com.enchere.enchere.model.Login;
import com.enchere.enchere.model.Token;

/**
 *
 * @author tsotsoa
 */
public interface LoginDAO {

    public void inscription(com.enchere.enchere.model.Login log);

    public com.enchere.enchere.model.Token Login(Login login);
}
