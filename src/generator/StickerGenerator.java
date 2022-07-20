package generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import configuration.FontImpact;

public class StickerGenerator extends FontImpact {

    public void cria(InputStream inputStream, String nomeArquivo, Float rating) throws Exception {

        
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        var font = new Font("Impact", Font.PLAIN, 84);
        graphics.setFont(font);

        if (rating > 9) {
            graphics.setColor(Color.YELLOW);
            graphics.drawString("NOTA: " + rating, 100, novaAltura - 120);

        } 
        else {
            graphics.setColor(Color.RED);
            graphics.drawString("NOTA: " + rating, 100, novaAltura - 120);
        }
            ImageIO.write(novaImagem, "png", new File("output/" + nomeArquivo));
        }

    }