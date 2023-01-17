package com.enchere.enchere.model;

public class Login {
    int id;
    String login;
    String mdp;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String SHA1(){
        String pass="";
        int ndr=this.getMdp().toCharArray().length;
        for(int i=0;i<ndr;i++){
            pass+="*";
        }
        return pass;

    }

}
