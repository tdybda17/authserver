package org.mediabump.auth.domain.tools.provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class Credentials {

    private static Map<String, Object> credentials;

    private static Object get(String key) {
        if (credentials == null) {
            readCredentials();
        }
        return credentials.get(key);
    }

    public static String getString(Setting setting) {
        return get(setting.getSettingName()).toString();
    }

    public static Map<String, Object> getAll() {
        if (credentials == null) {
            readCredentials();
        }
        return credentials;
    }

    public static boolean getBoolean(Setting setting) {
        return (boolean) get(setting.getSettingName());
    }

    public static int getInt(Setting setting) {
        return (int) get(setting.getSettingName());
    }

    private static void readCredentials() {
        InputStream in = Credentials.class.getResourceAsStream("/config.json");

        try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();
            credentials = mapper.readValue(sb.toString(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Setting {
        APP_SESSION_SECURE_COOKIE("app.session_cookie_secure"),
        APP_SECRET("app.secret"),

        DB_HOST_NAME("db.host"),
        DB_PORT("db.port"),
        DB_USERNAME("db.username"),
        DB_PASSWORD("db.password"),
        DB_SCHEMA("db.schema"),

        ENDPOINTS_LOGIN_SUCCESS_REDIRECT("endpoints.login_success_redirect"),
        ENDPOINTS_LOGIN_URI("endpoints.login_uri");
        ;

        private String settingName;

        Setting(String settingName) {
            this.settingName = settingName;
        }

        public String getSettingName() {
            return settingName;
        }
    }

}
