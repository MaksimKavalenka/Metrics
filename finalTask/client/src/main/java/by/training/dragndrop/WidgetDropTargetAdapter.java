package by.training.dragndrop;

import static by.training.constants.AppDefaultConstants.DATA_FLAVOR;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;

import by.training.ui.JIconPanel;
import by.training.ui.JWidgetPanel;

public class WidgetDropTargetAdapter extends DropTargetAdapter implements DropTargetListener {

    private JWidgetPanel widgetPanel;

    public WidgetDropTargetAdapter(final JWidgetPanel widgetPanel) {
        this.widgetPanel = widgetPanel;
        new DropTarget(widgetPanel, DnDConstants.ACTION_COPY, this, true, null);
    }

    @Override
    public void drop(final DropTargetDropEvent event) {
        try {
            Transferable transferable = event.getTransferable();

            if (event.isDataFlavorSupported(DATA_FLAVOR)) {
                JIconPanel iconPanel = (JIconPanel) transferable.getTransferData(DATA_FLAVOR);
                event.acceptDrop(DnDConstants.ACTION_COPY);
                widgetPanel.getDependency().setIconPanel(iconPanel);
                event.dropComplete(true);
                return;
            }

            event.rejectDrop();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
