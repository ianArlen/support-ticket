package com.gruposalinas.orchestrator.util;

public class Validator {

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidName(String name) {
      return !name.isEmpty() && !name.matches(".*\\d.*");
    }
}