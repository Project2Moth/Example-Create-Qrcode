import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.PDF417;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class QRCode {
    private static ImageIcon createQRCode(String key, int width, int height) {

        QRCodeWriter writer = new QRCodeWriter();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int white = 255 << 16 | 255 << 8 | 255;
        int black = 0;
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = writer.encode(key, BarcodeFormat.QR_CODE, width, height);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j< height; j++) {
                    image.setRGB(i, j, bitMatrix.get(i, j) ? black : white);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());
        JLabel jLabel = new JLabel();
        jLabel.setIcon(createQRCode("kenhlaptrinh.net",400,400));
        panel.add(jLabel);
        panel.setLayout(new CardLayout());
        JFrame frame = new JFrame();
        frame.setLayout(new CardLayout());
        frame.add(panel);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
