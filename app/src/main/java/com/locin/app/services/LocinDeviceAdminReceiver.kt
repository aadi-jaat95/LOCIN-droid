package com.locin.app.services

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.Toast

class LocinDeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Toast.makeText(context, "LOCIN Device Admin enabled - Uninstall protection active", Toast.LENGTH_SHORT).show()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Toast.makeText(context, "LOCIN Device Admin disabled", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun isActive(context: Context): Boolean {
            val dpm = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            val componentName = ComponentName(context, LocinDeviceAdminReceiver::class.java)
            return dpm.isAdminActive(componentName)
        }

        fun requestActivation(context: Context) {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                val componentName = ComponentName(context, LocinDeviceAdminReceiver::class.java)
                putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
                putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "LOCIN needs device admin permission to prevent uninstall during active focus sessions. This helps you stay committed to your goals.")
            }
            context.startActivity(intent)
        }
    }
}
