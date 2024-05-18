package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvConfig {
    private static Map<String, String> envVariables;

    public EnvConfig() {
        envVariables = new HashMap<>();
        loadEnvFile();
        new Constants();
    }

    private void loadEnvFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\Users\\Fabricio\\Desktop\\Universidad\\3a - POO\\Proyecto Circus\\poo_game\\.env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue; // Ignorar líneas vacías y comentarios
                }
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envVariables.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEnvVariable(String key) {
        return envVariables.get(key);
    }

    public static boolean getEnvVariableAsBoolean(String key) {
        String value = envVariables.get(key);
        return value != null && value.equalsIgnoreCase("true");
    }
}
