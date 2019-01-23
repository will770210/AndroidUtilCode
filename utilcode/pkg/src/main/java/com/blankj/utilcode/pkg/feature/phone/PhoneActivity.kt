package com.blankj.utilcode.pkg.feature.phone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.lib.base.BaseBackActivity
import com.blankj.utilcode.pkg.R
import com.blankj.utilcode.pkg.helper.PermissionHelper
import com.blankj.utilcode.util.PhoneUtils
import com.blankj.utilcode.util.SpanUtils
import kotlinx.android.synthetic.main.activity_phone.*

/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/10/13
 * desc  : demo about PhoneUtils
 * ```
 */
class PhoneActivity : BaseBackActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, PhoneActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData(bundle: Bundle?) {

    }

    override fun bindLayout(): Int {
        return R.layout.activity_phone
    }

    override fun initView(savedInstanceState: Bundle?, contentView: View) {
        setTitle(R.string.demo_phone)

        dialBtn.setOnClickListener(this)
        callBtn.setOnClickListener(this)
        sendSmsBtn.setOnClickListener(this)
        sendSmsSilentBtn.setOnClickListener(this)

        PermissionHelper.requestPhone(
                {
                    SpanUtils.with(aboutPhoneTv)
                            .appendLine("isPhone: " + PhoneUtils.isPhone())
                            .appendLine("getDeviceId: " + PhoneUtils.getDeviceId())
                            .appendLine("getIMEI: " + PhoneUtils.getIMEI())
                            .appendLine("getMEID: " + PhoneUtils.getMEID())
                            .appendLine("getIMSI: " + PhoneUtils.getIMSI())
                            .appendLine("getPhoneType: " + PhoneUtils.getPhoneType())
                            .appendLine("isSimCardReady: " + PhoneUtils.isSimCardReady())
                            .appendLine("getSimOperatorName: " + PhoneUtils.getSimOperatorName())
                            .appendLine("getSimOperatorByMnc: " + PhoneUtils.getSimOperatorByMnc())
                            .appendLine("getPhoneStatus: " + PhoneUtils.getPhoneStatus())
                            .create()
                },
                {
                    SpanUtils.with(aboutPhoneTv)
                            .appendLine("isPhone: " + PhoneUtils.isPhone())
                            .appendLine("getDeviceId: " + "need permission")
                            .appendLine("getIMEI: " + "need permission")
                            .appendLine("getMEID: " + "need permission")
                            .appendLine("getIMSI: " + "need permission")
                            .appendLine("getPhoneType: " + PhoneUtils.getPhoneType())
                            .appendLine("isSimCardReady: " + PhoneUtils.isSimCardReady())
                            .appendLine("getSimOperatorName: " + PhoneUtils.getSimOperatorName())
                            .appendLine("getSimOperatorByMnc: " + PhoneUtils.getSimOperatorByMnc())
                            .appendLine("getPhoneStatus: " + "need permission")
                            .create()
                }
        )
    }

    override fun doBusiness() {

    }

    override fun onWidgetClick(view: View) {
        when (view.id) {
            R.id.dialBtn -> PhoneUtils.dial("10000")
            R.id.callBtn -> PermissionHelper.requestPhone { PhoneUtils.call("10000") }
            R.id.sendSmsBtn -> PhoneUtils.sendSms("10000", "sendSms")
            R.id.sendSmsSilentBtn -> PermissionHelper.requestSms { PhoneUtils.sendSmsSilent("10000", "sendSmsSilent") }
        }
    }
}