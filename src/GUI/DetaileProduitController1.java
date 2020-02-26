/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import IServices.IServiceProduit;
import Services.ServiceProduit;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author anasc
 */
public class DetaileProduitController1 implements Initializable {

    @FXML
    private ImageView img_ann;
    @FXML
    private Label lbl_desc;
    @FXML
    private Label lbl_Region;
    @FXML
    private Label lbl_viwes;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label lbl_prix;

    public static int idd;
    private Image img;
    private IServiceProduit annonceService;
    @FXML
    private Button showpdf;
    private String filePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        annonceService = new ServiceProduit();
        Produit a = annonceService.getProduitById(idd);
        lbl_titre.setText(a.getNomProd());
        lbl_desc.setText(a.getDescription());
        lbl_Region.setText(a.getNomRef());
        lbl_viwes.setText(Integer.toString(a.getViews()));
        lbl_prix.setText("DT" + " " + Double.toString(a.getPrix()));
        img = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + a.getImg());
        img_ann.setImage(img);
    }

    @FXML
    private void show_pdf(ActionEvent event) {
        QRcode();
        creatpdf();
    }

    private void QRcode() {
        
        String myCodeText = lbl_titre.getText() +  lbl_prix.getText()  +   lbl_desc.getText();
        filePath = "C:\\Users\\aymen\\Desktop\\Tunfugees\\src\\Image\\qrc" + lbl_titre.getText() + ".png";
      
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);
        try {
            //chn7a4ar el hint map mte3i eli chnestoki fyha 
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            //hintMap.put(EncodeHintType.MARGIN, 1);
            /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            // creation qr
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
    }

    private void creatpdf() {

        try {
            annonceService = new ServiceProduit();
            Produit a = annonceService.getProduitById(idd);
            String nomProd = a.getNomProd();
            String out = "D:\\Annonce\\Annonce" + nomProd + ".pdf";
            Document document = new Document();
            StringBuilder boxText = new StringBuilder();
            PdfWriter.getInstance(document, new FileOutputStream(out));
            document.open();
            com.itextpdf.text.Image Eco = com.itextpdf.text.Image.getInstance("C:\\ecosystemjava\\src\\res\\logoeco.png");
            Eco.scaleToFit(200, 70);
            document.add(Eco);
            document.add(new Paragraph("\n\n\n\n\n"));
            document.add(new Paragraph("Annonce :"));
            PdfPTable table = new PdfPTable(4); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            PdfPCell cell1 = new PdfPCell(new Paragraph("Image"));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Titre"));
            cell2.setBorderColor(BaseColor.BLUE);
            cell2.setPaddingLeft(5);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Description"));
            cell3.setBorderColor(BaseColor.BLUE);
            cell3.setPaddingLeft(5);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Prix"));
            cell3.setBorderColor(BaseColor.BLUE);
            cell3.setPaddingLeft(5);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            document.add(table);
            PdfPTable table1 = new PdfPTable(4); // 3 columns.
            table1.setWidthPercentage(100); //Width 100%
            table1.setSpacingBefore(10f); //Space before table
            table1.setSpacingAfter(10f); //Space after table

            com.itextpdf.text.Image x = com.itextpdf.text.Image.getInstance("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\" + a.getImg());
               x.scaleToFit(100, 100);
            PdfPCell image = new PdfPCell(x);
            image.setBorderColor(BaseColor.BLUE);
            image.setPaddingLeft(10);
            image.setHorizontalAlignment(Element.ALIGN_CENTER);
            image.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell txt_titre = new PdfPCell(new Paragraph(lbl_titre.getText()));
            txt_titre.setBorderColor(BaseColor.BLUE);
            txt_titre.setPaddingLeft(5);
            txt_titre.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_titre.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell txt_desc = new PdfPCell(new Paragraph(lbl_desc.getText()));
            txt_desc.setBorderColor(BaseColor.BLUE);
            txt_desc.setPaddingLeft(5);
            txt_desc.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_desc.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell txt_prix = new PdfPCell(new Paragraph(lbl_prix.getText()));
            txt_prix.setBorderColor(BaseColor.BLUE);
            txt_prix.setPaddingLeft(5);
            txt_prix.setHorizontalAlignment(Element.ALIGN_CENTER);
            txt_prix.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(image);
            table1.addCell(txt_titre);
            table1.addCell(txt_desc);
            table1.addCell(txt_prix);
            document.add(table1);
            com.itextpdf.text.Image qrc = com.itextpdf.text.Image.getInstance(filePath);
            qrc.scaleToFit(400, 400);
            document.add(qrc);
            document.close();
           
            System.out.println("Document '" + out + "' generated");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetaileProduitController1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(DetaileProduitController1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetaileProduitController1.class.getName()).log(Level.SEVERE, null, ex);
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PDF générer");
        alert.setHeaderText(null);
        alert.setContentText("PDF générer");
        alert.showAndWait();
    }

}
