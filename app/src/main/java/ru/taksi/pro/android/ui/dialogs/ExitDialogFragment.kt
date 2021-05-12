package ru.taksi.pro.android.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.ui.view.IExitView

class ExitDialogFragment(private val view: IExitView) : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.exit_dialog))
                .setPositiveButton(getString(R.string.Yes)) { dialog, id ->
                    view.onExit()
                    dialog.cancel()
                }
                .setNegativeButton(getString(R.string.No)) { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}