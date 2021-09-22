package com.example.pixeryapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    var ImageList = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region pro level coding here, dont look

        ImageList.add(R.drawable.p0)
        ImageList.add(R.drawable.p1)
        ImageList.add(R.drawable.p2)
        ImageList.add(R.drawable.p3)
        ImageList.add(R.drawable.p4)
        ImageList.add(R.drawable.p5)
        ImageList.add(R.drawable.p6)
        ImageList.add(R.drawable.p7)
        ImageList.add(R.drawable.p8)
        ImageList.add(R.drawable.p9)
        ImageList.add(R.drawable.p10)
        ImageList.add(R.drawable.p11)
        ImageList.add(R.drawable.p12)
        ImageList.add(R.drawable.p13)
        ImageList.add(R.drawable.p14)
        ImageList.add(R.drawable.p15)
        ImageList.add(R.drawable.p16)
        ImageList.add(R.drawable.p17)
        ImageList.add(R.drawable.p18)
        ImageList.add(R.drawable.p19)
        ImageList.add(R.drawable.p20)
        ImageList.add(R.drawable.p21)
        ImageList.add(R.drawable.p22)
        ImageList.add(R.drawable.p23)
        ImageList.add(R.drawable.p24)
        ImageList.add(R.drawable.p25)
        ImageList.add(R.drawable.p26)
        ImageList.add(R.drawable.p27)
        ImageList.add(R.drawable.p28)
        ImageList.add(R.drawable.p29)
        ImageList.add(R.drawable.p30)
        ImageList.add(R.drawable.p31)
        ImageList.add(R.drawable.p32)
        ImageList.add(R.drawable.p33)
        ImageList.add(R.drawable.p34)
        ImageList.add(R.drawable.p35)
        ImageList.add(R.drawable.p36)
        ImageList.add(R.drawable.p37)
        ImageList.add(R.drawable.p38)
        ImageList.add(R.drawable.p39)
        ImageList.add(R.drawable.p40)
        ImageList.add(R.drawable.p41)
        ImageList.add(R.drawable.p42)
        ImageList.add(R.drawable.p43)
        ImageList.add(R.drawable.p44)
        ImageList.add(R.drawable.p45)
        ImageList.add(R.drawable.p46)
        ImageList.add(R.drawable.p47)
        ImageList.add(R.drawable.p48)
        ImageList.add(R.drawable.p49)
        ImageList.add(R.drawable.p50)
        ImageList.add(R.drawable.p51)
        ImageList.add(R.drawable.p52)
        ImageList.add(R.drawable.p53)
        ImageList.add(R.drawable.p54)
        ImageList.add(R.drawable.p55)
        ImageList.add(R.drawable.p56)
        ImageList.add(R.drawable.p57)
        ImageList.add(R.drawable.p58)
        ImageList.add(R.drawable.p59)
        ImageList.add(R.drawable.p60)
        ImageList.add(R.drawable.p61)
        ImageList.add(R.drawable.p62)
        ImageList.add(R.drawable.p63)
        ImageList.add(R.drawable.p64)
        ImageList.add(R.drawable.p65)
        ImageList.add(R.drawable.p66)
        ImageList.add(R.drawable.p67)
        ImageList.add(R.drawable.p68)
        ImageList.add(R.drawable.p69)
        ImageList.add(R.drawable.p70)
        ImageList.add(R.drawable.p71)
        ImageList.add(R.drawable.p72)
        ImageList.add(R.drawable.p73)
        ImageList.add(R.drawable.p74)
        ImageList.add(R.drawable.p75)
        ImageList.add(R.drawable.p76)
        ImageList.add(R.drawable.p77)
        ImageList.add(R.drawable.p78)
        ImageList.add(R.drawable.p79)
        ImageList.add(R.drawable.p80)
        ImageList.add(R.drawable.p81)
        ImageList.add(R.drawable.p82)
        ImageList.add(R.drawable.p83)
        ImageList.add(R.drawable.p84)
        ImageList.add(R.drawable.p85)
        ImageList.add(R.drawable.p86)
        ImageList.add(R.drawable.p87)
        ImageList.add(R.drawable.p88)
        ImageList.add(R.drawable.p89)
        ImageList.add(R.drawable.p90)
        ImageList.add(R.drawable.p91)
        ImageList.add(R.drawable.p92)
        ImageList.add(R.drawable.p93)
        ImageList.add(R.drawable.p94)
        ImageList.add(R.drawable.p95)
        ImageList.add(R.drawable.p96)
        ImageList.add(R.drawable.p97)
        ImageList.add(R.drawable.p98)
        ImageList.add(R.drawable.p99)

        //endregion

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}