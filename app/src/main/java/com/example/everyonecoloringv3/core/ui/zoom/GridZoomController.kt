package com.example.everyonecoloringv3.core.ui.zoom

import android.content.Context
import android.view.MotionEvent
import android.view.ScaleGestureDetector

/**
 * Reusable pinch-zoom controller that tracks zoom scale and notifies listeners.
 * Call [onTouch] from your view's touch listener and react to [onZoomChanged].
 */
class GridZoomController(
    context: Context,
    private val minScale: Float = 0.5f,
    private val maxScale: Float = 2.0f,
    private val onZoomChanged: (Float) -> Unit
) {
    var zoomScale: Float = 1f
        private set

    var isScaling: Boolean = false
        private set

    private val detector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            isScaling = true
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            zoomScale = (zoomScale * detector.scaleFactor).coerceIn(minScale, maxScale)
            onZoomChanged(zoomScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {
            isScaling = false
        }
    })

    /**
     * Forward touch events here. Returns true if currently handling a pinch gesture.
     */
    fun onTouch(event: MotionEvent): Boolean {
        detector.onTouchEvent(event)
        return isScaling
    }
}
