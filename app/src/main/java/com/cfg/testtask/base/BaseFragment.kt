package com.cfg.testtask.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cfg.testtask.R
import com.cfg.testtask.base.events.MessageEvent
import com.cfg.testtask.base.events.NavAction
import com.cfg.testtask.ui.dialog.LoadingFullScreenDialog
import com.cfg.testtask.ui.dialog.LoadingFullScreenDialog.Companion.PROGRESS_DIALOG_TAG
import com.cfg.testtask.utils.extension.launchInViewScope
import com.cfg.testtask.utils.extension.showSnackMessage
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(
    @LayoutRes contentLayoutId: Int
): Fragment(contentLayoutId) {

    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigationEvent()
        observeShowLoadingDialog()
        observeMessage()
    }

    private fun observeNavigationEvent() {
        launchInViewScope {
            viewModel.navAction.collect { action ->
                when (action) {
                    is NavAction.NavigationUp -> findNavController().navigateUp()
                    is NavAction.ToDirection -> {
                        try {
                            findNavController().navigate(action.direction)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    private fun observeShowLoadingDialog() {
        launchInViewScope {
            viewModel.isShowLoadingDialog.collect { isShowDialog ->
                if (isShowDialog) {
                    LoadingFullScreenDialog().showNow(childFragmentManager, PROGRESS_DIALOG_TAG)
                } else {
                    (childFragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG)
                            as? LoadingFullScreenDialog)?.dismiss()
                }
            }
        }
    }

    private fun observeMessage() {
        launchInViewScope {
            viewModel.message.collect { messageEvent ->
                requireActivity().showSnackMessage(
                    messageEvent.message,
                    R.color.black,
                    if (messageEvent is MessageEvent.Success) R.color.accent_success else R.color.accent_error,
                    Snackbar.LENGTH_SHORT
                )

            }
        }
    }

}
