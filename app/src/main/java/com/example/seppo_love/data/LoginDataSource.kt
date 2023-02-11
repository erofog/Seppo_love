package com.example.seppo_love.data

import com.example.seppo_love.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // Authenticate the user against a remote data source
            val authenticatedUser = authenticateWithRemoteDataSource(username, password)

            // If the user was authenticated successfully, return the authenticated user
            return Result.Success(authenticatedUser)
        } catch (e: Throwable) {
            // If the user authentication failed, return a failure result
            return Result.Error(IOException("Error logging in", e))
        }
    }

    private fun authenticateWithRemoteDataSource(username: String, password: String): LoggedInUser {
        // Perform the actual authentication against a remote data source
        // (e.g. by sending a request to a server)

        if (username == "test" && password == "password") {
            return LoggedInUser(java.util.UUID.randomUUID().toString(), username)
        } else {
            throw Exception("Incorrect username or password")
        }
    }
    fun logout() {
        // Revoke authentication by invalidating the user's session on the remote data source
        // (e.g. by sending a request to a server)

        // For demonstration purposes, assume that the user's session has been successfully invalidated
    }
}
