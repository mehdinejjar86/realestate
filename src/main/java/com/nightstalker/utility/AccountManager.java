package com.nightstalker.utility;

import com.nightstalker.people.Profile;
import com.nightstalker.account.Account;
import com.nightstalker.account.Month;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nightstalker.utility.HashPassword.checkPassword;

public class AccountManager {
    public static String emailRegex = "^[\\w]+[\\w-\\.]*[\\w]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&-])[A-Za-z\\d@$!%*?&-]{8,}$";
    public static String dateRegex = "^(?<day>\\d{1,2})-(?<month>\\d{1,2})-(?<year>\\d{1,})$";

    private static final String URL = "jdbc:postgresql://localhost:5432/RealEstate";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Account createAccount()
    {
        String email;
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide a functioning email");
        while (true) {
            System.out.print(">> ");
            email = scanner.nextLine();
            Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
            Matcher emailMatcher = emailPattern.matcher(email);
            if (emailMatcher.find())
                if(isEmailAvailable(email))
                    break;
                else
                    System.out.println("Email already Exist");
            else
                System.out.println("Invalid email!");
        }
        System.out.println("Please provide a password [Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character]");
        while (true) {
            System.out.print(">> ");
            password = scanner.nextLine();
            Pattern passwordPattern = Pattern.compile(passwordRegex);
            Matcher passwordMatcher = passwordPattern.matcher(password);
            if (passwordMatcher.find())
                break;
            else
                System.out.println("Invalid password!");
        }

        int dayI = 0;
        int monthI = 0;
        int yearI = 0;

        System.out.println("Please provide the date of birth under this format: dd-mm-yyyy");

        while(true) {
            boolean correctDay = false;
            boolean correctMonth = false;
            boolean correctYear = false;
            System.out.print(">> ");
            String date = scanner.nextLine();
            Pattern datePattern = Pattern.compile(dateRegex, Pattern.COMMENTS);
            Matcher dateMatcher = datePattern.matcher(date);

            if (dateMatcher.find()) {
                String day = dateMatcher.group(1);
                String month = dateMatcher.group(2);
                String year = dateMatcher.group(3);

                try {
                    monthI = Integer.parseInt(month);
                    if (monthI > 0 && monthI <= 12)
                        correctMonth = true;
                    else {
                        System.out.printf("Invalid month of birth: %d%n", monthI);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("Invalid month of birth: %s%n", e.getMessage());
                    continue;
                }

                try {
                    yearI = Integer.parseInt(year);
                    int currentYear = Year.now().getValue();
                    if (yearI > currentYear - 150 && yearI < currentYear)
                        correctYear = true;
                    else {
                        System.out.printf("Invalid year of birth: %d%n", yearI);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("Invalid year of birth: %s%n", e.getMessage());
                    continue;
                }

                try {
                    Month monthE = Month.values()[monthI - 1];
                    dayI = Integer.parseInt(day);
                    if (dayI > 0 && dayI <= monthE.getDay(yearI)) {
                        correctDay = true;
                    }
                    else {
                        System.out.printf("Invalid day of birth: %d%n", dayI);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("Invalid day of birth: %s%n", e.getMessage());
                    continue;
                }
            } else {
                System.out.println("Invalid date format! Correct format is dd-mm-yyyy");
            }

            if (correctDay && correctMonth && correctYear)
                break;
        }

        LocalDate dob = LocalDate.of(yearI, monthI, dayI);
        return new Account(email.toLowerCase(), HashPassword.hashPassword(password), dob);
    }

    public static void insertAccount(Account account) throws SQLException {
        String sql = "INSERT INTO account (email, hashed_password, dob) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getEmail());
            pstmt.setString(2, account.getPassword());
            pstmt.setDate(3, Date.valueOf(account.getDob()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("An account was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred " + e.getMessage());
            throw e;
        }
    }

    public static List<Optional<Account>> getAccount() {
        String sql = "SELECT email, hashed_password, dob FROM account";
        List<Optional<Account>> accountsList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String email = rs.getString("email");
                String hashedPassword = rs.getString("hashed_password");
                LocalDate dob = rs.getDate("dob").toLocalDate();

                Account account = new Account(email, hashedPassword, dob);
                accountsList.add(Optional.of(account));
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            accountsList = SerializeManager.read("account.ser");
        }
        return accountsList;
    }

    public static Optional<Profile> logIn (List<Optional<Profile>> profile) {

        Optional<String> email;
        Optional<String> password;
        Optional<Profile> userFound = Optional.empty();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your email");
        while (true) {
            System.out.print(">> ");
            email = Optional.ofNullable(scanner.nextLine());
            Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
            Matcher emailMatcher = emailPattern.matcher(email.orElse("unknown"));
            if (emailMatcher.find())
                break;
            else
                System.out.println("Invalid email!");
        }
        System.out.println("Please enter your password");
        System.out.print(">> ");
        password = Optional.ofNullable(scanner.nextLine());
        Optional<String> finalEmail = email;
        Optional<String> finalPassword = password;
        userFound = profile.stream()
                .map(p -> p.orElse(null))
                .filter(Objects::nonNull)
                .filter(p -> p.getAccount().getEmail().equals(finalEmail.orElse("unknown").toLowerCase()) && checkPassword(finalPassword.orElse("unknown"), p.getAccount().getPassword() ))
                .findFirst();
        if (userFound.isEmpty())
            System.out.println("Invalid Password or Email!");

        return userFound;
    }

    public static boolean isEmailAvailable(String email) {
        String sql = "SELECT 1 FROM account WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return !rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return false;
        }
    }

}
