package com.harsh.githubClient.ui

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.harsh.githubClient.databinding.ActivitySplashBinding
import com.harsh.githubClient.util.GitHubClientUtil
import com.harsh.githubClient.util.GitHubClientUtil.Companion.SPLASH_DELAY

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private var mAnimation: Animation? = null

    private var splashHandler: Handler? = null
    private val splashRunnable = Runnable { navigateToHomeScreen() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        doAnimation()
    }

    private fun doAnimation() {
        mAnimation = AnimationUtils
            .loadAnimation(this, GitHubClientUtil.getRandomSplashAnimation())
        mAnimation?.run { duration = SPLASH_DELAY }
        binding.splashImage.startAnimation(mAnimation)
        binding.splashTitle.startAnimation(mAnimation)
        splashHandler = Handler()
        splashHandler?.run { postDelayed(splashRunnable, SPLASH_DELAY) }
    }

    override fun onTouchEvent(evt: MotionEvent): Boolean {
        if (evt.action == MotionEvent.ACTION_DOWN) {
            splashHandler!!.removeCallbacks(splashRunnable)
            navigateToHomeScreen()
        }
        return true
    }

    private fun navigateToHomeScreen() {
        startActivity(MainActivity.createIntent(this))
        finish()
    }
}
