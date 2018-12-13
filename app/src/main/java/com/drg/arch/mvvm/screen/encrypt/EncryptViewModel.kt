package com.drg.arch.mvvm.screen.encrypt

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.drg.arch.mvvm.ViewModelProviderFactory
import com.drg.arch.mvvm.screen.base.BaseViewModel
import com.drg.arch.mvvm.util.crypt.EncryptionServices


/**
 * Created by aleksey.stepanov
 * 8/3/18.
 */
class EncryptViewModel : BaseViewModel() {

	val availableAliases = MutableLiveData<String>()
	val input = MutableLiveData<String>()
	val encrypted = MutableLiveData<String>()
	val decrypted = MutableLiveData<String>()

	private var crypt: EncryptionServices? = null
	private var ctx: Context? = null


	fun init(applicationContext: Context?) {
		ctx = applicationContext
		crypt = EncryptionServices(ctx!!)

		val aliases = crypt!!.aliases()

		val aliasesString = StringBuilder()

		while (aliases.hasMoreElements()) {
			val alias = aliases.nextElement() as String
			aliasesString.append("\n").append(alias)
//			println("alias name: $alias")
//			val certificate = keystore.getCertificate(alias)
//			System.out.println(certificate.toString())

		}

		availableAliases.value = aliasesString.toString()
	}

	fun onProcessClick() {

		var encryptedData = ""

		crypt?.apply {

			//			createMasterKey()

			encryptedData = encrypt(input.value ?: "")

			encrypted.value = encryptedData


		}

		crypt = EncryptionServices(ctx!!)

		decrypted.value = crypt?.decrypt(encryptedData)


	}

	/* data types */

	class Factory(viewModel: EncryptViewModel) : ViewModelProviderFactory<EncryptViewModel>(viewModel)
}