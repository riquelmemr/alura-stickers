import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
// import java.io.FileInputStream;/
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickGenerator {

    public void creator(InputStream inputStream, String fileName) throws Exception {

        // Leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("./img/TopMovies_1.jpg"));
        // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imageOriginal = ImageIO.read(inputStream);
        BufferedImage myImage = ImageIO.read(new File("./img/eu.png"));
        
        // Cria uma nova imagem em memória com transparência e tamanho novo
        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        int myWidth = myImage.getWidth();
        int myHeight = myImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        Image resizedImage = myImage.getScaledInstance(width - myWidth, newHeight - height, Image.SCALE_DEFAULT);
        int resizedImageW = width - myWidth;
        int resizedImageH = newHeight - height;

        // Copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        if (width < 500) {
            graphics.drawImage(resizedImage, (width + resizedImageW + 30) / 2, (newHeight - resizedImageH - 56), null);
        } else {
            graphics.drawImage(myImage, width - myWidth + 80, newHeight - myHeight + 30, null);
        }

        // Configurando fonte e escrever uma frase na nova imagem
        int fontSize = 100;
        var msg = "TOP DEMAIS!";
        var font = new Font("Impact", Font.BOLD, fontSize);
        graphics.setFont(font);

        for (int i = fontSize; i > 0; i--) {
            if (graphics.getFontMetrics().stringWidth(msg) > width) {
                graphics.setFont(font);
                font = new Font("Impact", Font.BOLD, --fontSize);
            } else
                break;
        }

        if (width < 500) {
            graphics.drawString(msg, (width / 2) - (graphics.getFontMetrics().stringWidth(msg)) / 2, newHeight - 8);
        } else {
            graphics.drawString(msg, (width / 2) - (graphics.getFontMetrics().stringWidth(msg)) / 2, newHeight - 80);
        }
        
        graphics.setColor(Color.WHITE);

        // Escrever a nova imagem em um arquivo
        File createPaste = new File("../alura-stickers/img/extract");
        
        if (!createPaste.exists()) {
            createPaste.mkdir();
        }
        
        fileName = fileName.replaceAll(":", "");
        ImageIO.write(newImage, "png", new File("img/extract/" + fileName));
    }
}
