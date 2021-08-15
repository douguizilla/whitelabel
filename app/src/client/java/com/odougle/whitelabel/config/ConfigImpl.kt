package com.odougle.whitelabel.config

import javax.inject.Inject

class ConfigImpl @Inject constructor() : Config {
    override val addButtonVisibility: Boolean = false
}