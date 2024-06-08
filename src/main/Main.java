package main;

import utils.EnvConfig;

public class Main {
    public static void main(String[] args) {
        EnvConfig env = new EnvConfig(); // * Carga los .env
        new GameSystem(env);
    }

}
