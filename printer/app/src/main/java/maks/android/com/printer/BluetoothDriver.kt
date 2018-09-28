package maks.android.com.printer

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import java.io.IOException
import java.io.OutputStream
import java.util.*

class BluetoothDriver( private val mAdapter : BluetoothAdapter) {
    private val binaryArray = arrayOf(
            "0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011",
            "1100", "1101", "1110", "1111")

    private val hexString = "0123456789ABCDEF"

    private var mDevice: BluetoothDevice? = null
    private var mSocket: BluetoothSocket? = null
    private var mOutputStream: OutputStream? = null

    fun connect(deviceName: String) {
        if (BluetoothAdapter.getDefaultAdapter() == null) { } else {
            findDeviceByName(deviceName)
            connectToDevice()
        }
    }

    private fun findDeviceByName(name: String) {
        try {
            val pairedDevices = mAdapter.bondedDevices
            if (pairedDevices.size > 0) {
                for (device in pairedDevices) {
                    if (device.name == name) {
                        mDevice = device
                        break
                    }
                }
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun connectToDevice() {
        try {
            val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
            mSocket = mDevice!!.createRfcommSocketToServiceRecord(uuid)
            mSocket!!.connect()
            mOutputStream = mSocket!!.outputStream

        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun print(data: ByteArray) {
        try {
            mOutputStream!!.write(data)
            mOutputStream!!.flush()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: IOException) {
            mOutputStream!!.flush()
            e.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            mOutputStream!!.close()
            mSocket!!.close()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun decodeBitmap(bm : Bitmap):ByteArray?{

        val bmCompressed = compressPic(bm)

        val bmpWidth = bmCompressed.width
        val bmpHeight = bmCompressed.height

        val list = ArrayList<String>() //binaryString list
        var sb: StringBuffer

        var bitLen = bmpWidth / 8
        val zeroCount = bmpWidth % 8

        var zeroStr = ""
        if (zeroCount > 0) {
            bitLen = bmpWidth / 8 + 1
            for (i in 0 until 8 - zeroCount) {
                zeroStr += "0"
            }
        }

        for (i in 0 until bmpHeight) {
            sb = StringBuffer()
            for (j in 0 until bmpWidth) {
                val color = bmCompressed.getPixel(j, i)

                val r = color shr 16 and 0xff
                val g = color shr 8 and 0xff
                val b = color and 0xff

                // if color close to white，bit='0', else bit='1'
                if (r > 160 && g > 160 && b > 160) {
                    sb.append("0")
                }
                else {
                    sb.append("1")
                }
            }
            if (zeroCount > 0) {
                sb.append(zeroStr)
            }
            list.add(sb.toString())
        }

        val bmpHexList = binaryListToHexStringList(list)
        val commandHexString = "1D763000"
        var widthHexString = Integer
                .toHexString(if (bmpWidth % 8 == 0)
                    bmpWidth / 8
                else
                    bmpWidth / 8 + 1)
//        if (widthHexString.length > 2) {  //WIDTH SIZE
//            Log.e("decodeBitmap error", " width is too large")
//            return null
//        } else if (widthHexString.length == 1) {   // WIDTH SIZE
//            widthHexString = "0$widthHexString"
//        }
        widthHexString += "00"

        var heightHexString = Integer.toHexString(bmpHeight)
//        if (heightHexString.length > 4) {    //HEIGHT SIZE
//            Log.e("decodeBitmap error", " height is too large")
//            return null
//        } else if (heightHexString.length == 2) {   // HEIGHT SIZE
//            heightHexString = "0$heightHexString"
//        }
        heightHexString += "00"

        val commandList = ArrayList<String>()
        commandList.add(commandHexString + widthHexString + heightHexString)
        commandList.addAll(bmpHexList)

        return hexListToByte(commandList)
    }

    private fun binaryListToHexStringList(list: List<String>): List<String> {
        val hexList = ArrayList<String>()
        for (binaryStr in list) {
            val sb = StringBuffer()
            var i = 0
            while (i < binaryStr.length) {
                val str = binaryStr.substring(i, i + 8)

                val hexString = binaryStrToHexString(str)
                sb.append(hexString)
                i += 8
            }
            hexList.add(sb.toString())
        }
        return hexList
    }

    private fun binaryStrToHexString(binaryStr: String): String {
        var hex = ""
        val f4 = binaryStr.substring(0, 4)
        val b4 = binaryStr.substring(4, 8)
        for (i in 0 until binaryArray.size) {
            if (f4 == binaryArray[i])
                hex += hexString.substring(i, i + 1)
        }
        for (i in 0 until binaryArray.size) {
            if (b4 == binaryArray[i])
                hex += hexString.substring(i, i + 1)
        }
        return hex
    }

    private fun hexListToByte(list: List<String>): ByteArray {
        val commandList = ArrayList<ByteArray>()
        for (hexStr in list) {
            commandList.add(hexStringToBytes(hexStr)!!)
        }
        return copy(commandList)
    }

    private fun hexStringToBytes(hexString: String?): ByteArray? {
        var hexStr = hexString
        if (hexStr == null || hexStr == "") {
            return null
        }
        hexStr = hexStr.toUpperCase()
        val length = hexStr.length / 2
        val hexChars = hexStr.toCharArray()
        val d = ByteArray(length)
        for (i in 0 until length) {
            val pos = i * 2
            d[i] = (charConvertToByte(hexChars[pos]).toInt() shl 4 or charConvertToByte(hexChars[pos +
                    1]).toInt()).toByte()
        }
        return d
    }

    private fun copy(srcArrays: List<ByteArray>): ByteArray {
        var len = 0
        for (srcArray in srcArrays) {
            len += srcArray.size
        }
        val destArray = ByteArray(len)
        var destLen = 0
        for (srcArray in srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.size)
            destLen += srcArray.size
        }
        return destArray
    }

    private fun charConvertToByte(c: Char): Byte {
        return "0123456789ABCDEF".indexOf(c).toByte()
    }

//    ******************************************

    fun compressPic(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val newWidth = 240
        val newHeight = 240
        val targetBmp = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)
        val targetCanvas = Canvas(targetBmp)
        targetCanvas.drawColor(-0x1)
        targetCanvas.drawBitmap(bitmap, Rect(0, 0, width, height), Rect(0, 0, newWidth, newHeight), null)
        return targetBmp
    }

    fun px2Byte(x: Int, y: Int, bit: Bitmap): Byte {
        if (x < bit.width && y < bit.height) {
            val b: Byte
            val pixel = bit.getPixel(x, y)
            val red = pixel and 0x00ff0000 shr 16
            val green = pixel and 0x0000ff00 shr 8
            val blue = pixel and 0x000000ff
            val gray = RGB2Gray(red, green, blue)
            if (gray < 128) {
                b = 1
            } else {
                b = 0
            }
            return b
        }
        return 0
    }


    private fun RGB2Gray(r: Int, g: Int, b: Int): Int {
        return (0.29900 * r + 0.58700 * g + 0.11400 * b).toInt()
    }

    fun draw2PxPoint(bmp: Bitmap): ByteArray {
        val size = bmp.width * bmp.height / 8 + 1000
        val data = ByteArray(size)
        var k = 0
        data[k++] = 0x1B
        data[k++] = 0x33
        data[k++] = 0x00
        var j = 0
        while (j < bmp.height / 24f) {
            data[k++] = 0x1B
            data[k++] = 0x2A
            data[k++] = 33
            data[k++] = (bmp.width % 256).toByte() //nL
            data[k++] = (bmp.width / 256).toByte() //nH
            for (i in 0 until bmp.width) {
                for (m in 0..2) {
                    for (n in 0..7) {
                        val b = px2Byte(i, j * 24 + m * 8 + n, bmp)
                        data[k] = (data[k] + (data[k] + b)).toByte()
                    }
                    k++
                }
            }
            data[k++] = 10
            j++
        }
        return data
    }


}