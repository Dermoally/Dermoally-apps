package com.polije.dermoally_apps.utils

import com.polije.dermoally_apps.data.history.History

object DummyHistoryProvider {
    fun getDummyHistories(): List<History> {
        return listOf(
            History(
                photoUrl = "https://via.placeholder.com/100",
                createdAt = "6 June 2024",
                disease = "Acne",
                accuracy = "Accuracy: 95%",
                id = "1"
            ),
            History(
                photoUrl = "https://via.placeholder.com/100",
                createdAt = "7 June 2024",
                disease = "Eczema",
                accuracy = "Accuracy: 90%",
                id = "2"
            ),
            History(
                photoUrl = "https://via.placeholder.com/100",
                createdAt = "8 June 2024",
                disease = "Psoriasis",
                accuracy = "Accuracy: 85%",
                id = "3"
            )
        )
    }
}