package com.harsh.githubClient.util

import com.harsh.githubClient.R
import java.security.SecureRandom
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GitHubClientUtil {
    companion object {
        const val SPLASH_DELAY: Long = 2500
        const val REPO_PATH = "REPO_PATH"

        const val patternFromServer = "yyyy-MM-dd'T'HH:mm:ss"
        const val customPattern = "dd-MM-yyyy hh:mm a"

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

        fun getFormattedDate(dateString: String?, case: String): String {
            return getAbbreviatedFromDateTime(
                dateString,
                patternFromServer,
                customPattern, "$case date not found"
            )
        }

        fun getAbbreviatedFromDateTime(
            dateTime: String?,
            serverFormat: String,
            clientFormat: String,
            extra: String
        ): String {
            val input = SimpleDateFormat(serverFormat)
            val output = SimpleDateFormat(clientFormat)
            try {
                val getAbbreviate = input.parse(dateTime)
                return output.format(getAbbreviate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return extra
        }
    }
}