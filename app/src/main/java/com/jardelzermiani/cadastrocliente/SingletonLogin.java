package com.jardelzermiani.cadastrocliente;

public final class SingletonLogin{
    private static final SingletonLogin INSTANCE = new SingletonLogin();

    public static String nickname;

    private SingletonLogin(){
    }

    public static SingletonLogin getInstance(){
        return INSTANCE;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
}
