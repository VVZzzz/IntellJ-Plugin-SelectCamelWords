package com.wrh.dev.cr_tool;

import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.editor.ex.EditorSettingsExternalizable;
import com.intellij.openapi.wm.IdeFrame;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MyKeyListener implements ApplicationActivationListener {
    private final KeyEventDispatcher dispatcher = new MyKeyEventDispatcher();

    @Override
    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
    }

    @Override
    public void applicationDeactivated(@NotNull IdeFrame ideFrame) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
    }

    private static class MyKeyEventDispatcher implements KeyEventDispatcher {
        // 分发器逻辑
        private final EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();
        private boolean altPressed = false;
        private boolean commandPressed = false;
        private boolean leftOrRightPressed = false;
        private boolean isCombinationTriggered = false;

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            // 1. 处理按键按下事件
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                updateKeyState(e, true);

                // 检测组合键：Option + Command + 左/右箭头
                if (altPressed && commandPressed /*&& leftOrRightPressed*/) {
                    if (!isCombinationTriggered) {
                        isCombinationTriggered = true;
                        //executeBeforeAction(e); // 执行自定义前置逻辑
                        System.out.println("isCombinationTriggered");
                        editorSettingsExternalizable.setCamelWords(true);
                    }
                    // 放行事件，让系统处理默认的“选择单词”功能
                    return false;
                }
            }

            // 2. 处理按键释放事件
            else if (e.getID() == KeyEvent.KEY_RELEASED) {
                updateKeyState(e, false);

                // 当 Command 键释放且组合键曾触发时，执行后置逻辑
                if (!commandPressed && isCombinationTriggered) {
                    isCombinationTriggered = false;
                    //executeAfterAction(e); // 执行自定义后置逻辑
                    editorSettingsExternalizable.setCamelWords(false);
                    System.out.println("releaseCombinationTriggered");
                }
            }
            return false;
        }

        // 更新按键状态
        private void updateKeyState(KeyEvent e, boolean isPressed) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_ALT) altPressed = isPressed;
            if (keyCode == KeyEvent.VK_TAB) commandPressed = isPressed;
            if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                leftOrRightPressed = isPressed;
            }
        }
    }

}
