package com.taonce.mvvm.util

import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.regex.Pattern


/**
 * Author: Taonce
 * Date: 2019/4/1
 * Desc: 字符串相关工具
 */

/**
 * 判断字符串是否都是数字
 */
fun String.isNumAll(): Boolean {
    val pattern: Pattern = Pattern.compile("[0-9]*")
    return pattern.matcher(this).matches()
}

/**
 * 验证字符串是否符合手机号规则
 */
fun String.isPhoneNum(): Boolean {
    val rule = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}\$"
    return (!this.isEmpty() && this.matches(Regex(rule)))
}

/**
 * 判断字符串是否都是字母
 */
fun String.isLetterAll(): Boolean {
    val pattern: Pattern = Pattern.compile("[a-z]*")
    return pattern.matcher(this).matches()
}

/**
 * 判断车牌号是否规则
 */
fun String.isCarNum(): Boolean =
    (!this.isEmpty()
            && this.matches(Regex("^[\u4e00-\u9fa5]{1}[A-H_J-N_P-Z]{1}[A-H_J-N_P-Z_0-9]{5,6}$")))

/**
 * 判断身份证号码是否规则
 */
fun String.isIdCard(): Boolean {
    val pattern: Pattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")
    return pattern.matcher(this).matches()
}

/**
 * 指定范围内的大写转小写
 * [startIndex] 开始的下标
 * [endIndex] 结束的下标，此下标对应的字母不转
 * @return ABCD [3,4] -> ABCd
 */
fun String.toLowerCase(
    startIndex: Int = 0,
    endIndex: Int = this.length
): String {
    val subString: String = this.substring(startIndex, endIndex)
    return if (0 <= startIndex && startIndex <= this.length
        && 0 <= endIndex && endIndex <= this.length
        && startIndex <= endIndex
        && !this.isEmpty()
    ) this.replaceFirst(
        subString,
        subString.toLowerCase(Locale.getDefault())
    ) else ""
}

/**
 * 指定范围内的小写转大写
 * [startIndex] 开始的下标
 * [endIndex] 结束的下标，此下标对应的字母不转
 * @return abcd [3,4] -> abcD
 */
fun String.toUpperCase(
    startIndex: Int = 0,
    endIndex: Int = this.length
): String {
    val subString: String = this.substring(startIndex, endIndex)
    return if (0 <= startIndex && startIndex <= this.length
        && 0 <= endIndex && endIndex <= this.length
        && startIndex <= endIndex
        && !this.isEmpty()
    ) this.replaceFirst(
        subString,
        subString.toLowerCase(Locale.getDefault())
    )
    else ""
}

/**
 * MD5加密
 */
fun String.md5(): String {
    return if (this.isEmpty()) ""
    else {
        try {
            val md5: MessageDigest = MessageDigest.getInstance("MD5")
            val bytes: ByteArray = md5.digest(this.toByteArray())
            return with(StringBuilder()) {
                bytes.forEach {
                    val temp: String = Integer.toHexString(it.toInt() and 0xff)
                    if (temp.length == 1) {
                        append("0").append(temp)
                    } else {
                        append(temp)
                    }
                }
                toString()
            }
        } catch (e: NoSuchAlgorithmException) {
            ""
        }
    }
}

/**
 * SHA-1加密
 */
fun String.sha1(): String {
    return if (this.isEmpty()) ""
    else {
        try {
            val md5: MessageDigest = MessageDigest.getInstance("SHA-1")
            val bytes: ByteArray = md5.digest(this.toByteArray())
            return with(StringBuilder()) {
                bytes.forEach {
                    val temp: String = Integer.toHexString(it.toInt() and 0xff)
                    if (temp.length == 1) {
                        append("0").append(temp)
                    } else {
                        append(temp)
                    }
                }
                toString()
            }
        } catch (e: NoSuchAlgorithmException) {
            ""
        }
    }
}

/**
 * SHA-256加密
 */
fun String.sha256(): String {
    return if (this.isEmpty()) ""
    else {
        try {
            val md5: MessageDigest = MessageDigest.getInstance("SHA-256")
            val bytes: ByteArray = md5.digest(this.toByteArray())
            return with(StringBuilder()) {
                bytes.forEach {
                    val temp: String = Integer.toHexString(it.toInt() and 0xff)
                    if (temp.length == 1) {
                        append("0").append(temp)
                    } else {
                        append(temp)
                    }
                }
                toString()
            }
        } catch (e: NoSuchAlgorithmException) {
            ""
        }
    }
}

