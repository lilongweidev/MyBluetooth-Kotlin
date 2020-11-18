package com.llw.bluetooth.adapter

import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothClass.Device.*
import android.bluetooth.BluetoothClass.Device.Major.*
import android.bluetooth.BluetoothDevice
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.llw.bluetooth.R

/**
 * 蓝牙设备适配器
 */
class DeviceAdapter(layoutResId: Int, data: MutableList<BluetoothDevice>?) :
    BaseQuickAdapter<BluetoothDevice, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: BluetoothDevice?) {
        val tvName = helper!!.getView<TextView>(R.id.tv_name)
        val icon = helper.getView<ImageView>(R.id.iv_device_type)

        //根据设备类型设置图标
        getDeviceType(item!!.bluetoothClass.majorDeviceClass, icon)

        tvName.text = if (item.name == null) "无名" else item.name

        //蓝牙设备绑定状态判断
        val tvState = helper!!.getView<TextView>(R.id.tv_bond_state)
        tvState.text = when (item.bondState) {
            10 -> "未配对"
            11 -> "正在配对..."
            12 -> "已配对"
            else -> "未配对"
        }
        //添加item点击事件
        helper.addOnClickListener(R.id.item_device)

    }

    /**
     * 根据类型设置图标
     * @param type 类型码
     * @param icon 图标
     */
    private fun getDeviceType(type: Int, icon: ImageView) {
        when (type) {
            AUDIO_VIDEO_HEADPHONES,//耳机
            AUDIO_VIDEO_WEARABLE_HEADSET,//穿戴式耳机
            AUDIO_VIDEO_HANDSFREE,//蓝牙耳机
            AUDIO_VIDEO //音频设备
            -> icon.setImageResource(R.mipmap.icon_headset)
            COMPUTER //电脑
            -> icon.setImageResource(R.mipmap.icon_computer)
            PHONE //手机
            -> icon.setImageResource(R.mipmap.icon_phone)
            HEALTH //健康类设备
            -> icon.setImageResource(R.mipmap.icon_health)
            AUDIO_VIDEO_CAMCORDER, //照相机录像机
            AUDIO_VIDEO_VCR //录像机
            -> icon.setImageResource(R.mipmap.icon_vcr)
            AUDIO_VIDEO_CAR_AUDIO //车载设备
            -> icon.setImageResource(R.mipmap.icon_car)
            AUDIO_VIDEO_LOUDSPEAKER //扬声器
            -> icon.setImageResource(R.mipmap.icon_loudspeaker)
            AUDIO_VIDEO_MICROPHONE //麦克风
            -> icon.setImageResource(R.mipmap.icon_microphone)
            AUDIO_VIDEO_PORTABLE_AUDIO //打印机
            -> icon.setImageResource(R.mipmap.icon_printer)
            AUDIO_VIDEO_SET_TOP_BOX //音频视频机顶盒
            -> icon.setImageResource(R.mipmap.icon_top_box)
            AUDIO_VIDEO_VIDEO_CONFERENCING //音频视频视频会议
            -> icon.setImageResource(R.mipmap.icon_meeting)
            AUDIO_VIDEO_VIDEO_DISPLAY_AND_LOUDSPEAKER //显示器和扬声器
            -> icon.setImageResource(R.mipmap.icon_tv)
            AUDIO_VIDEO_VIDEO_GAMING_TOY //游戏
            -> icon.setImageResource(R.mipmap.icon_game)
            AUDIO_VIDEO_VIDEO_MONITOR //可穿戴设备
            -> icon.setImageResource(R.mipmap.icon_wearable_devices)
            else -> icon.setImageResource(R.mipmap.icon_bluetooth)
        }
    }

    /**
     * 刷新适配器
     */
    fun changeBondDevice() {
        notifyDataSetChanged()
    }
}