package com.akinpelumi.crop2cashkotlin.callback

import com.akinpelumi.crop2cashkotlin.model.ExhibitModel

interface ISingleExhibitSelectionCallback {
    fun onSelect(item: ExhibitModel?)
}