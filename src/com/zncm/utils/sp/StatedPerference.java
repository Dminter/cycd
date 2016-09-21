package com.zncm.utils.sp;

import android.content.SharedPreferences;
import android.graphics.Color;

//对系统SharedPerference的封装
public class StatedPerference extends SharedPerferenceBase {

    private static final String STATE_PREFERENCE = "state_preference";
    private static SharedPreferences sharedPreferences;

    enum Key {
        default_disk_path, // 程序数据默认存储位置
        color_theme, // 主题
        temporary_disk_path, // 缓存路径
        note_tag, // 日记最后标签
        index_tag, // TAB最后位置
        tab_tag,
        city_tag, // 天气城市
        weather_info, // 天气信息
        backup_note_path, // 日记备份路径
        backup_checklist_path, // 清单备份路径
        backup_account_path, // 账户备份路径
        backup_all_path,//备份所有数据
        pwd_info, // 日记密码
        delete_show_dialog, // 删除是否显示对话框
        app_version_code, // 程序版本
        is_first, // 首次打开
        auto_location,// 自动定位
        install_time
    }

    public static void setAppVersionCode(Integer app_version_code) {
        putInt(getSharedPreferences(), Key.app_version_code.toString(), app_version_code);
    }

    public static Integer getAppVersionCode() {
        return getInt(getSharedPreferences(), Key.app_version_code.toString(), 0);
    }

    public static void setIsFirst(Boolean is_first) {
        putBoolean(getSharedPreferences(), Key.is_first.toString(), is_first);
    }

    public static Boolean getIsFirst() {
        return getBoolean(getSharedPreferences(), Key.is_first.toString(), true);
    }

    public static void setAutoLocation(Boolean auto_location) {
        putBoolean(getSharedPreferences(), Key.auto_location.toString(), auto_location);
    }

    public static Boolean getAutoLocation() {
        return getBoolean(getSharedPreferences(), Key.auto_location.toString(), false);
    }

    public static void setDeleteShowDialog(boolean delete_show_dialog) {
        putBoolean(getSharedPreferences(), Key.delete_show_dialog.toString(), delete_show_dialog);
    }

    public static boolean getDeleteShowDialog() {
        return getBoolean(getSharedPreferences(), Key.delete_show_dialog.toString(), true);
    }

    public static void setTemporaryDiskPath(String path) {
        putString(getSharedPreferences(), Key.temporary_disk_path.toString(), path);
    }

    public static String getTemporaryDiskPath() {
        return getString(getSharedPreferences(), Key.temporary_disk_path.toString(), "");
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null)
            sharedPreferences = getPreferences(STATE_PREFERENCE);
        return sharedPreferences;
    }

    public static void setDefaultDiskPath(String path) {
        putString(getSharedPreferences(), Key.default_disk_path.toString(), path);
    }

    public static String getDefaultDiskPath() {
        return getString(getSharedPreferences(), Key.default_disk_path.toString(), "");
    }

    public static void setNoteTag(String note_tag) {
        putString(getSharedPreferences(), Key.note_tag.toString(), note_tag);
    }

    public static String getNoteTag() {
        return getString(getSharedPreferences(), Key.note_tag.toString(), "其他");
    }

    public static void setCityTag(String city_tag) {
        putString(getSharedPreferences(), Key.city_tag.toString(), city_tag);
    }

    public static String getCityTag() {
        return getString(getSharedPreferences(), Key.city_tag.toString(), "101040100");
    }

    public static void setWeatherInfo(String weather_info) {
        putString(getSharedPreferences(), Key.weather_info.toString(), weather_info);
    }

    public static String getWeatherInfo() {
        return getString(getSharedPreferences(), Key.weather_info.toString(), "");
    }

    public static void setBackupNotePath(String backup_checklist_path) {
        putString(getSharedPreferences(), Key.backup_checklist_path.toString(), backup_checklist_path);
    }

    public static String getBackupNotePath() {
        return getString(getSharedPreferences(), Key.backup_checklist_path.toString(), "");
    }

    public static void setBackupCheckListPath(String backup_note_path) {
        putString(getSharedPreferences(), Key.backup_note_path.toString(), backup_note_path);
    }

    public static String getBackupCheckListPath() {
        return getString(getSharedPreferences(), Key.backup_note_path.toString(), "");
    }

    public static void setBackupAccountPath(String backup_account_path) {
        putString(getSharedPreferences(), Key.backup_account_path.toString(), backup_account_path);
    }

    public static String getBackupAccountPath() {
        return getString(getSharedPreferences(), Key.backup_account_path.toString(), "");
    }

    public static void setBackupAllPath(String backup_all_path) {
        putString(getSharedPreferences(), Key.backup_all_path.toString(), backup_all_path);
    }

    public static String getBackupAllPath() {
        return getString(getSharedPreferences(), Key.backup_all_path.toString(), "");
    }

    public static void setPwdInfo(String pwd_info) {
        putString(getSharedPreferences(), Key.pwd_info.toString(), pwd_info);
    }

    public static String getPwdInfo() {
        return getString(getSharedPreferences(), Key.pwd_info.toString(), "");
    }

    public static void setTabTag(String tab_tag) {
        putString(getSharedPreferences(), Key.tab_tag.toString(), tab_tag);
    }

    public static String getTabTag() {
        return getString(getSharedPreferences(), Key.tab_tag.toString(), "");
    }

    public static void setIndexTag(Integer index_tag) {
        putInt(getSharedPreferences(), Key.index_tag.toString(), index_tag);
    }

    public static Integer getIndexTag() {
        return getInt(getSharedPreferences(), Key.index_tag.toString(), 0);
    }

    public static void setColorTheme(Integer color_theme) {
        putInt(getSharedPreferences(), Key.color_theme.toString(), color_theme);
    }

    public static Integer getColorTheme() {
        return getInt(getSharedPreferences(), Key.color_theme.toString(), Color.RED);
    }


    public static void setInstallTime(Long install_time) {
        putLong(getSharedPreferences(), Key.install_time.toString(), install_time);
    }

    public static Long getInstallTime() {
        return getLong(getSharedPreferences(), Key.install_time.toString(), 0l);
    }

}
