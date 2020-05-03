package com.jaspervanmerle.cglocal.util

import com.jaspervanmerle.cglocal.CGLocal
import com.jaspervanmerle.cglocal.controller.StatusController
import com.jaspervanmerle.cglocal.view.MainView
import mu.KotlinLogging
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.JPanel
import kotlin.math.min
import kotlin.math.round
import kotlin.reflect.KClass

val logger = KotlinLogging.logger {}

fun <T : JPanel> setCenter(newCenter: KClass<T>, parent: KClass<*> = MainView::class) {
    if (CGLocal.stopping) {
        return
    }

    logger.info("Placing ${newCenter.simpleName} in the center of ${parent.simpleName}")

    val parentPanel = koin.get(parent) as JPanel
    val newCenterPanel = koin.get(newCenter) as JPanel

    val parentLayout = parentPanel.layout as BorderLayout
    val currentCenterPanel = parentLayout.getLayoutComponent(BorderLayout.CENTER)
    if (currentCenterPanel != null) {
        parentPanel.remove(currentCenterPanel)
        parentPanel.revalidate()
        parentPanel.repaint()
    }

    parentPanel.add(newCenterPanel, BorderLayout.CENTER)
    parentPanel.revalidate()
    parentPanel.repaint()
}

fun setStatus(status: String) {
    if (CGLocal.stopping) {
        return
    }

    logger.info("Changing status to '$status'")
    koin.get<StatusController>().status = status
}

fun Color.manipulate(factor: Float): Color {
    val newRed = min(round(red * factor).toInt(), 255)
    val newGreen = min(round(green * factor).toInt(), 255)
    val newBlue = min(round(blue * factor).toInt(), 255)
    return Color(newRed, newGreen, newBlue, alpha)
}
