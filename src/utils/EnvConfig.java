package utils;

import java.io.*;
import java.util.Properties;

public class EnvConfig {
    private final String ENV_FILE = ".env";
    private Properties envProps;

    public EnvConfig() {
        try {
            envProps = loadEnvFile();
            new Constants(envProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Función para cargar el archivo .env en un Properties
    private Properties loadEnvFile() throws IOException {
        Properties props = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(ENV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("#")) {
                    String[] keyValue = line.split("=", 2);
                    if (keyValue.length == 2) {
                        props.setProperty(keyValue[0].trim(), keyValue[1].trim());
                    }
                }
            }
        }
        return props;
    }

    // Función para modificar o agregar una variable en el Properties
    public void setEnvVariable(String key, String value) throws IOException {
        envProps.setProperty(key, value);
        saveEnvFile(envProps);
    }

    // Función para guardar el Properties en el archivo .env
    private void saveEnvFile(Properties props) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ENV_FILE))) {
            for (String key : props.stringPropertyNames()) {
                writer.write(key + "=" + props.getProperty(key));
                writer.newLine();
            }
        }
    }

    public Properties getEnvProps() {
        return envProps;
    }
}
