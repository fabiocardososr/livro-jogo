package livro.jogo.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private static final Component OBSERVER = new Component() {

        private static final long serialVersionUID = 1;
    };

    private static final long serialVersionUID = 1;

    public static BufferedImage bufferize(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        try {
            MediaTracker tracker = new MediaTracker(OBSERVER);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
            tracker.removeImage(img, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedImage buffered = new BufferedImage(img.getWidth(OBSERVER), img.getHeight(OBSERVER), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = buffered.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();
        return buffered;
    }

    private boolean stretched = true;

    private BufferedImage image;

    public ImagePanel(String enderecoImagem) {
        super();
        setBackground(Color.BLACK);
        //BufferedImage imagem;
        try {
            InputStream arquivo =  new FileInputStream(enderecoImagem);
            setImage(ImageIO.read(arquivo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImagePanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public ImagePanel(LayoutManager layout) {
        super(layout);
    }

    public ImagePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isSideBySide() {
        return !stretched;
    }

    public boolean isStretched() {
        return stretched;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setImage(Image image) {
        setImage(bufferize(image));
    }

    public void setSideBySide(boolean sideBySide) {
        stretched = !sideBySide;
    }

    public void setStretched(boolean stretch) {
        this.stretched = stretch;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            super.paintComponent(g);
            return;
        }
        int w = getWidth();
        int h = getHeight();
        if (stretched) {
            g.drawImage(image, 0, 0, w, h, this);
            return;
        }
        int iw = image.getWidth();
        int ih = image.getHeight();
        int colunas = w / iw;
        int linhas = h / ih;
        if (colunas * iw < w) {
            colunas++;
        }
        if (linhas * ih < h) {
            linhas++;
        }
        int offsetX = 0;
        for (int i = 0; i < linhas; i++) {
            int y = i * ih;
            if (y > h) {
                break;
            }
            for (int j = 0; j < colunas; j++) {
                int x = j * iw + offsetX;
                if (j == 0 && x > 0) {
                    g.drawImage(image, -(iw - x), y, iw, ih, this);
                }
                if (j == colunas - 1 && x < w) {
                    g.drawImage(image, x + iw, y, iw, ih, this);
                }
                if (x > w) {
                    break;
                }
                if (x < -iw) {
                    break;
                }
                g.drawImage(image, x, y, iw, ih, this);
            }
        }
    }
}