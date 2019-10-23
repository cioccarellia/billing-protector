@file:Suppress("ClassName")

package com.andreacioccarelli.billingprotector.assets

/**
 * Designed and Developed by Andrea Cioccarelli
 */

object definitions {

    private const val lucky_patcher = "Lucky Patcher"
    private const val freedom = "Freedom"
    private const val xmodgames = "XModGames"
    private const val uret_patcher = "Uret Patcher"
    private const val creeplays_patcher = "Creeplays Patcher"
    private const val creehack = "Creehack"
    private const val playcards = "Leo Playcards"
    private const val appsara = "AppSara"
    private const val modded_store = "Modded Google Play Store"


    object match {
        /**
         * Lucky Patcher
         */
        const val field1 =      "com.chelpus.lackypatch"
        const val khash1 =      "419cb6875f3fa235ed925c28afc44f2e"
        const val kname1 =      lucky_patcher

        const val field2 =      "com.dimonvideo.luckypatcher"
        const val khash2 =      "cd9de8549c3e6e151300734f112091b3"
        const val kname2 =      lucky_patcher

        const val field3 =      "com.forpda.lp"
        const val khash3 =      "c2f5ef59294b96727c061ecc0a792392"
        const val kname3 =      lucky_patcher

        const val field4 =      "com.android.vending.licensing.ILicensingService"
        const val khash4 =      "238b2cc715459f7b9458e0e0744b15e2"
        const val kname4 =      lucky_patcher

        /**
         * Uret patcher
         * */
        const val field6 =      "uret.jasi2169.patcher"
        const val khash6 =      "7a3a1cdb08b74117b3c20f2e04ba099e"
        const val kname6 =      uret_patcher

        /**
         * Creeplays Patcher
         * */
        const val field7 =      "org.creeplays.hack"
        const val khash7 =      "b30bec898f00f528e382b95856ebb909"
        const val kname7 =      creeplays_patcher

        /**
         * CreeHack
         * */
        const val field8 =      "apps.zhasik007.hack"
        const val khash8 =      "48e3b21aec993834ca0a91f48a7099c6"
        const val kname8 =      creehack

        /**
         * Leo Playcards
         * */
        const val field9 =      "com.leo.playcard"
        const val khash9 =      "9d8a3405cd058e411c224fb680fa103c"
        const val kname9 =      playcards

        /**
         * AppSara
         * */
        const val field10 =     "com.appsara.app"
        const val khash10 =     "a00e921d238bb1b84d104e293313d225"
        const val kname10 =     appsara
    }


    object slice {
        /**
         * Lucky Patcher
         */
        const val field1 =      "com.android.vending.billing.InAppBillingService."
        const val khash1 =      "48b19168f8f466638c400c2e8eb8eebe"
        const val kname1 =      lucky_patcher

        const val field2 =      "com.android.vending.billing.InAppBillingService."
        const val khash2 =      "48b19168f8f466638c400c2e8eb8eebe"
        const val kname2 =      lucky_patcher

        /**
         * Freedom
         * */
        const val field3 =      "jase.freedom"
        const val khash3 =      "fde5230b767d93fbc23010f9de757f04"
        const val kname3 =      freedom

        const val field4 =      "madkite.freedom"
        const val khash4 =      "771df92f8cc82ad893bb3378a155338d"
        const val kname4 =      freedom

        /**
         * Modded Play Store
         * */
        const val field5 =      "com.android.vendinc"
        const val khash5 =      "81907933708408ddc91af1f381e65bff"
        const val kname5 =      modded_store

        /**
         * XModGames
         * */
        const val field6 =      "com.xmodgame"
        const val khash6 =      "00d0c2bf72590fd9281bb0d60a408092"
        const val kname6 =      xmodgames
    }

    object classname {
        /**
         * Lucky Patcher
         * */
        const val field1 =      "com.lp"
        const val khash1 =      "38d21e850edd2a6c6253918770a59b27"
        const val kname1 =      lucky_patcher

    }

    object regexp {
        /**
         * Lucky Patcher
         * */
        private const val l = "LІＬ"
        private const val u = "uцџｕ"
        private const val c = "cсçｃ"
        private const val k = "kкќӄｋ"
        private const val y = "yуӳӱӰӯӮУўｙ"
        private const val space = """+\-*\/.#@__\`"${'$'} ~    　"""
        private const val p = "PРҎҏрＰｐ"
        private const val a = "aаӑӓａ"
        private const val t = "tтｔ"
        private const val h = "hнһӈҺｈ"
        private const val e = "eєёеҽзэҿｅ"
        private const val r = "rяｒ"
        private const val s = "sѕꜱ"


        const val field1 =      """-*[$l][$u][$c][$k][$y][$space][$p][$a][$t][$c][$h][$e][$r][$s]?-*"""
        const val khash1 =      "3b06b73e54dddf47fe5b20833f94a2c8"
        const val kname1 =      lucky_patcher


        /**
         * Lucky Patcher
         * */
        const val field2 =      """ru\..?.?[a]{3,}.?.?.?.?.?\.installer"""
        const val khash2 =      "bbf8435e91dd3c17b741c2aa4b15d763"
        const val kname2 =      lucky_patcher
    }

}