package by.training.dragndrop;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import by.training.ui.JIconPanel;

public class IconDragGestureListener implements DragGestureListener {

    @Override
    public void dragGestureRecognized(final DragGestureEvent event) {
        Cursor cursor = null;
        JIconPanel iconPanel = (JIconPanel) event.getComponent();

        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }

        event.startDrag(cursor, new IconTransferable(iconPanel));
    }

}
