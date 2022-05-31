//package com.george.orca.service;
//
//import com.george.orca.domain.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public interface LoginService extends UserDetailsService {
//
//    UserDetails loadUserByUsername(String username);
//    /**
//     * Get the number of users.
//     */
//    long getNumberOfUsers();
//
//    /**
//     * Find a user by login.
//     *
//     * @param login
//     *            The user login
//     * @return The user value object
//     */
//    UserDetails findUser(String login);
//
//    /**
//     * Find all users with a login starting with the "loginStart" string.
//     */
//    List<UserDetails> findUsersByLogin(String loginStart);
//
//    /**
//     * Find the current user.
//     * <p>
//     * This method relies on Acegy Security.
//     * </p>
//     *
//     * @return The current user.
//     */
//    UserDetails getCurrentUser();
//
//    /**
//     * Update a user's information.
//     *
//     * @param user
//     *            The user to update
//     */
//    void updateUser(UserDetails user);
//
//    /**
//     * Enable a user account.
//     *
//     * @param login
//     *            The user login
//     */
//    void enableUser(String login);
//
//    /**
//     * Disable a user account.
//     *
//     * @param login
//     *            The user login
//     */
//    void disableUser(String login);
//
//    /**
//     * Create a new user.
//     *
//     * @param user
//     *            The user to create
//     */
//
//    /**
//     * Send a user's password by email.
//     *
//     * @param user
//     *            The user
//     */
//    void sendPassword(UserDetails user);
//
//}
