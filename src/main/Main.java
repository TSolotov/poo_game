package main;

import utils.EnvConfig;

public class Main {
    public static void main(String[] args) {
        new EnvConfig(); // * Carga los .env
        new Game();
    }

}
