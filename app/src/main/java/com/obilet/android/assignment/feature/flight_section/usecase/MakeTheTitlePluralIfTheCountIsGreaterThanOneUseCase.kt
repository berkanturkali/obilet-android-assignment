package com.obilet.android.assignment.feature.flight_section.usecase

import javax.inject.Inject

class MakeTheTitlePluralIfTheCountIsGreaterThanOneUseCase @Inject constructor() {

    companion object {
        private const val PLURAL_SUFFIX = "s"
        private const val PLURAL_FORM_OF_CHILD = "Children"
        private const val PLURAL_SUFFIX_THE_WORDS_THAT_ENDS_WITH_Y = "ies"
        private const val CHILD = "Child"
    }

    /*
     * I know that I could have done this by using the plural string resources but
     * I just wanted to make it using this way
     */
    operator fun invoke(count: Int, title: String): String {
        return if (count > 1) {
            if (title.lowercase() == CHILD.lowercase()) {
                PLURAL_FORM_OF_CHILD
            } else if (title.endsWith("y")) {
                title.substring(0, title.length - 1) + PLURAL_SUFFIX_THE_WORDS_THAT_ENDS_WITH_Y
            } else {
                title + PLURAL_SUFFIX
            }
        } else {
            title
        }
    }
}