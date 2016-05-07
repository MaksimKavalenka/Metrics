package by.training.dragndrop;

import static by.training.constants.AppDefaultConstants.DATA_FLAVOR;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import by.training.ui.JIconPanel;

public class IconTransferable implements Transferable {

    private JIconPanel iconPanel;

    public IconTransferable(final JIconPanel iconPanel) {
        this.iconPanel = iconPanel;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] {DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(final DataFlavor flavor) {
        return flavor.equals(DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(final DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (flavor.equals(DATA_FLAVOR)) {
            return iconPanel;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

}
