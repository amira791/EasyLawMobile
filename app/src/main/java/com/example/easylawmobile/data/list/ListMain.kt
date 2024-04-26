



package com.siviwe.composeapp.data

import com.example.easylawmobile.R


data class Law (
    val id: Int,
    val name: String,
    val image: Int
)


object Laws {
    val MainLaw = listOf(
    Law (
    1,
    "الدستور الجزائري",
        R.drawable.law

    ),
        Law (
            1,
            "لاستشارات القضائية",
            R.drawable.law

        ),
        Law (
            1,
            "التحارة و الأعمال",
            R.drawable.law

        ),
        Law (
            1,
            "الجرائد الرسمية",
            R.drawable.law

        ),
        Law (
            1,
            "الدستور الجزائري",
            R.drawable.law

        ),
        Law (
            1,
            "الدستور الجزائري",
            R.drawable.law

        ),
        Law (
            1,
            "الدستور الجزائري",
            R.drawable.law

        )

    )

}

