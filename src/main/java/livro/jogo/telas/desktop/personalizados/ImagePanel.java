package livro.jogo.telas.desktop.personalizados;

import livro.jogo.enums.ImagensDoLivroFlorestaDaDestruicao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private static final Component OBSERVER = new Component() {
        private static final long serialVersionUID = 1L;
    };

    private static final long serialVersionUID = 1L;

    private boolean stretched = true;
    private BufferedImage image;

    public ImagePanel(ImagensDoLivroFlorestaDaDestruicao enderecoImagem) {
        super();
        setBackground(Color.BLACK);
        try (InputStream imgStream = ImagePanel.class.getClassLoader().getResourceAsStream(enderecoImagem.getEnderecoImagem())) {
            if (imgStream == null) {
                throw new RuntimeException("Imagem não encontrada: " + enderecoImagem.getEnderecoImagem());
            }
            setImage(loadAndResizeImage(imgStream, 800, 600));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImagePanel(String enderecoImagem) {
        super();
        setBackground(Color.BLACK);
        try (InputStream imgStream = ImagePanel.class.getClassLoader().getResourceAsStream(enderecoImagem)) {
            if (imgStream == null) {
                throw new RuntimeException("Imagem não encontrada: " + enderecoImagem);
            }
            setImage(loadAndResizeImage(imgStream, 800, 600));
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

    public void setImage(BufferedImage image) {
        if (this.image != null) {
            this.image.flush(); // libera recursos da imagem anterior
        }
        this.image = image;
        repaint();
    }

    public void setImage(Image image) {
        setImage(bufferize(image));
    }

    // Método para converter Image em BufferedImage
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

    // Novo método para carregar e redimensionar imagem para economizar memória
    public static BufferedImage loadAndResizeImage(InputStream inputStream, int maxWidth, int maxHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputStream);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        if (width <= maxWidth && height <= maxHeight) {
            return originalImage;
        }

        float scale = Math.min((float) maxWidth / width, (float) maxHeight / height);
        int newWidth = Math.round(width * scale);
        int newHeight = Math.round(height * scale);

        BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        originalImage.flush(); // libera a imagem original da memória
        return resized;
    }

    public boolean isSideBySide() {
        return !stretched;
    }

    public boolean isStretched() {
        return stretched;
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
