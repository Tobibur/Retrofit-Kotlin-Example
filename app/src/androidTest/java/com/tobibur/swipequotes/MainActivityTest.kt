package com.tobibur.swipequotes

import android.support.test.rule.ActivityTestRule
import android.widget.TextView
import com.tobibur.swipequotes.view.MainActivity
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule @JvmField
    val mActivityTestRule : ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)
    private var mainActivity : MainActivity? = null

    @Before
    fun setUp(){
        try {
            mainActivity = mActivityTestRule.activity
        }catch (e: Exception){
            throw e
        }
    }

    @Test
    fun testLaunch() {
        val view = mainActivity?.findViewById<TextView>(R.id.quoteTextView)
        assertNotNull(view)   //we will use this a lot
    }

    @After
    fun tearDown() {
        try {
            mainActivity = null
        }catch (e: Exception){
            throw e
        }
    }
}