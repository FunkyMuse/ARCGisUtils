package com.crazylegend.arcgisextensions.abstracts

import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.crazylegend.arcgisextensions.addGraphicsOverlay
import com.crazylegend.arcgisextensions.addOnTouchListener
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.MapView


/**
 * Created by crazy on 11/19/19 to long live and prosper !
 */
abstract class AbstractMapFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    abstract val mapView: MapView
    var localMap: ArcGISMap? = null
    var graphicsOverlay: GraphicsOverlay? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.addOnTouchListener(requireContext()) { screenPoint, locationPoint ->
            handleMapTouch(screenPoint, locationPoint)
        }
        loadMapPackage()
        createGraphicsOverlay()
    }

    abstract fun loadMapPackage()

    abstract fun handleMapTouch(screenPoint: Point, locationPoint: com.esri.arcgisruntime.geometry.Point)

    private fun createGraphicsOverlay() {
        graphicsOverlay = GraphicsOverlay()
        mapView.addGraphicsOverlay(graphicsOverlay)
    }


    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.dispose()
    }

}