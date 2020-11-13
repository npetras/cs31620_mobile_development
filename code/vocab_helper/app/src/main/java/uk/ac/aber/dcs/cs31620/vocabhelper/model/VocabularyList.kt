package uk.ac.aber.dcs.cs31620.vocabhelper.model

/**
 *
 */
class VocabularyList {
    val list = mutableListOf<VocabularyItem>(
        VocabularyItem(
            "Hallo",
            "Hello",
            "Used as a greeting or to begin a phone conversation."
        ),
        VocabularyItem(
            "Servus",
            "Hello",
            """
                Used as a greeting or to begin a phone conversation, predominently in the region of 
                Bayern.
            """.trimIndent()
        ),
        VocabularyItem(
            "Guten Morgen",
            "Good morning",
            "Used as a greeting in the morning"
        )
    )
}
