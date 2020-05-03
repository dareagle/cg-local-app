package com.jaspervanmerle.cglocal.view

import com.jaspervanmerle.cglocal.Constants
import com.jaspervanmerle.cglocal.controller.StatusController
import com.jaspervanmerle.cglocal.util.koin
import java.awt.Dimension
import java.awt.Font

class StatusView : View("align center") {
    private val controller: StatusController by koin.inject()

    init {
        preferredSize = Dimension(Constants.WIDTH, 30)
        background = Constants.BLACK

        label(controller.statusProperty) {
            foreground = Constants.WHITE
            font = font.deriveFont(Font.PLAIN, 14f)
        }
    }
}
