package com.example.proyecto1.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.FirebaseAuthManager
import kotlinx.coroutines.launch

data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {

    var uiState by mutableStateOf(AuthUiState())
        private set

    val isLoggedIn: Boolean
        get() = FirebaseAuthManager.currentUser != null

    fun onNameChange(value: String) {
        uiState = uiState.copy(name = value)
    }

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value)
    }

    fun onConfirmPasswordChange(value: String) {
        uiState = uiState.copy(confirmPassword = value)
    }

    fun clearError() {
        uiState = uiState.copy(error = null)
    }

    fun login(onSuccess: () -> Unit) {
        val email = uiState.email.trim()
        val password = uiState.password

        if (email.isEmpty() || password.isEmpty()) {
            uiState = uiState.copy(error = "Completa todos los campos")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            val result = FirebaseAuthManager.login(email, password)
            uiState = uiState.copy(isLoading = false)
            result.onSuccess {
                onSuccess()
            }.onFailure {
                uiState = uiState.copy(error = it.message ?: "Error al iniciar sesión")
            }
        }
    }

    fun register(onSuccess: () -> Unit) {
        val name = uiState.name.trim()
        val email = uiState.email.trim()
        val password = uiState.password
        val confirm = uiState.confirmPassword

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            uiState = uiState.copy(error = "Completa todos los campos")
            return
        }

        if (password.length < 6) {
            uiState = uiState.copy(error = "La contraseña debe tener al menos 6 caracteres")
            return
        }

        if (password != confirm) {
            uiState = uiState.copy(error = "Las contraseñas no coinciden")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            val result = FirebaseAuthManager.register(name, email, password)
            uiState = uiState.copy(isLoading = false)
            result.onSuccess {
                onSuccess()
            }.onFailure {
                uiState = uiState.copy(error = it.message ?: "Error al registrarse")
            }
        }
    }
}
