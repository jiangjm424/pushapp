package cn.jm.happy.ipush.vendor

//极光sdk中对应的rom编号
//            0 to "jpush"
//            1 to "xiaomi"
//            2 to "huawei"
//            3 to "meizu"
//            4 to "oppo"
//            5 to "vivo"
//            8 to "fcm"
sealed class Vendor(val platform: Byte, val name: String) {
    object Jpush : Vendor(0, "jpush")
    object XiaoMi : Vendor(1, "xiaomi")
    object HuaWei : Vendor(2, "huawei")
    object MeiZu : Vendor(3, "meizu")
    object Oppo : Vendor(4, "oppo")
    object Vivo : Vendor(5, "vivo")
    object Fcm : Vendor(8, "fcm")


    companion object {
        fun mapVendor(romType: Int): Vendor {
            return when (romType) {
                1 -> XiaoMi
                2 -> HuaWei
                4 -> Oppo
                5 -> Vivo
                else -> Jpush
            }
        }
    }
}
