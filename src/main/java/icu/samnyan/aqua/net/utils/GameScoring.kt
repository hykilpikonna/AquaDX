package icu.samnyan.aqua.net.utils


val mai2Scores = listOf(
    100.5 to "SSS+",
    100.0 to "SSS",
    99.5  to "SS+",
    99.0  to "SS",
    98.0  to "S+",
    97.0  to "S",
    94.0  to "AAA",
    90.0  to "AA",
    80.0  to "A",
    75.0  to "BBB",
    70.0  to "BB",
    60.0  to "B",
    50.0  to "C",
    0.0   to "D",
).map { (k, v) -> (k * 10000).toInt() to v }

val chu3Scores = listOf(
    100.75 to "SSS",
    100.0  to "SS",
    97.5   to "S",
    95.0   to "AAA",
    92.5   to "AA",
    90.0   to "A",
    80.0   to "BBB",
    70.0   to "BB",
    60.0   to "B",
    50.0   to "C",
    0.0    to "D",
).map { (k, v) -> (k * 10000).toInt() to v }