package ru.taksi.pro.android.mvvm.model.entity.address

import com.google.gson.annotations.Expose

data class Suggestions(
    @Expose val suggestions: List<Suggestion>
)