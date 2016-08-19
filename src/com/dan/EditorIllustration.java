package com.dan;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;


public class EditorIllustration extends AnAction {
    private static final Logger logger = Logger.getInstance(EditorIllustration.class);

    static {
        logger.info("Init Type-Less");
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        typedAction.setupHandler(new MyTypeHandler(charMap));

    }

    @Override
    public void actionPerformed(final AnActionEvent anActionEvent) {
//        //Get all the required data from data keys
//        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
//        final Project project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT);
//        //Access document, caret, and selection
//        final Document document = editor.getDocument();
//        final SelectionModel selectionModel = editor.getSelectionModel();
//
//        final int start = selectionModel.getSelectionStart();
//        final int end = selectionModel.getSelectionEnd();
//        //New instance of Runnable to make a replacement
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                document.replaceString(start, end, "Replacement");
//            }
//        };
//        //Making the replacement
//        WriteCommandAction.runWriteCommandAction(project, runnable);
//        selectionModel.removeSelection();
    }

    @Override
    public void update(final AnActionEvent e) {
//        //Get required data keys
//        final Project project = e.getData(CommonDataKeys.PROJECT);
//        final Editor editor = e.getData(CommonDataKeys.EDITOR);
//        //Set visibility only in case of existing project
//        // and editor and if some text in the editor is selected
//        e.getPresentation().setVisible((
//                project != null
//                        && editor != null
//                        && editor.getSelectionModel().hasSelection()));
    }
}
