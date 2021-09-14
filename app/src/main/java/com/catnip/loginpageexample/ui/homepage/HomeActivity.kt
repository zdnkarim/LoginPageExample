package com.catnip.loginpageexample.ui.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.annotation.SuppressLint
import com.catnip.loginpageexample.R
import com.catnip.loginpageexample.data.preference.UserPreference
import com.catnip.loginpageexample.databinding.ActivityHomeBinding
import com.catnip.loginpageexample.ui.login.LoginActivity
import com.catnip.loginpageexample.utils.Constans
import com.catnip.loginpageexample.utils.ProtectedMediaChromeClient
import com.shashank.sony.fancytoastlib.FancyToast

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.text_actionbar_homepage)

        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.wvPage.settings.javaScriptEnabled = true
        binding.wvPage.settings.allowContentAccess = true
        binding.wvPage.settings.domStorageEnabled = true
        binding.wvPage.webChromeClient = ProtectedMediaChromeClient()
        binding.wvPage.loadUrl(Constans.URL_WEBVIEW_HOMEPAGE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_homepage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                FancyToast.makeText(
                    this,
                    getString(R.string.text_toast_logout_success),
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                deleteSessionData()
                navigateToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun deleteSessionData() {
        UserPreference(this).isUserLoggedIn = false
    }
}