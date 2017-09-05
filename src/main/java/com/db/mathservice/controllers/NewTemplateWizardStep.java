package com.db.mathservice.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum NewTemplateWizardStep {
    CHOOSE_TYPE("choose_type.html"),
    CONFIGURATION_EDITOR("configuration_editor.html"),
    SAVE_OPTIONS("save_options.html");

    @Getter
    private final String html;
}
