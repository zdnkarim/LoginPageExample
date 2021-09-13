package com.catnip.loginpageexample.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catnip.loginpageexample.R
import com.catnip.loginpageexample.data.preference.UserPreference
import com.catnip.loginpageexample.databinding.ActivityMainBinding
import com.catnip.loginpageexample.ui.homepage.HomeActivity
import com.catnip.loginpageexample.utils.Constans
import com.shashank.sony.fancytoastlib.FancyToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnLoginAccount.setOnClickListener {
            if (isFormLoginValid()) {
                checkLogin()
            }
        }
        binding.llActionSsoFb.setOnClickListener {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_fb_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
        }
        binding.llActionSsoGmail.setOnClickListener {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_gmail_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
        }
    }

    private fun isFormLoginValid(): Boolean {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        if (username.isEmpty()) {
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.text_error_username_empty)
        } else {
            binding.tilUsername.isErrorEnabled = false
        }
        if (password.isEmpty()) {
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.text_error_password_empty)
        } else {
            binding.tilPassword.isErrorEnabled = false
        }
        return isFormValid
    }

    private fun checkLogin() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (username == Constans.DUMMY_USERNAME && password == Constans.DUMMY_PASSWORD) {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
            saveLoginData()
            navigateToHomePage()
        } else {
            FancyToast.makeText(
                this,
                getString(R.string.text_toast_login_error),
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,
                true
            ).show()
        }

    }

    private fun saveLoginData() {
        UserPreference(this).isUserLoggedIn = true
    }

    private fun navigateToHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}