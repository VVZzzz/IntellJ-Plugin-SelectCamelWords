package com.wrh.dev.cr_tool;

import com.intellij.application.options.editor.EditorSmartKeysConfigurable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.ex.EditorSettingsExternalizable;

public class SelectCamelWordsAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("SelectCamelWordsAction.actionPerformed");

        // 获取编辑器设置
        EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();

        // 对之前的状态取反
        editorSettingsExternalizable.setCamelWords(!editorSettingsExternalizable.isCamelWords());

    }
}
