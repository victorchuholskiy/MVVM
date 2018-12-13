package com.drg.arch.mvvm.screen.login

import android.app.Activity
import android.content.Intent
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.widget.Button
import androidx.test.core.app.ApplicationProvider
import com.drg.arch.mvvm.App
import com.drg.arch.mvvm.R
import com.drg.arch.mvvm.screen.dashboard.DashboardActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.RuntimeEnvironment



/**
 * Created by viktor.chukholskiy
 * 11/28/18.
 */
@RunWith(RobolectricTestRunner::class)
class LoginActivityTest {

	private lateinit var activity: LoginActivity

	private lateinit var loginEditText: TextInputEditText
	private lateinit var passwordEditText: TextInputEditText

	private lateinit var loginTextInputLayout: TextInputLayout
	private lateinit var passwordTextInputLayout: TextInputLayout

	private lateinit var loginBtn: Button

	@Before
	fun setupInitActivity() {
		activity = Robolectric.setupActivity(LoginActivity::class.java)
		loginEditText = activity.findViewById(R.id.et_login)
		passwordEditText = activity.findViewById(R.id.et_password)
		loginTextInputLayout = activity.findViewById(R.id.til_login)
		passwordTextInputLayout = activity.findViewById(R.id.til_password)
		loginBtn = activity.findViewById(R.id.btn_login)
	}

	@Test
	fun loginPassword_editTask_checkDefState() {
		assertTrue(loginEditText.text.isNullOrEmpty())
		assertTrue(passwordEditText.text.isNullOrEmpty())
	}

	@Test
	fun btnLogin_enabled() {
		assertTrue(loginBtn.isEnabled)
	}

	@Test
	fun clickBtnLogin_emptyValues() {
		loginEditText.setText("")
		passwordEditText.setText("")

		assertEquals(null, loginTextInputLayout.error)
		assertEquals(null, passwordTextInputLayout.error)

		loginBtn.performClick()

		assertEquals("Empty login", loginTextInputLayout.error)
		assertEquals("Empty pass", passwordTextInputLayout.error)
	}


	/*@Test
	fun clickBtnLogin_correctValues() {
		loginEditText.setText("abc")
		passwordEditText.setText("abc")

		assertEquals(null, loginTextInputLayout.error)
		assertEquals(null, passwordTextInputLayout.error)

		loginBtn.performClick()

		assertEquals(null, loginTextInputLayout.error)
		assertEquals(null, passwordTextInputLayout.error)

		val expectedIntent = Intent(activity, DashboardActivity::class.java)
		val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
		if (actual.component != null) {
			assertEquals(expectedIntent.component, actual.component)
		}
	}*/
}