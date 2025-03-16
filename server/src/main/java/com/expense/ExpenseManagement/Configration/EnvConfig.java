package com.expense.ExpenseManagement.Configration;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getGoogleClientId() {
        return dotenv.get("GOOGLE_CLIENT_ID");
    }

    public static String getGoogleClientSecret() {
        return dotenv.get("GOOGLE_CLIENT_SECRET");
    }
}

