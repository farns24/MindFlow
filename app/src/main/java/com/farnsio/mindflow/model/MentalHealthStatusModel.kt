package com.farnsio.mindflow.model

import java.time.LocalDateTime

class MentalHealthStatusModel(
    val patiance: Int,
    val energy: Int,
    val timestamp: LocalDateTime,
    val notes: String
)
