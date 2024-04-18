package com.obilet.android.assignment.feature.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.button.MaterialButton
import com.obilet.android.assignment.R
import com.obilet.android.assignment.feature.search.listener.RetryButtonClickListener

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected lateinit var errorWithRetryButtonDialog: Dialog

    private lateinit var errorWithOkayButtonDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupErrorWithRetryButtonDialog()
        setupErrorWithOkayButtonDialog()
    }

    private fun setupErrorWithRetryButtonDialog() {
        errorWithRetryButtonDialog = Dialog(requireContext())
        errorWithRetryButtonDialog.apply {
            setContentView(R.layout.dialog_error_with_retry_button)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.attributes?.windowAnimations = R.style.Theme_OBilet_DialogStyle
            window?.decorView?.setBackgroundResource(android.R.color.transparent)
            val closeButton = findViewById<ImageView>(R.id.closeIv)
            val retryButton = findViewById<MaterialButton>(R.id.retryBtn)
            closeButton.setOnClickListener {
                errorWithRetryButtonDialog.dismiss()
            }


            if (this is RetryButtonClickListener) {
                retryButton.setOnClickListener {
                    onRetryButtonClick()
                }
            }
        }
    }

    private fun setupErrorWithOkayButtonDialog() {
        errorWithOkayButtonDialog = Dialog(requireContext())
        errorWithOkayButtonDialog.apply {
            setContentView(R.layout.dialog_error_with_okay_button)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.attributes?.windowAnimations = R.style.Theme_OBilet_DialogStyle
            window?.decorView?.setBackgroundResource(android.R.color.transparent)
            val okayButton = findViewById<MaterialButton>(R.id.okayBtn)
            okayButton.setOnClickListener {
                errorWithOkayButtonDialog.dismiss()
            }
        }
    }

    fun showErrorDialogWithOkayButton(errorMessage: String) {
        val errorTv = errorWithOkayButtonDialog.findViewById<TextView>(R.id.errorMessageTv)
        errorTv.text = errorMessage
        errorWithOkayButtonDialog.show()
    }

    fun showErrorDialog(errorMessage: String) {
        val errorMessageTv = errorWithRetryButtonDialog.findViewById<TextView>(R.id.errorMessageTv)
        errorMessageTv.text = errorMessage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}