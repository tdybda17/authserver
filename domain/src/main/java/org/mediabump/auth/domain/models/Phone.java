package org.mediabump.auth.domain.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Phone {
    private final String id;
    private final String countryCode;
    private final String number;

    public Phone(String id, String countryCode, String number) {
        this.id = id;
        this.countryCode = countryCode;
        this.number = number;
    }

    public Phone(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("P.ID");
        this.countryCode = resultSet.getString("P.COUNTRY_CODE");
        this.number = resultSet.getString("P.NUMBER");
    }

    public String getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public static Phone from(ResultSet resultSet) {
        try {
            return new Phone(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }
}
