package org.kainos.ea.api;

import org.kainos.ea.cli.Login;
import org.kainos.ea.client.*;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AuthService {

    private AuthDao authDao = new AuthDao();

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getUsername());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            }
        }


        throw new FailedToLoginException();
    }

    public String register(Login login) throws SQLException, FailedToRegisterUserException {
        try {
            authDao.register(login);
            if (!authDao.validLogin(login)) {
                throw new FailedToRegisterUserException();
            }

        } catch (FailedToRegisterUserException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean isAdmin(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (roleID == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }


    public boolean isManagement(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (roleID == 5) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isSales(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (roleID == 4) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isHR(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (roleID == 3) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }
}
