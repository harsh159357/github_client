package com.harsh.githubClient.util

import com.harsh.githubClient.R
import java.security.SecureRandom
import java.util.*

class GitHubClientUtil {
    companion object {
        const val SPLASH_DELAY: Long = 2000

        //Some sample splash animations
        val splashAnimation = intArrayOf(
            R.anim.fade_in,
            R.anim.zoom_in,
            R.anim.slide_down,
            R.anim.bounce_down,
            R.anim.rotate_clockwise,
            R.anim.rotate_anti_clockwise
        )

        fun generateRandomInteger(min: Int, max: Int): Int {
            val rand = SecureRandom()
            rand.setSeed(Date().time)
            return rand.nextInt(max - min + 1) + min
        }

        fun getRandomSplashAnimation(): Int {
            return splashAnimation[generateRandomInteger(0, splashAnimation.size - 1)]
        }
    }
}