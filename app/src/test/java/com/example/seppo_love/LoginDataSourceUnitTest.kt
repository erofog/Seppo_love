package com.example.seppo_love
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import com.example.seppo_love.data.LoginDataSource

class LoginDataSourceTest {

    private val loginDataSource = LoginDataSource()
    sealed class Result<out T: Any> {
        data class Success<out T : Any>(val data: T) : Result<T>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }

    data class LoggedInUser(
        val userId: String,
        val displayName: String
    )

    @Test
    fun login_validCredentials_returnsSuccess() {
        // Arrange
        val username = "test"
        val password = "password"

        // Act
        val result = loginDataSource.login(username, password)

        // Assert
        assertTrue(result is Result.Success)
        result as Result.Success
        assertTrue(result.data is LoggedInUser)
    }

    @Test
    fun login_invalidCredentials_returnsError() {
        // Arrange
        val username = "invalid"
        val password = "credentials"

        // Act
        val result = loginDataSource.login(username, password)

        // Assert
        assertTrue(result is Result.Error)
        result as Result.Error
        assertTrue(result.exception is IOException)
        assertEquals("Error logging in", result.exception?.message)
    }

    @Test
    fun logout_always_revokesAuthentication() {
        // Arrange

        // Act
        loginDataSource.logout()

        // Assert
        // Asserting the result of logout is not possible, as it does not return a value
        // To verify that logout works as expected, additional testing would be required
        // (e.g. by adding logging or by checking the state of the remote data source)
    }
}